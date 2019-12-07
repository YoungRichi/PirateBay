package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class Parrot extends ActorBeta {

    String[] str = {"Parrot1.png", "Parrot2.png", "Parrot3.png"};
    Animation flyAnim = loadAnimationFromFiles(str, 0.2f, true);
    float dropOffRate = 2;
    float dropOffTimer = dropOffRate;
    //int soldierNumMax = 10;
    //int soldierNumRemaining = soldierNumMax;

    ArrayList<Soldier> soldiers;
    int soldierNum = 10;
    int soldierIndex = 0;
    boolean dropOffCompleted = false;

    public Parrot(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(flyAnim);
        setSize(Gdx.graphics.getHeight() / 4 * getWidth() / getHeight() , Gdx.graphics.getHeight() / 4);
        setBoundaryRectangle();
        setSpeed(0);
        setMotionAngle(180);

        soldiers = new ArrayList<Soldier>();
        for (int i = 0; i < soldierNum; i++)
            soldiers.add(new Soldier());
    }

    @Override
    public void act(float dt) {
        super.act(dt);


        if (!ScreenBeta.loseGame) {
            applyPhysics(dt);
            if (getX() <= Gdx.graphics.getWidth() / 4 && !dropOffCompleted) {
                setSpeed(0);
                DropOffSoldier(dt);
            }
            if (dropOffCompleted) {
                setSpeed(180);
                setMotionAngle(180);
            }
            if(getX()+getWidth() <=0 )
                remove();
            //boundToWorld();
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
