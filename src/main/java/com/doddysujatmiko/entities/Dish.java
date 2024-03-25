package com.doddysujatmiko.entities;

public class Dish {
    private String name;
    private int price = 1000;

    public Dish() {

    }

    public Dish(String name) {
        this.name = name;
    }

    public Dish(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
