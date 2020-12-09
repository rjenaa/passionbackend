package com.batch.businessbatch.controllers;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.batch.businessbatch.aws.FileService;
import com.batch.businessbatch.models.Sales;
import com.batch.businessbatch.services.SalesService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalesController {

    Logger logger = LoggerFactory.getLogger(SalesController.class);

    @Autowired
    private SalesService salesService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/business/sales", method = RequestMethod.GET)
    public List<Sales> getAllSales(){
        return salesService.getAllSales();
    }

    @RequestMapping(value = "/business/{Id}/sales", method = RequestMethod.POST)
    public void createSales(@PathVariable Long Id, @RequestParam(value= "file") final MultipartFile multipartFile){
        File file = fileService.uploadFile(multipartFile, Id);
        logger.info("Uploading " + file.getName());
    }
}
