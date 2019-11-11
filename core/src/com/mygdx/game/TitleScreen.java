package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TitleScreen extends ScreenBeta {

    ActorBeta background;
    ActorBeta tapToPlay;
    Skin arcade;
    Label tap;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    @Override
    public void initialize() {
        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));


        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("TitleScreen.png");
        background.setSize(WIDTH,HEIGHT);

        tapToPlay =  new ActorBeta(1500, 500, mainStage);
        tapToPlay.loadTexture("TapMe.png");

        tap = new Label("TAP!!!", arcade);
        tap.setPosition(WIDTH /2, HEIGHT *7/8);
        tap.setScale(2);
        tap.setFontScale(3.f);
        //tap.setWrap(true);

        mainStage.addActor(tap);



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
