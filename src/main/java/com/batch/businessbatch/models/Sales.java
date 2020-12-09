package com.batch.businessbatch.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private String date;
    private String location;
    private double food;
    private double drinks;
    private double tax;
    private double giftCard;
    private double total;
    private Long statementId;

    public Sales() {
    }

    public Sales(Long id, Long accountId, String date, String location, double food, double drinks, double tax, double giftCard, double total, Long statementId) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.location = location;
        this.food = food;
        this.drinks = drinks;
        this.tax = tax;
        this.giftCard = giftCard;
        this.total = total;
        this.statementId = statementId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getDrinks() {
        return drinks;
    }

    public void setDrinks(double drinks) {
        this.drinks = drinks;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(double giftCard) {
        this.giftCard = giftCard;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", food=" + food +
                ", drinks=" + drinks +
                ", tax=" + tax +
                ", giftCard=" + giftCard +
                ", total=" + total +
                ", statementId=" + statementId +
                '}';
    }
}
