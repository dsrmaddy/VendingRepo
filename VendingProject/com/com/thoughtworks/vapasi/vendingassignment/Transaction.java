package com.thoughtworks.vapasi.vendingassignment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String transactionId;
    private int itemId;
    private int entryAmount;
    private int amountReturned;
    private int totalAmountInMachine;

    public Transaction(int itemId, int entryAmount, int amountReturned, int totalAmountInMachine) {
        this.itemId = itemId;
        this.entryAmount = entryAmount;
        this.amountReturned = amountReturned;
        this.totalAmountInMachine = totalAmountInMachine;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
        Date date = new Date();
        this.transactionId = dateFormat.format(date);
        System.out.println("Transaction id is :"+transactionId);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getEntryAmount() {
        return entryAmount;
    }

    public int getAmountReturned() {
        return amountReturned;
    }

    public int getTotalAmountInMachine() {
        return totalAmountInMachine;
    }
}


