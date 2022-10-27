package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.*;
import java.nio.charset.StandardCharsets;

class FileReading{
    static InputStream getFileFromResourceAsStreamFortxt(String fileName) {
        ClassLoader classLoader = FileReading.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Can not read the file! " + fileName);
        } else {
            return inputStream;
        }
    }

    static void printInputStream(InputStream content, boolean delay, String color) {

        System.out.println(color);
        try (InputStreamReader streamReader = new InputStreamReader(content, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (delay) {
                    System.out.println(line);
                    Console.pause(800);
                    if(NewGame.skip==true){
                        Thread.currentThread().interrupt();
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Color.RESET);
        }
    }
}

