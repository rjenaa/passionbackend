package com.batch.businessbatch.services;

import com.batch.businessbatch.models.Expense;
import com.batch.businessbatch.repository.ExpenseRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExcelService excelService;

    public List<Expense> ExcelIngester(File file, Long accountId){
        List<Expense> expenseList = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(file);
            for(int i = 0; i < workbook.getNumberOfSheets(); i++){
                Sheet tempSheet = workbook.getSheetAt(i);
                for (Row row: tempSheet){
                    if (row.getRowNum() == 0 || row.getRowNum() == 1) continue;

                    Expense expense = excelService.rowToExpenses(row, accountId);
                    expenseList.add(expense);
                    expenseRepository.save(expense);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return expenseList;
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public Expense updateExpense(Expense expense){
        return expenseRepository.save(expense);
    }
}
