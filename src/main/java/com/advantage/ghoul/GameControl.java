package com.advantage.ghoul;


import com.apps.util.Console;


import java.io.*;
import java.util.Scanner;

public class GameControl {
    private static final GameControl startGame = new GameControl();
    private Scanner scanner = new Scanner(System.in);
    private final NewGame newGame = new NewGame();
    MusicHandler musichandle = new MusicHandler();

    private GameControl() {
        Console.clear();
        display();
//        musichandle.play();
        startGame();
    }

    //method
    private void startGame() {
        System.out.print("\n Enter 'new game' to play or 'quit game' to exit: \n");
        String input = scanner.nextLine().trim();
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
        FileReading.printInputStream(summary, Color.RED);
    }

    private void display() {
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("TheGhoul.txt");
        FileReading.printInputStream(logo, Color.GREEN);
        gameSummary();
    }


    static void loseGame() {
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("end.txt");
        FileReading.printInputStream(logo, Color.RED);
        System.out.println("You lose the game, Do you want to try again? [yes or no]");
        Scanner scanner = new Scanner(System.in);
        String playerChoose = scanner.nextLine().trim().toLowerCase();
        if (playerChoose.equals("yes")) {
            GameControl startAgain = new GameControl();
        } else {
            System.exit(0);
        }
    }

    public static GameControl getStartGame() {
        return startGame;
    }
}
