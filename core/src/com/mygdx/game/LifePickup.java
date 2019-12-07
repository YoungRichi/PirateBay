package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;

public class LifePickup extends ActorBeta {

    public LifePickup(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("LivesIcon.png");
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangle();
        addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.1f))));
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(!ScreenBeta.loseGame) {
            applyPhysics(dt);
            if (getY() + getHeight() <= 0)
                remove();
            //boundToWorld();
        }
    }
}
