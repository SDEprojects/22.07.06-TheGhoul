package com.advantage.ghoul;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Character {
    private final int MAX_HP=20;
    private List<Item> inventory=new LinkedList<>();
    private final Scanner inputItem=new Scanner(System.in);
    int hp;

     Character() {
//        String command=inputItem.nextLine();
//        check_Inventory(command);
    }



    private void check_Inventory(String command) {
        if(command=="check item"){
            getInventory();
        }
    }

    private List<Item> getInventory() {
        return inventory;
    }

}
