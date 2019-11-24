package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Parrot extends ActorBeta {

    String[] parrotAnim = {"Parrot1.png", "Parrot2.png", "Parrot3.png"};

    Parrot()
    {
        Animation idleAnim = loadAnimationFromFiles(parrotAnim, 0.2f, true);
        this.setBoundaryRectangle();
        setMaxSpeed(800);
        setAnimation(idleAnim);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(100);
        moveLeft();
        //accelerateAtAngle(270);
        applyPhysics(dt);
        //boundToWorld();
    }

    public void moveLeft()
    {
        setSpeed(100);
        setMotionAngle(180);
    }
}
