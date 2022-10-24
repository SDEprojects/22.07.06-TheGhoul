package com.advantage.ghoul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;


class Item {
    private String name;
    private String location;
    private String description;

    public Item() {
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
//    @Override
//    public String toString(){
//        return "Item: name=" + getName() + ", location=" + getLocation()
//                + ", description=" + getDescription();
//    }

// for testing purpose will be release
    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser=new JSONParser();
        FileReader reader=new FileReader("others/Item.txt");


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object obj=parser.parse(reader);
//            System.out.println(obj.getClass().getSimpleName());
            String items = obj.toString();
//            System.out.println(items.getClass().getSimpleName());

            List<Item> listItem = objectMapper.readValue(items, new TypeReference<List<Item>>(){});
//            System.out.println(listItem);
            System.out.println(listItem.get(0).getName());

        }catch (final Exception e){
            e.printStackTrace();
        }


    }
}
