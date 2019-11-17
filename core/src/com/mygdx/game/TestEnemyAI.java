package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestEnemyAI extends ActorBeta
{
    String[] goString = {"planeGreen0.png", "planeGreen1.png", "planeGreen2.png"};

    Animation<TextureRegion>goAnim;
    enum EnemyState
    {
        GoLeft,
        GoUp,
        GoDown,
        DeployTroops,
        Death
    }

    public EnemyState enemyState;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    public TestEnemyAI()
    {
        setMotionAngle(180);

        goAnim = loadAnimationFromFiles(goString, 0.5f, true);

        enemyState = EnemyState.GoLeft;

        this.setPosition(WIDTH, HEIGHT/2);
        this.setScale(this.getScaleX() * -1 * 2, this.getScaleY() * 2);
        this.setBoundaryRectangle();
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);

        switch (enemyState)
        {
            case GoLeft:
                GoLeft();
                break;

            case GoUp:
                GoUp();
                break;

            case GoDown:
                GoDown();
                break;

            default:
                break;
        }
    }

    void GoLeft()
    {
        setAnimation(goAnim);
        moveBy(-5, 0);
    }

    void GoUp()
    {
        setAnimation(goAnim);
        moveBy(0, 5);
    }

    void GoDown()
    {
        setAnimation(goAnim);
        moveBy(0, -5);
    }
}
