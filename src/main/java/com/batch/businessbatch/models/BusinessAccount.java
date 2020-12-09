package com.batch.businessbatch.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class BusinessAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String location;
    private  double balance;
    private  String lastStatement;
//    @OneToMany(targetEntity = Expense.class)
//    private final List<Expense> expenseList = new ArrayList<>();
//
//    @OneToMany(targetEntity = Sales.class)
//    private final List<Sales> salesList = new ArrayList<>();


    public BusinessAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLastStatement() {
        return lastStatement;
    }

    public void setLastStatement(String lastStatement) {
        this.lastStatement = lastStatement;
    }

//    public List<Expense> getExpenseList() {
//        return expenseList;
//    }
//
//    public List<Sales> getSalesList() {
//        return salesList;
//    }

    @Override
    public String toString() {
        return "BusinessAccount{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", balance=" + balance +
                ", lastStatement=" + lastStatement +
//                ", transactionList=" + expenseList +
                '}';
    }
}
