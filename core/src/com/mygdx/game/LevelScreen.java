package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import javax.sound.sampled.LineEvent;

public class LevelScreen extends ScreenBeta {

    ActorBeta background;
    ActorBeta LevelButton;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    @Override
    public void initialize() {

        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("Level_Background.png");
        background.setSize(WIDTH,HEIGHT);

        LevelButton =  new ActorBeta(WIDTH/4, HEIGHT/4, mainStage);
        LevelButton.loadTexture("StageButton.png");
        LevelButton.setScale(0.25f);




    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PirateBay.setActiveScreen(new GameScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }

}
