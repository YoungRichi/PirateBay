package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class NoTapZone extends ActorBeta {

    String[] str = {"CircleZone1.png", "CircleZone2.png" };
    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);
    float cannonAspectRatio;

    public NoTapZone(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        cannonAspectRatio = 525 / 275;
        setSize(Gdx.graphics.getHeight() / 5 * 525/275, Gdx.graphics.getHeight() / 5 * 525/275); // double the cannon width
        setBoundaryPolygon(8);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }
}
