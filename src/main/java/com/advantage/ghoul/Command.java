package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

class Command {
    ParseCommand check=new ParseCommand();

    String commandFilter(String[] command) {
        if (command.length == 3) {
            return command[1] + " " + command[2];
        } else if (command.length == 2) {
            return command[1];
        } else {
            return null;
        }
    }

    void executeCommand(String verb, String noun, Character playerAbility, ItemMenu gameItems, List<Location> rooms,Character player) {
        verb=check.verifyAction(verb);
        if (verb.equals("look")) {
            System.out.println(gameItems.looking(noun, gameItems));
        } else if (verb.equals("get")) {
            playerAbility.addItem(Location.currentRoom, noun, gameItems, rooms);
        } else if(verb.equals("use") && playerAbility.getInventory().contains(noun)){
            playerAbility.useHealingPotion(player);
        } else if (verb.equals("drop")) {
            playerAbility.dropItem(noun, playerAbility, gameItems, rooms);
        } else if (verb.equals("check") && noun.equals("bag")) {
            playerAbility.checkInventory();
        } else {
            System.out.println("There is no " + noun + " in this room");
        }
    }

    void gameCommand(String command) {
        if (command.equals("help")) {
            help();
        }else if(command.equals("map")){
            map();
        }
        else {
            System.out.println("invalid input");
        }
    }

    void fightCommand(String verb, String noun, Character playerAbility, Character player,
                      Character monster, Character ghoul, Character king, List<Location> rooms, LinkedList<String> monsterList){
        if(noun.equals("monster")&&Location.currentRoom.equals("basement")) {
            playerAbility.attack(player, monster,monsterList);
        }else if(noun.equals("ghoul")&&Location.currentRoom.equals("dungeon")){
            playerAbility.attack(player, ghoul,monsterList);
        }else if(noun.equals("king")&&Location.currentRoom.equals("dungeon")){
            playerAbility.attack(player, king,monsterList);
        }else{
            System.out.println("Invalid input");
        }
    }

    void help() {
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("help.txt");
        FileReading.printInputStream(is, Color.RED);
    }
    void map(){
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("map.txt");
        FileReading.printInputStream(is, Color.YELLOW);
    }
}