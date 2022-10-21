package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Game {
    //fields
    Intro story = new Intro();
    private final Scanner scanner = new Scanner(System.in);

    //method
    public void newGame() {
        gameSummary();

        while (true) {
            System.out.print("\n Enter 'new game' to play or 'quit game' to exit: \n");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("new game")) {
                story.introStory();
                break;
            } else if (input.equalsIgnoreCase("quit game")) {
                System.out.println("\n Thanks for playing!");
                System.exit(0);
            } else {
                System.out.println("wrong input.");
            }
        }
    }

    public void gameSummary() {
        try {
            Files.lines(Path.of("doc", "gameSummary.txt")).
                    forEach(line -> {
                        System.out.println("\033[33m" + line + "\033[0m");
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


