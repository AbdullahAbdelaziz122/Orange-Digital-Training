package org.example.observable;

import org.example.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class Account implements Observable{

    private double balance;
    
    
    private List<Observer> observers = new ArrayList<>();

    public Account(double initialBalance){
        this.balance = initialBalance;
    }

    // Basic Observable methods;
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        System.out.println("Category "+ observer.getName() + " Removed successfully !");
        System.out.println("--------------------------------------");
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        System.out.println("Updating Budgets for Categories for the New Balance: "+ this.balance+ " $");
        System.out.println("");
        for(Observer observer : observers ){
            observer.update(this.balance);
        }
    }


    // Account Operations
    public void deposit(double amount) {
        this.balance += amount;
        notifyObserver();
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            notifyObserver();
        } else {
            System.out.println("Insufficient funds.");
        }
    }



}
