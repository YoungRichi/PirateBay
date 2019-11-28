package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Soldier extends ActorBeta {

    String[] soldierAnim = {"SoldierBoy.png"};
    Animation idleAnim = loadAnimationFromFiles(soldierAnim, 0.2f, true);

    public Soldier(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 4 * getWidth() / getHeight() , Gdx.graphics.getHeight() / 4);
        setBoundaryRectangle();
        setMaxSpeed(800);

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
