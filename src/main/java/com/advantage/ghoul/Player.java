package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.List;

class Player {
    private String name;
    private String description;
    private List<ItemMenu> inventory = new ArrayList<>();

    public Player(){
        super();
    }

    public Player(String name, String description){
        this.name = name;
        this.description = description;
    }


    public void addItem(String roomName, String itemName){

        Location map = new Location();
        List<Location> rooms = map.locationRead();
        ItemMenu itemInRoom;


        for (int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getCurrent().equals(roomName)){
                itemName = rooms.get(i).getItem();
                System.out.println(itemName);
            }
        }

        ItemMenu items = new ItemMenu();
        itemInRoom = items.getItemByName(itemName);
        System.out.println(itemInRoom);
        inventory.add(itemInRoom);


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

    //For testing purposes
    public static void main(String[] args) {
        Location map = new Location();
        List<Location> rooms = map.locationRead();


        Player newPlayer = new Player();
        String room = "dinning room";
        newPlayer.addItem(room, "healing potion");
        newPlayer.addItem("library", "ancient book");

        System.out.println(newPlayer.getInventory());

    }
}