package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;

public class CannonBall extends ActorBeta {

    float maxFireDuration = 10.0f;
    float fireTimer = maxFireDuration;
    boolean isFiring = false;

    public CannonBall(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("Cannonball.png");
        setSize(Gdx.graphics.getHeight() / 25 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 25);
        setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);

        if(ScreenBeta.isPaused) // if the game is paused, then stop moving
        {
            moveBy(0, 0);
            rotateBy(0);
            setOrigin(getWidth()/2, getHeight()/2);
        }
        else if(fireTimer > 0 && isFiring)
        {
            moveBy(GetVelocity().x, GetVelocity().y);
            rotateBy(10);
            setOrigin(getWidth()/2, getHeight()/2);
        }

        if(fireTimer <=0)
        {
            isFiring = false;
            fireTimer = maxFireDuration;
            remove();
            ExplosionEffect explosionEffect = new ExplosionEffect(getX(), getY(), ScreenBeta.mainStage);
        }

        // Check collisions
        for (Rock rock : ActorBeta.getListRock())
        {
            if(overlaps(rock))
            {
                Vector2 offset = preventOverlap(rock);
                if (offset != null) {
                    if (Math.abs(offset.x) > Math.abs(offset.y))
                        SetVelocityXY(GetVelocity().x * (-1), GetVelocity().y);
                    else
                        SetVelocityXY(GetVelocity().x, GetVelocity().y * (-1));
                }
            }
        }

        for (Soldier soldier : ActorBeta.getListSoldier())
        {
            if(overlaps(soldier))
            {
                soldier.remove();
                //ExplosionEffect explosionEffect = new ExplosionEffect(soldier.getX(), soldier.getY(), ScreenBeta.mainStage);
            }
        }

        for (BoatSmall boatSmall : ActorBeta.getListSmallBoat())
        {
            if(overlaps(boatSmall))
            {
                boatSmall.remove();
                ExplosionEffect explosionEffect = new ExplosionEffect(boatSmall.getX(), boatSmall.getY(), ScreenBeta.mainStage);
            }
        }

        for (BoatMedium boatMedium : ActorBeta.getListMediumBoat())
        {
            if(overlaps(boatMedium))
            {
                boatMedium.remove();
                ExplosionEffect explosionEffect = new ExplosionEffect(boatMedium.getX(), boatMedium.getY(), ScreenBeta.mainStage);
            }
        }

        for (BoatBig boatBig : ActorBeta.getListBigBoat())
        {
            if(overlaps(boatBig))
            {
                Vector2 offset = preventOverlap(boatBig);
                if (offset != null) {
                    if (Math.abs(offset.x) > Math.abs(offset.y))
                        SetVelocityXY(GetVelocity().x * (-1), GetVelocity().y);
                    else
                        SetVelocityXY(GetVelocity().x, GetVelocity().y * (-1));
                }

                setMotionAngle(getMotionAngle() + 180);
                Vector2 boatBigPos  = new Vector2(  boatBig.getX(),  boatBig.getY() );
                Vector2 cannonBallPos = new Vector2( getX(), getY() );
                Vector2 hitVector = boatBigPos.sub( cannonBallPos );
                boatBig.addAction(Actions.sequence(Actions.moveBy(hitVector.x, hitVector.y),
                        Actions.delay(0.1f), Actions.moveBy(hitVector.x * -1, hitVector.y * -1 )));
                boatBig.health --;
                if(boatBig.health == 0)
                {
                    boatBig.remove();
                }
                ExplosionEffect explosionEffect = new ExplosionEffect(boatBig.getX(), boatBig.getY(), ScreenBeta.mainStage);
            }
        }

        for (Cannon cannon : ActorBeta.getListCannon())
        {
            if(overlaps(cannon))
            {
                //prevent overlaping multiple times
                preventOverlap(cannon);
                setMotionAngle(getMotionAngle() + 180);
                Vector2 cannonPos  = new Vector2(  cannon.getX(),  cannon.getY() );
                Vector2 cannonBallPos = new Vector2( getX(), getY() );
                Vector2 hitVector = cannonPos.sub( cannonBallPos );
                cannon.addAction(Actions.sequence(Actions.moveBy(hitVector.x, hitVector.y),
                        Actions.delay(0.1f), Actions.moveBy(hitVector.x * -1, hitVector.y * -1 )));
                cannon.lives --;
                fireTimer = 0; // the player is able to fire again only when the cannon ball's fireTimer reached zero
                ExplosionEffect explosionEffect = new ExplosionEffect(getX(), getY(), ScreenBeta.mainStage);
            }
            else if(!isFiring)
            {
                if(0 < fireTimer && fireTimer < 7)
                    cannon.cannonState = CannonState.chargeDanger;
                else if(fireTimer < 10)
                    cannon.cannonState = CannonState.chargeNormal;
                else
                    cannon.cannonState = CannonState.Idle;
            }
        }

        // check collision with the edge of the screen
        if(getY() > Gdx.graphics.getHeight()- getHeight())
        {
            setY(Gdx.graphics.getHeight() - getHeight());
            SetVelocityXY(GetVelocity().x, GetVelocity().y * (-1));
        }
        if(getY() < 0)
        {
            setY(0);
            SetVelocityXY(GetVelocity().x, GetVelocity().y * (-1));
        }
        if(getX() < 0)
        {
            setX(0);
            SetVelocityXY(GetVelocity().x * (-1), GetVelocity().y);
        }
        if(getX() > Gdx.graphics.getWidth() - getWidth())
        {
            setX(Gdx.graphics.getWidth() - getWidth());
            SetVelocityXY(GetVelocity().x * (-1), GetVelocity().y);
        }
        //boundToWorld();
    }
}
