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
            //stair.draw();
            stair.act(10f);
        }
    }

    private void addStair(){
        if(Camera.cordsToMeters(Resources.getCamera()).y > this.maxStairCoordinateY - 20) {
            stair = new Stair();

            //stairLength = MathUtils.random(6,10);

            stair.setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);

            leftRightStair *= -1;

            //stair.setPosition(leftRightStair*((stairLength - Constants.SCREEN_SIZE_WIDTH)/2f), this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y);
            stair.setPosition(MathUtils.random(-10,10), this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y);
            maxStairCoordinateY = maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;
            stairArray.add(stair);
        }
    }

    private int randomMinusPlus() {
        return random.nextBoolean() ? 1 : -1;
    }

    private void removeStair(){

        if(Resources.getPlayer().getBodyPlayer().getPosition().y - 14 > stairArray.get(0).getStairBody().getPosition().y){
            if(stairArray.get(0).getStairBody().getAngularVelocity() == 0) {
                stairArray.get(0).getStairBody().setAngularVelocity(MathUtils.random(-3, 3));
                stairArray.get(0).getStairBody().setLinearVelocity(0, -5);
            }
            //stairArray.removeValue(stairArray.get(0), true);

            if(Resources.getPlayer().getBodyPlayer().getPosition().y -20 > stairArray.first().getStairBody().getPosition().y) {
                Stair st = stairArray.get(0);
                stairArray.removeValue(stairArray.get(0), true);
                st.destroyStair();

                //stairArray.get(0).dispose();
                //stairArray.removeIndex(0);
            }
        }
    }


    public void dispose() {
        for(Stair stair : stairArray){
            stair.dispose();
        }
    }


}
