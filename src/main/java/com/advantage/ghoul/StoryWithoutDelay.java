package com.advantage.ghoul;

import com.apps.util.Console;
import java.util.Scanner;

class StoryWithoutDelay implements Runnable {
    private Scanner skip = new Scanner(System.in);

    @Override
    public void run() {
        String input = skip.nextLine();
        if (input.length() > 0) {
            Console.clear();
            NewGame.skip = true;
            Thread.interrupted();
        }
    }
}
