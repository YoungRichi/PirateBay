package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Cannon extends ActorBeta {

    String[] str = {"Cannon.png"};

    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);

    int lives = 3;

    public Cannon(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 8 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 8);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }


}


