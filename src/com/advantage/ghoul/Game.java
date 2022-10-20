package com.advantage.ghoul;

import java.util.Scanner;

public class Game {
    //fields
    private final Scanner scanner = new Scanner(System.in);
    private boolean userInput = false;

    //method
    public void newGame() {

        while (!userInput) {
            System.out.print("\n Enter NEW GAME to play : \n");
            String input = scanner.nextLine().toLowerCase();
            if (input.matches("new game")) {
                System.out.println("let's play");
                // --Intro here--
            } else {
                System.out.println("wrong input");
                userInput = true;
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


