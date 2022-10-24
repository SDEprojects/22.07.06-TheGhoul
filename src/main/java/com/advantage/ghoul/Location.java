package com.advantage.ghoul;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private FileReader reader;
    private List<String> locationNameList=new ArrayList<>();
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

    public void locationRead(){
        try {
            reader=new FileReader("others/room1.txt");
            Object obj=parser.parse(reader);
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
        System.out.println(locationNameList);
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
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

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return "Room: current Room=" + getCurrent() + ", north=" + getNorth()+", south="+getSouth()+", west="+getWest()
                +", east="+getEast()+", item="+getItem()+", description="+getDescription();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Location abc=new Location();
        abc.locationRead();
    }
}
