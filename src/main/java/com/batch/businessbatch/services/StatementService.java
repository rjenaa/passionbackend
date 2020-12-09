package com.batch.businessbatch.services;

import com.batch.businessbatch.models.Expense;
import com.batch.businessbatch.models.Sales;
import com.batch.businessbatch.models.Statement;
import com.batch.businessbatch.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    StatementRepository statementRepository;

    public Optional<Statement> getStatementById(Long Id){
        return statementRepository.findById(Id);
    }

    public Statement addStatement(Statement statement){
        return statementRepository.save(statement);
    }

    public double calcBalance(List<Expense> expenses, List<Sales> sales){
        double totalExpenses = 0;
        double totalSales = 0;

        for(Expense expense: expenses){
            totalExpenses += expense.getAmount().doubleValue();
        }
        for(Sales sale: sales){
            totalSales += sale.getTotal();
        }

        return totalSales-totalExpenses;
    }

    public List<Statement> getAllStatement(){
        return statementRepository.findAll();
    }

    public double calcAvgSales(List<Sales> sales){
        double total = 0;
        for(Sales sales1: sales){
            total+=sales1.getTotal();
        }
        return total/sales.size();
    }

    public double calcAvgExpense(List<Expense> expenses){
        double total = 0;
        for(Expense expense: expenses){
            total+=expense.getAmount().doubleValue();
        }
        return total/expenses.size();
    }

    public List<Statement> getStatementByAccountId(Long id){
        return statementRepository.findStatementByAccountId(id);
    }





}
