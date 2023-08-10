package org.example;

import java.util.Date;

public class Card {
    private String cardNumber;
    private String pin;
    private double balance;
    private boolean isBlocked;
    private Date blockedUntil;

    public Card(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.isBlocked = false;
    }

    public boolean isCorrectPin(String pin) {
        return this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    public void deposit(double amount) {
        if (amount <= 1000000) {
            balance += amount;
        }
    }

    public boolean isBlocked() {
        if (isBlocked && new Date().after(blockedUntil)) {
            isBlocked = false;
        }
        return isBlocked;
    }

    public void block() {
        isBlocked = true;
        blockedUntil = new Date(new Date().getTime() + 24 * 60 * 60 * 1000); // блокировка на 24 часа
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean isValidCardNumber() {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }
}
