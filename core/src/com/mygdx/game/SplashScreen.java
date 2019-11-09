package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class SplashScreen extends ScreenBeta {
    CannonBall cannonBall;
    OrthographicCamera camera;
    Vector3 mouseCoord;
    Vector2 dir;
    float fireTimer;
    float maxFireDuration;
    boolean isFiring, fireTrigger;
    boolean isTouchDown;

    @Override
    public void initialize() {
        camera = new OrthographicCamera();
        mouseCoord = new Vector3();
        dir = new Vector2();

        ActorBeta background = new ActorBeta(-100, 0, mainStage);
        background.loadTexture( "background_4.jpg" );
        background.setSize(Gdx.graphics.getWidth() * 2f,Gdx.graphics.getHeight() * 2f);

        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cannonBall = new CannonBall();
        cannonBall.setPosition(0, 0);
        cannonBall.setScale(1.0f);
        mainStage.addActor(cannonBall);

        maxFireDuration = 10.0f;
        fireTimer = maxFireDuration;
        isFiring = false;
        fireTrigger = false;
        isTouchDown = false;
    }

    @Override
    public void update(float dt) {
        if(fireTrigger)
        {
            fireTimer -= dt;
        }
        if(fireTimer > 0 && isFiring)
            cannonBall.moveBy(cannonBall.GetVelocity().x, cannonBall.GetVelocity().y);
        if(fireTimer <= 0 && !isTouchDown)
        {
            fireTrigger = false;
            isFiring = false;
            fireTimer = maxFireDuration;
        }
        if(cannonBall.getY() > Gdx.graphics.getHeight()- cannonBall.getHeight())
        {
            cannonBall.setY(Gdx.graphics.getHeight() - cannonBall.getHeight());
            cannonBall.SetVelocityXY(cannonBall.GetVelocity().x, cannonBall.GetVelocity().y * (-1));
        }
        if(cannonBall.getY() < 0)
        {
            cannonBall.setY(0);
            cannonBall.SetVelocityXY(cannonBall.GetVelocity().x, cannonBall.GetVelocity().y * (-1));
        }
        if(cannonBall.getX() < 0)
        {
            cannonBall.setX(0);
            cannonBall.SetVelocityXY(cannonBall.GetVelocity().x * (-1), cannonBall.GetVelocity().y);
        }
        if(cannonBall.getX() > Gdx.graphics.getWidth() - cannonBall.getWidth())
        {
            cannonBall.setX(Gdx.graphics.getWidth() - cannonBall.getWidth());
            cannonBall.SetVelocityXY(cannonBall.GetVelocity().x * (-1), cannonBall.GetVelocity().y);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!fireTrigger)
        {
            fireTrigger = true;
            cannonBall.SetVelocity(screenX, (screenY - Gdx.graphics.getHeight())*(-1));
        }
        isTouchDown = true;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!isFiring)
        isFiring = true;
        System.out.println(fireTimer);
        isTouchDown = false;
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
