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

    float elapsed;


    @Override
    public void initialize()
    {

        //splashTexture = new Texture(Gdx.files.internal("SplashLogo2.png"));
        /*splash = new Sprite(splashTexture);
        splash.setSize(WIDTH, HEIGHT);
        //Create "paper" to draw
        batch = new SpriteBatch();*/

        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("splashBG.png");
        background.setSize(WIDTH, HEIGHT);

        splashTex = new ActorBeta(); //(0, 0, mainStage);
        splashTex.loadTexture("SplashLogoEdited.png");
        splashTex.setSize(WIDTH /3, HEIGHT /2);
        splashTex.setPosition(WIDTH / 2 - splashTex.getWidth() / 2, HEIGHT / 2 - splashTex.getHeight() / 2);
        splashTex.setColor(splashTex.getColor().r, splashTex.getColor().g, splashTex.getColor().b, 0.0f);
        splashTex.addAction(Actions.sequence(Actions.delay(0.5f), Actions.parallel(Actions.sizeBy(WIDTH / 2, HEIGHT / 2, 3), Actions.moveTo(WIDTH / 12, HEIGHT / 100, 3),
                Actions.fadeIn(3))));
        mainStage.addActor(splashTex);
    }

    /*@Override
    public void render(float delta)
    {
        //setting color
        //Gdx.gl.glClearColor(0,0,0,1);
        //getting color
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // batch.begin();
       // splash.draw(batch);
       // batch.end();

        //elapsed +=delta;

       // if (elapsed > 3)
      //  {
      //      ((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
     //   }
        //Go to the next Screen
    }

   /* @Override
    public void dispose()
    {
       // batch.dispose();
       // splash.getTexture().dispose();
    }*/



    @Override
    public void update(float dt)
    {
        elapsed += dt;

         if (elapsed > 3.5)
          {
              ((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
          }
        //Go to the next Screen
    }
}
