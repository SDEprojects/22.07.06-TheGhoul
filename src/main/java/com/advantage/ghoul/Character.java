package com.advantage.ghoul;

import java.util.*;

class Character {

    private final int MAX_HP = 20;
    private int hp;
    private final List<ItemMenu> inventory = new LinkedList<>();
    private final int inventorySize = 10;
    private final Scanner inputItem = new Scanner(System.in);
    private final ItemMenu item = new ItemMenu();
    Scanner scanner = new Scanner(System.in);


    //Test main
    public static void main(String[] args) {
        Character test = new Character();

        //test.addItem();
        test.check_Inventory();
    }

    // anon comment
    // -refactor to implement item menu
    private void check_Inventory() {
        System.out.println("Enter check bag to see inventory");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("check bag")) {
            System.out.println("checking bag...");
            System.out.println(inventory.toArray());
        }
    }
    //add to inventory
    private void addItem() {
        System.out.println("Type get item to add it in your bag");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("get item")) {
            System.out.println("adding" + item + "to your bag");
            inventory.add(item);
        }
    }

    private List<ItemMenu> getInventory() {
        return inventory;
    }
}
