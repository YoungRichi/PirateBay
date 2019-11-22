package com.mygdx.game;

public class PirateBay extends GameBeta {
    SplashScreen splashScreen;
    LevelScreen levelScreen;
    TitleScreen titleScreen;
    GameScreen gameScreen;
    OverScreen overScreen;
    EndScreen endScreen;

    boolean paused = false;


    @Override
    public void create() {
        super.create();

/*        splashScreen = new SplashScreen();
        setActiveScreen(splashScreen);*/

/*
        titleScreen = new TitleScreen();
        setActiveScreen(titleScreen);
*/

/*        levelScreen = new LevelScreen();
        setActiveScreen(levelScreen);*/

/*        gameScreen = new GameScreen();
        setScreen(gameScreen);*/

        overScreen = new OverScreen();
        setActiveScreen(overScreen);

/*        endScreen = new EndScreen();
        setScreen(endScreen);*/

    }
}
