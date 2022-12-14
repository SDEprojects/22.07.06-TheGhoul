package com.advantage.ghoul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ItemMenu {
    private String name;
    private String location;
    private String description;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<ItemMenu> listItem;
    private List<String> itemNameList = new ArrayList<>();
    private FileReading file = new FileReading();

    ItemMenu() {
        super();
    }

    ItemMenu(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    //business function
    List<ItemMenu> dataReader() {
        try {
            String itemData = file.dataReader("Item.txt");
            listItem = objectMapper.readValue(itemData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItem;
    }

    List<String> itemList() {
        List<ItemMenu> listItem = dataReader();
        for (int i = 0; i < listItem.size(); i++) {
            itemNameList.add(listItem.get(i).getName());
        }
        return itemNameList;
    }

    ItemMenu getItemByName(String name) {
        ItemMenu Items = new ItemMenu();
        List<ItemMenu> listItem = dataReader();
        ItemMenu item = new ItemMenu();

        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).getName().equals(name)) {
                item = listItem.get(i);
            }
        }
        return item;
    }

    String looking(String name, ItemMenu item) {
        String description;
        ItemMenu object = item.getItemByName(name);
        description = object.getDescription();
        return description;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item: name=" + getName() + ", location=" + getLocation()
                + ", description=" + getDescription();
    }
}

