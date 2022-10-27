package com.advantage.ghoul;

import com.apps.util.Console;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class StoryWithoutDelay implements Runnable{
    private Scanner skip=new Scanner(System.in);
    @Override
    public void run() {
        String input= skip.nextLine();
        if(input.length()>0){
            Console.clear();
            NewGame.skip=true;
            Thread.interrupted();
        }
    }
}
