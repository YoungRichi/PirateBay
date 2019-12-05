package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


/**
 * Created by markapptist on 2018-10-16.
 */

public abstract class ScreenBeta implements Screen, InputProcessor {

    //protected Stage mainStage;
    static Stage mainStage;
    //STAGE
    protected Stage uiStage;

    //BOOLEANS
    static boolean isPaused;
    static boolean loseGame;
    static boolean playLevel; // set to true after tutorial completed
    static boolean delayToDisplay;
    final int waveNum = 5; // number of waves in a level

    //
    boolean enemySpawned;
    boolean lvlStarted, lvlEnd;
    boolean[] waves;
    float bwtWaveTimeMax;
    float bwtWaveTimer;
    float toNextLevelTimer;


    static int score, highscore;
    Preferences prefs;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();


    SpriteBatch batch;
    //CONSTRUCTOR
    ScreenBeta()
    {

        isPaused = false;
        loseGame = false;

        lvlStarted = false;
        enemySpawned = false;
        waves = new boolean[waveNum];
        for(int i = 0; i < waves.length; i++)
        {
            waves[i] = false;
        }
        bwtWaveTimeMax = 5;
        bwtWaveTimer = bwtWaveTimeMax;
        toNextLevelTimer = 2;

        mainStage = new Stage();
        uiStage = new Stage();

        /***
         * TODO: USE THE TABLE BELOW TO SET THE BUTTONS ON BOTH START SCREEN AND GAME SCREEN
         */

        //uiTable = new Table();
        //uiTable.setFillParent(true);
        //uiTable.align(Align.center);
        //uiStage.addActor(uiTable);

        /*
        //FONTS
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = Texture.TextureFilter.Linear;
        fontParameters.magFilter = Texture.TextureFilter.Linear;

        BitmapFont customFont = fontGenerator.generateFont(fontParameters);
        */
        //INITIALIZE A DEFAULT BUTTON
        //buttonStyle = new ButtonStyle();
        //button = new Button(buttonStyle);
        //button.setTransform(true);

        //INITIALIZE A LABEL
        //labelStyle = new LabelStyle();
        //labelStyle.fontColor = Color.BLACK;
        //labelStyle.font = customFont;

        //label = new Label("LABEL", labelStyle);

        //uiStage.addActor(button);
        //uiStage.addActor(label);

        //INITIALIZE TABLE
        /**
         * TODO: PLAY WITH THE TABLE UNTIL THINGS ARE ALIGNED PROPERLY ON BOTH SCREENS
         */
        //uiTable.padTop(30);
        //uiTable.add(button).padBottom(100);
        //uiTable.row();
        //uiTable.add(label);

        initialize();

        prefs = Gdx.app.getPreferences("PirateBay");
        highscore = prefs.getInteger("highscore", 0);
        /*
        if(score > highscore)
        {
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

         */
    }

    boolean waveEnd(float deltaTime)
    {

        for (int i = 0; i < waves.length; i ++)
        {
            if( i < waves.length -1 && waves[i] && enemySpawned)
            {
                bwtWaveTimer -= deltaTime;
                if(bwtWaveTimer <= 0)
                {
                    waves[i] = false;
                    enemySpawned = false;
                    waves[i+1] = true;
                    bwtWaveTimer = bwtWaveTimeMax;
                    return true;
                }
            }
            if (i == waves.length - 1 && waves[i] && ActorBeta.getListSmallBoat().size() + ActorBeta.getListMediumBoat().size() +
                    ActorBeta.getListBigBoat().size() + ActorBeta.getListSoldier().size() + ActorBeta.getListParrot().size() == 0) // add high speed boat later
            {
                lvlEnd = true;
            }
        }
        return false;
    }
    void SpawnEnemies (int boatSmallNum, int boatMedNum, int boatBigNum, int parrotNum)
    {
        if(boatSmallNum > 0)
        {
            for (int i = 1; i < boatSmallNum + 1; i++)
            {
                new BoatSmall(WIDTH, HEIGHT / boatSmallNum * i - HEIGHT / 12, mainStage );
            }
        }

        if(boatMedNum > 0)
        {
            for (int i = 1; i < boatMedNum + 1; i++)
            {
                new BoatMedium(WIDTH, HEIGHT / boatMedNum * i - HEIGHT / 8, mainStage );
            }
        }

        if(boatBigNum > 0)
        {
            for (int i = 1; i < boatBigNum + 1; i++)
            {
                new BoatBig(WIDTH, HEIGHT / boatBigNum * i - HEIGHT / 6, mainStage );
            }
        }

        if(parrotNum > 0)
        {
            for (int i = 1; i < parrotNum + 1; i++)
            {
                new Parrot(WIDTH, HEIGHT / parrotNum * i - HEIGHT / 4, mainStage );
            }
        }

        enemySpawned = true;
    }
    public abstract void initialize();

    /**
     *  Called when this becomes the active screen in a Game.
     *  Set up InputMultiplexer here, in case screen is reactivated at a later time.
     */
    @Override
    public void show() {

        //GET the InputMultiplexer
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();

        //Add InputProcessor to the screen
        im.addProcessor(this);

        //Add InputProcessor to the stage
        im.addProcessor(mainStage);
        im.addProcessor(uiStage);
    }

    /**
     *  Called when this is no longer the active screen in a Game.
     *  Screen class and Stages no longer process input.
     *  Other InputProcessors must be removed manually.
     */
    @Override
    public void hide() {

        //Get InputProcessor
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();

        //Remove InputProcessor
        im.removeProcessor(this);
        im.removeProcessor(mainStage);
        im.removeProcessor(uiStage);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public abstract void update(float dt);

    @Override
    public void render(float delta) {

        //score++;
        if(score > highscore)
        {
            highscore = score;
            prefs.putInteger("highscore", score);
            prefs.flush();
        }

        //PAUSE LOGIC
        if(isPaused)
            delta = 0;
        else {
            delta = Math.min(delta, 1/30.0f);
        }

        mainStage.act(delta);
        uiStage.act(delta);

        update(delta);
        mainStage.setDebugAll(true);

        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();

        //uiTable.setDebug(true);
    }

    public boolean isTouchDownEvent(Event e) {
        return (e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
