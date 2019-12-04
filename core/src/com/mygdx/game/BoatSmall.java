package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoatSmall extends ActorBeta {
    String[] boatSAnim = {"SmallBoat.png", "SmallBoat1.png", "SmallBoat2.png"};
    Animation idleAnim = loadAnimationFromFiles(boatSAnim, 0.2f, true);
    float dropOffRate = 2;
    float dropOffTimer = dropOffRate;
    ActorBeta pathFinderBelow;
    ActorBeta pathFinderAbove;

    public BoatSmall(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangleEdited();
        SetUpGroup();
        setSpeed(120);
        setMotionAngle(180);

        pathFinderBelow = new ActorBeta(0,0, s);
        pathFinderBelow.loadTexture("cannonTesting.png");
        pathFinderBelow.setSize( getWidth() - 30, 8 );
        pathFinderBelow.setBoundaryRectangle();
        pathFinderBelow.setVisible(false);

        pathFinderAbove = new ActorBeta(0,0, s);
        pathFinderAbove.loadTexture("cannonTesting.png");
        pathFinderAbove.setSize( getWidth() - 30, 8 );
        pathFinderAbove.setBoundaryRectangle();
        pathFinderAbove.setVisible(false);
    }


    @Override
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);

        pathFinderBelow.setPosition( getX() + 15, getY() - 8 );
        pathFinderAbove.setPosition( getX() + 15, getY()+ getHeight()/3);

        if(pathFinderBelow.CheckCollisionRock())
            upGroup = true;
        if(pathFinderAbove.CheckCollisionRock())
            upGroup = false;
        if(!CheckCollisionRock())
            setMotionAngle(180);

        if(getY() < 0)
        {
            setY(0);
            setMotionAngle(90);
        }
        if(getY() > Gdx.graphics.getHeight() - getHeight())
        {
            setY(Gdx.graphics.getHeight() - getHeight());
            setMotionAngle(-90);
        }

        for (Barricade barricade : ActorBeta.getListBarricade())
        {
            if(overlaps(barricade))
            {
                preventOverlap(barricade);
                barricade.healthCurr -= barricade.smallDamRate;
            }
        }

        if(ActorBeta.getListBarricade().size() == 0 && getX() <= Gdx.graphics.getWidth() * 5 /16)
        {
            setSpeed(0);
            DropOffSoldier(dt);
        }

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
