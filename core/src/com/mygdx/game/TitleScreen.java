package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TitleScreen extends ScreenBeta {

    ActorBeta background;
    ActorBeta tapToPlay;
    Skin arcade;
    Label tap;
    Table table;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    @Override
    public void initialize() {
        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));

        background = new ActorBeta();//, mainStage);
        background.setPosition(0,0);
        background.loadTexture("TitleScreen.png");
        background.setSize(WIDTH,HEIGHT);
        mainStage.addActor(background);

        tapToPlay =  new ActorBeta(); //, mainStage);
        tapToPlay.loadTexture("TapMe.png");

        tap = new Label("TAP!!!", arcade);
        tap.setPosition(WIDTH /2, HEIGHT *7/8);
        tap.setScale(2);
        tap.setFontScale(3.f);
        //tap.setWrap(true);

        table = new Table(arcade);
        table.add(tap).padRight(50);
        table.add(tapToPlay);
        table.setPosition(WIDTH  - tap.getWidth() * 4, HEIGHT / 8);
        mainStage.addActor(table);

       // mainStage.addActor(tap);



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
