package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class MediumBoatBall extends ActorBeta {

    public MediumBoatBall(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("Cannonball.png");
        setSize(Gdx.graphics.getHeight() / 30 * getWidth()/getHeight(), Gdx.graphics.getHeight() / 30);
        setBoundaryRectangle();
        setSpeed(100);
        setMotionAngle(180);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        if (!ScreenBeta.loseGame) {
            applyPhysics(dt);
            rotateBy(10);
            setOrigin(getWidth()/2, getHeight()/2);
            for (Barricade barricade : ActorBeta.getListBarricade()) {
                if (overlaps(barricade)) {
                    preventOverlap(barricade);
                    setMotionAngle(getMotionAngle() + 180);
                    Vector2 barricadePos = new Vector2(barricade.getX(), barricade.getY());
                    Vector2 mediumBoatBallPos = new Vector2(getX(), getY());
                    Vector2 hitVector = barricadePos.sub(mediumBoatBallPos);
                    float offset = 0.1f;
                    barricade.addAction(Actions.sequence(Actions.moveBy(hitVector.x * offset, hitVector.y * offset),
                            Actions.delay(0.1f), Actions.moveBy(hitVector.x * -1 * offset, hitVector.y * -1 * offset)));
                    remove();
                    ExplosionEffect explosionEffect = new ExplosionEffect(getX(), getY(), ScreenBeta.mainStage);
                    barricade.healthCurr -= barricade.mediumDamRate;
                    //System.out.println(barricade.healthCurr);
                }
            }
        }
    }
}
