package com.batch.businessbatch.services;

import com.batch.businessbatch.models.Expense;
import com.batch.businessbatch.models.Sales;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExcelService {

    public Sales rowToSales(Row row, Long accountId){
        Sales sales = new Sales();
        Date date = DateUtil.getJavaDate(row.getCell(0).getNumericCellValue());

        if (row.getCell(0) != null)  sales.setDate(new SimpleDateFormat("MM/dd/yyyy").format(date));
        if (row.getCell(1) != null)  sales.setLocation(row.getCell(1).getStringCellValue());
        if (row.getCell(2) != null)  sales.setFood(row.getCell(2).getNumericCellValue());
        if (row.getCell(3) != null)  sales.setDrinks(row.getCell(3).getNumericCellValue());
        if (row.getCell(4) != null)  sales.setTax(new BigDecimal(row.getCell(4).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        if (row.getCell(5) != null)  sales.setGiftCard(row.getCell(5).getNumericCellValue());
        if (row.getCell(6) != null)  sales.setTotal(new BigDecimal(row.getCell(6).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        sales.setAccountId(accountId);
        return sales;
    }

    //Methods from kumarpankaj18 excel dependency to make simplify
    public Expense rowToExpenses(Row row, Long accountId){
        Expense expense = new Expense();
        Date date = DateUtil.getJavaDate(row.getCell(0).getNumericCellValue());

        if (row.getCell(0) != null)  expense.setDate(new SimpleDateFormat("MM/dd/yyyy").format(date));
        if (row.getCell(1) != null)  expense.setLocation(row.getCell(1).getStringCellValue());
        if (row.getCell(2) != null)  expense.setDescription(row.getCell(2).getStringCellValue());
        if (row.getCell(3) != null)  expense.setAmount(new BigDecimal(row.getCell(3).getNumericCellValue()).setScale(2, RoundingMode.HALF_UP));
        expense.setAccountId(accountId);

        return expense;
    }




}
