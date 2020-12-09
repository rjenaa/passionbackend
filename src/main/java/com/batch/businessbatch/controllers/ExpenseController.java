package com.batch.businessbatch.controllers;

import com.batch.businessbatch.aws.FileService;
import com.batch.businessbatch.models.Expense;
import com.batch.businessbatch.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ExpenseController {

    Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/business/expenses",method = RequestMethod.GET)
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @RequestMapping(value = "/business/{Id}/expenses", method = RequestMethod.POST)
    public void createExpenses(@PathVariable Long Id, @RequestParam(value= "file") final MultipartFile multipartFile){
        File file = fileService.uploadFile(multipartFile, Id);
        logger.info("Uploading " + file.getName());
    }
}
