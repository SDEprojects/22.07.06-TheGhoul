package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Intro {

    public static final String YELLOW = "\033[33m";
    public static final String RESET = "\033[0m";

    public void introStory() {
        Console.clear();

        try {
            Files.lines(Path.of("doc", "introStory.txt")).
                    forEach(line -> {
                        System.out.println(YELLOW + line);
                        Console.pause(1000);
                    });
            System.out.println(RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
