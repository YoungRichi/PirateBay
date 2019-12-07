package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class BoatFast extends ActorBeta {

    String[] boatSAnim = {"FastBoat1.png", "FastBoat2.png"};
    Animation idleAnim = loadAnimationFromFiles(boatSAnim, 0.2f, true);
    float dropOffRate = 2;
    float dropOffTimer = dropOffRate;
    ActorBeta pathFinderBelow;
    ActorBeta pathFinderAbove;
    ArrayList<Soldier> soldiers;
    int soldierNum = 2;
    int soldierIndex = 0;
    boolean dropOffCompleted = false;


    public BoatFast(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 12 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 12);
        setBoundaryRectangleEdited();
        SetUpGroup();
        setSpeed(0);
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

        ableToSetIsland = true;

        soldiers = new ArrayList<Soldier>();
        for (int i = 0; i < soldierNum; i++)
            soldiers.add(new Soldier());
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        pathFinderBelow.setPosition( getX() + 15, getY() - 8 );
        pathFinderAbove.setPosition( getX() + 15, getY()+ getHeight()/3);

        if (!ScreenBeta.loseGame) {
            applyPhysics(dt);
            if (pathFinderBelow.CheckCollisionObstacle()|| pathFinderBelow.getY() <= 0)
                upGroup = true;
            if (pathFinderAbove.CheckCollisionObstacle()|| pathFinderAbove.getY() >= Gdx.graphics.getHeight())
                upGroup = false;
            if (!CheckCollisionObstacle())
                setMotionAngle(180);

            for (Barricade barricade : ActorBeta.getListBarricade()) {
                if (overlaps(barricade)) {
                    preventOverlap(barricade);
                    barricade.healthCurr -= barricade.smallDamRate;
                }
            }

            if (ActorBeta.getListBarricade().size() == 0 && getX() <= Gdx.graphics.getWidth() * 5 / 16) {
                setSpeed(0);
                if(!dropOffCompleted)
                    DropOffSoldier(dt);
            }
        }

    }
    void DropOffSoldier(float deltaTime)
    {
        dropOffTimer -= deltaTime;
        if(dropOffTimer <=0)
        {
            soldiers.get(soldierIndex).setSpeed(12);
            soldiers.get(soldierIndex).setPosition(getX(), getY()+ getHeight()/2);
            ScreenBeta.mainStage.addActor(soldiers.get(soldierIndex));
            if(soldierIndex < soldierNum - 1)
                soldierIndex++;
            else
                dropOffCompleted = true;

            dropOffTimer = dropOffRate;
        }
    }
}
