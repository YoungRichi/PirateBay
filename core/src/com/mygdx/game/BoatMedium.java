package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BoatMedium extends ActorBeta {

    String[] boatMAnim = {"MediumBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatMAnim, 0.2f, true);

    BoatMedium()
    {
        this.setBoundaryRectangle();
        setMaxSpeed(800);
        setAnimation(idleAnim);
    }
}


