package com.batch.businessbatch.controllers;

import com.batch.businessbatch.models.Statement;
import com.batch.businessbatch.services.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StatementController {

    @Autowired
    private StatementService statementService;

    @RequestMapping(value = "/statements", method = RequestMethod.GET)
    public List<Statement> getAllStatements(){
        return statementService.getAllStatement();
    }

    @RequestMapping(value = "/statements/{id}", method = RequestMethod.GET)
    public Optional<Statement> getStatementById(@PathVariable Long id){
        return statementService.getStatementById(id);
    }

    @RequestMapping(value = "/statements/filter/{id}", method = RequestMethod.GET)
    public List<Statement> getStatementsByAccountId(@PathVariable Long id){
        return statementService.getStatementByAccountId(id);
    }



}
