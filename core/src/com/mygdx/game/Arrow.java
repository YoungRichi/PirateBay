package com.mygdx.game;

public class Arrow extends ActorBeta {

    Arrow() {

        String[] planeAnim = {"Arrow1.png", "Arrow2.png"};

        loadAnimationFromFiles(planeAnim, 1.1f, true);

        this.setBoundaryRectangle();
        //this.setBoundaryCircle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }

}
