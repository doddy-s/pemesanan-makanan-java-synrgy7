package com.doddysujatmiko.entities;

public class Transaction {
    private Dish dish;
    private int amount;

    private int totalPrice;

    public Transaction(Dish dish, int amount) {
        this.dish = dish;
        this.amount = amount;
        totalPrice = dish.getPrice() * amount;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
