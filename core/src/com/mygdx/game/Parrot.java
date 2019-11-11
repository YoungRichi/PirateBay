package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Parrot extends ActorBeta {
    String[] parrotAnim = {"Parrot1.png", "Parrot2.png", "Parrot3.png"};
    Animation idleAnim = loadAnimationFromFiles(parrotAnim, 0.2f, true);

    Parrot()
    {
        Animation idleAnim = loadAnimationFromFiles(parrotAnim, 0.2f, true);
        this.setBoundaryRectangle();
        setMaxSpeed(800);
        setAnimation(idleAnim);
    }
}
