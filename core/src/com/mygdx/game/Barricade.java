package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Barricade extends ActorBeta {

    Barricade() {

        super();
        String[] planeAnim = {"Barricade.png"};

        loadAnimationFromFiles(planeAnim, 0.1f, true);

        this.setSize(Gdx.graphics.getWidth() /20, Gdx.graphics.getHeight());
        this.setBoundaryRectangle();
        //this.setBoundaryCircle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }
}
