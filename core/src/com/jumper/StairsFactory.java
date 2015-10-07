package com.jumper;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class StairsFactory extends Actor {
    private Array<Stair> stairArray;
    private int maxStairCoordinateY;
    private Stair stair;
    private int stairLength;
    private Random random;
    private int leftRightStair = -1;

    public StairsFactory(){

        this.maxStairCoordinateY = 0;

        stairArray = new Array<Stair>();
        random=new Random();

        initStairs();
    }

    private void initStairs(){
        stair = new Stair();
        stair.setSize(8, Constants.STAIR_WIDTH);
        stair.setPosition((8 - 24) / 2f, -5);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(10, Constants.STAIR_WIDTH);
        stair.setPosition((24 - 10) / 2f, 0);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(9, Constants.STAIR_WIDTH);
        stair.setPosition((9 - 24) / 2f, 5);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(9, Constants.STAIR_WIDTH);
        stair.setPosition((24 - 9) / 2f, 10);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(9, Constants.STAIR_WIDTH);
        stair.setPosition(-(24 - 9) / 2f, 15);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(10, Constants.STAIR_WIDTH);
        stair.setPosition((24 - 10) / 2f, 20);
        stairArray.add(stair);

        this.maxStairCoordinateY = 20;

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        addStair();
        removeStair();
    }

    private void addStair(){
        if(Camera.cordsToMeters(Resources.getCamera()).y > this.maxStairCoordinateY - 20) {
            stair = new Stair();

            stairLength = MathUtils.random(6,10);

            stair.setSize(stairLength, Constants.STAIR_WIDTH);

            leftRightStair *= -1;

            stair.setPosition(leftRightStair*((stairLength - Constants.SCREEN_SIZE_WIDTH)/2f), this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y);
            maxStairCoordinateY = maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;
            stairArray.add(stair);
        }
    }

    private int randomMinusPlus() {
        return random.nextBoolean() ? 1 : -1;
    }

    private void removeStair(){
        if(stairArray.size > Constants.MAXIMUM_STAIRS) {
            stairArray.first().dispose();
            stairArray.removeIndex(0);
        }
    }


    public void dispose() {
        for(Stair stair : stairArray){
            stair.dispose();
        }
    }


}
