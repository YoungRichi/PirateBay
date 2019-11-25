package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class BoatSmall extends ActorBeta {
    String[] boatSAnim = {"SmallBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatSAnim, 0.2f, true);


    BoatSmall()
    {
        super();
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

    public void moveUp()
    {
        setSpeed(150);
        setMotionAngle(90);
    }
}

