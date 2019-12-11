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
/*        level01 = new Level01();
        setActiveScreen(level01);*/

//===================LEVEL TESTING===============================================================
/*        Level02 level02 = new Level02();
        setActiveScreen(level02);*/

/*        Level04 level04 = new Level04();
        setActiveScreen(level04);*/

/*        Level05 level05 = new Level05();
        setActiveScreen(level05);*/


/*        Level07 level07 = new Level07();
        setActiveScreen(level07);*/

/*        Level08 level08 = new Level08();
        setActiveScreen(level08);*/

        Level09 level09 = new Level09();
        setActiveScreen(level09);
/*
        Level10 level10 = new Level10();
        setActiveScreen(level10);*/

//===================LEVEL TESTING===============================================================

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

        //endScreen = new EndScreen();
        //setScreen(endScreen);

    }
}
