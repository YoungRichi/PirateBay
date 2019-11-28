package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Parrot extends ActorBeta {

    String[] str = {"Parrot1.png", "Parrot2.png", "Parrot3.png"};
    Animation flyAnim = loadAnimationFromFiles(str, 0.2f, true);
    float dropOffRate = 2;
    float dropOffTimer = dropOffRate;
    int soldierNumMax = 10;
    int soldierNumRemaining = soldierNumMax;

    public Parrot(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(flyAnim);
        setSize(Gdx.graphics.getHeight() / 4 * getWidth() / getHeight() , Gdx.graphics.getHeight() / 4);
        setBoundaryRectangle();
        setSpeed(100);
        setMotionAngle(180);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(getX() <= Gdx.graphics.getWidth() / 4 && soldierNumRemaining > 0)
        {
            setSpeed(0);
            DropOffSoldier(dt);
        }
        if(soldierNumRemaining == 0)
        {
            setSpeed(180);
            setMotionAngle(180);
        }
        //boundToWorld();
    }
    void DropOffSoldier(float deltaTime)
    {
        dropOffTimer -= deltaTime;
        if(dropOffTimer <=0)
        {
            new Soldier(getX(), getY() - getHeight() * 0.5f, ScreenBeta.mainStage);
            soldierNumRemaining --;
            dropOffTimer = dropOffRate;
        }
    }
}
