package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameScreen extends ScreenBeta {

    Skin arcade;
    CannonBall cannonBall;
    OrthographicCamera camera;
    Vector3 mouseCoord;
    Vector2 dir;
    float fireTimer;
    float maxFireDuration;
    boolean isFiring, fireTrigger;
    boolean isTouchDown;
    Button pauseButton;
    ActorBeta pauseButtonTex;
    Button resumeButton;
    ActorBeta resumeButtonTex;

    //===========Richard Testing======================
    Parrot parrot;
    SoldierBox box;
    BoatBig bigBoat;
    BoatMedium mediumBoat;
    BoatSmall smallBoat;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();


    //===========Richard Testing======================

    @Override
    public void initialize() {
        CreateLevel();
    }

    @Override
    public void update(float dt) {
        ControlCannonBall(dt);
        CheckPauseResumeButton();
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
       // PirateBay.setActiveScreen(new OverScreen());

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

    @Override
    public void pause() {
        super.pause();
        isPaused = true;
    }

    @Override
    public void resume() {
        super.resume();
        isPaused = false;
    }

    void CreateLevel()
    {
        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));


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
        mainStage.addActor(parrot);

        box = new SoldierBox();
        box.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/8 );
        box.setScale(0.5f);
        mainStage.addActor(box);

        bigBoat = new BoatBig();
        bigBoat.setPosition(Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/10 );
        bigBoat.setScale(0.35f);
        mainStage.addActor(bigBoat);

        mediumBoat = new BoatMedium();
        mediumBoat.setPosition(Gdx.graphics.getWidth()/16 , Gdx.graphics.getHeight()/15 );
        mediumBoat.setScale(0.35f);
        mainStage.addActor(mediumBoat);

        smallBoat = new BoatSmall();
        smallBoat.setPosition(Gdx.graphics.getWidth()/4 , Gdx.graphics.getHeight()/20 );
        smallBoat.setScale(0.35f);
        mainStage.addActor(smallBoat);
        //===========Richard Testing======================

        pauseButton= new Button(arcade);
        pauseButtonTex = new ActorBeta();
        pauseButtonTex.loadTexture("PauseButton.png");
        pauseButtonTex.setScale(0.5f);
        pauseButton.add(pauseButtonTex);
        pauseButton.setPosition(WIDTH / 18, HEIGHT - HEIGHT / 9);
        mainStage.addActor(pauseButton);

        resumeButton= new Button(arcade);
        resumeButtonTex = new ActorBeta();
        resumeButtonTex.loadTexture("PlayButton.png");
        resumeButtonTex.setScale(0.5f);
        resumeButton.add(resumeButtonTex);
        resumeButton.setPosition(WIDTH / 18, HEIGHT - HEIGHT / 9);
        mainStage.addActor(resumeButton);
        resumeButton.setVisible(false);
    }

    void ControlCannonBall(float dt)
    {
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


    void CheckPauseResumeButton()
    {
        if(pauseButton.isPressed())
        {
            pause();
            pauseButton.setVisible(false);
            resumeButton.setVisible(true);
        }

        if(resumeButton.isPressed())
        {
            resume();
            pauseButton.setVisible(true);
            resumeButton.setVisible(false);
        }
    }
}
