package com.mygdx.game;

public class PirateBay extends GameBeta {
    SplashScreen splashScreen;
    LevelScreen levelScreen;
    GameScreen gameScreen;
    OverScreen overScreen;
    EndScreen endScreen;

    boolean paused = false;


    @Override
    public void create() {
        super.create();

/*        splashScreen = new SplashScreen();
        setActiveScreen(splashScreen);*/

/*        levelScreen = new LevelScreen();
        setActiveScreen(levelScreen);*/

/*        gameScreen = new GameScreen();
        setScreen(gameScreen);*/

/*        overScreen = new OverScreen();
        setActiveScreen(overScreen);*/

        endScreen = new EndScreen();
        setScreen(endScreen);

    }
}
