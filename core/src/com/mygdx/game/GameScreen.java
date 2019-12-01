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
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenBeta {

    //=============== Player ========================//
    CannonBall cannonBall;
    Ball ball;
    Vector3 mouseCoord;
    Vector2 dir;
    boolean fireTrigger; // indicate whether the cannon ball is loaded
    CannonBase cannonBase;
    Cannon cannon;
    Lives liveIcon;

    //================ UI ============================//
    Skin arcade;
    Button pauseButton, resumeButton;
    ActorBeta pauseButtonTex, resumeButtonTex;
    Label livesCount;
    Label winMsg;

    //================= Sounds =========================//
    Sound explosion, hit, gameOver, shoot, parrotSound, lvlCompleted, click;

    //================= Enemies =======================//

    int boatBigNumMax, boatMediumNumMax, boatSmallNumMax;
    int rockNumMax;

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
        CheckPauseResumeButton();
        CheckGameState();
        livesCount.setText("x " + cannon.lives);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!fireTrigger)
        {
            Vector2 cannonOriPos = new Vector2(cannon.getX() + cannon.getWidth() / 2,cannon.getY()+ cannon.getHeight() / 2);
            Vector2 touchPos = new Vector2(screenX, (screenY - HEIGHT)*(-1));
            Vector2 uIButtonPos = new Vector2(- pauseButtonTex.getX() + pauseButtonTex.getWidth()/2, - pauseButtonTex.getY() + pauseButtonTex.getHeight()/2);
            cannon.fireDir = touchPos.sub(cannonOriPos);
            Vector2 touchPos1 = new Vector2(screenX, screenY);
            Vector2 buttonZone = touchPos1.sub(uIButtonPos);

            if(cannon.fireDir.len() > cannon.getWidth()&&
                    buttonZone.len2() > 2f * (pauseButtonTex.getWidth()/2 * pauseButtonTex.getWidth()/2 ) + pauseButtonTex.getHeight()/2 * pauseButtonTex.getHeight()/2) // able to load cannon ball only when the touch down position is outside of the range
            {
                fireTrigger = true;
                cannon.setRotation(cannon.fireDir.angle());
                cannon.setOrigin(cannon.getWidth()/2, cannon.getHeight()/2);

                cannonBall = new CannonBall(cannon.getX()+ cannon.getWidth()/2 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth(),
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT / 50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth(), mainStage);
                cannonBall.SetVelocity(screenX, (screenY - HEIGHT)*(-1) - cannonBall.getHeight()/2);
                cannonBall.setVisible(false);
                // HEIGHT / 50 is half of the cannon ball height

                ball = new Ball(cannon.getX()+ cannon.getWidth()/2 - HEIGHT/50 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth()/3,
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT/50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth()/3, mainStage);
            }
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(fireTrigger)
        {
            if(!cannonBall.isFiring)
            {
                cannonBall.isFiring = true; // the cannon start firing
                if(cannon.cannonState != CannonState.Idle) // Not allow to change to shoot anim if the cannon is in idle state
                    cannon.cannonState = CannonState.Shoot;
                cannonBall.setVisible(true);
            }
        }

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
        cannonBase = new CannonBase();
        cannonBase.setPosition(cannonBase.getWidth()/2, HEIGHT/3);
        mainStage.addActor(cannonBase);
        cannon = new Cannon(cannonBase.getX() , cannonBase.getY() + cannonBase.getHeight()*3/4 , mainStage);
        new Barricade(WIDTH * 5 / 16, 0, mainStage);
        new NoTapZone(cannon.getX()+ cannon.getWidth()/2 - cannon.getWidth(), cannon.getY() + cannon.getHeight()/2 - cannon.getWidth() , mainStage);
        liveIcon = new Lives(cannon.getX(), cannon.getY() + cannon.getHeight(), mainStage);

        //================================== Obstacles ===========================================//

        rockNumMax = 10;

        for (int i = 0; i < rockNumMax; i++)
        {
            new Rock(WIDTH / 2, HEIGHT * 3/4 - HEIGHT * i / 15 , mainStage); //- WIDTH * i / 30
        }

        new Rock(WIDTH / 2 + HEIGHT/15 + 20, HEIGHT /10 * 9 , mainStage);
        new Rock(WIDTH / 2 + HEIGHT/15 + 20, HEIGHT /4 - 50, mainStage);
        //================================== Enemies ============================================//

        boatBigNumMax = 5;
        boatMediumNumMax = 3;
        boatSmallNumMax = 5;

        for (int i = 0; i < boatBigNumMax; i++)
        {
            new BoatBig(WIDTH, HEIGHT/10 * i, mainStage);
        }
        new BoatSmall(WIDTH, HEIGHT/2, mainStage);
        new BoatSmall(WIDTH, HEIGHT/3, mainStage);
        new BoatMedium(WIDTH, HEIGHT/3, mainStage);
        new BoatMedium(WIDTH, HEIGHT/2, mainStage);

        //============================== Labels =================================================//

        float refResolutionFactor = HEIGHT/480;

        livesCount = new Label("x 3", arcade);
        livesCount.setAlignment(Align.center);
        livesCount.setFontScale(1 * refResolutionFactor);
        livesCount.setSize(HEIGHT/12, HEIGHT/12);
        livesCount.setPosition(liveIcon.getX() + liveIcon.getWidth() + 10, liveIcon.getY());
        mainStage.addActor(livesCount);

        winMsg = new Label(" ", arcade);
        winMsg.setAlignment(Align.center);
        winMsg.setFontScale(2 * refResolutionFactor);
        winMsg.setSize(WIDTH, HEIGHT/4);
        winMsg.setPosition(WIDTH / 2 - winMsg.getWidth()/2, HEIGHT / 2 - winMsg.getHeight()/2);

        mainStage.addActor(winMsg);

        //============================== Buttons ================================================//

        pauseButtonTex = new ActorBeta();
        pauseButtonTex.loadTexture("PauseButton.png");
        float pauseBtnAR = pauseButtonTex.getWidth() / pauseButtonTex.getHeight();
        pauseButtonTex.setSize(HEIGHT / 10 * pauseBtnAR, HEIGHT /10);
        pauseButtonTex.setColor(1,1,1, 0.5f);
        pauseButton= new Button(arcade);
        pauseButton.setSize(pauseButtonTex.getWidth() * 0.9f, pauseButtonTex.getHeight() * 0.9f);
        pauseButton.add(pauseButtonTex);
        pauseButton.setPosition(20, HEIGHT - pauseButton.getHeight() * 1.2f);
        pauseButton.setColor(1,1,1,0);
        mainStage.addActor(pauseButton);

        resumeButtonTex = new ActorBeta();
        resumeButtonTex.loadTexture("PlayButton.png");
        float resumeBtnAR = resumeButtonTex.getWidth() / resumeButtonTex.getHeight();
        resumeButtonTex.setSize(HEIGHT / 10 * resumeBtnAR, HEIGHT /10);
        resumeButtonTex.setColor(1,1,1,0.5f);
        resumeButton= new Button(arcade);
        resumeButton.setSize(resumeButtonTex.getWidth() * 0.9f, resumeButtonTex.getHeight() * 0.9f);
        resumeButton.add(resumeButtonTex);
        resumeButton.setPosition(20, HEIGHT - resumeButton.getHeight() * 1.2f);
        resumeButton.setColor(1,1,1,0);
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

    void CheckGameState()
    {
        if(ActorBeta.getListSmallBoat().size() + ActorBeta.getListMediumBoat().size() + ActorBeta.getListBigBoat().size() == 0)
        {
            //Win
            winMsg.setText("Level Completed!");
            isPaused = true;
            cannonBall.fireTimer = 0;
        }
        if(cannon.lives == 0 || loseGame)
        {
            winMsg.setText("You lose!");
            isPaused = true;
            //cannonBall.fireTimer = 0;
        }
    }

}
