package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Soldier extends ActorBeta {

<<<<<<< Updated upstream
    String[] str = {"Soldier.jpg"};
    Animation moveAnim = loadAnimationFromFiles(str, 0.2f, true);
=======
<<<<<<< HEAD
    String[] str = {"Soldier.jpg"};
    Animation moveAnim = loadAnimationFromFiles(str, 0.2f, true);
=======
    String[] soldierAnim = {"SoldierBoy.png"};
    Animation idleAnim = loadAnimationFromFiles(soldierAnim, 0.2f, true);
>>>>>>> d6a658cecc25f0454e361009486267b801d5f23c
>>>>>>> Stashed changes

    public Soldier(float x, float y, Stage s) {
        super(x, y, s);

<<<<<<< Updated upstream
=======
<<<<<<< HEAD
>>>>>>> Stashed changes
        setAnimation(moveAnim);
        setSize(Gdx.graphics.getHeight() / 15 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 15);
        setBoundaryRectangleEdited();
        setSpeed(12);
        setMotionAngle(180);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
        if(getX() <= -getWidth())
        {
            remove();
            ScreenBeta.loseGame = true;
        }
<<<<<<< Updated upstream
=======
=======
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
>>>>>>> d6a658cecc25f0454e361009486267b801d5f23c
>>>>>>> Stashed changes
    }
}
