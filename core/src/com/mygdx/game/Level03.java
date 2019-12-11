package com.mygdx.game;

public class Level03 extends MyScreenBeta {

    @Override
    public void EnemiesInit() {
        levelNum = 3; // define the current level
        waveNum = 2;
        waves = new boolean[waveNum];
        for(int i = 0; i < waves.length; i++)
        {
            waves[i] = false;
        }

        smallBoatNums = new int[] {1,1};//{1,1,2,2,2}; // value of each element is the number of the object each wave
        medBoatNums = new int[]{1,1};//{2,2,3,3,3};
        bigBoatNums = new int[]{1,1};//{0,0,1,1,2};
        fastBoatNums = new int[]{1,1};//{0,0,0,1,1};
        parrotNums = new int[]{0,1};//{0,0,0,0,1};

        // the parameter should be less than 14 due to the size of the object and the height of the screen
        CreateBoatSmall(2); // sum of all elements in the smallBoatNums array
        CreateBoatMedium(2); // sum of all elements in the medBoatNums array
        CreateBoatBig(2); // sum of all elements in the bigBoatNums array
        CreateBoatFast(2); // sum of all elements in the fastBoatNums array
        CreateParrot(1); // sum of all elements in the parrotNums array

        lifePickup.setPosition(WIDTH * 3 / 4, HEIGHT); // y coordinate should always be HEIGHT
        barricadeHealthPickup.setPosition(WIDTH/2, HEIGHT); // y coordinate should always be HEIGHT
        hasLifePickup = true; // when this is set to true, its timer must be set
        hasHealthPickup = true;
        lifePickupSpawnTimer = 10; // in seconds. The pickup will be spawned when the timer reaches zero
        healthPickupSpawnTimer = 10; // in second. The pickup will be spawned when the timer reaches zero

    }

    @Override
    public ScreenBeta GetScreen() {
        return new Level04();
    }
}

