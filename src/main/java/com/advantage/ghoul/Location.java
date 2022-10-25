package com.advantage.ghoul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Location {
    String current;
    String north;
    String south;
    String west;
    String east;
    String item;
    String description;
    private JSONParser parser=new JSONParser();
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Location> listRooms;
    private List<String> locationNameList=new ArrayList<>();

    Location() {
        super();
    }

    Location(String current, String north, String south, String west, String east, String item, String description) {
        this.current = current;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.item = item;
        this.description = description;
    }

    public List<Location> locationRead(){
        try {
            InputStream locationFile = FileReading.getFileFromResourceAsStreamFortxt("room1.txt");
            String result = new BufferedReader(new InputStreamReader(locationFile))
                    .lines().collect(Collectors.joining("\n"));
            Object obj=parser.parse(result);
            String rooms = obj.toString();
            listRooms = objectMapper.readValue(rooms, new TypeReference<>(){});
            for(int i=0;i<listRooms.size();i++){
                locationNameList.add(listRooms.get(i).getCurrent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listRooms;
    }

    String getCurrent() {
        return current;
    }

    String getNorth() {
        return north;
    }

    String getSouth() {
        return south;
    }

    String getWest() {
        return west;
    }

    String getEast() {
        return east;
    }


    String getItem() {
        return item;
    }


    String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "Room: current Room=" + getCurrent() + ", north=" + getNorth()+", south="+getSouth()+", west="+getWest()
                +", east="+getEast()+", item="+getItem()+", description="+getDescription();
    }

//    public static void main(String[] args) throws FileNotFoundException {
//        Location Locations = new Location();
//        List<Location> rooms = Locations.locationRead();
//        System.out.println(rooms);
//        String current = "library";
//        String directionInput;
//        String direction = "";
    void moving(String direction) {
        String current="lobby";
        System.out.println("You are in the " + current);
        System.out.println("Where do you want to go next: ");

//            for(int i = 0; i < listRooms.size(); i++){
//                if(current.equals(listRooms.get(i).getCurrent())){
//                    System.out.println(i);
//                    System.out.println(listRooms.get(i));
//                    if(direction.equals("north")){
//
//                        if(!listRooms.get(i).getNorth().equals("No exit")) {
//                            current = listRooms.get(i).getNorth();
//                        }else{
//                            System.out.println("Wrong way!");
//                        }
//
//                    } else if(direction.equals("south")){
//
//                        if(!listRooms.get(i).getSouth().equals("No exit")) {
//                            current = listRooms.get(i).getSouth();
//                        }else{
//                            System.out.println("Wrong way!");
//                        }
//                    }else if(direction.equals("east")){
//
//                        if(!listRooms.get(i).getEast().equals("No exit")){
//                            current = listRooms.get(i).getEast();
//                        }else{
//                            System.out.println("Wrong way!");
//                        }
//                    }else if(direction.equals("west")){
//
//                        if(!listRooms.get(i).getWest().equals("No exit")){
//                            current = listRooms.get(i).getEast();
//                        }else{
//                            System.out.println("Wrong way!");
//                        }
//                    }else{
//                        System.out.println("Please enter a valid entry");
//                    }
//
//                }
//            }
    }
}
