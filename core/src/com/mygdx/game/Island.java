package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Island extends ActorBeta {
    String[] str = {"Island_Hide1.png", "Island_Hide2.png", "Island_Hide3.png", "Island_Hide4.png"};
    Animation idleAnim = loadAnimationFromFiles(str, 0.6f, true);

    public Island(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 7 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 7);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }


}
