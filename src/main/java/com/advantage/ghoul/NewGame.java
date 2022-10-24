package com.advantage.ghoul;

import com.apps.util.Console;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class NewGame {
    private final String YELLOW = "\033[33m";
    private final String RESET = "\033[0m";
    private Character player=new Character();
    private Scanner inputValue = new Scanner(System.in);
    private boolean isRunning;

    void introStory() {
        Console.clear();
        try {
            Files.lines(Path.of("doc", "introStory.txt")).
                    forEach(line -> {
                        System.out.println(YELLOW + line);
//                        Console.pause(1000);
                    });
            gameLoop(isRunning);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void gameLoop(boolean isRunning){
        System.out.println("Game Start\n> Type the help for checking the game command");
        while(!isRunning){
            String commandInput = inputValue.nextLine();
            if(commandInput.equals("help")){
                help();
            }else if(commandInput.equals("s")){
                saving();
            }else if(commandInput.equals("q")){
                isRunning=true;
                // print out some works like thanks for playing
            }else if(commandInput.length()>1){
                executeCommand();
            }
            else{
                System.out.println("Invalid input, please try again. Type help for checking the command");
            }
        }
    }

    private void executeCommand() {
    }

    private void saving() {
    }

    private void help(){
        Console.clear();
        try {
            Files.lines(Path.of("doc", "Command.txt")).
                    forEach(line -> {
                        System.out.println(YELLOW + line);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
