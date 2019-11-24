package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    //-------------SOUND------------------------
    Sound explosion;
    Sound hit;
    Sound gameOver;
    Sound shoot;
    Sound parrotSound;
    Sound lvlCompleted;
    Sound click;



    //===========Richard Testing======================
    Parrot parrot;
    SoldierBox box;
    BoatBig bigBoat;
    BoatMedium mediumBoat;
    BoatSmall smallBoat;
    Cannon cannon;
    CannonBase cannonBase;
    Barricade barricade;
    Lives liveIcon;
    Rock rock;

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
        CheckCollision();
        parrot.moveLeft();


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
        //gameOver.play();

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
        cannonBall.setSize(WIDTH/10, HEIGHT/8);
        //cannonBall.setScale(1f);
        mainStage.addActor(cannonBall);

        maxFireDuration = 10.0f;
        fireTimer = maxFireDuration;
        isFiring = false;
        fireTrigger = false;
        isTouchDown = false;

        //===========Richard Testing======================
        parrot = new Parrot();
        parrot.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/4 );
        //parrot.setSize(WIDTH/6, HEIGHT/4);
        parrot.setScale(1f);
        mainStage.addActor(parrot);

/*        cannonBall = new CannonBall();
        cannonBall.setPosition(WIDTH/2,HEIGHT/2);
        cannonBall.setSize(WIDTH/10, HEIGHT/8);
        mainStage.addActor(cannonBall);*/

/*        box = new SoldierBox();
        box.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/8 );
        box.setSize(WIDTH/8, HEIGHT/6);
        mainStage.addActor(box);

        bigBoat = new BoatBig();
        bigBoat.setPosition(Gdx.graphics.getWidth() *3/4 , Gdx.graphics.getHeight() * 3/8);
        bigBoat.setSize(WIDTH/4, HEIGHT/3);
        mainStage.addActor(bigBoat);

        mediumBoat = new BoatMedium();
        mediumBoat.setPosition(Gdx.graphics.getWidth()*3/4 , Gdx.graphics.getHeight()/14 );
        mediumBoat.setSize(WIDTH/6, HEIGHT/4);
        mainStage.addActor(mediumBoat);

        smallBoat = new BoatSmall();
        smallBoat.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/2 );
        smallBoat.setSize(WIDTH/8, HEIGHT/8);
        mainStage.addActor(smallBoat);*/

        cannonBase = new CannonBase();
        cannonBase.setPosition(0, Gdx.graphics.getHeight()/2);
        cannonBase.setSize(WIDTH/10, HEIGHT/8);
        mainStage.addActor(cannonBase);

        cannon = new Cannon();
        cannon.setPosition(0, Gdx.graphics.getHeight()/2 + HEIGHT/12);
        cannon.setSize(WIDTH/10, HEIGHT/8);
        mainStage.addActor(cannon);

        barricade = new Barricade();
        barricade.setPosition(Gdx.graphics.getWidth() * 5/16, 0);
        barricade.setSize(WIDTH/20, HEIGHT);
        mainStage.addActor(barricade);

        liveIcon = new Lives();
        liveIcon.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight() * 13/16);
        liveIcon.setSize(WIDTH/10, HEIGHT/8);
        mainStage.addActor(liveIcon);

        rock = new Rock();
        rock.setPosition(Gdx.graphics.getWidth() * 3/4 , Gdx.graphics.getHeight()* 3/4);
        rock.setSize(WIDTH/5, HEIGHT/4);
        mainStage.addActor(rock);


        //===========Richard Testing======================

        pauseButton= new Button(arcade);
        pauseButtonTex = new ActorBeta();
        pauseButtonTex.loadTexture("PauseButton.png");
        //pauseButtonTex.setScale(0.5f);
        pauseButtonTex.setSize(WIDTH /10, HEIGHT /8);
        pauseButton.add(pauseButtonTex);
        pauseButton.setPosition(WIDTH / 18, HEIGHT * 27/ 32 );
        mainStage.addActor(pauseButton);

        resumeButton= new Button(arcade);
        resumeButtonTex = new ActorBeta();
        resumeButtonTex.loadTexture("PlayButton.png");
        //resumeButtonTex.setScale(0.5f);
        resumeButtonTex.setSize(WIDTH /10, HEIGHT /8);
        resumeButton.add(resumeButtonTex);
        resumeButton.setPosition(WIDTH / 18, HEIGHT * 27/ 32);
        mainStage.addActor(resumeButton);
        resumeButton.setVisible(false);


        //-------------------------------------Sound--------------------------------
        explosion = Gdx.audio.newSound(Gdx.files.internal("Sound/explosion.wav"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("Sound/gameOver.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("Sound/hitsound.wav"));
        lvlCompleted = Gdx.audio.newSound(Gdx.files.internal("Sound/levelCompleted.wav"));
        parrotSound = Gdx.audio.newSound(Gdx.files.internal("Sound/parrot.wav"));
        shoot = Gdx.audio.newSound(Gdx.files.internal("Sound/shoot.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
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
            click.play();

            pause();
            pauseButton.setVisible(false);
            resumeButton.setVisible(true);
        }

        if(resumeButton.isPressed())
        {
            click.play();

            resume();
            pauseButton.setVisible(true);
            resumeButton.setVisible(false);
        }
    }

    void CheckCollision()
    {
        if (cannonBall.overlaps(rock))
        {
            //pause();
            rock.preventOverlap(cannonBall);
           // parrot.moveLeft();
        }

        if (cannonBall.overlaps(parrot))
        {
            //pause();
            parrot.preventOverlap(cannonBall);
            // parrot.moveLeft();
        }

    }
}
