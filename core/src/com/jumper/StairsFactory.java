package com.jumper;


import com.badlogic.gdx.math.MathUtils;
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
    private Random random;

    public StairsFactory(){

        this.maxStairCoordinateY = 0;

        stairArray = new Array<Stair>();
        random=new Random();

        initStairs();
    }

    private void initStairs(){
        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((5) / 2f, -5);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((7) / 2f, 0);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((5) / 2f, 5);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((8) / 2f, 10);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition(-(8) / 2f, 15);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((-7) / 2f, 20);
        stairArray.add(stair);

        this.maxStairCoordinateY = 20;

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        addStair();
        removeStair();

        for(Stair stair : stairArray){
            stair.draw();
            stair.act(10f);
        }
    }

    private void addStair(){
        if(Camera.cordsToMeters(Resources.getCamera()).y > this.maxStairCoordinateY - 20) {
            stair = new Stair();

            stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
            stair.setPosition(MathUtils.random(-10,10), this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y);

            maxStairCoordinateY = maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;
            stairArray.add(stair);
        }
    }

    private int randomMinusPlus() {
        return random.nextBoolean() ? 1 : -1;
    }

    private void removeStair(){
        stair = stairArray.get(0);

        if(stair.readyToBeDestroyed()){
            stairArray.removeValue(stair, true);
            stair.destroyStair();
        }
    }


    public void dispose() {
        for(Stair stair : stairArray){
            stair.dispose();
        }

    }


}
