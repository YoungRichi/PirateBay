package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ball extends ActorBeta {

    float fireDurationMax = 5f;

    public Ball(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("Cannonball.png");
        setSize(Gdx.graphics.getHeight() / 25 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 25);
        setColor(Color.RED);
        float width = getWidth();
        float height = getHeight();
        float originX = getX() + width / 2;
        float originY = getY() + height / 2;
        addAction(Actions.sequence(Actions.delay(0.5f), Actions.parallel(Actions.sizeTo(width * 2,height * 2, fireDurationMax),
                Actions.moveTo(originX - width, originY - height, fireDurationMax))));

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        for (CannonBall cannonBall : getListCannonBall())
        {
            cannonBall.setSize(getWidth(), getHeight());
            cannonBall.setBoundaryRectangle();
            cannonBall.setColor(Color.RED);
            if(cannonBall.isFiring || cannonBall.fireTimer < 0.1f)
                remove();
        }
    }
}
