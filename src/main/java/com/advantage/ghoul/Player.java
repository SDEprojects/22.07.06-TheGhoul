package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Player {
    private String name;
    private String description;
    private List<String> inventory = new ArrayList<>();
    private int MAXHP = 30;
    private int hp = 20;

    Player() {
        super();
    }

    Player(String name, String description) {
        this.name = name;
        this.description = description;
    }

    void addItem(String roomName, String itemName, ItemMenu items, List<Location> rooms) {
        int roomNumber=-1;

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCurrent().equals(roomName)&& rooms.get(i).getItem().equals(itemName)) {
                roomNumber=i;
            }
        }
        ItemMenu itemInRoom = items.getItemByName(itemName);
        if (roomNumber==-1) {
            System.out.println("There is no " + itemName + " in this area");
        } else {
            inventory.add(itemName);
            rooms.get(roomNumber).setItem("no item");
            items.setLocation("inventory");
            System.out.println("You get " + itemName + " in your bag!");
        }
    }

    void dropItem(String itemName,Player player,ItemMenu items,List<Location> rooms) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).equals(itemName)) {
                inventory.remove(itemName);
                System.out.println("You drop " + itemName + " from your bag!");
                items.setLocation(Location.currentRoom);
            }else{
                System.out.println("There is no " + itemName + " to drop from your bag");
            }
        }
    }

    void checkInventory(){
        getInventory();
       if(getInventory().size()>0){
           System.out.println("You have following items: " + getInventory() + ".");
       }else {
           System.out.println("You have nothing in your bag");
       }
    }

    void useHealingPotion(){
        if(inventory.contains("healing potion")){
            if (getHp() == MAXHP){
                System.out.println("You are healthy. You do not need the healing potion.");
            }else if(getHp()<MAXHP-5){
                setHp(getHp()+5);
            }else{
                setHp(MAXHP);
            }

        }

        getInventory().remove("healing potion");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}