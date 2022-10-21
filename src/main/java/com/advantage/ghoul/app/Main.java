package com.advantage.ghoul.app;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        Splashscreen splash = new Splashscreen();
//        Game game = new Game();
//        splash.display();
//        game.gameSummary();
//        game.newGame();
        JSONParser parser=new JSONParser();
        FileReader reader=new FileReader("res/Item.txt");
        Object obj=parser.parse(reader);
        System.out.println(obj);
        ObjectMapper objectMapper = new ObjectMapper();
//        Object emp = objectMapper.readValue(obj);
//        System.out.println(emp);
    }

}
