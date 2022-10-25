package com.advantage.ghoul;

import java.util.*;

public class Character {

    private final int MAX_HP = 20;
    private int hp;
    private final int inventorySize = 10;
    private final List<ItemMenu> inventory = new LinkedList<>();
    private final ItemMenu item = new ItemMenu();
    Scanner scanner = new Scanner(System.in);

    private List<ItemMenu> getInventory() {
        return inventory;
    }
}
