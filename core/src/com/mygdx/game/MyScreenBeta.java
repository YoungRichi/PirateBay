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
import com.badlogic.gdx.utils.IntArray;

import java.util.ArrayList;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public abstract class MyScreenBeta extends ScreenBeta {

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
    Button pauseButton, resumeButton;
    ActorBeta pauseButtonTex, resumeButtonTex;
    Label livesCount, winMsg, scoreUI;
    float refResolutionFactor;
    ScoreBoard scoreBoard;
    Label highscoreUI, currentScoreUI;
    Button replayButton;
    ActorBeta replayButtonTex;

    //================ Level =========================//

    float startLvlTimer;
    int levelNum; // the
    int levelTotalNum;
    float toNextLvlTimer;


    //================= Obstacles =====================//
    //Island island;
    //Rock rock;

    //================= Sounds ========================//
    Sound explosion, hit, gameOver, shoot, parrotSound, lvlCompleted, click;

    //================= Enemies =======================//

    int[] smallBoatNums, medBoatNums, bigBoatNums, fastBoatNums, parrotNums;
    int rockNumMax;
    int smlBoatStartIndex, medBoatStartIndex, bigBoatStartIndex, fastBoatStartIndex, parrotStartIndex;

    @Override
    public void initialize() {
        CreateLevel();
    }

    @Override
    public void update(float dt) {

        startLvlTimer -= dt;
        if(startLvlTimer <= 0 && !lvlStarted)
        {
            winMsg.setText("");
            waves[0] = true;
            lvlStarted = true;
            enemySpawned = true;
            GetListSpawnSmlBoat(smlBoatStartIndex, smallBoatNums[0]);
            GetListSpawnMedBoat(medBoatStartIndex, medBoatNums[0]);
            GetListSpawnBigBoat(bigBoatStartIndex, bigBoatNums[0]);
            GetListSpawnFastBoat(fastBoatStartIndex, fastBoatNums[0]);
            GetListSpawnParrot(parrotStartIndex, parrotNums[0]);
            smlBoatStartIndex += smallBoatNums[0];
            medBoatStartIndex += medBoatNums[0];
            bigBoatStartIndex += bigBoatNums[0];
            fastBoatStartIndex += fastBoatNums[0];
            parrotStartIndex += parrotNums[0];

        }

        if(waveEnd(dt)&& !enemySpawned)
        {
            for (int i = 1; i < waveNum; i++)
            {
                if(waves[i])
                {
                    GetListSpawnSmlBoat(smlBoatStartIndex, smallBoatNums[i]);
                    GetListSpawnMedBoat(medBoatStartIndex, medBoatNums[i]);
                    GetListSpawnBigBoat(bigBoatStartIndex, bigBoatNums[i]);
                    GetListSpawnFastBoat(fastBoatStartIndex, fastBoatNums[i]);
                    GetListSpawnParrot(parrotStartIndex, parrotNums[i]);
                    smlBoatStartIndex += smallBoatNums[i];
                    medBoatStartIndex += medBoatNums[i];
                    bigBoatStartIndex += bigBoatNums[i];
                    fastBoatStartIndex += fastBoatNums[i];
                    parrotStartIndex += parrotNums[i];
                }
            }
        }

        if(fireTrigger)
        {
            ControlCannonBall(dt);
        }

        CheckPauseResumeButton();
        CheckGameState(dt);
        livesCount.setText("x " + cannon.lives);
        scoreUI.setText("" + score);
    }

    void CheckGameState(float deltaTime)
    {
        if(cannon.lives == 0 )
        {
            loseGame = true;
        }

        if(loseGame)
        {
            winMsg.setText("You lose!");
            scoreBoard.addAction(Actions.sequence(Actions.delay(2), Actions.fadeIn(1)));
            uiStage.addActor(scoreBoard);
            highscoreUI.setText("High Score \n" + ScreenBeta.highscore);
            highscoreUI.addAction(Actions.sequence(Actions.delay(2), Actions.fadeIn(1)));
            uiStage.addActor(highscoreUI);
            currentScoreUI.setText("Current Score \n" + ScreenBeta.score);
            currentScoreUI.addAction(Actions.sequence(Actions.delay(2), Actions.fadeIn(1)));
            uiStage.addActor(currentScoreUI);
            replayButtonTex.addAction(Actions.sequence(Actions.delay(2), Actions.fadeIn(1)));
            uiStage.addActor(replayButton);
            if(replayButton.isPressed())
            {
                ScreenBeta.score = 0;
                PirateBay.setActiveScreen(new Level01());
                loseGame = false;
            }

        }

        if(lvlEnd)
        {
            winMsg.setText("Wave " + levelNum + " Cleared!");
            toNextLvlTimer -= deltaTime;
            if(toNextLvlTimer <= 0)
            {
                PirateBay.setActiveScreen(GetScreen());
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
        background.loadTexture( "Battlefield2.png" );
        background.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        fireTrigger = false; // if the player touches the screen, then the value is true
        startLvlTimer = 3;
        playLevel = true; // is set after the tutorial
        toNextLvlTimer = 2;

        //================================= Player ==============================================//
        cannonBase = new CannonBase();
        cannonBase.setPosition(cannonBase.getWidth()/2, HEIGHT/3);
        mainStage.addActor(cannonBase);
        cannon = new Cannon(cannonBase.getX() , cannonBase.getY() + cannonBase.getHeight()*3/4 , mainStage);
        new Barricade(WIDTH * 3 / 16, 0, mainStage);
        new NoTapZone(cannon.getX()+ cannon.getWidth()/2 - cannon.getWidth(), cannon.getY() + cannon.getHeight()/2 - cannon.getWidth() , mainStage);
        liveIcon = new Lives(cannon.getX(), cannon.getY() + cannon.getHeight(), mainStage);

        //================================== Obstacles ===========================================//

        //rockNumMax = 0;

        //for(int i = 0; i < rockNumMax; i++)
        //{
            //Rock rock = new Rock(WIDTH / 2 + WIDTH / 10, 0 + i * HEIGHT / 15, mainStage);
        //}

        //top rocks
        new Island(WIDTH * 15/64, HEIGHT / 2 + HEIGHT/15, mainStage);
       // rock = new Rock (WIDTH * 5/16, HEIGHT * 5/8 , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 5/8 + HEIGHT/15 , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 5/8 + 2* HEIGHT/15 , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 5/8 + 3* HEIGHT/15 , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 5/8 + 4* HEIGHT/15 , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 5/8 + 5* HEIGHT/15 , mainStage);


        //rock = new Rock (WIDTH * 5/16, HEIGHT / 2 + HEIGHT / 15, mainStage);

        //lower half rocks
        new Rock (WIDTH * 9/32, HEIGHT * 1/8 - 2 * HEIGHT/15  , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 1/8 - HEIGHT/15  , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 1/8  , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 1/8 + HEIGHT / 15  , mainStage);
        new Rock (WIDTH * 9/32, HEIGHT * 1/8 + 2 * HEIGHT / 15  , mainStage);



        //Middle Islands
        new Rock(WIDTH *6/8, HEIGHT * 18/32, mainStage); // east
        new Island(WIDTH *5/8, HEIGHT * 14/32, mainStage); // west
        new Island(WIDTH *5/8+ WIDTH/15, HEIGHT *3/4, mainStage); //north
        new Island(WIDTH *5/8+ WIDTH/15, HEIGHT *1/4, mainStage); //south

        //Lower screen rock (3 rocks)
        new Rock (WIDTH * 7/16 , HEIGHT * 3/16   , mainStage);
        new Rock (WIDTH * 9/16 , HEIGHT * 3/16   , mainStage);
        new Island (WIDTH * 8/16 , HEIGHT * 5/16   , mainStage);
        new Rock (WIDTH * 14/16 , HEIGHT * 1/16   , mainStage);
        new Rock (WIDTH * 14/16 , HEIGHT * 0   , mainStage);

        // Upper Screen rock
        new Rock (WIDTH * 7/16 , HEIGHT * 13/16   , mainStage); // left
        new Rock (WIDTH * 9/16 , HEIGHT * 11/16   , mainStage); // middle
        new Rock (WIDTH * 9/16 , HEIGHT * 13/16 - HEIGHT/15   , mainStage); // middle
       // rock = new Rock (WIDTH * 9/16 , HEIGHT * 12/16 - (2 * HEIGHT/15)   , mainStage); // middle

        new Rock ( WIDTH * 14/16, HEIGHT * 13/16, mainStage); //far east

        //island = new Island(WIDTH / 4, HEIGHT / 2, mainStage);

        //================================== Enemies ============================================//

        boatSmalls = new ArrayList<BoatSmall>();
        boatMediums = new ArrayList<BoatMedium>();
        boatBigs = new ArrayList<BoatBig>();
        boatFasts = new ArrayList<BoatFast>();
        parrots = new ArrayList<Parrot>();

        smlBoatStartIndex = 0;
        medBoatStartIndex = 0;
        bigBoatStartIndex = 0;
        fastBoatStartIndex = 0;
        parrotStartIndex = 0;

        EnemiesInit();


        //============================== Labels =================================================//

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
        winMsg.setText("Wave " + levelNum);
        mainStage.addActor(winMsg);

        scoreUI =  new Label("" + score, arcade);
        scoreUI.setAlignment(Align.center);
        scoreUI.setFontScale(1 * refResolutionFactor);
        scoreUI.setSize(WIDTH/2, HEIGHT/12);
        scoreUI.setPosition(WIDTH / 2 - scoreUI.getWidth()/2, HEIGHT - scoreUI.getHeight());
        mainStage.addActor(scoreUI);

        scoreBoard = new ScoreBoard();
        scoreBoard.setSize(HEIGHT/2 * scoreBoard.getWidth()/scoreBoard.getHeight(), HEIGHT/2);
        scoreBoard.setPosition(WIDTH / 2 - scoreBoard.getWidth()/2, HEIGHT/2 - scoreBoard.getHeight()/2);
        scoreBoard.setColor(1,1,1,0);

        highscoreUI = new Label("High Score \n" + ScreenBeta.highscore, arcade);
        highscoreUI.setAlignment(Align.center);
        highscoreUI.setFontScale(1.2f * refResolutionFactor);
        highscoreUI.setSize(scoreBoard.getWidth(), scoreBoard.getHeight()/3);
        highscoreUI.setPosition(scoreBoard.getX() + scoreBoard.getWidth() / 2 - highscoreUI.getWidth()/2, scoreBoard.getY() + scoreBoard.getHeight() - highscoreUI.getHeight());
        highscoreUI.setColor(1,1,1,0);

        currentScoreUI = new Label("Current Score \n" + ScreenBeta.score, arcade);
        currentScoreUI.setAlignment(Align.center);
        currentScoreUI.setFontScale(1 * refResolutionFactor);
        currentScoreUI.setSize(scoreBoard.getWidth(), scoreBoard.getHeight()/3);
        currentScoreUI.setPosition(scoreBoard.getX() + scoreBoard.getWidth() / 2 - currentScoreUI.getWidth()/2, scoreBoard.getY() + scoreBoard.getHeight()/2 - currentScoreUI.getHeight()/2);
        currentScoreUI.setColor(1,1,1,0);

        replayButtonTex = new ActorBeta();
        replayButtonTex.loadTexture("replayButton.png");
        float replayBtnAR = replayButtonTex.getWidth() / replayButtonTex.getHeight();
        replayButtonTex.setSize(HEIGHT / 10 * replayBtnAR, HEIGHT /10);
        replayButtonTex.setColor(1,1,1, 0);
        replayButton= new Button(arcade);
        replayButton.setSize(replayButtonTex.getWidth() * 0.9f, replayButtonTex.getHeight() * 0.9f);
        replayButton.add(replayButtonTex);
        replayButton.setPosition(scoreBoard.getX() + scoreBoard.getWidth() / 2 - replayButtonTex.getWidth()/2, scoreBoard.getY() + scoreBoard.getHeight()/2 - replayButtonTex.getHeight() * 2);
        replayButton.setColor(1,1,1,0);

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
        uiStage.addActor(pauseButton);

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
        uiStage.addActor(resumeButton);
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

    public abstract void EnemiesInit();
    public abstract ScreenBeta GetScreen ();


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(playLevel && !fireTrigger && startLvlTimer <= 0 && !loseGame) // there is a transition from firstTutorial to secondTutorial, during which secondTutorial and tutCompleted are all false
        {
            Vector2 cannonOriPos = new Vector2(cannon.getX() + cannon.getWidth() / 2,cannon.getY()+ cannon.getHeight() / 2);
            Vector2 touchPos = new Vector2(screenX, (screenY - HEIGHT)*(-1));
            Vector2 uIButtonPos = new Vector2(- pauseButtonTex.getX() + pauseButtonTex.getWidth()/2, - pauseButtonTex.getY() + pauseButtonTex.getHeight()/2);
            cannon.fireDir = touchPos.sub(cannonOriPos);
            Vector2 touchPos1 = new Vector2(screenX, screenY);
            Vector2 buttonZone = touchPos1.sub(uIButtonPos);

            if(cannon.fireDir.len() > cannon.getWidth() &&
                    buttonZone.len2() > 2f * (pauseButtonTex.getWidth()/2 * pauseButtonTex.getWidth()/2 ) + pauseButtonTex.getHeight()/2 * pauseButtonTex.getHeight()/2) // able to load cannon ball only when the touch down position is outside of the range
            {
                fireTrigger = true;
                cannon.setRotation(cannon.fireDir.angle());
                cannon.setOrigin(cannon.getWidth()/2, cannon.getHeight()/2);

                cannonBall = new CannonBall(cannon.getX()+ cannon.getWidth()/2 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth(),
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT / 50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth(), mainStage);

                cannonBall.SetVelocity(screenX, ((screenY - HEIGHT)*(-1) - cannonBall.getHeight()/2));
                cannonBall.setVisible(false);
                // HEIGHT / 50 is half of the cannon ball height

                ball = new Ball(cannon.getX()+ cannon.getWidth()/2 - HEIGHT/25 +(float)(Math.cos(cannon.fireDir.angleRad()))*cannon.getWidth()/3,
                        cannon.getY() + cannon.getHeight()/2 - HEIGHT/50 + (float)(Math.sin(cannon.fireDir.angleRad()))*cannon.getWidth()/3, mainStage);

            }

        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(fireTrigger && !loseGame)
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


}
