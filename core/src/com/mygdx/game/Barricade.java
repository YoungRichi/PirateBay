package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Barricade extends ActorBeta {

    String[] str = {"Barricade.png"};

    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);

    float healthMax = 3600;
    float healthCurr;
    float damRate = 1;
    boolean isTakingDamage = false;

    public Barricade(float x, float y, Stage s) {
        super(x, y, s);
        healthCurr = healthMax;
        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() * getWidth() / getHeight(), Gdx.graphics.getHeight());
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(isTakingDamage)
        {
            healthCurr -= dt * damRate;
        }
        if(healthCurr <=0)
        {
            remove();
        }
        //boundToWorld();
    }
}
