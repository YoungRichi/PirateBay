package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class SplashScreen extends ScreenBeta {

    ActorBeta background;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    Sprite splash;
    Texture splashTexture;

    float elapsed;


    @Override
    public void initialize()
    {

        splashTexture = new Texture(Gdx.files.internal("Logo.png"));
        splash = new Sprite(splashTexture);
        splash.setSize(WIDTH, HEIGHT);
        //Create "paper" to draw
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta)
    {
        //setting color
        Gdx.gl.glClearColor(0,0,0,1);
        //getting color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splash.draw(batch);
        batch.end();

        elapsed +=delta;

        if (elapsed > 3)
        {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
        }
        //Go to the next Screen
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        splash.getTexture().dispose();
    }



    @Override
    public void update(float dt)
    {

    }
}
