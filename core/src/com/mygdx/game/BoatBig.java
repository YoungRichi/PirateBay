package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoatBig extends ActorBeta {

    String[] boatAnim = {"BigBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatAnim, 0.2f, true);
    boolean colliding = false;

    public BoatBig(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 6 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 6);
        setBoundaryRectangleEdited();
        setSpeed(120);
        //setSpeed(0);
        setMotionAngle(180); // moves left
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(!CheckCollisionRock(90)) // the boat will move in its current direction + 90 degree if it collides with rocks
            setMotionAngle(180);
        if(getY() < 0)
        {
            setY(0);
            setMotionAngle(90);
        }

        if(!Attack())
        {

            for (Barricade barricade : ActorBeta.getListBarricade())
            {
                barricade.hugeDamage = false;
            }
        }


    }

    boolean Attack()
    {
        for (Barricade barricade : ActorBeta.getListBarricade())
        {
            if(overlaps(barricade))
            {
                preventOverlap(barricade);
                barricade.hugeDamage = true;
                return true;
            }
        }
        return false;
    }

}

