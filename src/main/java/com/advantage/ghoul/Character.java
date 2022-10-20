package com.advantage.ghoul;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Character {
    private String name;
    private List<Item> inventory=new LinkedList<>();
    private final Scanner inputItem=new Scanner(System.in);

    public Character(String name) {
        this.name=name;
        String command=inputItem.nextLine();
        check_Inventory(command);
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
