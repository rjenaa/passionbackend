package com.batch.businessbatch.batch;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.batch.businessbatch.aws.FileService;
import com.batch.businessbatch.models.BusinessAccount;
import com.batch.businessbatch.models.Expense;
import com.batch.businessbatch.models.Sales;
import com.batch.businessbatch.models.Statement;
import com.batch.businessbatch.services.BusinessService;
import com.batch.businessbatch.services.ExpenseService;
import com.batch.businessbatch.services.SalesService;
import com.batch.businessbatch.services.StatementService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SceduledTask {

    @Value("${sqs.url}")
    private String sqs;

    @Value("${aws.url}")
    private String bucketURL;

    @Autowired
    private AmazonSQSAsync amazonSQSAsync;

    @Autowired
    FileService fileService;

    @Autowired
    SalesService salesService;

    @Autowired
    StatementService statementService;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    BusinessService businessService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    public void deleteMessages(List<Message> messages){
        logger.info("Deleting messages");
        for (Message message: messages){
            amazonSQSAsync.deleteMessage(sqs,message.getReceiptHandle());
        }
        logger.info("successfully deleted messages");
    }

    public List<String> pollSqs(){
        List<String> files = new ArrayList<>();
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqs).withAttributeNames("All");
        receiveMessageRequest.setMaxNumberOfMessages(10);
        receiveMessageRequest.setWaitTimeSeconds(20);
        List<Message> messages = amazonSQSAsync.receiveMessage(receiveMessageRequest).getMessages();
        for(Message message: messages){
            String input = message.getBody();
            System.out.println(message);
            String key = StringUtils.substringBetween(input, "key\":\"", "\",\"size");
            logger.info(key);
            files.add(key);
        }
        deleteMessages(messages);
        return files;
    }

    @Scheduled(fixedRate = 60000)
    public void createStatement(){
        logger.info("started checking");
        List<String> batch = pollSqs();
        List<Expense> expenseList = new ArrayList<>();
        List<Sales> salesList = new ArrayList<>();
        Statement statement = new Statement();

        Long id = null;

        if(batch.isEmpty()){
            logger.info("CurrentBatch is Empty");
        }
        else{
            for(String current: batch){
                if(current.toLowerCase().contains("sales")){
                    logger.info(current);
                    File file = fileService.getObject(current);
                    logger.info("Adding " + current + " into statement");
                    salesList.addAll(salesService.ExcelIngester(file, Long.parseLong(Character.toString(current.charAt(0)))));
                    for(Sales sales: salesList){
                        sales.setStatementId(statement.getId());
                        id = sales.getAccountId();
                    }
                    statement.setAccountId(id);
                }
                if(current.toLowerCase().contains("expense")){
                    File file = fileService.getObject(current);
                    logger.info("Adding " + current + " into statement");
                    expenseList.addAll(expenseService.ExcelIngester(file, Long.parseLong(Character.toString(current.charAt(0)))));
                    for(Expense expense: expenseList){
                        expense.setStatementId(statement.getId());
                        id = expense.getAccountId();
                    }
                    statement.setAccountId(id);
                }

            }
            statement.setMonthlybalance(statementService.calcBalance(expenseList, salesList));
            statement.setNumberOfFiles(expenseList.size()+salesList.size());
            statement.setAvgExpenses(statementService.calcAvgExpense(expenseList));
            statement.setAvgSales(statementService.calcAvgSales(salesList));
            statement.setExpenses(expenseList);
            statement.setSales(salesList);
            statementService.addStatement(statement);
        }
        batch.clear();
    }
}
