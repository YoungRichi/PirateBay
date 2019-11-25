package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestObstacle extends ActorBeta
{
    String[] goString = {"planeGreen0.png", "planeGreen1.png", "planeGreen2.png"};

    Animation<TextureRegion> goAnim;

    public TestObstacle()
    {
        goAnim = loadAnimationFromFiles(goString, 0, false);
    }
}
