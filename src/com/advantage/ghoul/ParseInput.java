package com.advantage.ghoul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParseInput {
    private String output;
    private Scanner userInput=new Scanner(System.in);
    private String command=userInput.nextLine();

    public ParseInput() {
        runCommand(command);
    }

    private String runCommand(String command){
        List<String> word;
        String test="works";
        List<String> strlist=new ArrayList<>();
        try {
            String lowstr = command.trim().toLowerCase();
            if(lowstr.length()==0){
                test="Enter the Command please (Verb+Noun)";
            }else{
                word=commandList(lowstr);
                parseCommand(word);
            }
        }catch (Exception e){
            System.out.println("invalid input");
        }
        return test;
    }

    private List<String> commandList(String word){
        List<String> strlist=new ArrayList<>();
        String delimeter="[ \t,.:;?!\"']+";
        String[] words=word.split(delimeter);
        for (String ele:words){
            strlist.add(ele);
        }
       return strlist;
    }

    private void parseCommand(List<String> word) {
        String verb;
        String noun;
        List<String> commands=new ArrayList<>(Arrays.asList("take","drop"));
        List<String> objects=new ArrayList<>(Arrays.asList("sword","ring","snake"));

        if(word.size()!=2){
            System.out.println("Only 2 word Commands ");
        }else{
            verb=word.get(0);
            noun=word.get(1);
            if(!commands.contains(verb)){
                System.out.println(verb+" is not a Command verb!");
            }
            if(!objects.contains(noun)){
                System.out.println(noun+" is not a correct name!");
            }
        }
    }

}
