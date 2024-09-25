package org.example.observers;

import org.example.observable.Account;

public class Category implements Observer{
    private String name;
    private double balance;
    private double percentage;
    private Account account;
    private double limit = 0;
    
    


    public Category(String categoryName, double percentage){
        this.limit += percentage;
        this.name = categoryName;
        this.percentage = percentage;
        System.out.println("Category "+ name + " added successfully !");
        System.out.println("--------------------------------------");


    }

    @Override
    public void update(double newBalance) {
        this.balance = newBalance * this.percentage;
        System.out.println("* Budget for '"+ name + "' is: "+ this.balance+" $");
        System.out.println("");
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
