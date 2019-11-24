package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BoatBig extends ActorBeta {


    BoatBig()
    {
        String[] boatAnim = {"BigBoat.png"};
        Animation idleAnim = loadAnimationFromFiles(boatAnim, 0.2f, true);
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
        setSpeed(120);
        setMotionAngle(180);
    }

}

