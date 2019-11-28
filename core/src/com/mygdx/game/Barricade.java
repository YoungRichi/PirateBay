package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Barricade extends ActorBeta {

    String[] str = {"Barricade.png"};

    String[] threeQuadStr = {"Barricade_3QuadDam.png"};
    String[] halfStr = {"Barricade_HalfDam.png"};
    String[] oneQuadStr = {"Barricade_OneQuadDam.png"};

    Animation idleAnim = loadAnimationFromFiles(str, 0.1f, true);
    Animation threeQuadDamAnim = loadAnimationFromFiles(threeQuadStr, 0.1f, true);
    Animation halfDamAnim = loadAnimationFromFiles(halfStr, 0.1f, true);
    Animation oneQuadDamAnim = loadAnimationFromFiles(oneQuadStr, 0.1f, true);

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
            //System.out.println(healthCurr);
        }

        if(smallDamage)
        {
            healthCurr -= dt * smallDamRate;
            //System.out.println(healthCurr);
        }

        if(healthCurr <=0)
        {
            remove();
        }
        else if(healthCurr <= healthMax * 0.25f)
        {
            setAnimation(oneQuadDamAnim);
            setSize(Gdx.graphics.getHeight() * getWidth() / getHeight(), Gdx.graphics.getHeight());
            setBoundaryRectangle();
        }
        else if(healthCurr <= healthMax * 0.5f)
        {
            setAnimation(halfDamAnim);
            setSize(Gdx.graphics.getHeight() * getWidth() / getHeight(), Gdx.graphics.getHeight());
            setBoundaryRectangle();
        }
        else if(healthCurr <= healthMax * 0.75f)
        {
            setAnimation(threeQuadDamAnim);
            setSize(Gdx.graphics.getHeight() * getWidth() / getHeight(), Gdx.graphics.getHeight());
            setBoundaryRectangle();
        }


        //boundToWorld();
    }
}
