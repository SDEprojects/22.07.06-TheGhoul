package com.advantage.ghoul;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameControl {
    private static final GameControl startGame = new GameControl();
    private Scanner scanner = new Scanner(System.in);
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
            System.out.println(Color.RED + "Invalid input." + Color.YELLOW);
            startGame();
        }
    }

    private void gameSummary() {
        InputStream summary = FileReading.getFileFromResourceAsStreamFortxt("gameSummary.txt");
        FileReading.printInputStream(summary,Color.RED);
    }

    private void display() {
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("TheGhoul.txt");
        FileReading.printInputStream(logo,Color.GREEN);
        gameSummary();
    }

    public static GameControl getStartGame() {
        return startGame;
    }
}
