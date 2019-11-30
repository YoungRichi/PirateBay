package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;

public class CannonBase extends ActorBeta {

    String[] str = {"CannonBaseNew3.png"};

    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);

    public CannonBase() {
        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 6 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 6);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }

}
