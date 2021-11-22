package com.thoughtworks.vapasi.vendingassignment;

import java.util.*;

public class VendingMachine {
    private HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
    private int totalAmount = 0;
    private int lastCustomerBalanceReturned;

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

    private void populateItemMap(int id, Item item) {
        itemMap.put(id, item);
    }

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
        vm.displayMenu();
        vm.getUserInput();
    }

    private void getUserInput() {
        //Take input from user
        System.out.println("Enter Item ID: ");
        Scanner s = new Scanner(System.in);
        int userId = s.nextInt();
        System.out.println("Enter amount: ");
        int userAmount = s.nextInt();
        takeOrder(userId, userAmount);
    }

    private void takeOrder(int itemId, int entryAmount) {
        Item item = getItem(itemId);
        if(item == null) {
            System.out.println("Enter valid Item ID");
            return;
        }
        int price = item.getPrice();
        if(entryAmount < price) {
            System.out.println("You have insufficient funds to buy this");
            return;
        } else if(entryAmount > price) {
            int balance = entryAmount-price;
            System.out.println("Returning amount is :"+balance);
        }
        totalAmount = totalAmount + price;
        dispense(item.getItemName());
    }

    private void dispense(String name){
        System.out.println("Dispensing item "+name);
    }

    //to keep track of vending machine
    private int getVendingMachineAmount() {
        return totalAmount;
    }

}
