package com.mygdx.game;

public class Level01 extends MyScreenBeta {
    @Override
    public void EnemiesInit() {
        levelNum = 1; // define the current level
        // array of size ScreenBeta.waveNum will be used as default. Only the first waveNum values will be used
        smallBoatNums = new int[] {1,1,2,2,2};
        medBoatNums = new int[]{2,2,3,3,3};
        bigBoatNums = new int[]{0,0,1,1,2};
        //highSpeedBoatNums = new int[]{};
        parrotNums = new int[]{0,0,0,0,1};
        CreateBoatSmall(8);
        CreateBoatMedium(13);
    }

    @Override
    public ScreenBeta GetScreen() {
        return new Wave1(); // return next level if current level is cleared.
    }
}
