package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class GameScreen extends ScreenBeta {

    CannonBall cannonBall;
    OrthographicCamera camera;
    Vector3 mouseCoord;
    Vector2 dir;
    float fireTimer;
    float maxFireDuration;
    boolean isFiring, fireTrigger;
    boolean isTouchDown;

    //===========Richard Testing======================
    Parrot parrot;
    SoldierBox box;
    BoatBig bigBoat;
    BoatMedium mediumBoat;
    BoatSmall smallBoat;


    //===========Richard Testing======================


    //===========Sam Testing==========================
    TestObstacle obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6;
    TestObstacle[] obstacles;
    //===========Sam Testing==========================

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    @Override
    public void initialize() {
        camera = new OrthographicCamera();
        mouseCoord = new Vector3();
        dir = new Vector2();

        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture( "Battlefield.png" );
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cannonBall = new CannonBall();
        cannonBall.setPosition(0, 0);
        cannonBall.setScale(0.3f);
        mainStage.addActor(cannonBall);

        maxFireDuration = 10.0f;
        fireTimer = maxFireDuration;
        isFiring = false;
        fireTrigger = false;
        isTouchDown = false;

        //===========Richard Testing======================
        parrot = new Parrot();
        parrot.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/4 );
        parrot.setScale(0.5f);
        //mainStage.addActor(parrot);

        box = new SoldierBox();
        box.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/8 );
        box.setScale(0.5f);
        //mainStage.addActor(box);

        bigBoat = new BoatBig();
        bigBoat.setPosition(WIDTH , HEIGHT / 10 - 250);
        bigBoat.setScale(0.15f);
        mainStage.addActor(bigBoat);

        mediumBoat = new BoatMedium();
        mediumBoat.setPosition(WIDTH , HEIGHT / 6);
        mediumBoat.setScale(0.13f);
        mainStage.addActor(mediumBoat);

        smallBoat = new BoatSmall();
        smallBoat.setPosition(WIDTH , HEIGHT / 4);
        smallBoat.setScale(0.1f);
        mainStage.addActor(smallBoat);
        //===========Richard Testing======================


        //===========Sam Testing==========================//
        obstacle1 = new TestObstacle();
        obstacle1.setPosition(WIDTH/2 + 600, HEIGHT/2);
        obstacle1.setBoundaryRectangle();
        mainStage.addActor(obstacle1);

        obstacle2 = new TestObstacle();
        obstacle2.setPosition(obstacle1.getX() - 320, obstacle1.getY() + 200);
        obstacle2.setBoundaryRectangle();
        mainStage.addActor(obstacle2);

        obstacle3 = new TestObstacle();
        obstacle3.setPosition(obstacle1.getX() - 320, obstacle1.getY() - 200);
        obstacle3.setBoundaryRectangle();
        mainStage.addActor(obstacle3);

        obstacle4 = new TestObstacle();
        obstacle4.setPosition(obstacle1.getX() - 500, obstacle1.getY());
        obstacle4.setBoundaryRectangle();
        mainStage.addActor(obstacle4);

        obstacle5 = new TestObstacle();
        obstacle5.setPosition(obstacle4.getX() - 320, obstacle1.getY() + 200);
        obstacle5.setBoundaryRectangle();
        mainStage.addActor(obstacle5);

        obstacle6 = new TestObstacle();
        obstacle6.setPosition(obstacle4.getX() - 320, obstacle1.getY() - 200);
        obstacle6.setBoundaryRectangle();
        mainStage.addActor(obstacle6);


        obstacles = new TestObstacle[]{obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6};
        //===========Sam Testing==========================//

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

        //=============Collision detection=================//
        for (int i = 0; i < obstacles.length; i++)
        {
            if (obstacles[i].overlaps(smallBoat) && smallBoat.boatTimer < 0)
            {
                smallBoat.boatTimer = 0.5f;
                Random randomInt = new Random();
                int rollResult = randomInt.nextInt(2);
                if(rollResult == 0)
                {
                    smallBoat.enemyState = BoatSmall.EnemyState.GoUp;
                }
                if(rollResult == 1)
                {
                    smallBoat.enemyState = BoatSmall.EnemyState.GoDown;
                }

                //if(obstacles[i].getY() < smallBoat.getY())
                //    smallBoat.enemyState = BoatSmall.EnemyState.GoDown;
                //if(obstacles[i].getY() >= smallBoat.getY())
                //    smallBoat.enemyState = BoatSmall.EnemyState.GoUp;

                //obstacles[i].preventOverlap(smallBoat);
            }

            if (obstacles[i].overlaps(mediumBoat) && mediumBoat.boatTimer < 0)
            {
                mediumBoat.boatTimer = 1f;
                Random randomInt = new Random();
                int rollResult = randomInt.nextInt(2);
                if(rollResult == 0)
                {
                    mediumBoat.enemyState = BoatMedium.EnemyState.GoUp;
                }
                if(rollResult == 1)
                {
                    mediumBoat.enemyState = BoatMedium.EnemyState.GoDown;
                }
            }

            if (obstacles[i].overlaps(bigBoat) && bigBoat.boatTimer < 0)
            {
                bigBoat.boatTimer = 2f;
                Random randomInt = new Random();
                int rollResult = randomInt.nextInt(2);
                if(rollResult == 0)
                {
                    bigBoat.enemyState = BoatBig.EnemyState.GoUp;
                }
                if(rollResult == 1)
                {
                    bigBoat.enemyState = BoatBig.EnemyState.GoDown;
                }
            }
            //=============Collision detection=================//
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

        //================RICHARD TESTING=====================
        PirateBay.setActiveScreen(new OverScreen());

        //============================================

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
