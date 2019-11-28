package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Barricade extends ActorBeta {

    String[] str = {"Barricade.png"};
    String[] str75 = {"Barricade75.png"};
    String[] str50 = {"Barricade50.png"};
    String[] str25 = {"Barricade25.png"};


    Animation idleAnim = loadAnimationFromFiles(str75, 0.1f, true);

    float healthMax = 100;
    float healthCurr;

    // variables indicating Big Boat attack
    float hugeDamRate = 1.5f;
    boolean hugeDamage = false;

    // variables indicating Medium Boat attack
    float mediumDamRate = 1.2f;

    // variables indicating Small Boat attack
    float smallDamRate = 1.0f;
    boolean smallDamage = false;

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

        // is under Big Boat attack
        if(hugeDamage)
        {
            healthCurr -= dt * hugeDamRate;
            System.out.println(healthCurr);
        }

        if(smallDamage)
        {
            healthCurr -= dt * smallDamRate;
            System.out.println(healthCurr);
        }

        if(healthCurr <=0)
        {
            remove();
        }
        //boundToWorld();
    }
}
