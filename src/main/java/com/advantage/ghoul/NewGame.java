package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NewGame {
    private Character player = new Character();
    private Scanner inputValue = new Scanner(System.in);
    private String delimiter = "[ \t,.:;?!\"']+";
    private List<String> verbs = new ArrayList<>(Arrays.asList("check", "look", "get", "use", "open"));
    private List<String> verbForMoving=new ArrayList<>(Arrays.asList("go","run"));
    private List<String> direction=new ArrayList<>(Arrays.asList("south","north","east","west","back"));
    private ItemMenu gameItems = new ItemMenu();
    private boolean isRunning=false;
    private Location movement=new Location();
    List<Location> rooms = movement.locationRead();
    private String objectName;

    void gameLoop(boolean isRunning) {
        System.out.println("Game Start\n> Type the help for checking the game command");
        while (!isRunning) {
            System.out.println("What is your next command");
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
            }else if(commandInput[0].equals("quit")&& commandInput.length == 1){
                isRunning=true;
            }else if(commandInput[0].equals("look")&& gameItems.itemList().contains(objectName)){
                gameItems.looking(objectName);
            }
            else if (verbs.contains(commandInput[0]) && gameItems.itemList().contains(objectName)) {

                // create another file for the location then=>verbs.contains(command[0]) && (gameItems.ItemList().contains(objectName)||location)
//            commandInput.executeCommand(commandInput[0], objectName);
                System.out.println("works");
                System.out.println(Location.currentRoom);
               gameItems.looking(objectName);
                Player newPlayer = new Player();
                newPlayer.addItem(Location.currentRoom, objectName);
                System.out.println(newPlayer.getInventory());

            } else if (verbForMoving.contains(commandInput[0]) && direction.contains((commandInput[1]))){
                movement.moving(commandInput[1],rooms);
            }
            else {
                System.out.println(commandInput.length);
                System.out.println("Invalid input. Please enter the 'verb' + 'name'. Type help for checking the command");
            }
        }
    }

    private void saving() {
    }

    private void help() {
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("help.txt");
        FileReading.printInputStream(is,false,Color.RED);
    }
    void introStory() {
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("IntroStory.txt");
        FileReading.printInputStream(is,true,Color.RED);
        System.out.println(movement.getLocationByName("lobby").getDescription());
        gameLoop(isRunning);
    }
}
