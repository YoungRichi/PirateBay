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

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    //---SOUND---
    Music titleMusic;
    Sound clickSound;

    @Override
    public void initialize() {
        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));
        //float screenRatio = WIDTH/HEIGHT;

        background = new ActorBeta();//, mainStage);
        background.setPosition(0,0);
        background.loadTexture("TitleScreen.png");
        background.setSize(WIDTH,HEIGHT);
        mainStage.addActor(background);

        tapToPlay =  new ActorBeta(); //, mainStage);
        tapToPlay.loadTexture("TapMe.png");
        //===
        float TapAspectRatio = tapToPlay.getWidth()/tapToPlay.getHeight();
        tapToPlay.setSize(WIDTH/10 * TapAspectRatio, HEIGHT/10);

        tap = new Label("TAP!", arcade);
        float TextAspectRatio = tap.getWidth()/tap.getHeight();

        tap.setPosition(WIDTH /2 * TextAspectRatio, HEIGHT *7/8);
        tap.setScale(1);
        //========
        tap.setFontScale( WIDTH/2000 * TextAspectRatio);
        //tap.setWrap(true);

        table = new Table(arcade);
        table.add(tap).padRight(50);
        table.add(tapToPlay);
        //table.setPosition(WIDTH  - tap.getWidth() * screenRatio, HEIGHT / 8);
        table.setPosition(WIDTH  * 7/8, HEIGHT / 8);

        mainStage.addActor(table);

       // mainStage.addActor(tap);

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
        titleMusic.setVolume(0.5f);

        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));


    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clickSound.play();

        PirateBay.setActiveScreen(new LevelScreen());

        return super.touchDown(screenX, screenY, pointer, button);
    }


}
