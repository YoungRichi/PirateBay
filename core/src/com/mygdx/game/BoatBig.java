package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BoatBig extends ActorBeta {

    String[] boatAnim = {"BigBoat.png", "BigBoat1.png", "BigBoat2.png", "BigBoat3.png"};
    Animation idleAnim = loadAnimationFromFiles(boatAnim, 0.2f, true);
    int health = 2;
    float dropOffRate = 2;
    float dropOffTimer = dropOffRate;
    ActorBeta pathFinderBelow;
    ActorBeta pathFinderAbove;

    public BoatBig(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 6 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 6);
        SetUpGroup();
        setBoundaryRectangleEdited();
        setSpeed(120);
        setMotionAngle(180); // moves left

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
    public void act(float dt) {
        super.act(dt);

        pathFinderBelow.setPosition( getX() + 15, getY() - 8 );
        pathFinderAbove.setPosition( getX() + 15, getY()+ getHeight()/3);

        applyPhysics(dt);
        if(pathFinderBelow.CheckCollisionRock())
            upGroup = true;
        if(pathFinderAbove.CheckCollisionRock())
            upGroup = false;

        if(!CheckCollisionRock())
            setMotionAngle(180);

        // check collision with the edges of the screen
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
                barricade.healthCurr -= barricade.hugeDamRate;
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

