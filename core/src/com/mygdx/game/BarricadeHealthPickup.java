package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class BarricadeHealthPickup extends ActorBeta {

    public BarricadeHealthPickup(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("BarricadeHealth.png");
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangle();
        setSpeed(0);
        addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.2f))));
        ScreenBeta.loadMusic("Sound/FallingSound.ogg", "pickup");
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(!ScreenBeta.loseGame) {
            applyPhysics(dt);
            if(getSpeed() != 0)
            {
                if(!ScreenBeta.getMusic("pickup").isPlaying())
                {
                    ScreenBeta.getMusic("pickup").play();
                    ScreenBeta.getMusic("pickup").setLooping(true);
                }
            }

            if (getY() + getHeight() <= 0)
            {
                remove();
                ScreenBeta.getMusic("pickup").stop();
                ScreenBeta.getMusic("pickup").dispose();
            }

        }
        else if(ScreenBeta.getMusic("pickup").isPlaying())
        {
            ScreenBeta.getMusic("pickup").stop();
            ScreenBeta.getMusic("pickup").dispose();
        }

        //boundToWorld();
    }
}
