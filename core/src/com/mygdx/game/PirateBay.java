package com.mygdx.game;

public class PirateBay extends GameBeta {
    SplashScreen splashScreen;
    boolean paused = false;

    GameScreen gameScreen;

    @Override
    public void create() {
        super.create();
        splashScreen = new SplashScreen();
        setActiveScreen(splashScreen);
        //setScreen(splashScreen);

/*
        gameScreen = new GameScreen();
        setScreen(gameScreen);*/

    }
}
