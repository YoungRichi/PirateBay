package com.mygdx.game;

public class CannonBase extends ActorBeta {


    CannonBase() {

        String[] planeAnim = {"CannonBase.png"};

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
