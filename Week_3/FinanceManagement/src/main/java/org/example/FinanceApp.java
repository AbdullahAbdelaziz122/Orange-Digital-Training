package org.example;

import org.example.observable.Account;
import org.example.observers.Category;

public class FinanceApp {
    public static void main(String[] args) {
    	
    	Account Abdullah = new Account(10500.0);

		System.out.println("=======================First Operation==========================");
    	Category food = new Category("food", 0.3);
    	Category cloth = new Category("Cloth", 0.3);
    	Category car = new Category("Car", 0.2);
		Category education = new Category("Education", 0.2);
		Category charity = new Category("Charity", 0.1);

		Abdullah.addObserver(food);
		Abdullah.addObserver(cloth);
		Abdullah.addObserver(car);
		Abdullah.addObserver(education);
		Abdullah.addObserver(charity);

    	Abdullah.withdraw(500.0);



		System.out.println("=======================Second Operation==========================");
		Abdullah.removeObserver(car);
		Abdullah.deposit(10000);

    }
}