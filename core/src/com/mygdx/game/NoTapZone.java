package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class NoTapZone extends ActorBeta {

    String[] str = {"NoTapZone1.png", "NoTapZone2.png" };
    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);

    public NoTapZone(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 4 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 4);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }


}
