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
    static String currentRoom = "outside";
    private String current;
    private String north;
    private String south;
    private String west;
    private String east;
    private String item;
    private String description;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Location> locations;
    private List<String> locationNameList = new ArrayList<>();
    private FileReading file = new FileReading();

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

    List<Location> dataReader() {
        try {
            String locationData = file.dataReader("room1.txt");
            locations = objectMapper.readValue(locationData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }

    Location getLocationByName(String name) {
        Location room = null;
        List<Location> Locations = dataReader();
        for (Location location : Locations) {
            if (location.getCurrent().equals(name)) {
                return room = location;
            }
        }
        return room;
    }

    void moving(String direction, List<Location> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            if (currentRoom.equals(rooms.get(i).getCurrent())) {
                if (direction.equals("north")) {
                    if (!rooms.get(i).getNorth().equals("no exit")) {
                        currentRoom = rooms.get(i).getNorth();
                        break;
                    } else {
                        System.out.println(Color.RED + "Wrong way!" + Color.RESET);
                    }
                } else if (direction.equals("south")) {
                    if (!rooms.get(i).getSouth().equals("no exit")) {
                        currentRoom = rooms.get(i).getSouth();
                        break;
                    } else {
                        System.out.println(Color.RED + "Wrong way!" + Color.RESET);
                    }
                } else if (direction.equals("east")) {
                    if (!rooms.get(i).getEast().equals("no exit")) {
                        currentRoom = rooms.get(i).getEast();
                        break;
                    } else {
                        System.out.println(Color.RED + "Wrong way!" + Color.RESET);
                    }
                } else if (direction.equals("west")) {
                    if (!rooms.get(i).getWest().equals("no exit")) {
                        currentRoom = rooms.get(i).getWest();
                        break;
                    } else {
                        System.out.println(Color.RED + "Wrong way!" + Color.RESET);
                    }
                } else {
                    System.out.println(Color.RED + "Please enter a valid entry" + Color.RESET);
                    currentRoom = currentRoom;
                }
            }
        }
        System.out.println(getLocationByName(Location.currentRoom).getDescription());
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

    public void setItem(String item) {
        this.item = item;
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