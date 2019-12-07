package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lives extends ActorBeta {

    String[] str = {"LivesIcon.png"};

    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);

    public Lives(float x, float y, Stage s) {
        super(x, y, s);
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }
}
