package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Pirate extends ActorBeta {

    String[] str = {"EndScreen.png", "EndScreen.png","EndScreen.png","EndScreen.png","EndScreen2.png","EndScreen.png","EndScreen2.png"};
    Animation flyAnim = loadAnimationFromFiles(str, 0.3f, true);
    public Pirate(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(flyAnim);
        setSize(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
    }
}
