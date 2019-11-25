package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Rock extends ActorBeta {

    Rock() {

        String[] planeAnim = {"smallRock.png"};

        loadAnimationFromFiles(planeAnim, 0.1f, true);

        this.setBoundaryRectangle();
        this.setSize(Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/15);
        //this.setBoundaryCircle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }
}
