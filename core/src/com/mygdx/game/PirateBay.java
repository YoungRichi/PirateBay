package com.mygdx.game;

public class PirateBay extends GameBeta {
    SplashScreen splashScreen;
    boolean paused = false;

    @Override
    public void create() {
        super.create();
        splashScreen = new SplashScreen();
        setScreen(splashScreen);
    }
}
