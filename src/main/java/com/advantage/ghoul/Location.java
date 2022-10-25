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
    static String currentRoom="lobby";
    String current;
    String north;
    String south;
    String west;
    String east;
    String item;
    String description;
    private JSONParser parser = new JSONParser();
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Location> listRooms;
    private List<String> locationNameList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public Location() {
        super();
    }

    public Location(String current, String north, String south, String west, String east, String item, String description) {
        this.current = current;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.item = item;
        this.description = description;
    }

    public List<Location> locationRead() {
        try {
            InputStream locationFile = FileReading.getFileFromResourceAsStreamFortxt("room1.txt");
            String result = new BufferedReader(new InputStreamReader(locationFile))
                    .lines().collect(Collectors.joining("\n"));
            Object obj = parser.parse(result);
            String rooms = obj.toString();
            listRooms = objectMapper.readValue(rooms, new TypeReference<>() {
            });
            for (int i = 0; i < listRooms.size(); i++) {
                //create the list of the room name in the with array list
                locationNameList.add(listRooms.get(i).getCurrent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listRooms;
    }

    public Location getLocationByName(String name) {
        Location room = null;
        List<Location> Locations = locationRead();
        for (Location location : Locations) {
            if (location.getCurrent().equals(name)) {
                return room = location;
            }
        }
        return room;
    }

    public void moving(String direction) {
        Location Locations = new Location();
        List<Location> rooms = Locations.locationRead();
        System.out.println(Locations.getLocationByName(currentRoom).getDescription());

        for (int i = 0; i < rooms.size(); i++) {
            if (current.equals(rooms.get(i).getCurrent())) {
                if (direction.equals("north")) {
                    if (!rooms.get(i).getNorth().equals("No exit")) {
                        current = rooms.get(i).getNorth();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else if (direction.equals("south")) {
                    if (!rooms.get(i).getSouth().equals("No exit")) {
                        current = rooms.get(i).getSouth();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else if (direction.equals("east")) {
                    if (!rooms.get(i).getEast().equals("No exit")) {
                        current = rooms.get(i).getEast();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else if (direction.equals("west")) {
                    if (!rooms.get(i).getWest().equals("No exit")) {
                        current = rooms.get(i).getEast();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else {
                    System.out.println("Please enter a valid entry");
                    current = current;
                }
            }
        }
    }

    public String getCurrent() {
        return current;
    }

    public String getNorth() {
        return north;
    }

    public String getSouth() {
        return south;
    }

    public String getWest() {
        return west;
    }

    public String getEast() {
        return east;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Room: current Room=" + getCurrent() + ", north=" + getNorth() + ", south=" + getSouth() + ", west=" + getWest()
                + ", east=" + getEast() + ", item=" + getItem() + ", description=" + getDescription();
    }
}
