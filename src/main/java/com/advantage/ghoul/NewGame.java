package com.advantage.ghoul;

import com.apps.util.Console;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class NewGame {
    static boolean skip = false;
    private Character playerAbility = new Character();
    private Scanner inputValue = new Scanner(System.in);
    private String delimiter = "[ \t,.:;?!\"']+";
    private ParseCommand commandPares = new ParseCommand();
    private List<String> verbs = commandPares.command();
    private List<String> verbForMoving = commandPares.movingCommand;
    private List<String> direction = commandPares.direction;
    private ItemMenu gameItems = new ItemMenu();
    private boolean isRunning = false;
    private Location movement = new Location();
    List<Location> rooms = movement.dataReader();
    private Command InputCommand = new Command();
    private String objectName;
    private LinkedList<String> monsterList=playerAbility.npcNameList();
    Character player = playerAbility.createCharacter(playerAbility.getCharacterByName("player"));
    Character monster = playerAbility.createCharacter(playerAbility.getCharacterByName("monster"));
    Character ghoul = playerAbility.createCharacter(playerAbility.getCharacterByName("ghoul"));
    Character king = playerAbility.createCharacter(playerAbility.getCharacterByName("king"));

    void gameLoop(boolean isRunning) {
        while (!isRunning) {
            System.out.println("\n> Type 'help' for game instructions");
            System.out.println("\nWhat is your next command:");
            String wordInput = inputValue.nextLine().trim();
            String[] commandInput = wordInput.toLowerCase().split(delimiter);
            objectName = InputCommand.commandFilter(commandInput);
            boolean testCommand=commandInput.length > 1 && verbs.contains(commandInput[0]);
            if (commandInput.length == 1 && commandInput[0].equals("quit")) {
                isRunning = true;
            } else if (commandInput.length == 1) {
                InputCommand.gameCommand(commandInput[0]);
            } else if (testCommand && (gameItems.itemList().contains(objectName))) {
                InputCommand.executeCommand(commandInput[0], objectName, playerAbility, gameItems, rooms,player);
            } else if(testCommand && (monsterList.contains(objectName))){
                InputCommand.fightCommand(commandInput[0],objectName,playerAbility,player,monster,ghoul,king,rooms,monsterList);
            } else if (commandInput.length == 2 && verbForMoving.contains(commandInput[0]) && direction.contains((commandInput[1]))) {
                Console.clear();
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
