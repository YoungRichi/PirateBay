package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;

public class BoatMedium extends ActorBeta {

    String[] boatMAnim = {"MediumBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatMAnim, 0.2f, true);

    enum EnemyState
    {
        GoLeft,
        GoUp,
        GoDown,
        DeployTroops,
        Death
    }
    public EnemyState enemyState;
    public float boatTimer = 0;


    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    BoatMedium()
    {
        this.setBoundaryRectangle();

        enemyState = EnemyState.GoLeft;
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);

        switch (enemyState)
        {
            case GoLeft:
                GoLeft();
                break;

            case GoUp:
                GoUp();
                break;

            case GoDown:
                GoDown();
                break;

            default:
                break;
        }

        boatTimer -= dt;
        if(boatTimer <= 0)
            enemyState = EnemyState.GoLeft;
    }

    void GoLeft()
    {
        setAnimation(idleAnim);
        moveBy(-2, 0);
    }

    void GoUp()
    {
        setAnimation(idleAnim);
        moveBy(0, 2);
    }

    void GoDown()
    {
        setAnimation(idleAnim);
        moveBy(0, -2);
    }
}


