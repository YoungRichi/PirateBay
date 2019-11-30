package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;


enum CannonState  {Idle, Shoot, chargeNormal, chargeWarning, chargeDanger }
public class Cannon extends ActorBeta {

    String[] idleStr = {"Cannon_Idle3.png", "Cannon_Idle2.png", "Cannon_Idle3.png", "Cannon_Idle2.png" ,"Cannon_Idle1.png"};
    String[] chargeNormalStr = {"Cannon_Rest1.png", "Cannon_Rest1.png", "Cannon_Rest2.png", "Cannon_Rest3.png", "Cannon_Rest3.png"};
    String[] chargeWarningStr = {"Cannon_Warn1.png", "Cannon_Warn2.png", "Cannon_Warn3.png", "Cannon_Warn2.png"};
    String[] chargeDangerStr = {"Cannon_Danger1.png", "Cannon_Danger3.png","Cannon_Danger2.png" };
    String[] shootStr = {"Cannon_Charge1.png","Cannon_Shoot1.png", "Cannon_Shoot2.png", "Cannon_Shoot3.png","Cannon_Shoot3.png","Cannon_Shoot3.png","Cannon_Shoot3.png"};

    Animation idleAnim = loadAnimationFromFiles(idleStr, 0.5f, true);
    Animation chargeNormalAnim = loadAnimationFromFiles(chargeNormalStr, 0.1f, true);
    Animation chargeWarningAnim = loadAnimationFromFiles(chargeWarningStr, 0.1f, true);
    Animation chargeDangerAnim = loadAnimationFromFiles(chargeDangerStr, 0.1f, true);
    Animation shootAnim = loadAnimationFromFiles(shootStr, 0.3f, true);

    int lives = 3;
    CannonState cannonState;
    float toIdleTimer = 1.0f;
    Vector2 fireDir = Vector2.Zero;

    public Cannon(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        if(cannonState == CannonState.Idle)
        {
            setAnimation(idleAnim);
            setRotation(0);
            setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);
        }
        else if(cannonState == CannonState.Shoot)
        {
            setAnimation(shootAnim);
            setRotation(fireDir.angle());
            setSize(Gdx.graphics.getHeight() / 7 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 7);
            setOrigin(getWidth()/2, getHeight()/2);
            toIdleTimer -= dt;
            if(toIdleTimer <= 0)
            {
                cannonState = CannonState.Idle;
                toIdleTimer = 1.0f;
            }
        }
        else
        {
            if (cannonState == CannonState.chargeNormal)
                setAnimation(chargeNormalAnim);
            else if (cannonState == CannonState.chargeWarning)
                setAnimation(chargeWarningAnim);
            else if (cannonState == CannonState.chargeDanger)
                setAnimation(chargeDangerAnim);
            setRotation(fireDir.angle());
            setSize(Gdx.graphics.getHeight() / 10 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 10);
            setOrigin(getWidth()/2, getHeight()/2);
        }

        setBoundaryRectangle();
        //boundToWorld();
    }


}


