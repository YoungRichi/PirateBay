package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class Island extends ActorBeta {

    String[] idleStr = {"Island_Hide1.png"};
    String[] hiddenStr = {"Island_Hide1.png", "Island_Hide2.png", "Island_Hide3.png", "Island_Hide4.png"};

    Animation idleAnim = loadAnimationFromFiles(idleStr, 0.6f, true);
    Animation hiddenAnim = loadAnimationFromFiles(hiddenStr, 0.1f, true);
    boolean hidden = false; //
    boolean setToHide = false;
    float hideTimer = 1; // time from playing hiddenAnim to actual hidden
    float goToHideTimer = 0.2f; // time from setToHide is set to time of playing hiddenAnim
    //boolean isGoToHideTimerSet = false;
    float hiddenDurationMax = 10; // hidden time
    float hiddenDuration = hiddenDurationMax;

    public Island(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(idleAnim);
        setSize(Gdx.graphics.getHeight() / 7 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 7);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        if(hidden)
        {
            setAnimation(hiddenAnim);
            setSize(Gdx.graphics.getHeight() / 7 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 7);
            setBoundaryRectangle();
            hideTimer -= dt;
            if(hideTimer <=0)
            {
                setVisible(false);
                setSize(0,0);
                setBoundaryRectangle();
                hiddenDuration -= dt;
                if(hiddenDuration <= 0)
                {
                    hidden = false;
                    hiddenDuration = hiddenDurationMax;
                    hideTimer = 1;
                    setToHide = false;
                    //isGoToHideTimerSet = false;
                }
            }
        }
        else
        {
            setAnimation(idleAnim);
            setVisible(true);
            setSize(Gdx.graphics.getHeight() / 7 * getWidth() / getHeight(), Gdx.graphics.getHeight() / 7);
            setBoundaryRectangle();
        }
        //if(setToHide && !isGoToHideTimerSet) // setToHide is set either to false or true by other objects
        //{
            //Random rand = new Random();
            //goToHideTimer = rand.nextInt(5) + 2 ;
            //isGoToHideTimerSet = true;
        //}
        //if(isGoToHideTimerSet)
        if(setToHide)
        {
            goToHideTimer -= dt;
            if(goToHideTimer <= 0)
            {
                hidden = true;
            }
        }

        //boundToWorld();
    }


}
