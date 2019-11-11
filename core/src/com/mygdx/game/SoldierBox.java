package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class SoldierBox extends ActorBeta {

    String[] boxAnim = {"SoldierBox1.png", "SoldierBox2.png"};
    Animation idleAnim = loadAnimationFromFiles(boxAnim, 0.2f, true);

    SoldierBox()
    {
        Animation idleAnim = loadAnimationFromFiles(boxAnim, 0.2f, true);
        this.setBoundaryRectangle();
        setMaxSpeed(800);
        setAnimation(idleAnim);
    }
}
