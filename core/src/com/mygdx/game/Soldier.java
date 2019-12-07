package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Soldier extends ActorBeta {


    String[] str = {"Soldier_Walk1.png", "Soldier_Walk2.png", "Soldier_Walk3.png", "Soldier_Walk4.png", "Soldier_Walk5.png"};
    Animation moveAnim = loadAnimationFromFiles(str, 0.2f, true);

    public Soldier() {
        setAnimation(moveAnim);
        setSize(Gdx.graphics.getHeight() / 15 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 15);
        setBoundaryRectangleEdited();
        setSpeed(12);
        setMotionAngle(180);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if (!ScreenBeta.loseGame) {
            applyPhysics(dt);
            for (NoTapZone noTapZone : ActorBeta.getListNoTapZone()) {
                if (overlaps(noTapZone)) {
                    preventOverlap(noTapZone);
                }
            }
            if (getX() <= -getWidth()) {
                remove();
                //ScreenBeta.loseGame = true;
            }
        }
    }
}
