package com.advantage.ghoul;

import com.apps.util.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Intro {

    public void introStory() {

        try {
            Files.lines(Path.of("doc", "introStory.txt")).
                    forEach(line -> {
                        System.out.println("\033[33m" + line + "\033[0m");
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
