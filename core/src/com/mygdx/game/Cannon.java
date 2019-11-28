package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Cannon extends ActorBeta {

    String[] str = {"Cannon.png"};
    String[] cannonAnim = {"Cannon_Idle3.png", "Cannon_Idle2.png", "Cannon_Idle3.png", "Cannon_Idle2.png" ,"Cannon_Idle1.png"};
    String[] chargeAnim = {"Cannon_Rest1.png", "Cannon_Rest1.png", "Cannon_Rest2.png", "Cannon_Rest3.png", "Cannon_Rest3.png"};
    String[] shootAnim = {"Cannon_Charge1.png","Cannon_Shoot1.png", "Cannon_Shoot2.png", "Cannon_Shoot3.png"};
    String[] dangerAnim = {"Cannon_Danger1.png", "Cannon_Danger1.png", "Cannon_Danger2.png", "Cannon_Danger3.png","Cannon_Danger3.png" };




    Animation idleAnim = loadAnimationFromFiles(cannonAnim, 0.5f, true);
    //Animation idleAnim = loadAnimationFromFiles(chargeAnim, 0.1f, true);
    //Animation idleAnim = loadAnimationFromFiles(shootAnim, 0.1f, true);
    //Animation idleAnim = loadAnimationFromFiles(dangerAnim, 0.1f, true);


    int lives = 3;

    public Cannon(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 8 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 8);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        //boundToWorld();
    }


}


