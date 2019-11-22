package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class OverScreen extends ScreenBeta {

    ActorBeta background;

    ActorBeta tapToPlay;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    Sound gameOver;
    Sound clickSound;


    @Override
    public void initialize() {
        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("GameOverScreen.png");
        background.setSize(WIDTH,HEIGHT);

        tapToPlay =  new ActorBeta(1500, 500, mainStage);
        tapToPlay.loadTexture("TapMe.png");

        gameOver = Gdx.audio.newSound(Gdx.files.internal("Sound/gameOver.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
       // gameOver.play();

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clickSound.play();
        PirateBay.setActiveScreen(new EndScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }

}
