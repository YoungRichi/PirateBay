package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;


enum CannonState  {Idle, Shoot, chargeNormal, chargeDanger }
public class Cannon extends ActorBeta {


    String[] str = {"Cannon.png"};
    String[] idleStr = {"Cannon_Idle3.png", "Cannon_Idle2.png", "Cannon_Idle3.png", "Cannon_Idle2.png" ,"Cannon_Idle1.png"};
    String[] chargeNormalStr = {"Cannon_Rest1.png", "Cannon_Rest1.png", "Cannon_Rest2.png", "Cannon_Rest3.png", "Cannon_Rest3.png"};
    String[] chargeDangerStr = {"Cannon_Danger1.png", "Cannon_Danger1.png", "Cannon_Danger2.png", "Cannon_Danger3.png","Cannon_Danger3.png" };
    String[] shootStr = {"Cannon_Charge1.png","Cannon_Shoot1.png", "Cannon_Shoot2.png", "Cannon_Shoot3.png"};

    Animation idleAnim = loadAnimationFromFiles(idleStr, 0.5f, true);
    Animation chargeNormalAnim = loadAnimationFromFiles(chargeNormalStr, 0.1f, true);
    Animation chargeDangerAnim = loadAnimationFromFiles(chargeDangerStr, 0.1f, true);
    Animation shootAnim = loadAnimationFromFiles(shootStr, 0.5f, false);

    int lives = 3;
    CannonState cannonState;
    float toIdleTimer = 0.5f;

    public Cannon(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        //setAnimation(shootAnim);
        setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(cannonState == CannonState.Idle)
        {
            setAnimation(idleAnim);
            setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);
        }
        else if(cannonState == CannonState.Shoot)
        {
            setAnimation(shootAnim);
            setSize(Gdx.graphics.getHeight() / 7 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 7);
            toIdleTimer -= dt;
            if(toIdleTimer <= 0)
            {
                cannonState = CannonState.Idle;
                toIdleTimer = 0.5f;
            }
        }
        else if (cannonState == CannonState.chargeNormal)
        {
            setAnimation(chargeNormalAnim);
            setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);
        }
        else if (cannonState == CannonState.chargeDanger)
        {
            setAnimation(chargeDangerAnim);
            setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);

        }

        setBoundaryRectangle();
        //boundToWorld();
    }


}


