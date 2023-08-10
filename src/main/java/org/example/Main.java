package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(1000000); // начальный баланс банкомата
        List<Card> cards;
        try {
            cards = FileManager.loadCardsFromFile("data.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных из файла.");
            return;
        }

        ATMService atmService = new ATMService(atm, cards);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Вставить карту");
            System.out.println("2. Проверить баланс");
            System.out.println("3. Снять деньги");
            System.out.println("4. Пополнить счет");
            System.out.println("5. Выход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите номер карты:");
                    String cardNumber = scanner.next();
                    System.out.println("Введите ПИН-код:");
                    String pin = scanner.next();
                    atmService.insertCard(cardNumber, pin);
                    break;
                case 2:
                    atmService.checkBalance();
                    break;
                case 3:
                    System.out.println("Введите сумму для снятия:");
                    double amountToWithdraw = scanner.nextDouble();
                    atmService.withdraw(amountToWithdraw);
                    break;
                case 4:
                    System.out.println("Введите сумму для пополнения:");
                    double amountToDeposit = scanner.nextDouble();
                    atmService.deposit(amountToDeposit);
                    break;
                case 5:
                    try {
                        FileManager.saveCardsToFile(cards, "data.txt");
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении данных в файл.");
                    }
                    System.exit(0);
                    break;
            }
        }
    }
}