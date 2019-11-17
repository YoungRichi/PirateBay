package com.mygdx.game;

public class Rock extends ActorBeta {

    Rock() {

        String[] planeAnim = {"Rock.png"};

        loadAnimationFromFiles(planeAnim, 0.1f, true);

        this.setBoundaryRectangle();
        //this.setBoundaryCircle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }
}
