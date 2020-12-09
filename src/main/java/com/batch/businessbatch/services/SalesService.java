package com.batch.businessbatch.services;

import com.batch.businessbatch.models.Expense;
import com.batch.businessbatch.models.Sales;
import com.batch.businessbatch.repository.ExpenseRepository;
import com.batch.businessbatch.repository.SalesRepository;
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
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ExcelService excelService;

    public List<Sales> ExcelIngester(File file, Long accountId){
        List<Sales> salesList = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(file);
            for(int i = 0; i < workbook.getNumberOfSheets(); i++){
                Sheet tempSheet = workbook.getSheetAt(i);
                for (Row row: tempSheet){
                    if (row.getRowNum() == 0 || row.getRowNum() == 1) continue;

                    Sales sales = excelService.rowToSales(row, accountId);
                    salesList.add(sales);
                    salesRepository.save(sales);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    public List<Sales> getAllSales(){
        return salesRepository.findAll();
    }

    public Sales updateSales(Sales sales){
        return salesRepository.save(sales);
    }



}
