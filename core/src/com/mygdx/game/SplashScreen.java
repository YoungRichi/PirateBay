package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class SplashScreen extends ScreenBeta {

    ActorBeta background;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    Sprite splash;
    Texture splashTexture;
    ActorBeta splashTex;
    float totalTime, delayTime, animatedTime;


    @Override
    public void initialize()
    {
        totalTime = 5f; // time before going to the next screen
        delayTime = 1f;
        animatedTime = 3;
        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("splashBG.png");
        background.setSize(WIDTH, HEIGHT);

        splashTex = new ActorBeta(); //(0, 0, mainStage);
        splashTex.loadTexture("SplashLogoEdited.png");
        float aspectRatio = splashTex.getWidth()/splashTex.getHeight(); // keep original aspect ratio when using setSize() method
        splashTex.setSize(HEIGHT/3 * aspectRatio, HEIGHT/3 );
        splashTex.setPosition(WIDTH / 2 - splashTex.getWidth() / 2, HEIGHT / 2 - splashTex.getHeight() / 2);
        splashTex.setColor(splashTex.getColor().r, splashTex.getColor().g, splashTex.getColor().b, 1.0f);
        splashTex.addAction(Actions.sequence(Actions.delay(delayTime), Actions.parallel(Actions.sizeTo(HEIGHT * aspectRatio, HEIGHT, animatedTime),
                Actions.moveTo(WIDTH/2 - HEIGHT * aspectRatio / 2, HEIGHT/2 - HEIGHT / 2, animatedTime),
                Actions.fadeIn(animatedTime)), Actions.fadeOut(delayTime)));
        mainStage.addActor(splashTex);
    }

    @Override
    public void update(float dt)
    {
        totalTime -= dt;

        //Go to the next Screen
        if (totalTime <= 0)
          {
              //((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
              PirateBay.setActiveScreen(new TitleScreen());
          }

    }
}
