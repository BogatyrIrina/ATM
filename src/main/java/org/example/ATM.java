package org.example;

public class ATM {
    private double balance;

    public ATM(double balance) {
        this.balance = balance;
    }

    public boolean canWithdraw(double amount) {
        return amount <= balance;
    }

    public void withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
