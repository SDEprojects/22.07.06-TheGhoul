package com.advantage.ghoul;

import java.util.Scanner;

public class Game {
    //fields
    private final Scanner scanner = new Scanner(System.in);

    //method
    public void newGame() {

        while (true) {
            System.out.print("\n Enter 'new game' to play or 'quit game' to exit: \n");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("new game")) {
                System.out.println("let's play!");
                // --Intro here--
            } else if (input.equalsIgnoreCase("quit game")) {
                System.out.println("\n Thanks for playing!");
                System.exit(0);
            } else {
                System.out.println("wrong input.");
            }
        }
    }


    public void gameSummary() {
        System.out.println("========================================================= \n" +
                "“The Ghoul” is a game of adventure, danger, and deception. \n" +
                "You, as the bravest knight, have been tasked by your king  \n" +
                "to kill a demonic creature and retrieve a sacred stone. \n" +
                "Will you succeed, or does destiny have other plans for you? \n" +
                "=========================================================");
    }
}


