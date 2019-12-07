package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class BarricadeHealthPickup extends ActorBeta {

    public BarricadeHealthPickup() {
        loadTexture("BarricadeHealth.png");
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangle();
        addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.2f))));
        //setSpeed(0);
        //setMotionAngle(270);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(!ScreenBeta.loseGame) {
            applyPhysics(dt);
            if (getY() + getHeight() <= 0)
                remove();
        }
        //boundToWorld();
    }
}
