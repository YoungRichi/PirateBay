package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

    Sound clickSound;


    @Override
    public void initialize() {
        comic = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));


        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("EndScreen.png");
        background.setSize(WIDTH,HEIGHT);

        tapToPlay =  new ActorBeta(WIDTH/2, HEIGHT/2, mainStage);
        tapToPlay.loadTexture("TapMe.png");
        float TapAspectRatio = tapToPlay.getWidth()/tapToPlay.getHeight();
        tapToPlay.setSize(WIDTH/10 * TapAspectRatio, HEIGHT/10);

        dialogLb = new Label("The Pirate bay is at peace", comic);
        float TextAspectRatio = dialogLb.getWidth()/dialogLb.getHeight();

        dialogLb.setPosition(WIDTH * 17/32, HEIGHT *6/8);
        dialogLb.setScale(1);
        dialogLb.setFontScale(WIDTH/7000 * TextAspectRatio);
        dialogLb.setWrap(false);

        mainStage.addActor(dialogLb);

        ///
        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));

    }

    @Override
    public void update(float dt) {
    if (talkCounter == 2)
    {
        PirateBay.setActiveScreen(new SplashScreen());
    }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        clickSound.play();
        dialogLb.setText("For Now...");
        talkCounter++;


        //PirateBay.setActiveScreen(new TitleScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }

}
