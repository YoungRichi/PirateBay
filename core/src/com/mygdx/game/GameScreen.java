package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends ScreenBeta {

    //=============== Player ========================//
    CannonBase cannonBase;
    Cannon cannon;
    CannonBall cannonBall;
    Ball ball;
    Vector3 mouseCoord;
    Vector2 dir;
    boolean fireTrigger; // indicate whether the cannon ball is loaded
    Lives liveIcon;

    //================ UI ============================//
    Skin arcade;
    ActorBeta fader, tapToPlay;
    Label livesCount, winMsg, tapText;
    SequenceAction tapTextSequenceAction, tapToPlaySequenceAction, winMsgSequenceAction;
    SequenceAction faderSequenceAction, faderSequenceActions02;
    float refResolutionFactor;
    Group instructionGroup;

    //================ Level =========================//

    float startLvlTimer;
    boolean firstTutorial, secondTutorial, tutCompleted;
    boolean secondTutTransition;
    float disTextTimer, secondTutTransTimer01, secondTutTransTimer02;
    float secondTutTimer;
    float spawnEnemyTimer;
    float toWaveOneTimer;
    boolean waveOne;

    //================= Sounds ========================//
    Sound explosion, hit, gameOver, shoot, parrotSound, lvlCompleted, click;

    //================= Enemies =======================//

    BoatSmall boatSmall01;
    int rockNumMax;

    @Override
    public void initialize() {
        CreateLevel();
    }

    @Override
    public void update(float dt) {

        startLvlTimer -= dt;
        if(fireTrigger && !delayToDisplay)
        {
            ControlCannonBall(dt);
            FirstTutorial();
        }
        if(delayToDisplay) {
            disTextTimer -= dt;
            if (disTextTimer <= 0) {
                delayToDisplay = false;
            }
        }
        SecondTutorial(dt);
        livesCount.setText("x " + cannon.lives);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {


        if(secondTutorial && !fireTrigger || tutCompleted && !fireTrigger)// || playLevel && !fireTrigger) // there is a transition from firstTutorial to secondTutorial, during which secondTutorial and tutCompleted are all false
        {
            Vector2 cannonOriPos = new Vector2(cannon.getX() + cannon.getWidth() / 2,cannon.getY()+ cannon.getHeight() / 2);
            Vector2 touchPos = new Vector2(screenX, (screenY - HEIGHT)*(-1));
            cannon.fireDir = touchPos.sub(cannonOriPos);
            Vector2 touchPos1 = new Vector2(screenX, screenY);

            if(cannon.fireDir.len() > cannon.getWidth()) // able to load cannon ball only when the touch down position is outside of the range
            {
                fireTrigger = true;
                cannon.setRotation(cannon.fireDir.angle());
                cannon.setOrigin(cannon.getWidth()/2, cannon.getHeight()/2);

                cannonBall = new CannonBall(cannon.getX()+ cannon.getWidth()/2 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth(),
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT / 50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth(), mainStage);
                cannonBall.SetVelocity(screenX, (screenY - HEIGHT)*(-1) - cannonBall.getHeight()/2);
                cannonBall.setVisible(false);
                // HEIGHT / 50 is half of the cannon ball height

                ball = new Ball(cannon.getX()+ cannon.getWidth()/2 - HEIGHT/25 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth()/3,
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT/50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth()/3, mainStage);
            }
        }

        if(firstTutorial && !fireTrigger && startLvlTimer + 1 <= 0) // tutorial aim and fire mechanic; 1 is the value of tapToPlay delay duration actions
        {
            Vector2 cannonOriPos = new Vector2(cannon.getX() + cannon.getWidth() / 2,cannon.getY()+ cannon.getHeight() / 2);
            Vector2 touchPos = new Vector2(screenX, (screenY - HEIGHT)*(-1));
            cannon.fireDir = touchPos.sub(cannonOriPos);

            if(cannon.fireDir.len() > cannon.getWidth() &&
                    -screenY+HEIGHT >= boatSmall01.getY() && -screenY+HEIGHT <= boatSmall01.getY() + boatSmall01.getHeight()/3)
            {
                fireTrigger = true;
                cannon.setRotation(cannon.fireDir.angle());
                cannon.setOrigin(cannon.getWidth()/2, cannon.getHeight()/2);

                cannonBall = new CannonBall(cannon.getX()+ cannon.getWidth()/2 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth(),
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT / 50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth(), mainStage);
                cannonBall.SetVelocity(screenX, (screenY - HEIGHT)*(-1) - cannonBall.getHeight()/2);
                cannonBall.setVisible(false);
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

    void FirstTutorial ()
    {
        if(cannonBall.overlaps(boatSmall01) && firstTutorial)
        {
            winMsg.removeAction(winMsgSequenceAction);
            tapToPlay.removeAction(tapToPlaySequenceAction);
            tapText.removeAction(tapTextSequenceAction);

            winMsg.setText("The cannon ball keeps moving until it explodes");
            winMsg.setAlignment(Align.center);
            winMsg.setFontScale(1 * refResolutionFactor);
            winMsg.setSize(WIDTH, HEIGHT/4);
            winMsg.setPosition(WIDTH / 2 - winMsg.getWidth()/2, HEIGHT / 2 - winMsg.getHeight()/2);
            winMsg.setColor(1,1,1,1);
            winMsg.setWrap(true);

            delayToDisplay = true;
            firstTutorial = false;
            tapToPlay.setColor(1,1,1,0);
            tapText.setColor(1,1,1,0);
        }
    }

    // set tutComplete and waveOne to true
    void SecondTutorial(float deltaTime)
    {
        if(cannon.lives == 2 && !secondTutorial && !tutCompleted)
        {
            tapToPlay.setColor(1,1,1,1);
            tapText.setColor(1,1,1,1);
            instructionGroup.setPosition(livesCount.getX() + livesCount.getWidth()/2, livesCount.getY()-livesCount.getWidth());
            tapText.setText("The player loses one life if they are hit by the ball");
            winMsg.setText(" ");
            secondTutTransTimer01 -= deltaTime;
            if(secondTutTransTimer01 <= 0)
            {
                fader.addAction(faderSequenceAction);
                tapToPlay.setColor(1,1,1,0);
                tapText.setColor(1,1,1,0);
                winMsg.setText("Try to tap and hold longer. Watch the size of the ball carefully!");
                secondTutTransTimer02 -= deltaTime;
                if(secondTutTransTimer02 <= 0)
                {
                    secondTutTransition = true;
                }
            }
        }
        // trasition to second tutorial
        if(secondTutTransition)
        {
            fader.removeAction(faderSequenceAction);
            fader.setColor(1,1,1,0);
            winMsg.setText(" ");
            cannon.lives = 3;
            secondTutTimer -= deltaTime;
            if(secondTutTimer <= 0)
            {
                boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
                boatSmall01.setSpeed(0);
                secondTutTransition = false;
                secondTutorial = true;
            }
        }
        // practice shooting logic
        if(secondTutorial && cannonBall.fireTimer <= 0 && ActorBeta.getListSmallBoat().size() == 0 && !tutCompleted)
        {
            boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
            boatSmall01.setSpeed(0);

            BoatSmall boatSmall02 = new BoatSmall(WIDTH / 2, HEIGHT * 3/ 4, mainStage);
            boatSmall02.setSpeed(0);

            BoatSmall boatSmall03 = new BoatSmall(WIDTH / 2, HEIGHT / 4, mainStage);
            boatSmall03.setSpeed(0);
            secondTutorial = false;
            tutCompleted = true;
        }

        if(cannon.lives <=0)
            cannon.lives = 0;

        if(tutCompleted  && ActorBeta.getListSmallBoat().size() == 0 && !waveOne)
        {
            cannonBall.fireTimer = 0;
            winMsg.setText("Enemies are coming!");
            tutCompleted = false;
            waveOne = true;
            for(Rock rock : ActorBeta.getListRock())
            {
                rock.remove();
            }
        }
        if(waveOne)
        {
            toWaveOneTimer -= deltaTime;
            if(toWaveOneTimer <= 0)
            {
                playLevel = true;
                PirateBay.setActiveScreen(new Wave1());
            }
        }
    }

    void CreateLevel()
    {
        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));

        mouseCoord = new Vector3();
        dir = new Vector2();
        refResolutionFactor = HEIGHT/480;

        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture( "Battlefield.png" );
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        fireTrigger = false; // if the player touches the screen, then the value is true
        startLvlTimer = 3;
        disTextTimer = 5;
        secondTutTimer = 1;
        secondTutTransTimer01 = 5;
        secondTutTransTimer02 = 5;
        toWaveOneTimer = 3;
        firstTutorial = true;
        secondTutTransition = false;
        secondTutorial = false;
        spawnEnemyTimer = 2; // for tutorial
        tutCompleted = false;
        waveOne = false;
        playLevel = false;
        //================================= Player ==============================================//
        cannonBase = new CannonBase();
        cannonBase.setPosition(cannonBase.getWidth()/2, HEIGHT/3);
        mainStage.addActor(cannonBase);
        cannon = new Cannon(cannonBase.getX() , cannonBase.getY() + cannonBase.getHeight()*3/4 , mainStage);
        new Barricade(WIDTH * 5 / 16, 0, mainStage);
        new NoTapZone(cannon.getX()+ cannon.getWidth()/2 - cannon.getWidth(), cannon.getY() + cannon.getHeight()/2 - cannon.getWidth() , mainStage);
        liveIcon = new Lives(cannon.getX(), cannon.getY() + cannon.getHeight(), mainStage);

        //================================== Obstacles ===========================================//

        rockNumMax = 15;

        for(int i = 0; i < rockNumMax; i++)
        {
            new Rock(WIDTH / 2 + WIDTH / 10, 0 + i * HEIGHT / 15, mainStage);
        }
        //================================== Enemies ============================================//

        boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
        boatSmall01.setSpeed(0);

        //============================== Labels =================================================//

        livesCount = new Label("x 3", arcade);
        livesCount.setAlignment(Align.center);
        livesCount.setFontScale(1 * refResolutionFactor);
        livesCount.setSize(HEIGHT/12, HEIGHT/12);
        livesCount.setPosition(liveIcon.getX() + liveIcon.getWidth() + 10, liveIcon.getY());
        mainStage.addActor(livesCount);

        tapToPlay = new ActorBeta();
        tapToPlay.loadTexture("TapMe.png");
        float TapAspectRatio = tapToPlay.getWidth()/tapToPlay.getHeight();
        tapToPlay.setSize(HEIGHT/10 * TapAspectRatio, HEIGHT/10);
        tapToPlay.setPosition(0,0);
        tapToPlay.setColor(1,1,1,0);
        tapToPlaySequenceAction = Actions.sequence(Actions.delay(startLvlTimer + 1), Actions.repeat(10,
                Actions.sequence(Actions.fadeIn(1f), Actions.fadeOut(0.5f))));
        tapToPlay.addAction(tapToPlaySequenceAction);

        tapText = new Label("TAP HERE TO FIRE!", arcade);
        tapText.setFontScale(1 * refResolutionFactor);
        tapText.setSize(WIDTH * 1.5f / 2, HEIGHT/6);
        tapText.setWrap(true);
        tapText.setPosition(tapToPlay.getX(), tapToPlay.getY() - tapToPlay.getHeight()* 2);
        tapText.setColor(1,1,1,0);
        tapTextSequenceAction = Actions.sequence(Actions.delay(startLvlTimer + 1), Actions.repeat(10,
                Actions.sequence(Actions.fadeIn(1f), Actions.fadeOut(0.5f))));
        tapText.addAction(tapTextSequenceAction);

        instructionGroup = new Group();
        instructionGroup.setPosition(WIDTH/2 - boatSmall01.getWidth(), HEIGHT/2 - boatSmall01.getHeight()/1.5f);
        mainStage.addActor(instructionGroup);
        instructionGroup.addActor(tapToPlay);
        instructionGroup.addActor(tapText);

        fader = new ActorBeta(0,0, mainStage);
        fader.loadTexture("cannonTesting.png");
        fader.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        fader.setColor(1,1,1,0);
        faderSequenceAction = Actions.sequence(Actions.delay(0.5f),
                Actions.fadeIn(2));

        winMsg = new Label(" ", arcade);
        winMsg.setAlignment(Align.center);
        winMsg.setFontScale(2 * refResolutionFactor);
        winMsg.setSize(WIDTH, HEIGHT/4);
        winMsg.setPosition(WIDTH / 2 - winMsg.getWidth()/2, HEIGHT / 2 - winMsg.getHeight()/2);
        winMsg.setText("Are You Ready!");
        winMsgSequenceAction = Actions.sequence(Actions.delay(0.5f),Actions.fadeOut(disTextTimer - 1.5f));
        winMsg.addAction(winMsgSequenceAction);
        faderSequenceActions02 = Actions.sequence(Actions.fadeIn(0.1f), Actions.fadeOut(6));
        mainStage.addActor(winMsg);

        //==================================== Sound =============================================//
        explosion = Gdx.audio.newSound(Gdx.files.internal("Sound/explosion.wav"));
        gameOver = Gdx.audio.newSound(Gdx.files.internal("Sound/gameOver.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("Sound/hitsound.wav"));
        lvlCompleted = Gdx.audio.newSound(Gdx.files.internal("Sound/levelCompleted.wav"));
        parrotSound = Gdx.audio.newSound(Gdx.files.internal("Sound/parrot.wav"));
        shoot = Gdx.audio.newSound(Gdx.files.internal("Sound/shoot.wav"));
        click = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));
    }

}
