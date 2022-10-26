package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.List;

class Player {
    private List<ItemMenu> inventory = new ArrayList<>();

    void addItem(String roomName, String itemName,ItemMenu items,List<Location> rooms){
        System.out.println("works");
//        for (int i = 0; i < rooms.size(); i++){
//            if(rooms.get(i).getCurrent().equals(roomName)){
//                itemName = rooms.get(i).getItem();
//                System.out.println(itemName);
//            }
//        }
//        ItemMenu itemInRoom = items.getItemByName(itemName);
//        System.out.println(itemInRoom);
//        inventory.add(itemInRoom);
//        System.out.println("You get "+itemName+" in your bag!");
    }

    public List<ItemMenu> getInventory() {
        return inventory;
    }
}