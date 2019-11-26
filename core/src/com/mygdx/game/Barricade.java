package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Barricade extends ActorBeta
{
    String[] goString = {"planeGreen0.png", "planeGreen1.png", "planeGreen2.png"};

    Animation<TextureRegion> goAnim;

    public Barricade()
    {
        goAnim = loadAnimationFromFiles(goString, 1.0f, false);
    }
}
