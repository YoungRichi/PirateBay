package com.mygdx.game;

public class Lives extends ActorBeta {

    Lives() {

        String[] planeAnim = {"LivesIcon.png"};

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
