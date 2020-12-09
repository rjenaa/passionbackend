package com.batch.businessbatch.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double monthlyBalance;

    private int numberOfTransactions;

    private double avgSales;

    private double avgExpenses;

    private Long accountId;



    @OneToMany
    private List<Expense> expenses;

    @OneToMany
    private List<Sales> sales;

    public Statement() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Sales> getSales() {
        return sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    public double getAvgSales() {
        return avgSales;
    }

    public void setAvgSales(double avgSales) {
        this.avgSales = avgSales;
    }

    public double getAvgExpenses() {
        return avgExpenses;
    }

    public void setAvgExpenses(double avgExpenses) {
        this.avgExpenses = avgExpenses;
    }

    public double getMonthlybalance() {
        return monthlyBalance;
    }

    public void setMonthlybalance(double monthlybalance) {
        this.monthlyBalance = monthlybalance;
    }

    public int getNumberOfFiles() {
        return numberOfTransactions;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfTransactions = numberOfFiles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "id=" + id +
                ", monthlybalance=" + monthlyBalance +
                ", numberOfFiles=" + numberOfTransactions +
                '}';
    }
}
