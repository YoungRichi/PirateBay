package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CannonBall extends ActorBeta {

    CannonBall() {

//        String[] planeAnim = {"Cannonball.png", "Explosion1.png", "Explosion2.png", "Explosion3.png"};
        String[] planeAnim = {"cannonTesting.png"};

        loadAnimationFromFiles(planeAnim, 0.1f, true);

        this.setBoundaryRectangle();
        //this.setBoundaryCircle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
        //boundToWorld();
    }
}
