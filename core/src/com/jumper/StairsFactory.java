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

        onGameOver();
    }

    private void onGameOver(){
        if(Resources.getPlayer().getBodyPlayer().getPosition().y < maxStairCoordinateY-Constants.SCREEN_SIZE_HEIGHT){
            Resources.getPlayer().startNewGame();
        }
    }

    private void addStair(){
        if(Camera.cordsToMeters(Resources.getCamera()).y > this.maxStairCoordinateY - 20) {
            stair = new Stair();

            float xPos = MathUtils.random(-10, 10);
            float yPos = this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;

            stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
            stair.setPosition(xPos, yPos);

            addSpiral(xPos  + MathUtils.random(Constants.STAIR_LENGTH/2f - .5f), yPos);

            maxStairCoordinateY = maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;
            stairArray.add(stair);
        }
    }

    private void addSpiral(float xPos, float yPos) {
        if(MathUtils.random(5) == 1 && stair.notMovingLeftRight()){
            Resources.getSpiralFactory().addSpiral(new Spiral(), xPos, yPos);
        }
    }

    private int randomMinusPlus() {
        return random.nextBoolean() ? 1 : -1;
    }

    private void removeStair(){
        if(stairArray.size == 0){
            return;
        }

        stair = stairArray.get(0);

        if(stair.readyToBeDestroyed()){

            Resources.getSpiralFactory().removeSpiral();
            stairArray.removeValue(stair, true);
            stair.destroyStair();
            stair.dispose();


        }
    }


    public void dispose() {
        for(Stair stair : stairArray){
            stair.dispose();
        }
    }


}
