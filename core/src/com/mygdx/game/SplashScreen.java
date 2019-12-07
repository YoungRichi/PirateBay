package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class SplashScreen extends ScreenBeta {

    ActorBeta background;
    ActorBeta splashTex;
    float totalTime;
    float delayTime;
    float animatedTime;

    //Sound
    Sound spraySound;
    @Override
    public void initialize()
    {
        // time before going to the next screen
        totalTime = 5f;
        delayTime = 1f;
        animatedTime = 3;


        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("splashBG.png");
        background.setSize(WIDTH, HEIGHT);

        splashTex = new ActorBeta(); //(0, 0, mainStage);
        splashTex.loadTexture("SplashLogoEdited.png");
        //keep original aspect ratio when using setSize() method
        float aspectRatio = splashTex.getWidth()/splashTex.getHeight();
        //========
        splashTex.setSize(HEIGHT /2 * aspectRatio, HEIGHT /2);
        splashTex.setPosition(WIDTH / 2 - splashTex.getWidth() / 2, HEIGHT / 2 - splashTex.getHeight() / 2);
        splashTex.setColor(splashTex.getColor().r, splashTex.getColor().g, splashTex.getColor().b, 0.0f);
        splashTex.addAction(Actions.sequence(Actions.delay(delayTime), Actions.parallel(Actions.sizeTo(HEIGHT * aspectRatio, HEIGHT, animatedTime),
                Actions.moveTo(WIDTH / 2 - HEIGHT / 2 * aspectRatio, 0, animatedTime),
                Actions.fadeIn(animatedTime)), Actions.fadeOut(delayTime)));
        mainStage.addActor(splashTex);

        //===============SOUND------------------
        spraySound = Gdx.audio.newSound(Gdx.files.internal("Sound/spray.wav"));
        //spraySound.setVolume(spraySound.play(), 1f);

    }


    @Override
    public void update(float dt)
    {
        totalTime -= dt;

        //Go to the next Screen
         if (totalTime <= 0)
          {
              PirateBay.setActiveScreen(new TitleScreen());
          }
         //else if(elapsed > 0.0f && elapsed < 1.0f)
              //spraySound.play();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
