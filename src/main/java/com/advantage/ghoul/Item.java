package com.advantage.ghoul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

class Item {
    private String name;
    private String location;
    private String description;

    public Item() {
        super();
    }

    public Item(String name, String location, String description){
        this.name = name;
        this.location = location;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void useItem(){
        System.out.println("You have used the " + getName());
    }
    @Override
    public String toString(){
        return "Item: name=" + getName() + ", location=" + getLocation()
                + ", description=" + getDescription();
    }

    public static void main(String[] args) {
        Item sword = new Item("sword", "secret room", "divine sword");
        sword.useItem();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String items = "[{\"name\":\"Library Key\",\"location\":\"floor3\",\"description\":\"large black key\"}," +
                    "{\"name\":\"Special Key\",\"location\":\"main room first floor\",\"description\":\"special gold key\"}]";

            List<Item> itemList = objectMapper.readValue(items, new TypeReference<List<Item>>(){});
            System.out.println(itemList);
        }catch (final Exception e){
            e.printStackTrace();
        }
    }
}
