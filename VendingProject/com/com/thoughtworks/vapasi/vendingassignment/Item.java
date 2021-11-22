package com.thoughtworks.vapasi.vendingassignment;

public class Item {
    private int itemId;
    private String itemName;
    private int price;

    public Item(int itemId, String itemName, int price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPrice() {
        return price;
    }
}
