package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ScoreBoard extends ActorBeta {

    public ScoreBoard(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("Scroll.png");
        setSize(Gdx.graphics.getHeight() /2 * getWidth()/getHeight(), Gdx.graphics.getHeight() /2);
    }

    public ScoreBoard() {
        loadTexture("Scroll.png");
    }
}
