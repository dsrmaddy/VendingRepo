package com.thoughtworks.vapasi.vendingassignment;

import java.util.*;

public class VendingMachine {
    private HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>(); //map of itemId and corresponding items
    private int totalAmount = 0;
    private int lastCustomerBalanceReturned;
    private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
    //used to display item menu
    private void displayMenu() {
        Collection<Item> itemList;
        itemList = (Collection<Item>) itemMap.values();

        Formatter fmt = new Formatter();
        fmt.format("%15s %15s %15s\n", "Item ID", "Item Name", "Price");
        for (Item item : itemList)
        {
            fmt.format("%14s %14s %17s\n", item.getItemId(), item.getItemName(), item.getPrice());
        }
        System.out.println(fmt);
    }
     //fill the machine with the items
    private void populateItemMap(int id, Item item) {
        itemMap.put(id, item);
    }

    //returns the correct Item corresponding to its Id
    private Item getItem(int itemId) {
        return itemMap.get(itemId);
    }

    public static void main(String[] args) {
        Item item1 = new Item(1, "notebook", 20);
        Item item2 = new Item(2, "pen", 10);
        Item item3 = new Item(3, "biscuit", 25);
        VendingMachine vm = new VendingMachine();
        vm.populateItemMap(1, item1);
        vm.populateItemMap(2, item2);
        vm.populateItemMap(3, item3);
        //vm.displayMenu();
        vm.getUserInput();
    }

    //Take input from user
    private void getUserInput() {
        System.out.println("Enter 1 for vending or 2 for past transactions: ");
        Scanner s = new Scanner(System.in);
        int choice = 0;
        try {
            choice = s.nextInt();
            //Do vending if choice is 1 and transaction view if choice is 2
            if(choice == 1){
                displayMenu();
                System.out.println("Enter Item ID: ");
                int userId = s.nextInt();
                System.out.println("Enter amount: ");
                int userAmount = s.nextInt();
                takeOrder(userId, userAmount);
            } else if(choice == 2){
                getAllTransactions();
            } else {
                System.out.println("Please enter valid choice");
            }
            //displayMenu();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
        }finally {
            getUserInput();
        }
        
    }

    //contains logic for vending items and calculate balance
    private void takeOrder(int itemId, int entryAmount) {
        Item item = getItem(itemId);
        int balance = 0;
        if(item == null) {
            System.out.println("Enter valid Item ID");
            return;
        }
        int price = item.getPrice();
        if(entryAmount < price) {
            System.out.println("You have insufficient funds to buy this");
            return;
        } else if(entryAmount > price) {
            balance = entryAmount-price;
            System.out.println("Returning amount is :"+balance);
        }
        totalAmount = totalAmount + price; //keeps track of amount in vending machine
        dispense(item.getItemName(),itemId, entryAmount, balance, totalAmount);

    }

    //displays the item being dispensed and waits for next user input
    private void dispense(String name, int itemId, int entryAmount, int balance, int totalAmount){
        System.out.println("Dispensing item "+name);
        //every transaction needs to be tracked
        Transaction transaction = new Transaction(itemId, entryAmount, balance, this.totalAmount);
        transactionList.add(transaction);
    }

    //to keep track of vending machine amount
    private int getVendingMachineAmount() {
        return totalAmount;
    }

    //get all previous transactions
    private void getAllTransactions() {
        Formatter fmt = new Formatter();
        fmt.format("%17s %15s %17s %17s %15s\n", "Transaction ID", "Item ID", "Amount Entered", "Balance Returned", "Amount in Machine");
        for (Transaction transaction : transactionList)
        {
            fmt.format("%17s %14s %15s %15s %15s\n", transaction.getTransactionId(), transaction.getItemId(), transaction.getEntryAmount(), transaction.getAmountReturned(), transaction.getTotalAmountInMachine());
        }
        System.out.println(fmt);
    }

}
