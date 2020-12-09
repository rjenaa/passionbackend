package com.batch.businessbatch.batch;

import com.amazonaws.services.s3.AmazonS3;
import com.batch.businessbatch.models.Expense;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.JsonLineMapper;
import org.springframework.batch.item.file.separator.JsonRecordSeparatorPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.util.Map;


//@Configuration
//@EnableBatchProcessing
public class ImportJobConfiguration {
//
//    @Autowired
//    private AmazonS3 amazonS3;
//    @Value("${aws.s3.bucket}")
//    private String bucketName;
//
//
//    @Value("${aws.url}")
//    private String bucketURL;
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job importJob() throws Exception{
//        return this.jobBuilderFactory.get("importJob")
//                .start(importTransactions())
//                .next(applyTransactions())
//                .build();
//    }
//
//    @Bean
//    public Step importTransactions() throws Exception{
//        return this.stepBuilderFactory.get("importTransactions")
//                .<Expense, Expense>chunk(100)
//                .reader(excelTransactionReader(null))
//                .writer(transactionJdbcBatchItemWriter(null))
//                .build();
//    }
//
//    @Bean
//    public FlatFileItemReader<Map<String, Object>> AwsItemReader(DataSource dataSource) {
//        FlatFileItemReader<Map<String, Object>> reader = new FlatFileItemReader<>();
//        reader.setLineMapper(new JsonLineMapper());
//        reader.setRecordSeparatorPolicy(new JsonRecordSeparatorPolicy());
//        reader.setResource(resourceLoader.getResource("s3://" + bucketURL + "/" + dataSource));
//        return reader;
//    }
//
//    @Bean
//    @StepScope
//    ItemReader<Expense> excelTransactionReader(DataSource dataSource) {
//        PoiItemReader<Expense> poiItemReader = new PoiItemReader<>();
//        poiItemReader.setLinesToSkip(2);
//        poiItemReader.setResource(new ClassPathResource(null));
//        poiItemReader.setRowMapper(excelRowMapper());
//        return poiItemReader;
//    }
//
//    private RowMapper<Expense> excelRowMapper(){
//        BeanWrapperRowMapper<Expense> rowMapper = new BeanWrapperRowMapper<>();
//        rowMapper.setTargetType(Expense.class);
//        return rowMapper;
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Expense> transactionJdbcBatchItemWriter(DataSource dataSource){
//        return new JdbcBatchItemWriterBuilder<Expense>()
//                .dataSource(dataSource)
//                .sql("INSERT INTO TRANSACTION (TRANSACTION_ID, " +
//                        "BUSINESS_ACCOUNT_ACCOUNT_ID, " +
//                        "DESCRIPTION, " +
//                        "AMOUNT, " +
//                        "DATE) VALUES (:transactionId, " +
//                        ":accountId, " +
//                        ":description, " +
//                        ":date)")
//                .beanMapped()
//                .build();
//    }
//
//    @Bean
//    public Step applyTransactions() throws Exception{
//            return this.stepBuilderFactory.get("applyTransactions")
//                    .<Expense, Expense>chunk(100)
//                    .reader(applyTransactionReader(null))
//                    .writer(applyTransactionWriter(null))
//                    .build();
//    }
//
//    @Bean
//    public JdbcCursorItemReader<Expense> applyTransactionReader(DataSource dataSource) {
//        return new JdbcCursorItemReaderBuilder<Expense>()
//                .name("applyTransactionReader")
//                .dataSource(dataSource)
//                .sql("select transaction_id, " +
//                        "business_account_account_id, " +
//                        "description, " +
//                        "amount, " +
//                        "date " +
//                        "from TRANSACTION " +
//                        "order by date")
//                .rowMapper((resultSet, i) ->
//                        new Expense(
//                                resultSet.getLong("transaction_id"),
//                                resultSet.getLong("business_account_account_id"),
//                                resultSet.getString("description"),
//                                resultSet.getBigDecimal("amount"),
//                                resultSet.getTimestamp("date")))
//                .build();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Expense> applyTransactionWriter(DataSource dataSource){
//        return new JdbcBatchItemWriterBuilder<Expense>()
//                .dataSource(dataSource)
//                .sql("UPDATE ACCOUNT SET " +
//                        "BALANCE = BALANCE + :transactionAmount " +
//                        "WHERE BUSINESS_ACCOUNT_ID = : accountId")
//                .beanMapped()
//                .assertUpdates(false)
//                .build();
//    }



//    @Bean
//    @StepScope
//    public FlatFileItemReader<BusinessUpdate> transactionUpdateFlatFileItemReader(
//            @Value("#{jobParameters['customerUpdateFile']}") Resource inputFile) throws Exception{
//        return FlatFileItemReader<BusinessUpdate>()
//                .name("transactionUpdateItemReader")
//                .resource(inputFile)
//                .linkTokenizer(transactionUpdateLineTokenizer())
//                .fieldSetMapper(transactionUpdateFieldSetMapper())
//                .build();
//    }

//    @Bean
//    public LineTokenizer transactionUpdateLineTokenizer() throws Exception{
//        DelimitedLineTokenizer recordType1 = new DelimitedLineTokenizer();
//        recordType1.setNames("recordId", "location");
//        recordType1.afterPropertiesSet();
//        Map<String, LineTokenizer> tokenizers = new HashMap<>(1);
//        tokenizers.put("1*", recordType1);
//        PatternMatchingCompositeLineTokenizer lineTokenizer =
//                new PatternMatchingCompositeLineTokenizer();
//        lineTokenizer.setTokenizers(tokenizers);
//        return lineTokenizer;
//    }

//    @Bean
//    public FieldSetMapper<BusinessUpdate> businessUpdateFieldSetMapper(){
//        return fieldSet -> {
//            switch (fieldSet.readInt("recordId")){
//                case 1: return  new BusinessUpdate.BusinessLocationUpdate(
//                        fieldSet.readString("location"),
//                        fieldSet.readBigDecimal("balance"));
//            }
//
//            }
//        }
//    }






}
