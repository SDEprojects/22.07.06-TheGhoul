package com.advantage.ghoul;

import java.util.*;

class Character {
    private final int MAX_HP = 20;
    private List<ItemMenu> inventory = new LinkedList<>();
    private final Scanner inputItem = new Scanner(System.in);
    private int hp;

    private void check_Inventory(String command) {
        if (command == "check item") {
            getInventory();
        }
    }

    private List<ItemMenu> getInventory() {
        return inventory;
    }
}
