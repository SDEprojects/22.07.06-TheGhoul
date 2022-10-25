package com.advantage.ghoul;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ItemMenu {
    private String name;
    private String location;
    private String description;
    private JSONParser parser=new JSONParser();
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<ItemMenu> listItem;
    private List<String> itemNameList=new ArrayList<>();

    //Ctor
    ItemMenu(){
        super();
    }

    ItemMenu(String name, String location, String description){
        this.name = name;
        this.location = location;
        this.description = description;
    }
    //business function
    void itemMenuRead(){
        try {
            InputStream itemFile = FileReading.getFileFromResourceAsStreamFortxt("Item.txt");
            String result = new BufferedReader(new InputStreamReader(itemFile))
                    .lines().collect(Collectors.joining("\n"));
            Object obj=parser.parse(result);
            String items = obj.toString();
            listItem = objectMapper.readValue(items, new TypeReference<>(){});
            for(int i=0;i<listItem.size();i++){
                itemNameList.add(listItem.get(i).getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    List<String> itemList(){
        itemMenuRead();
        return itemNameList;
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

    @Override
    public String toString(){
        return "Item: name=" + getName() + ", location=" + getLocation()
                + ", description=" + getDescription();
    }

    public static void main(String[] args) {
        ItemMenu abc=new ItemMenu();
        abc.itemMenuRead();
    }
}

