package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class BoatMedium extends ActorBeta {

    String[] boatMAnim = {"MediumBoat.png", "MediumBoat1.png", "MediumBoat2.png", "MediumBoat3.png"};
    Animation idleAnim = loadAnimationFromFiles(boatMAnim, 0.2f, true);

    float timeBetweenFire = 3;
    float fireTimer = timeBetweenFire;
    ActorBeta pathFinderBelow;
    ActorBeta pathFinderAbove;

    public BoatMedium(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 8 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 8);
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

        ableToSetIsland = false;
    }

    @Override
    public void act(float dt) {
        super.act(dt);


        if (!ScreenBeta.loseGame) {
            applyPhysics(dt);
            if (pathFinderBelow.CheckCollisionObstacle())
                upGroup = true;
            if (pathFinderAbove.CheckCollisionObstacle())
                upGroup = false;
            if (!CheckCollisionObstacle()) // the boat will move in its current direction + 90 degree if it collides with rocks
                setMotionAngle(180);
            if (getY() < 0) {
                setY(0);
                setMotionAngle(90);
            }
            if (getX() < Gdx.graphics.getWidth() * 3 / 4) {
                setSpeed(0);
                Fire(dt);
            }
        }
    }

    void Fire(float deltatime)
    {
        fireTimer -= deltatime;
        if(fireTimer <=0)
        {
            MediumBoatBall mediumBoatBall = new MediumBoatBall(getX(), getY() + getHeight() / 3, ScreenBeta.mainStage);

            fireTimer = timeBetweenFire;
        }
    }
}


