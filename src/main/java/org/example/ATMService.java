package org.example;

import java.util.List;

public class ATMService {
    private ATM atm;
    private List<Card> cards;
    private Card currentCard;
    private int incorrectPinAttempts;

    public ATMService(ATM atm, List<Card> cards) {
        this.atm = atm;
        this.cards = cards;
    }

    public boolean insertCard(String cardNumber, String pin) {
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                if (!card.isValidCardNumber()) {
                    System.out.println("Неверный формат номера карты");
                    return false;
                }
                if (card.isBlocked()) {
                    System.out.println("Карта заблокирована");
                    return false;
                }
                if (card.isCorrectPin(pin)) {
                    currentCard = card;
                    incorrectPinAttempts = 0;
                    return true;
                } else {
                    incorrectPinAttempts++;
                    if (incorrectPinAttempts >= 3) {
                        card.block();
                        System.out.println("Карта заблокирована из-за 3 неверных попыток ввода ПИН-кода");
                    } else {
                        System.out.println("Неверный ПИН-код");
                    }
                    return false;
                }
            }
        }
        System.out.println("Карта не найдена");
        return false;
    }

    public void checkBalance() {
        if (currentCard != null) {
            System.out.println("Баланс: " + currentCard.getBalance());
        } else {
            System.out.println("Сначала вставьте карту");
        }
    }

    public void withdraw(double amount) {
        if (currentCard != null) {
            if (atm.canWithdraw(amount) && amount <= currentCard.getBalance()) {
                currentCard.withdraw(amount);
                atm.withdraw(amount);
                System.out.println("Снятие прошло успешно");
            } else {
                System.out.println("Недостаточно средств");
            }
        } else {
            System.out.println("Сначала вставьте карту");
        }
    }

    public void deposit(double amount) {
        if (currentCard != null) {
            if (amount <= 1000000) {
                currentCard.deposit(amount);
                atm.deposit(amount);
                System.out.println("Пополнение прошло успешно");
            } else {
                System.out.println("Сумма пополнения превышает 1 000 000");
            }
        } else {
            System.out.println("Сначала вставьте карту");
        }
    }
}
