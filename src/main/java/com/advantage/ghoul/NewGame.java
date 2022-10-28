package com.advantage.ghoul;

import com.apps.util.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NewGame {
    static boolean skip = false;
    private Character playerAbility = new Character();
    private Scanner inputValue = new Scanner(System.in);
    private String delimiter = "[ \t,.:;?!\"']+";
    private List<String> verbs = new ArrayList<>(Arrays.asList("check", "look", "get", "use", "open", "drop", "attack"));
    private List<String> verbForMoving = new ArrayList<>(Arrays.asList("go", "moving", "turn", "move"));
    private List<String> direction = new ArrayList<>(Arrays.asList("south", "north", "east", "west", "back"));
    private ItemMenu gameItems = new ItemMenu();
    private boolean isRunning = false;
    private Location movement = new Location();
    List<Location> rooms = movement.dataReader();
    private Command InputCommand = new Command();
    private String objectName;
    Character player = new Character("player", "this is a player", 100, 10);
    Character monster = new Character("monster", "this is a player", 100, 10);

    void gameLoop(boolean isRunning) {
        System.out.println("\n> Type 'help' for game instructions");
        while (!isRunning) {
            System.out.println("What is your next command:");
            String wordInput = inputValue.nextLine().trim();
            Console.clear();
            String[] commandInput = wordInput.toLowerCase().split(delimiter);
            objectName = InputCommand.commandFilter(commandInput);
            if (commandInput.length == 1 && commandInput[0].equals("quit")) {
                isRunning = true;
            } else if (commandInput.length == 1) {
                InputCommand.gameCommand(commandInput[0]);
            } else if (commandInput.length > 1 && verbs.contains(commandInput[0]) && (gameItems.itemList().contains(objectName))) {
                InputCommand.executeCommand(commandInput[0], objectName, playerAbility, gameItems, rooms);
            } else if (commandInput.length > 1 && verbs.contains(commandInput[0]) && (gameItems.itemList().contains(objectName))) {
                InputCommand.executeCommand(commandInput[0], objectName, playerAbility, gameItems, rooms);
            }else if (commandInput.length == 2 && verbForMoving.contains(commandInput[0]) && direction.contains((commandInput[1]))) {
                movement.moving(commandInput[1], rooms);
            } else {
                System.out.println(Color.YELLOW + "Invalid input. Please enter the 'verb' + 'name'. Type help for checking the command" + Color.RESET);
            }
        }
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
