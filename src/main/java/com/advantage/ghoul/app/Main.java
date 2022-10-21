package com.advantage.ghoul.app;

import com.advantage.ghoul.Game;
import com.advantage.ghoul.Splashscreen;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Splashscreen splash = new Splashscreen();
        Game game = new Game();
        splash.display();
        game.newGame();

    }
}
