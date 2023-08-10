package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Card> loadCardsFromFile(String filename) throws IOException {
        List<Card> cards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                cards.add(new Card(parts[0], parts[1], Double.parseDouble(parts[2])));
            }
        }
        return cards;
    }

    public static void saveCardsToFile(List<Card> cards, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Card card : cards) {
                bw.write(card.getCardNumber() + " " + card.getBalance());
                bw.newLine();
            }
        }
    }
}
