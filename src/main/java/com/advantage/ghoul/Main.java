package com.advantage.ghoul;

import com.fasterxml.jackson.databind.ObjectMapper;

class Main {
    public static void main(String[] args) {
        Splashscreen splash = new Splashscreen();
        Game game = new Game();
        splash.display();
        game.gameSummary();
        game.newGame();

    }
}
