package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private String description;
    private List<ItemMenu> inventory = new ArrayList<>();

    public Player() {
        super();
    }

    public Player(String name, String description) {
        this.name = name;
        this.description = description;
    }

    void addItem(String roomName, String itemName, ItemMenu items, List<Location> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCurrent().equals(roomName)) {
                itemName = rooms.get(i).getItem();
            }
        }
        ItemMenu itemInRoom = items.getItemByName(itemName);

        if (itemName.equals("no item")) {
            System.out.println("There is no " + itemName + " in this area");
        } else {
            inventory.add(itemInRoom);
            System.out.println("You get " + itemName + " in your bag!");
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ItemMenu> getInventory() {
        return inventory;
    }

}