package com.mygdx.game;

public class PirateBay extends GameBeta {
    SplashScreen splashScreen;
    LevelScreen levelScreen;
    TitleScreen titleScreen;
    TutorialScreen tutorialScreen;
    OverScreen overScreen;
    EndScreen endScreen;
    Level01 level01;


    boolean paused = false;


    @Override
    public void create() {
        super.create();

        level01 = new Level01();
        setActiveScreen(level01);


        //splashScreen = new SplashScreen();
        //setActiveScreen(splashScreen);


        //titleScreen = new TitleScreen();
        //setActiveScreen(titleScreen);


        //levelScreen = new LevelScreen();
        //setActiveScreen(levelScreen);

        //tutorialScreen = new TutorialScreen();
        //setScreen(tutorialScreen);

/*        overScreen = new OverScreen();
        setActiveScreen(overScreen);*/

/*        endScreen = new EndScreen();
        setScreen(endScreen);*/

    }
}
