//package linkedLists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class CardGame {
	
	private static LinkedList cardList = new LinkedList();  // make list

	public static void main(String[] args) {

		// File name to read from
        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Shuffle or randomize the deck manually
        ArrayList<Card> allCards = new ArrayList<>();
        while (true) {
            try {
                allCards.add(cardList.getFirst());
            } catch (Exception e) {
                break;
            }
        }
        Collections.shuffle(allCards);

        // Split into player and computer decks
        LinkedList playerDeck = new LinkedList();
        LinkedList cpuDeck = new LinkedList();

        for (int i = 0; i < allCards.size(); i++) {
            if (i % 2 == 0)
                playerDeck.add(allCards.get(i));
            else
                cpuDeck.add(allCards.get(i));
        }
		
	//My code
	//Added the "War" card game, compares the value of cards and whoever has the greater card value wins.
        int playerScore = 0;
        int cpuScore = 0;
        int rounds = Math.min(allCards.size() / 2, 26);
        Scanner nextRound = new Scanner(System.in);

        for (int i = 0; i < rounds; i++) {
            Card playerCard = playerDeck.getFirst();
            Card cpuCard = cpuDeck.getFirst();

            System.out.println("Round " + (i+1));
            System.out.println("Player plays: " + playerCard);
            System.out.println("Computer plays: " + cpuCard);

            if (playerCard.getCardValue() > cpuCard.getCardValue()) {
                System.out.println("Player wins this round!");
                playerScore++;
            } else if (cpuCard.getCardValue() > playerCard.getCardValue()) {
                System.out.println("Computer wins this round!");
                cpuScore++;
            } else {
                System.out.println("It's a tie!");
            }
            System.out.println();
            System.out.println("Next round? (press any key)");
            if (nextRound.hasNext()) {
                nextRound = new Scanner(System.in);
                continue;
            }
        }

        System.out.println("Final Score:");
        System.out.println("Player: " + playerScore);
        System.out.println("Computer: " + cpuScore);

        if (playerScore > cpuScore) System.out.println("Player wins!");
        else if (cpuScore > playerScore) System.out.println("Computer wins!");
        else System.out.println("Tie!");

	}//end main

}//end class
