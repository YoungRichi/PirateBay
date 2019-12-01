package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class Level01 extends ScreenBeta {

    //=============== Player ========================//
    CannonBall cannonBall;
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
    BoatSmall boatSmall01;
    boolean secondWave;

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

        new Rock(WIDTH / 2 + WIDTH / 10, HEIGHT / 2, mainStage);

        //================================== Enemies ============================================//

        boatSmall01 = new BoatSmall(WIDTH / 2, HEIGHT / 2, mainStage);
        boatSmall01.setSpeed(0);
        secondWave = false;

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
        winMsg.setText("Level 1");
        winMsg.addAction(Actions.sequence(Actions.delay(0.5f),Actions.fadeOut(2)));
        mainStage.addActor(winMsg);

        ActorBeta tapToPlay = new ActorBeta();
        tapToPlay.loadTexture("TapMe.png");
        float TapAspectRatio = tapToPlay.getWidth()/tapToPlay.getHeight();
        tapToPlay.setSize(HEIGHT/10 * TapAspectRatio, HEIGHT/10);
        tapToPlay.setPosition(0,0);
        tapToPlay.setColor(1,1,1,0);
        tapToPlay.addAction(Actions.sequence(Actions.delay(3), Actions.repeat(10,
                Actions.sequence(Actions.fadeIn(0.2f), Actions.fadeOut(0.5f)))));

        Label tapText = new Label("TAP HERE TO FIRE!", arcade);
        tapText.setAlignment(Align.center);
        tapText.setFontScale(1 * refResolutionFactor);
        tapText.setPosition(tapToPlay.getX(), tapToPlay.getY() - tapToPlay.getHeight());
        tapText.setColor(1,1,1,0);
        tapText.addAction(Actions.sequence(Actions.delay(3), Actions.repeat(10,
                Actions.sequence(Actions.fadeIn(0.2f), Actions.fadeOut(0.5f)))));

        Group instructionGroup = new Group();
        instructionGroup.setPosition(WIDTH/2 - boatSmall01.getWidth(), HEIGHT/2 - boatSmall01.getHeight() / 2);
        mainStage.addActor(instructionGroup);
        instructionGroup.addActor(tapToPlay);
        instructionGroup.addActor(tapText);

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
    }

    @Override
    public void update(float dt) {
        if(fireTrigger)
        {
            ControlCannonBall(dt);
        }
        //CheckPauseResumeButton();
        //CheckGameState();
        livesCount.setText("x " + cannon.lives);

        if(cannon.lives == 2)
        {
            secondWave = true;
        }

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


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!secondWave && !fireTrigger) // first way fire mechanic
        {
            Vector2 cannonOriPos = new Vector2(cannon.getX() + cannon.getWidth() / 2,cannon.getY()+ cannon.getHeight() / 2);
            Vector2 touchPos = new Vector2(screenX, (screenY - HEIGHT)*(-1));
            cannon.fireDir = touchPos.sub(cannonOriPos);

            if(cannon.fireDir.len() > cannon.getWidth() &&
                    -screenY+HEIGHT >= boatSmall01.getY()&& -screenY+HEIGHT <= boatSmall01.getY() + boatSmall01.getHeight())
                fireTrigger = true;
                cannon.setRotation(cannon.fireDir.angle());
                cannon.setOrigin(cannon.getWidth()/2, cannon.getHeight()/2);

                cannonBall = new CannonBall(cannon.getX()+ cannon.getWidth()/2 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth(),
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT / 50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth(), mainStage);
                cannonBall.SetVelocity(screenX, (screenY - HEIGHT)*(-1) - cannonBall.getHeight()/2);
                cannonBall.setVisible(false);

        }
        /*
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
            }
        }

         */

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
