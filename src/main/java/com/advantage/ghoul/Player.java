package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Player {
    private String name;
    private String description;
    private List<ItemMenu> inventory = new ArrayList<>();

    Player() {
        super();
    }

    Player(String name, String description) {
        this.name = name;
        this.description = description;
    }

    void addItem(String roomName, String itemName, ItemMenu items, List<Location> rooms) {
        int roomNumber=0;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCurrent().equals(roomName)) {
                itemName = rooms.get(i).getItem();
                roomNumber=i;
            }
        }
        ItemMenu itemInRoom = items.getItemByName(itemName);
        if (itemName.equals("no item")) {
            System.out.println("There is " + itemName + " in this area");
        } else {
            inventory.add(itemInRoom);
            rooms.get(roomNumber).setItem("no item");
            items.setLocation("inventory");
            System.out.println("You get " + itemName + " in your bag!");
        }
    }

    void dropItem(String itemName,Player player,ItemMenu items,List<Location> rooms) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).getName().equals(itemName)) {
                inventory.remove(player.getInventory().get(i));
                System.out.println("You drop " + itemName + " from your bag!");
                items.setLocation(Location.currentRoom);
            }else{
                System.out.println("There is no " + itemName + " to drop from your bag");
            }
        }
    }

    void checkInventory(){
       String itemList=inventory.stream().map(x->x.getName()).collect(Collectors.joining(", "));
       if(itemList.length()>0){
           System.out.println("You have following items: " + itemList + ".");
       }else {
           System.out.println("You have nothing in your bag");
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