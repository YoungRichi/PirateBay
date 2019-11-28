package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoatSmall extends ActorBeta {
    String[] boatSAnim = {"SmallBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatSAnim, 0.2f, true);
    float dropOffRate = 2;
    float dropOffTimer = dropOffRate;

    public BoatSmall(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangleEdited();
        setSpeed(120);
        //setSpeed(0);
        setMotionAngle(180);
    }


    @Override
    public void act(float dt)
    {
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
                barricade.smallDamage = false;
            }
        }
        // stop moving when the barricade is down
        if(ActorBeta.getListBarricade().size() == 0)
        {
            setSpeed(0);
            DropOffSoldier(dt);
        }

    }

    boolean Attack()
    {
        for (Barricade barricade : ActorBeta.getListBarricade())
        {
            if(overlaps(barricade))
            {
                preventOverlap(barricade);
                barricade.smallDamage = true;
                return true;
            }
        }
        return false;
    }
    void DropOffSoldier(float deltaTime)
    {
        dropOffTimer -= deltaTime;
        if(dropOffTimer <=0)
        {
            new Soldier(getX(), getY(), ScreenBeta.mainStage);
            dropOffTimer = dropOffRate;
        }
    }
}
