package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BoatSmall extends ActorBeta {
    String[] boatSAnim = {"SmallBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatSAnim, 0.2f, true);

    BoatSmall()
    {
        this.setBoundaryRectangle();
        setMaxSpeed(800);
        setAnimation(idleAnim);
    }
}

