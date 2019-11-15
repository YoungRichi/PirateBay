package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BoatBig extends ActorBeta {
    String[] boatAnim = {"BigBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatAnim, 0.2f, true);

    BoatBig()
    {
        this.setBoundaryRectangle();
        setMaxSpeed(800);
        setAnimation(idleAnim);
    }

}

