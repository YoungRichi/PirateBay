package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class TitleScreen extends ScreenBeta {

    ActorBeta background;

    ActorBeta tapToPlay;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();
    @Override
    public void initialize() {
        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("TitleScreen.png");
        background.setSize(WIDTH,HEIGHT);

        tapToPlay =  new ActorBeta(1500, 500, mainStage);
        tapToPlay.loadTexture("TapMe.png");



    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PirateBay.setActiveScreen(new LevelScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }


}
