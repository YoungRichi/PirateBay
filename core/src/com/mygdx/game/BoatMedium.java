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

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(100);
        moveLeft();
        applyPhysics(dt);

    }

    public void moveLeft()
    {
        setSpeed(150);
        setMotionAngle(180);
    }
}


