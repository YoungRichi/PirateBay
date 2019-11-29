package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Soldier extends ActorBeta {


    String[] str = {"SoldierBoy.png"};
    Animation moveAnim = loadAnimationFromFiles(str, 0.2f, true);


    public Soldier(float x, float y, Stage s) {
        super(x, y, s);

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
            //ScreenBeta.loseGame = true;
        }
    }
}
