package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NewGame {
    static boolean skip = false;
    private Player player = new Player();
    private Scanner inputValue = new Scanner(System.in);
    private String delimiter = "[ \t,.:;?!\"']+";
    private List<String> verbs = new ArrayList<>(Arrays.asList("check", "look", "get", "use", "open"));
    private List<String> verbForMoving = new ArrayList<>(Arrays.asList("go", "run"));
    private List<String> direction = new ArrayList<>(Arrays.asList("south", "north", "east", "west", "back"));
    private ItemMenu gameItems = new ItemMenu();
    private boolean isRunning = false;
    private Location movement = new Location();
    List<Location> rooms = movement.locationRead();
    private Command InputCommand = new Command();
    private String objectName;

    void gameLoop(boolean isRunning) {
        System.out.println("\n> Type 'help' for game instructions");
        while (!isRunning) {
            System.out.println("What is your next command:");
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
            } else if (commandInput[0].equals("quit") && commandInput.length == 1) {
                isRunning = true;
            } else if (verbs.contains(commandInput[0]) && gameItems.itemList().contains(objectName)) {
                InputCommand.executeCommand(commandInput[0], objectName, player, gameItems, movement, rooms);
            } else if (commandInput.length == 2 && verbForMoving.contains(commandInput[0]) && direction.contains((commandInput[1]))) {
                movement.moving(commandInput[1], rooms);
            } else {
                System.out.println(Color.YELLOW + "Invalid input. Please enter the 'verb' + 'name'. Type help for checking the command" + Color.RESET);
            }
        }
    }

    private void saving() {
    }

    private void help() {
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("help.txt");
        FileReading.printInputStream(is, false, Color.RED);
    }

    void introStory() {
        Console.clear();
        System.out.println(Color.YELLOW + "Type Anything to skip the story\n" + Color.RESET);
        StoryIntroWithDelay story = new StoryIntroWithDelay();
        Thread newThread = new Thread(story);
        StoryWithoutDelay abc = new StoryWithoutDelay();
        Thread skip = new Thread(abc);
        newThread.start();
        skip.start();
        try {
            newThread.join();
            Thread.sleep(1000);
            System.out.println(movement.getLocationByName("outside").getDescription());
            gameLoop(isRunning);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
