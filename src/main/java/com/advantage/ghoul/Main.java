package com.advantage.ghoul;

class Main {
    public static void main(String[] args) {
        Splashscreen splash = new Splashscreen();
        Game game = new Game();
        splash.display();
        game.gameSummary();
        game.newGame();


    }
}
