package com.mygdx.game;

public class PirateBay extends GameBeta {
    SplashScreen splashScreen;
    LevelScreen levelScreen;
    TitleScreen titleScreen;
    GameScreen gameScreen;
    OverScreen overScreen;
    EndScreen endScreen;
    Level01 level01;
    Wave1 wave1;

    boolean paused = false;


    @Override
    public void create() {
        super.create();

        wave1 = new Wave1();
        setActiveScreen(wave1);
        //level01 = new Level01();
        //setActiveScreen(level01);
        //splashScreen = new SplashScreen();
        //setActiveScreen(splashScreen);


        //titleScreen = new TitleScreen();
        //setActiveScreen(titleScreen);


        //levelScreen = new LevelScreen();
        //setActiveScreen(levelScreen);

        //gameScreen = new GameScreen();
        //setScreen(gameScreen);

/*        overScreen = new OverScreen();
        setActiveScreen(overScreen);*/

/*        endScreen = new EndScreen();
        setScreen(endScreen);*/

    }
}
