package com.advantage.ghoul;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class GameControl {
    private static final GameControl startGame = new GameControl();
    private Scanner scanner = new Scanner(System.in);
    private final String RED = "\u001B[31m";
    private final String YELLOW = "\033[0;33m";
    private final String GREEN = "\033[0;32m";
    private final String RESET = "\033[0m";
    private final NewGame newGame = new NewGame();

    private GameControl() {
        display();
        startGame();
    }

    //method
    private void startGame() {
        System.out.print("\n Enter 'new game' to play or 'quit game' to exit: \n");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("new game")) {
            newGame.introStory();
        } else if (input.equalsIgnoreCase("quit game")) {
            System.out.println("\n Thanks for playing!");
            System.exit(0);
        } else {
            System.out.println(RED + "Invalid input." + YELLOW);
            startGame();
        }
    }

    private void gameSummary() {
        try {
            Files.lines(Path.of("others", "gameSummary.txt")).
                    forEach(line -> {
                        System.out.println(YELLOW + line);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void display() {
        try {
            Files.lines(Path.of("others", "TheGhoul.txt")).
                    forEach(line -> {
                        System.out.println(GREEN + line);
                    });
            gameSummary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameControl getStartGame() {
        return startGame;
    }
}
