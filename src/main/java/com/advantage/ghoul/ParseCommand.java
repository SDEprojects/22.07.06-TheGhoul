package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ParseCommand {
    final List<String> actionCommand = new ArrayList<>(Arrays.asList("get", "pick", "grab",
            "take","check", "inspect", "inventory", "look", "show","drop"));
    final List<String> getCommand = new ArrayList<>(Arrays.asList("get", "pick", "grab", "take"));
    final List<String> checkCommand = new ArrayList<>(Arrays.asList("check", "inspect", "inventory"));
    final List<String> lookCommand = new ArrayList<>(Arrays.asList("look", "show"));
    final List<String> movingCommand=new ArrayList<>(Arrays.asList("go","moving","move","walk","run","turn"));
    final List<String> direction = new ArrayList<>(Arrays.asList("south", "north", "east", "west"));

    String verifyAction(String commandParse){
       if(getCommand.contains(commandParse)) {
           return "get";
       }else if(checkCommand.contains(commandParse)){
           return "check";
       }else if(lookCommand.contains(commandParse)){
           return "look";
       }
       return null;
    }
    String verifyMoving(String commandParse){
        if(movingCommand.contains(commandParse)) {
            return "go";
        }else{
            return null;
        }
    }



}

