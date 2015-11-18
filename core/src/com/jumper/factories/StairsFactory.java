package com.jumper.factories;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.jumper.Constants;
import com.jumper.Resources;
import com.jumper.entities.Stair;

import java.util.Random;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class StairsFactory extends Actor {
    private Array<com.jumper.entities.Stair> stairArray;
    private float maxStairCoordinateY;
    private com.jumper.entities.Stair stair;
    private Random random;

    public StairsFactory(){

        this.maxStairCoordinateY = 0;

        stairArray = new Array<com.jumper.entities.Stair>();
        random=new Random();

        initStairs();
    }

    private void initStairs(){
        stair = new com.jumper.entities.Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((5) / 2f, -5);
        stairArray.add(stair);

        stair = new com.jumper.entities.Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((7) / 2f, -1);
        stairArray.add(stair);

        stair = new Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((5) / 2f, 5);
        stairArray.add(stair);

        stair = new com.jumper.entities.Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition((8) / 2f, 10);
        stairArray.add(stair);

        stair = new com.jumper.entities.Stair();
        stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        stair.setPosition(-(8) / 2f, 15);
        stairArray.add(stair);

        stair = new com.jumper.entities.Stair();
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

        for(com.jumper.entities.Stair stair : stairArray){
            stair.draw();
            stair.act(10f);
        }

        //Gdx.app.log("app",String.valueOf(stairArray.size));

        onGameOver();
    }

    private void onGameOver(){
        if(Resources.getPlayer().getBodyPlayer().getPosition().y < maxStairCoordinateY-Constants.SCREEN_SIZE_HEIGHT){
            Resources.getPlayer().startNewGame();
        }
    }

    private void addStair(){
        //if(Math.round(Camera.cordsToMeters(Resources.getCamera()).y) > this.maxStairCoordinateY - 20f) {

        if(Resources.getPlayer().getBodyPlayer().getPosition().y > this.maxStairCoordinateY - 20 ){
        //if(20 == this.maxStairCoordinateY - Camera.cordsToMeters(Resources.getCamera()).y) {


            stair = new com.jumper.entities.Stair();

            float xPos = MathUtils.random(-10, 10);
            //float xPos = 0;
            float yPos = this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;

            stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
            stair.setPosition(xPos, yPos);

            //Gdx.app.log("maxf", String.valueOf(Camera.cordsToMeters(Resources.getCamera()).y) + "   " + Resources.getPlayer().getBodyPlayer().getPosition().y + "  " + String.valueOf(yPos));


            addSpiral(xPos  + MathUtils.random(Constants.STAIR_LENGTH/2f - .5f), yPos);
            Resources.getSnowBallFactory().addSnowBall(xPos,yPos + 3);

            maxStairCoordinateY = maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;
            stairArray.add(stair);
        }
    }

    private void addSpiral(float xPos, float yPos) {
        if(MathUtils.random(2) == 1 && stair.notMovingLeftRight()){
            Resources.getSpiralFactory().addSpiral(new com.jumper.entities.Spiral(), xPos, yPos);
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

            //Gdx.app.log("app", "destroy");

            Resources.getSpiralFactory().removeSpiral();
            stairArray.removeValue(stair, true);
            stair.destroyStair();
            stair.dispose();


        }
    }


    public void dispose() {
        for(com.jumper.entities.Stair stair : stairArray){
            stair.dispose();
        }
    }


}
