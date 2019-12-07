package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Finger extends ActorBeta {
    public Finger(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("TapMe.png");
        setSize(Gdx.graphics.getHeight() / 10 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 10);
        addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.1f))));
    }

    public Finger() {
        loadTexture("TapMe.png");
        setSize(Gdx.graphics.getHeight() / 10 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 10);
        addAction(Actions.forever(Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.1f))));
    }
}
