package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenBeta {

    Skin arcade;
    CannonBall cannonBall;
    Vector3 mouseCoord;
    Vector2 dir;
    boolean fireTrigger;
    Button pauseButton, resumeButton;
    ActorBeta pauseButtonTex, resumeButtonTex;

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

    ArrayList<Barricade> barricades;
    //ArrayList<BoatBig> boatBigs;
    //ArrayList<BoatMedium> boatMediums;

    int boatBigNumMax, boatMediumNumMax;
    int rockNumMax;



    //===========Richard Testing======================

    @Override
    public void initialize() {
        CreateLevel();
    }

    @Override
    public void update(float dt) {
        if(fireTrigger)
        {
            ControlCannonBall(dt);
        }
        CheckCollision();
        CheckPauseResumeButton();
        CheckGameState();
        livesCount.setText("x " + cannon.lives);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!fireTrigger && !isPaused)
        {
            fireTrigger = true;
            cannonBall = new CannonBall(cannon.getX() + cannon.getWidth() + 10,cannon.getY() + cannon.getHeight() - 15, mainStage);
            cannonBall.SetVelocity(screenX, (screenY - HEIGHT)*(-1));
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!cannonBall.isFiring)
            cannonBall.isFiring = true;

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

        mouseCoord = new Vector3();
        dir = new Vector2();

        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture( "Battlefield.png" );
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        fireTrigger = false; // if the player touches the screen, then the value is true


        //================================= Player ==============================================//

        cannonBase = new CannonBase(0, HEIGHT / 2, mainStage);
        cannon = new Cannon(0, HEIGHT / 2 + HEIGHT / 12, mainStage);
        barricades = new ArrayList<Barricade>();
        barricade = new Barricade(WIDTH * 5 / 16, 0, mainStage);
        barricades.add(barricade);
        liveIcon = new Lives(cannon.getX(), cannon.getY() + cannon.getHeight(), mainStage);

        //================================== Obstacles ===========================================//

        rockNumMax = 10;

        for (int i = 0; i < rockNumMax; i++)
        {
            new Rock(WIDTH / 2, HEIGHT - HEIGHT * i / 10 , mainStage); //- WIDTH * i / 30
        }

        //================================== Enemies ============================================//

        boatBigNumMax = 3;
        boatMediumNumMax = 3;

        parrot = new Parrot(WIDTH, HEIGHT / 4, mainStage);
        enemyCount++;

        bigBoat = new BoatBig(WIDTH, HEIGHT * 3 / 4 - 50, mainStage);
        enemyCount++;

        mediumBoat = new BoatMedium(WIDTH * 3 / 4, HEIGHT / 4, mainStage);
        enemyCount++;

        smallBoat = new BoatSmall(WIDTH, HEIGHT * 4 / 8, mainStage);
        enemyCount++;

        //============================== Labels =================================================//

        livesCount = new Label("x 3", arcade);
        livesCount.setPosition(liveIcon.getX() + liveIcon.getWidth() + 10, liveIcon.getY());
        mainStage.addActor(livesCount);

        winMsg = new Label(" ", arcade);
        winMsg.setPosition(WIDTH / 3, HEIGHT / 2);
        mainStage.addActor(winMsg);

        //============================== Buttons ================================================//

        pauseButtonTex = new ActorBeta();
        pauseButtonTex.loadTexture("PauseButton.png");
        float pauseBtnAR = pauseButtonTex.getWidth() / pauseButtonTex.getHeight();
        pauseButtonTex.setSize(HEIGHT / 8 * pauseBtnAR, HEIGHT /8);
        pauseButton= new Button(arcade);
        pauseButton.setSize(pauseButtonTex.getWidth() * 0.9f, pauseButtonTex.getHeight() * 0.9f);
        pauseButton.add(pauseButtonTex);
        pauseButton.setPosition(10, HEIGHT - pauseButton.getHeight() * 1.1f);
        mainStage.addActor(pauseButton);

        resumeButtonTex = new ActorBeta();
        resumeButtonTex.loadTexture("PlayButton.png");
        float resumeBtnAR = resumeButtonTex.getWidth() / resumeButtonTex.getHeight();
        resumeButtonTex.setSize(HEIGHT / 8 * resumeBtnAR, HEIGHT /8);
        resumeButton= new Button(arcade);
        resumeButton.setSize(resumeButtonTex.getWidth() * 0.9f, resumeButtonTex.getHeight() * 0.9f);
        resumeButton.add(resumeButtonTex);
        resumeButton.setPosition(10, HEIGHT - resumeButton.getHeight() * 1.1f);
        mainStage.addActor(resumeButton);
        resumeButton.setVisible(false);


        //==================================== Sound =============================================//
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
            cannonBall.fireTimer -= dt;
        }
        if(cannonBall.fireTimer <=0) // the player is able to fire again only when the cannon ball's fireTimer reached zero
        {
            fireTrigger = false;
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


        for (int i=0; i<barricades.size(); i++)
        {
            // Enemy collides with barricade
            if (parrot.overlaps(barricades.get(i)))
            {
                //Life icon goes down
                //lives--;
                //livesCount.setText("x " + lives);
                parrot.preventOverlap(barricades.get(i));
                //parrot.remove();
            }

            if (bigBoat.overlaps(barricades.get(i)))
            {
                //lives--;
                //livesCount.setText("x " + lives);
                bigBoat.preventOverlap(barricades.get(i));
                //bigBoat.remove();
            }
            if (mediumBoat.overlaps(barricades.get(i)))
            {
                //lives--;
                //livesCount.setText("x " + lives);
                mediumBoat.preventOverlap(barricades.get(i));
                //mediumBoat.remove();
            }
            if (smallBoat.overlaps(barricades.get(i)))
            {
                //lives--;
                //livesCount.setText("x " + lives);
                smallBoat.preventOverlap(barricades.get(i));
                //smallBoat.remove();
            }
        }


        //No life remaining
        if (lives <= 0)
        {
            /*PirateBay.setActiveScreen(new OverScreen());
            gameOver.play();*/
            for (int i=0; i<barricades.size();i++)
            {
                //barricades.get(i).remove();
                //barricades.remove(i);
            }

           // barricade.clear();
        }
    }

    void CheckGameState()
    {
        if(ActorBeta.getListSmallBoat().size() + ActorBeta.getListMediumBoat().size() + ActorBeta.getListBigBoat().size() == 0)
        {
            //Win
            winMsg.setText("Level Completed!");
            isPaused = true;
            cannonBall.fireTimer = 0;
        }
        if(cannon.lives == 0)
        {
            winMsg.setText("You lose!");
            isPaused = true;
            cannonBall.fireTimer = 0;
        }
    }

}
