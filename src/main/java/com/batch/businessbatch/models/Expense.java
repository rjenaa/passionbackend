package com.batch.businessbatch.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private Long accountId;
    private String description;
    private BigDecimal amount;
    private String date;
    private String location;
    private Long statementId;

    public Expense() {
    }

    public Expense(Long expenseId, Long accountId, String description, BigDecimal amount, String date, String location, Long statementId) {
        this.expenseId = expenseId;
        this.accountId = accountId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.location = location;
        this.statementId = statementId;
    }

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        if (amount != null){
            return amount;
        }
        else{
            return new BigDecimal(0);
        }
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
