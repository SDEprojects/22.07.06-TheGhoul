package com.advantage.ghoul;

import com.apps.util.Console;

import java.util.Scanner;

public class Game {
    //fields
    Intro story = new Intro();
    private final Scanner scanner = new Scanner(System.in);

    //method
    public void newGame() {
// TODO: Clear console and begin story line when 'new game' is entered
        while (true) {
            System.out.print("\n Enter 'new game' to play or 'quit game' to exit: \n");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("new game")) {
                //System.out.println("let's play!");
                Console.clear();
                story.introStory();
            } else if (input.matches("quit game")) {
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


