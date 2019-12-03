package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class Level01 extends ScreenBeta {

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
    ActorBeta tapToPlay;
    Label tapText;
    float refResolutionFactor;
    SequenceAction tapTextSequenceAction;
    SequenceAction tapToPlaySequenceAction;
    SequenceAction winMsgSequenceAction;
    SequenceAction faderSequenceAction;
    float startLvlTimer;
    Group instructionGroup;
    ActorBeta fader;
    //================= Sounds =========================//
    Sound explosion, hit, gameOver, shoot, parrotSound, lvlCompleted, click;

    //================= Enemies =======================//
    BoatSmall boatSmall01;
    boolean firstWave, secondWave;
    boolean secondWaveTransition;
    float disTextTimer;
    float secondWaveTransTimer01, secondWaveTransTimer02;
    float secondWaveTimer;
    float spawnEnemyTimer;

    @Override
    public void initialize() {
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

        for(int i = 0; i < 15; i++)
        {
            new Rock(WIDTH / 2 + WIDTH / 10, 0 + i * HEIGHT / 15, mainStage);
        }
        //================================== Enemies ============================================//

        boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
        boatSmall01.setSpeed(0);


        //============================== Labels =================================================//

        refResolutionFactor = HEIGHT/480;
        startLvlTimer = 3;
        disTextTimer = 5;
        secondWaveTimer = 1;
        secondWaveTransTimer01 = 5;
        secondWaveTransTimer02 = 5;
        firstWave = true;
        secondWaveTransition = false;
        secondWave = false;
        spawnEnemyTimer = 2;

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
        winMsg.setText("Level 1");
        winMsgSequenceAction = Actions.sequence(Actions.delay(0.5f),Actions.fadeOut(disTextTimer - 1.5f));
        winMsg.addAction(winMsgSequenceAction);
        mainStage.addActor(winMsg);
    }

    @Override
    public void update(float dt) {

        startLvlTimer -= dt;
        if(fireTrigger && !delayToDisplay)
        {
            ControlCannonBall(dt);
            firstTutorial();

            /*
            if(cannonBall.overlaps(boatSmall01) && firstWave)
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
                firstWave = false;
                tapToPlay.setColor(1,1,1,0);
                tapText.setColor(1,1,1,0);
            }

             */
        }
        //CheckPauseResumeButton();
        //CheckGameState();

        if(delayToDisplay) {
            disTextTimer -= dt;
            if (disTextTimer <= 0) {
                delayToDisplay = false;
            }
        }
        SecondTutorial(dt);
        /*
        if(cannon.lives == 2 && !secondWave)
        {
            tapToPlay.setColor(1,1,1,1);
            tapText.setColor(1,1,1,1);
            instructionGroup.setPosition(livesCount.getX() + livesCount.getWidth()/2, livesCount.getY()-livesCount.getWidth());
            tapText.setText("The player loses one life if they are hit by the ball");
            winMsg.setText(" ");
            secondWaveTransTimer01 -= dt;
            if(secondWaveTransTimer01 <= 0)
            {
                fader.addAction(faderSequenceAction);
                tapToPlay.setColor(1,1,1,0);
                tapText.setColor(1,1,1,0);
                winMsg.setText("Try to tap and hold longer. Watch the size of the ball carefully!");
                secondWaveTransTimer02 -= dt;
                if(secondWaveTransTimer02 <= 0)
                {
                    secondWaveTransition = true;
                }
            }
        }

        // trasition to second wave
        if(secondWaveTransition)
        {
            fader.removeAction(faderSequenceAction);
            fader.setColor(1,1,1,0);
            winMsg.setText(" ");
            cannon.lives = 3;
            secondWaveTimer -= dt;
            if(secondWaveTimer <= 0)
            {
                boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
                boatSmall01.setSpeed(0);
                secondWaveTransition = false;
                secondWave = true;
            }
        }

         */
        // practice shooting logic
        if(secondWave && cannonBall.fireTimer <= 0 && ActorBeta.getListSmallBoat().size() == 0)
        {
            boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
            boatSmall01.setSpeed(0);
        }
        livesCount.setText("x " + cannon.lives);
    }

    void firstTutorial ()
    {
        if(cannonBall.overlaps(boatSmall01) && firstWave)
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
            firstWave = false;
            tapToPlay.setColor(1,1,1,0);
            tapText.setColor(1,1,1,0);
        }
    }

    void SecondTutorial(float deltaTime)
    {
        if(cannon.lives == 2 && !secondWave)
        {
            tapToPlay.setColor(1,1,1,1);
            tapText.setColor(1,1,1,1);
            instructionGroup.setPosition(livesCount.getX() + livesCount.getWidth()/2, livesCount.getY()-livesCount.getWidth());
            tapText.setText("The player loses one life if they are hit by the ball");
            winMsg.setText(" ");
            secondWaveTransTimer01 -= deltaTime;
            if(secondWaveTransTimer01 <= 0)
            {
                fader.addAction(faderSequenceAction);
                tapToPlay.setColor(1,1,1,0);
                tapText.setColor(1,1,1,0);
                winMsg.setText("Try to tap and hold longer. Watch the size of the ball carefully!");
                secondWaveTransTimer02 -= deltaTime;
                if(secondWaveTransTimer02 <= 0)
                {
                    secondWaveTransition = true;
                }
            }
        }
        // trasition to second wave
        if(secondWaveTransition)
        {
            fader.removeAction(faderSequenceAction);
            fader.setColor(1,1,1,0);
            winMsg.setText(" ");
            cannon.lives = 3;
            secondWaveTimer -= deltaTime;
            if(secondWaveTimer <= 0)
            {
                boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
                boatSmall01.setSpeed(0);
                secondWaveTransition = false;
                secondWave = true;
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(firstWave && !fireTrigger && startLvlTimer + 1 <= 0) // first wave aim and fire mechanic; 1 is the value of tapToPlay delay duration actions
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

        if(secondWave  && !fireTrigger ) // second wave aim and fire mechanic
        {
            Vector2 cannonOriPos = new Vector2(cannon.getX() + cannon.getWidth() / 2,cannon.getY()+ cannon.getHeight() / 2);
            Vector2 touchPos = new Vector2(screenX, (screenY - HEIGHT)*(-1));
            cannon.fireDir = touchPos.sub(cannonOriPos);

            if(cannon.fireDir.len() > cannon.getWidth())
            {
                fireTrigger = true;
                cannon.setRotation(cannon.fireDir.angle());
                cannon.setOrigin(cannon.getWidth()/2, cannon.getHeight()/2);

                cannonBall = new CannonBall(cannon.getX()+ cannon.getWidth()/2 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth(),
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT / 50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth(), mainStage);
                cannonBall.SetVelocity(screenX, (screenY - HEIGHT)*(-1) - cannonBall.getHeight()/2);
                cannonBall.setVisible(false);
                ball = new Ball(cannon.getX()+ cannon.getWidth()/2 - HEIGHT/25 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth()/3,
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
}
