package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.InputStream;
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

    void executeCommand(String verb, String noun, Character player, ItemMenu gameItems, List<Location> rooms) {
        verb=check.verifyAction(verb);
        if (verb.equals("look")) {
            System.out.println(gameItems.looking(noun, gameItems));
        } else if (verb.equals("get")) {
            player.addItem(Location.currentRoom, noun, gameItems, rooms);
        } else if(verb.equals("use")&& player.getInventory().contains(noun)){
            player.useHealingPotion();
        } else if (verb.equals("drop")) {
            player.dropItem(noun, player, gameItems, rooms);
        } else if (verb.equals("check") && noun.equals("bag")) {
            player.checkInventory();
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

    void help() {
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("help.txt");
        FileReading.printInputStream(is, false, Color.RED);
    }
    void map(){
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("map.txt");
        FileReading.printInputStream(is, false, Color.YELLOW);
    }
}