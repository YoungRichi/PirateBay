package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ExplosionEffect extends ActorBeta {

    String[] str = {"Explosion1.png", "Explosion2.png", "Explosion3.png"};

    Animation IdleAnim = loadAnimationFromFiles(str, 0.1f, true);

    public ExplosionEffect(float x, float y, Stage s) {
        super(x, y, s);

        setAnimation(IdleAnim);
        setSize(Gdx.graphics.getHeight() / 8 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 8);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        addAction(Actions.sequence(Actions.fadeOut(1), Actions.removeActor()));
    }
}
