package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import javax.xml.soap.Text;

public class TitleScreen extends ScreenBeta {

    ActorBeta background;
    ActorBeta tapToPlay;
    Skin arcade;
    Label tap;
    Table table;

    //---SOUND---
    Music titleMusic;
    Sound clickSound;

    @Override
    public void initialize() {
        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));

        background = new ActorBeta(0,0, mainStage);//, mainStage);
        background.loadTexture("TitleScreen.png");
        background.setSize(WIDTH,HEIGHT);

        tapToPlay =  new ActorBeta(); //, mainStage);
        tapToPlay.loadTexture("TapMe.png");
        float TapAspectRatio = tapToPlay.getWidth()/tapToPlay.getHeight();
        tapToPlay.setSize(HEIGHT/10 * TapAspectRatio, HEIGHT/10);

        tap = new Label("TAP!", arcade);

        table = new Table(arcade);

        //table.setPosition(WIDTH  - tap.getWidth() * screenRatio, HEIGHT / 8);
        table.setPosition(WIDTH  * 7/8, HEIGHT / 8);
        table.add(tap).padRight(10);
        table.add(tapToPlay);

        mainStage.addActor(table);

        //Sound
        titleMusic = Gdx.audio.newMusic(Gdx.files.internal("Sound/menuBG.wav"));
        if(!titleMusic.isPlaying()) {
            titleMusic.play();
        }
        else
        {
            titleMusic.stop();
            titleMusic.dispose();
        }

        titleMusic.setLooping(true);
        titleMusic.setVolume(1.0f);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clickSound.play();

        PirateBay.setActiveScreen(new TutorialScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
