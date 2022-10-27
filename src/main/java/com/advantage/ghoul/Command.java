package com.advantage.ghoul;

import java.util.List;

class Command {
    void executeCommand(String verb, String noun, Player player, ItemMenu gameItems, Location movement, List<Location> rooms){
        if(verb.equals("look")){
            System.out.println(gameItems.looking(noun,gameItems));
        } else if(verb.equals("get")){
            player.addItem(Location.currentRoom,noun,gameItems,rooms);
        }else if(verb.equals("drop")){
            player.dropItem(noun,player,gameItems);
        }else if(verb.equals("check")){
            player.checkInventory();
        }
        else{
            System.out.println("There is no "+noun+" in this room");
        }
    }
}
