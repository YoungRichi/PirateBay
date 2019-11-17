package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class EndScreen extends ScreenBeta {

    Skin comic;
    ActorBeta background;
    ActorBeta tapToPlay;
    Label dialogLb;
    int talkCounter;


    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();
    @Override
    public void initialize() {
        comic = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));


        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("EndScreen.png");
        background.setSize(WIDTH,HEIGHT);

        tapToPlay =  new ActorBeta(1500, 500, mainStage);
        tapToPlay.loadTexture("TapMe.png");

        dialogLb = new Label("The Pirate bay is at peace", comic);
        dialogLb.setPosition(WIDTH * 17/32, HEIGHT *6/8);
        dialogLb.setScale(3);
        dialogLb.setFontScale(4.f);
        dialogLb.setWrap(false);

        mainStage.addActor(dialogLb);

    }

    @Override
    public void update(float dt) {
    if (talkCounter == 2)
    {
        PirateBay.setActiveScreen(new TitleScreen());
    }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        dialogLb.setText("For Now...");
        talkCounter++;

        //PirateBay.setActiveScreen(new TitleScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }

}
