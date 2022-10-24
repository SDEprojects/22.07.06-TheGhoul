package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NewGame {
    private final String YELLOW = "\033[33m";
    private final String RESET = "\033[0m";
    private Character player = new Character();
    private Scanner inputValue = new Scanner(System.in);
    private String delimiter = "[ \t,.:;?!\"']+";
    private List<String> verbs = new ArrayList<>(Arrays.asList("check", "look", "go", "get", "use", "open", "run"));
    private ItemMenu gameItems = new ItemMenu();
    private boolean isRunning;
    private String objectName;

    void gameLoop(boolean isRunning) {
        System.out.println("Game Start\n> Type the help for checking the game command");
        while (!isRunning) {
            String wordInput = inputValue.nextLine().trim();
            String[] commandInput = wordInput.toLowerCase().split(delimiter);
            if (commandInput.length == 3) {
                objectName = commandInput[1] + " " + commandInput[2];
            } else if (commandInput.length == 2) {
                objectName = commandInput[1];
            } else {
                objectName = null;
            }
            if (commandInput[0].equals("help") && commandInput.length == 1) {
                help();
            } else if (commandInput[0].equals("save") && commandInput.length == 1) {
                saving();
            } else if (verbs.contains(commandInput[0]) && gameItems.itemList().contains(objectName)) {
                // create another file for the location then=>verbs.contains(command[0]) && (gameItems.ItemList().contains(objectName)||location)
//            commandInput.executeCommand(commandInput[0], objectName);
                System.out.println("works");
            } else {
                System.out.println(commandInput.length);
                System.out.println("Invalid input. Please enter the 'verb' + 'name'. Type help for checking the command");
            }
        }
    }

    private void saving() {
    }

    private void help() {
        Console.clear();
        try {
            Files.lines(Path.of("others", "help.txt")).
                    forEach(line -> {
                        System.out.println(YELLOW + line);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void introStory() {
        Console.clear();
        try {
            Files.lines(Path.of("others", "introStory.txt")).
                    forEach(line -> {
                        System.out.println(YELLOW + line);
//                        Console.pause(1000);
                    });
            gameLoop(isRunning);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
