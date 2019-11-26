package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Random;

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


    int lives = 3;
    Label livesCount;

    int enemyCount;
    Label winMsg;
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
    Rock rock1, rock2, rock3, rock4, rock5, rock6;
    Rock[] rocks;

    ArrayList<Barricade> barricades;

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
        CheckGameState();
        // parrot.moveLeft();


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
        barricades = new ArrayList<Barricade>();

        camera = new OrthographicCamera();
        mouseCoord = new Vector3();
        dir = new Vector2();

        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture( "Battlefield.png" );
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cannonBall = new CannonBall();
        cannonBall.setPosition(0, 0);
        //cannonBall.setSize(WIDTH/10, HEIGHT/8);
        //cannonBall.setScale(1f);
        mainStage.addActor(cannonBall);

        maxFireDuration = 10.0f;
        fireTimer = maxFireDuration;
        isFiring = false;
        fireTrigger = false;
        isTouchDown = false;

        //===========Richard Testing======================
        parrot = new Parrot();
        parrot.setPosition(Gdx.graphics.getWidth() , Gdx.graphics.getHeight()/4);
        parrot.setSize(WIDTH/6, HEIGHT/4);
        mainStage.addActor(parrot);
        enemyCount++;

/*        box = new SoldierBox();
        box.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/8 );
        box.setSize(WIDTH/8, HEIGHT/6);
        mainStage.addActor(box);*/

        bigBoat = new BoatBig();
        bigBoat.setPosition(Gdx.graphics.getWidth() , Gdx.graphics.getHeight() * 6/8);
        bigBoat.setSize(WIDTH/8, HEIGHT/6);
        mainStage.addActor(bigBoat);
        enemyCount++;

        mediumBoat = new BoatMedium();
        mediumBoat.setPosition(Gdx.graphics.getWidth()*3/4 , Gdx.graphics.getHeight()/4 );
        mediumBoat.setSize(WIDTH/12, HEIGHT/8);
        mainStage.addActor(mediumBoat);
        enemyCount++;

        smallBoat = new BoatSmall();
        smallBoat.setPosition(Gdx.graphics.getWidth() , Gdx.graphics.getHeight()* 3/8 );
        smallBoat.setSize(WIDTH/2, HEIGHT/2);
        mainStage.addActor(smallBoat);
        enemyCount++;

        //--------------------Player---------------------------------------------------------

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
        //barricade.setSize(WIDTH/20, HEIGHT);
        mainStage.addActor(barricade);
        barricades.add(barricade);

        liveIcon = new Lives();
        liveIcon.setPosition(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight() * 13/16);
        liveIcon.setSize(WIDTH/10, HEIGHT/8);
        mainStage.addActor(liveIcon);

        //----------------Obstacle-----------------------------
        rock1 = new Rock();
        rock1.setPosition(WIDTH/2 + 600, HEIGHT/2);
        mainStage.addActor(rock1);

        rock2 = new Rock();
        rock2.setPosition(rock1.getX() - 320, rock1.getY() + 200);
        rock2.setBoundaryRectangle();
        mainStage.addActor(rock2);

        rock3 = new Rock();
        rock3.setPosition(rock1.getX() - 320, rock1.getY() - 200);
        rock3.setBoundaryRectangle();
        mainStage.addActor(rock3);

        rock4 = new Rock();
        rock4.setPosition(rock1.getX() - 500, rock1.getY());
        rock4.setBoundaryRectangle();
        mainStage.addActor(rock4);

        rock5 = new Rock();
        rock5.setPosition(rock4.getX() - 320, rock1.getY() + 200);
        rock5.setBoundaryRectangle();
        mainStage.addActor(rock5);

        rock6 = new Rock();
        rock6.setPosition(rock4.getX() - 320, rock1.getY() - 200);
        rock6.setBoundaryRectangle();
        mainStage.addActor(rock6);


        rocks = new Rock[]{rock1, rock2, rock3, rock4, rock5, rock6};





        //-----------------Labels------------------------------

        livesCount = new Label("3", arcade);
        float TextAspectRatio = livesCount.getWidth()/livesCount.getHeight();

        livesCount.setPosition(WIDTH * 5/8, HEIGHT * 7 / 8);
        livesCount.setFontScale(WIDTH/250 * TextAspectRatio);
        mainStage.addActor(livesCount);


        winMsg = new Label(" ", arcade);
        winMsg.setPosition(WIDTH / 3, HEIGHT / 2);
        winMsg.setFontScale(WIDTH/250 * TextAspectRatio);
        mainStage.addActor(winMsg);

        //---------------Buttons----------------------------
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

        if (cannonBall.overlaps(parrot))
        {
            parrot.remove();
            enemyCount--;
        }
        for (int i=0; i<barricades.size(); i++)
        {
            // Enemy collides with barricade
            if (parrot.overlaps(barricades.get(i)))
            {
                //Life icon goes down
                lives--;
                livesCount.setText(lives);
                parrot.preventOverlap(barricades.get(i));
                parrot.remove();
            }

            if (bigBoat.overlaps(barricades.get(i)))
            {
                lives--;
                livesCount.setText(lives);
                bigBoat.preventOverlap(barricades.get(i));
                bigBoat.remove();
            }
            if (mediumBoat.overlaps(barricades.get(i)))
            {
                lives--;
                livesCount.setText(lives);
                mediumBoat.preventOverlap(barricades.get(i));
                mediumBoat.remove();
            }
            if (smallBoat.overlaps(barricades.get(i)))
            {
                lives--;
                livesCount.setText(lives);
                smallBoat.preventOverlap(barricades.get(i));
                smallBoat.remove();
            }
        }


        //No life remaining
        if (lives <= 0)
        {
            /*PirateBay.setActiveScreen(new OverScreen());
            gameOver.play();*/
            for (int i=0; i<barricades.size();i++)
            {
                barricades.get(i).remove();
                barricades.remove(i);
            }

           // barricade.clear();
        }


        //=============Obstacles detection=================//
        for (int i = 0; i < rocks.length; i++)
        {
            if (rocks[i].overlaps(smallBoat) )
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
            }
        }
    }

    void CheckGameState()
    {
        if (enemyCount == 0)
        {
            //Win
            winMsg.setText("Level Completed!");
            isPaused = true;
        }

    }

}
