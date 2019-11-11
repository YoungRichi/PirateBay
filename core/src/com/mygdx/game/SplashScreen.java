package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

public class SplashScreen extends ScreenBeta {

    Sprite splash;
    Texture splashTexture;

    float elapsed;

    //SpriteBatch batch;
    @Override
    public void initialize()
    {
        splashTexture = new Texture(Gdx.files.internal("Cannonball.png"));
        splash = new Sprite(splashTexture);
        splash.setSize(WIDTH, HEIGHT);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta)
    {
        //setting color
        Gdx.gl.glClearColor(1,0,0,1);
        //getting color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        splash.draw(batch);
        batch.end();

        elapsed +=delta;

        if (elapsed > 3)
        {
            //((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
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
