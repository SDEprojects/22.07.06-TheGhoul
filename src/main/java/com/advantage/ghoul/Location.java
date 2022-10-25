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
import java.util.Scanner;


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

    public List<Location> locationRead(){
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

        return listRooms;

    }

    public Location getLocationByName(String name){
        Location room = null;
        List<Location> Locations = locationRead();
        for(Location location: Locations){
            if(location.getCurrent().equals(name)){
                room = location;
            }
        }

        return room;
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
        Location Locations = new Location();
        List<Location> rooms = Locations.locationRead();
        String current = "lobby";
        String directionInput;
        String direction = "";

        while(!current.equals("quit")){

            System.out.println(Locations.getLocationByName(current).getDescription());
            System.out.println("Where do you want to go next: ");
            directionInput = scanner.nextLine().trim();
            direction= directionInput.toLowerCase();
            System.out.println(direction);


            for(int i = 0; i < rooms.size(); i++){
                if(current.equals(rooms.get(i).getCurrent())){

                    if(direction.equals("north")){

                        if(!rooms.get(i).getNorth().equals("No exit")) {
                            current = rooms.get(i).getNorth();

                            break;
                        }else{
                            System.out.println("Wrong way!");
                        }

                    } else if(direction.equals("south")){

                        if(!rooms.get(i).getSouth().equals("No exit")) {
                            current = rooms.get(i).getSouth();
                            break;
                        }else{
                            System.out.println("Wrong way!");
                        }
                    }else if(direction.equals("east")){

                        if(!rooms.get(i).getEast().equals("No exit")){
                            current = rooms.get(i).getEast();
                            break;
                        }else{
                            System.out.println("Wrong way!");
                        }
                    }else if(direction.equals("west")){

                        if(!rooms.get(i).getWest().equals("No exit")){
                            current = rooms.get(i).getEast();
                            break;
                        }else{
                            System.out.println("Wrong way!");
                        }
                    }else{
                        System.out.println("Please enter a valid entry");
                        current = current;
                    }

                }
            }




        }


    }
}
