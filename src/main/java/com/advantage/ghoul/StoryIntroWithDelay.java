package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class StoryIntroWithDelay implements Runnable {

    @Override
    public void run() {
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("IntroStory.txt");
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Console.pause(1500);
                if (NewGame.skip) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            NewGame.skip = true;
            System.out.println(Color.RESET);
        }
    }
}

