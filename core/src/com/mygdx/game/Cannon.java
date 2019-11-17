package com.mygdx.game;

public class Cannon extends ActorBeta {
    Cannon() {

        String[] planeAnim = {"Cannon.png"};

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


