package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BoatSmall extends ActorBeta {
    String[] boatSAnim = {"SmallBoat.png"};
    Animation idleAnim = loadAnimationFromFiles(boatSAnim, 0.2f, true);

    Animation<TextureRegion>goAnim;
    enum EnemyState
    {
        GoLeft,
        GoUp,
        GoDown,
        DeployTroops,
        Death
    }
    public BoatSmall.EnemyState enemyState;
    public float boatTimer = 0;
    public int forceLeftCount = 0;


    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    BoatSmall()
    {
        //setMotionAngle(180);
        //this.setScale(this.getScaleX() * 0.1f, this.getScaleY() * 0.1f);
        //this.setPosition(WIDTH, HEIGHT/2);
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

            case DeployTroops:
                DeployTroops();
                break;

            default:
                break;
        }

        boatTimer -= dt;
        if(boatTimer <= 0 && enemyState != EnemyState.DeployTroops)
            enemyState = EnemyState.GoLeft;
    }

    void GoLeft()
    {
        setAnimation(idleAnim);
        moveBy(-2.5f, 0);
    }

    void GoUp()
    {
        setAnimation(idleAnim);
        moveBy(0, 2.5f);
    }

    void GoDown()
    {
        setAnimation(idleAnim);
        moveBy(0, -2.5f);
    }

    void DeployTroops()
    {
        setAnimation(idleAnim);
    }
}

