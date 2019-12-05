package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import javax.sound.sampled.LineEvent;

public class LevelScreen extends ScreenBeta {

    ActorBeta background;
    ActorBeta Level1ButtonTex;
    ActorBeta Level2ButtonTex;
    ActorBeta Level3ButtonTex;
    ActorBeta Level4ButtonTex;
    ActorBeta Level5ButtonTex;
    ActorBeta Level6ButtonTex;
    ActorBeta Level7ButtonTex;
    ActorBeta Level8ButtonTex;
    ActorBeta Level9ButtonTex;
    ActorBeta Level10ButtonTex;
    ActorBeta Level11ButtonTex;
    ActorBeta Level12ButtonTex;
    ActorBeta Level13ButtonTex;
    ActorBeta Level14ButtonTex;
    ActorBeta Level15ButtonTex;
    ActorBeta Level16ButtonTex;
    ActorBeta Level17ButtonTex;
    ActorBeta Level18ButtonTex;
    ActorBeta Level19ButtonTex;
    ActorBeta Level20ButtonTex;
    ActorBeta Level21ButtonTex;
    ActorBeta Level22ButtonTex;
    ActorBeta Level23ButtonTex;
    ActorBeta Level24ButtonTex;
    ActorBeta Level25ButtonTex;
    ActorBeta Level26ButtonTex;
    ActorBeta Level27ButtonTex;
    ActorBeta Level28ButtonTex;
    ActorBeta Level29ButtonTex;
    ActorBeta Level30ButtonTex;

    Button Level1Button;
    Button Level2Button;
    Button Level3Button;
    Button Level4Button;
    Button Level5Button;
    Button Level6Button;
    Button Level7Button;
    Button Level8Button;
    Button Level9Button;
    Button Level10Button;
    Button Level11Button;
    Button Level12Button;
    Button Level13Button;
    Button Level14Button;
    Button Level15Button;
    Button Level16Button;
    Button Level17Button;
    Button Level18Button;
    Button Level19Button;
    Button Level20Button;
    Button Level21Button;
    Button Level22Button;
    Button Level23Button;
    Button Level24Button;
    Button Level25Button;
    Button Level26Button;
    Button Level27Button;
    Button Level28Button;
    Button Level29Button;
    Button Level30Button;

    Label lvl1Label;
    Label lvl2Label;
    Label lvl3Label;
    Label lvl4Label;
    Label lvl5Label;
    Label lvl6Label;
    Label lvl7Label;
    Label lvl8Label;
    Label lvl9Label;
    Label lvl10Label;
    Label lvl11Label;
    Label lvl12Label;
    Label lvl13Label;
    Label lvl14Label;
    Label lvl15Label;
    Label lvl16Label;
    Label lvl17Label;
    Label lvl18Label;
    Label lvl19Label;
    Label lvl20Label;




    Skin arcade;
    Table buttonTable;
    Label title;
    Button quitButton;
    ActorBeta quitButtonTex;

    Table labelTable;

    Arrow arrow;


    //SOUND
    Sound clickSound;

    @Override
    public void initialize() {

        arcade = new Skin(Gdx.files.internal("arcade/skin/arcade-ui.json"));

        background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("Level_Background.png");
        background.setSize(WIDTH,HEIGHT);

        quitButtonTex = new ActorBeta();
        quitButtonTex.loadTexture("QuitButton.png");
        float quitAspectRatio = quitButtonTex.getWidth()/quitButtonTex.getHeight();
        quitButtonTex.setSize(HEIGHT /8 * quitAspectRatio, HEIGHT /8);
        quitButton = new Button(arcade);
        quitButton.add(quitButtonTex);
        quitButton.setPosition(WIDTH / 11, HEIGHT - HEIGHT / 6);

        mainStage.addActor(quitButton);

        title = new Label("Select level", arcade);
        title.setPosition(WIDTH / 4, HEIGHT * 13/ 16);
        float TextAspectRatio = title.getWidth()/title.getHeight();
        title.setFontScale(WIDTH/2000 * TextAspectRatio);
        mainStage.addActor(title);

        //=====================Arrow===============================
        arrow = new Arrow();
        arrow.setPosition(WIDTH* 27/32, Gdx.graphics.getHeight()/2);
        arrow.setSize(WIDTH/10, HEIGHT/10);
        mainStage.addActor(arrow);

        //____________________Level Buttons___________________
        Level1ButtonTex =  new ActorBeta();// mainStage);
        Level1ButtonTex.loadTexture("Level1.png");
        Level1ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level1Button = new Button(arcade);
        Level1Button.add(Level1ButtonTex);
        //Level1Button.add(lvl1Label);

        Level2ButtonTex =  new ActorBeta();// mainStage);
        Level2ButtonTex.loadTexture("Level2.png");
        Level2ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level2Button = new Button(arcade);
        Level2Button.add(Level2ButtonTex);

        Level3ButtonTex =  new ActorBeta();// mainStage);
        Level3ButtonTex.loadTexture("Level3.png");
        Level3ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level3Button = new Button(arcade);
        Level3Button.add(Level3ButtonTex);

        Level4ButtonTex =  new ActorBeta();// mainStage);
        Level4ButtonTex.loadTexture("Level4.png");
        Level4ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level4Button = new Button(arcade);
        Level4Button.add(Level4ButtonTex);

        Level5ButtonTex =  new ActorBeta();// mainStage);
        Level5ButtonTex.loadTexture("Level5.png");
        Level5ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level5Button = new Button(arcade);
        Level5Button.add(Level5ButtonTex);

        Level6ButtonTex =  new ActorBeta();// mainStage);
        Level6ButtonTex.loadTexture("Level6.png");
        Level6ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level6Button = new Button(arcade);
        Level6Button.add(Level6ButtonTex);

        Level7ButtonTex =  new ActorBeta();// mainStage);
        Level7ButtonTex.loadTexture("Level7.png");
        Level7ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level7Button = new Button(arcade);
        Level7Button.add(Level7ButtonTex);

        Level8ButtonTex =  new ActorBeta();// mainStage);
        Level8ButtonTex.loadTexture("Level8.png");
        Level8ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level8Button = new Button(arcade);
        Level8Button.add(Level8ButtonTex);

        Level9ButtonTex =  new ActorBeta();// mainStage);
        Level9ButtonTex.loadTexture("Level9.png");
        Level9ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level9Button = new Button(arcade);
        Level9Button.add(Level9ButtonTex);

        Level10ButtonTex =  new ActorBeta();// mainStage);
        Level10ButtonTex.loadTexture("Level10.png");
        Level10ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level10Button = new Button(arcade);
        Level10Button.add(Level10ButtonTex);

        Level11ButtonTex =  new ActorBeta();// mainStage);
        Level11ButtonTex.loadTexture("Level11.png");
        Level11ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level11Button = new Button(arcade);
        Level11Button.add(Level11ButtonTex);

        Level12ButtonTex =  new ActorBeta();// mainStage);
        Level12ButtonTex.loadTexture("Level12.png");
        Level12ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level12Button = new Button(arcade);
        Level12Button.add(Level12ButtonTex);

        Level13ButtonTex =  new ActorBeta();// mainStage);
        Level13ButtonTex.loadTexture("Level13.png");
        Level13ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level13Button = new Button(arcade);
        Level13Button.add(Level13ButtonTex);

        Level14ButtonTex =  new ActorBeta();// mainStage);
        Level14ButtonTex.loadTexture("Level14.png");
        Level14ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level14Button = new Button(arcade);
        Level14Button.add(Level14ButtonTex);

        Level15ButtonTex =  new ActorBeta();// mainStage);
        Level15ButtonTex.loadTexture("Level15.png");
        Level15ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level15Button = new Button(arcade);
        Level15Button.add(Level15ButtonTex);

        Level16ButtonTex =  new ActorBeta();// mainStage);
        Level16ButtonTex.loadTexture("Level16.png");
        Level16ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level16Button = new Button(arcade);
        Level16Button.add(Level16ButtonTex);

        Level17ButtonTex =  new ActorBeta();// mainStage);
        Level17ButtonTex.loadTexture("Level17.png");
        Level17ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level17Button = new Button(arcade);
        Level17Button.add(Level17ButtonTex);

        Level18ButtonTex =  new ActorBeta();// mainStage);
        Level18ButtonTex.loadTexture("Level18.png");
        Level18ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level18Button = new Button(arcade);
        Level18Button.add(Level18ButtonTex);

        Level19ButtonTex =  new ActorBeta();// mainStage);
        Level19ButtonTex.loadTexture("Level19.png");
        Level19ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level19Button = new Button(arcade);
        Level19Button.add(Level19ButtonTex);

        Level20ButtonTex =  new ActorBeta();// mainStage);
        Level20ButtonTex.loadTexture("LockedLevel.png");
        Level20ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level20Button = new Button(arcade);
        Level20Button.add(Level20ButtonTex);

        Level21ButtonTex =  new ActorBeta();// mainStage);
        Level21ButtonTex.loadTexture("StageButtonEdited.png");
        Level21ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level21Button = new Button(arcade);
        Level21Button.add(Level21ButtonTex);

        Level22ButtonTex =  new ActorBeta();// mainStage);
        Level22ButtonTex.loadTexture("StageButtonEdited.png");
        Level22ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level22Button = new Button(arcade);
        Level22Button.add(Level22ButtonTex);

        Level23ButtonTex =  new ActorBeta();// mainStage);
        Level23ButtonTex.loadTexture("StageButtonEdited.png");
        Level23ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level23Button = new Button(arcade);
        Level23Button.add(Level23ButtonTex);

        Level24ButtonTex =  new ActorBeta();// mainStage);
        Level24ButtonTex.loadTexture("StageButtonEdited.png");
        Level24ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level24Button = new Button(arcade);
        Level24Button.add(Level24ButtonTex);

        Level25ButtonTex =  new ActorBeta();// mainStage);
        Level25ButtonTex.loadTexture("StageButtonEdited.png");
        Level25ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level25Button = new Button(arcade);
        Level25Button.add(Level25ButtonTex);

        Level26ButtonTex =  new ActorBeta();// mainStage);
        Level26ButtonTex.loadTexture("StageButtonEdited.png");
        Level26ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level26Button = new Button(arcade);
        Level26Button.add(Level26ButtonTex);

        Level27ButtonTex =  new ActorBeta();// mainStage);
        Level27ButtonTex.loadTexture("StageButtonEdited.png");
        Level27ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level27Button = new Button(arcade);
        Level27Button.add(Level27ButtonTex);

        Level28ButtonTex =  new ActorBeta();// mainStage);
        Level28ButtonTex.loadTexture("StageButtonEdited.png");
        Level28ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level28Button = new Button(arcade);
        Level28Button.add(Level28ButtonTex);

        Level29ButtonTex =  new ActorBeta();// mainStage);
        Level29ButtonTex.loadTexture("StageButtonEdited.png");
        Level29ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level29Button = new Button(arcade);
        Level29Button.add(Level29ButtonTex);

        Level30ButtonTex =  new ActorBeta();// mainStage);
        Level30ButtonTex.loadTexture("StageButtonEdited.png");
        Level30ButtonTex.setSize(WIDTH / 8, HEIGHT / 8);
        Level30Button = new Button(arcade);
        Level30Button.add(Level30ButtonTex);



        //___________________________Level Buttons_____________________________//

        buttonTable = new Table(arcade);
        buttonTable.setPosition(WIDTH / 2, HEIGHT / 2);
        buttonTable.add(Level1Button, Level2Button, Level3Button, Level4Button, Level5Button);
        buttonTable.row();
        buttonTable.add(Level6Button, Level7Button, Level8Button, Level9Button, Level10Button);
        buttonTable.row();
        buttonTable.add(Level11Button, Level12Button, Level13Button, Level14Button, Level15Button);
        buttonTable.row();
        buttonTable.add(Level16Button, Level17Button, Level18Button, Level19Button, Level20Button);
        buttonTable.row();
       // buttonTable.add(Level25Button, Level26Button, Level27Button, Level28Button, Level29Button, Level30Button);
        //buttonTable.row();
        mainStage.addActor(buttonTable);


        labelTable = new Table(arcade);
        labelTable.setPosition(WIDTH / 2, HEIGHT / 2);
        labelTable.add(lvl1Label, lvl2Label, lvl3Label, lvl4Label, lvl5Label).padRight(150);
        labelTable.row();
        labelTable.add(lvl6Label, lvl7Label, lvl8Label, lvl9Label, lvl10Label).pad(1150);
        labelTable.row();
        labelTable.add(lvl11Label, lvl12Label, lvl13Label, lvl14Label, lvl15Label).pad(1150);
        labelTable.row();
        labelTable.add(lvl16Label, lvl17Label, lvl18Label, lvl19Label, lvl20Label).pad(1150);
        labelTable.row();
        mainStage.addActor(labelTable);

        //lvl1Label.setPosition(buttonTable.
        //mainStage.addActor(lvl1Label);

        //SOUND
        clickSound = Gdx.audio.newSound(Gdx.files.internal("Sound/click.wav"));

    }

    @Override
    public void update(float dt) {
        if(quitButton.isPressed())
        {
            clickSound.play();
            PirateBay.setActiveScreen(new TitleScreen());
        }

        if(Level1Button.isPressed())
        {
            clickSound.play();
            PirateBay.setActiveScreen(new TutorialScreen());
        }
    }
}
