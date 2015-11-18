package com.jumper.factories;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.jumper.Constants;
import com.jumper.Resources;
import com.jumper.entities.Stair;
import java.util.Random;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class StairsFactory extends Actor {

    private Array<Stair> stairArray;
    private Pool<Stair> stairPool;

    private float maxStairCoordinateY;
    private Stair stair;
    private Random random;

    public StairsFactory() {

        this.maxStairCoordinateY = 0;

        stairArray = new Array<Stair>();
        stairPool = new Pool<Stair>() {
            @Override
            protected Stair newObject() {
                return new Stair();
            }
        };

        random = new Random();

        initStairs();
    }

    private void initStairs() {
        stair = stairPool.obtain();
        stair.init((5) / 2f, -5);
        stairArray.add(stair);

        stair = stairPool.obtain();
        stair.init((7) / 2f, -1);
        stairArray.add(stair);

        stair = stairPool.obtain();
        stair.init((5) / 2f, 5);
        stairArray.add(stair);

        stair = stairPool.obtain();
        stair.init((8) / 2f, 10);
        stairArray.add(stair);

        stair = stairPool.obtain();
        stair.init(-(8) / 2f, 15);
        stairArray.add(stair);

        stair = stairPool.obtain();
        stair.init((-7) / 2f, 20);
        stairArray.add(stair);

        this.maxStairCoordinateY = 20;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        addStair();
        removeStair();

        for (Stair stair : stairArray) {
            stair.draw();
            stair.act(10f);
        }


        onGameOver();
    }

    private void onGameOver() {
        if (Resources.getPlayer().getBodyPlayer().getPosition().y < maxStairCoordinateY - Constants.SCREEN_SIZE_HEIGHT) {
            Resources.getPlayer().startNewGame();
        }
    }

    private void addStair() {

        if (Resources.getPlayer().getBodyPlayer().getPosition().y > this.maxStairCoordinateY - 20) {

            float xPos = MathUtils.random(-10, 10);
            float yPos = this.maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;

            stair = stairPool.obtain();
            stair.init(xPos, yPos);

            Resources.getSpiralFactory().addSpiral(xPos + MathUtils.random(Constants.STAIR_LENGTH / 2f - .5f), yPos, stair.notMovingLeftRight());
            Resources.getSnowBallFactory().addSnowBall(xPos, yPos + 3);

            maxStairCoordinateY = maxStairCoordinateY + Constants.SPACE_BETWEEN_STAIRS_Y;
            stairArray.add(stair);
        }
    }

    private void removeStair() {
        if (stairArray.size == 0) {
            return;
        }
        stair = stairArray.get(0);

        if (stair.readyToBeDestroyed()) {
            Resources.getSpiralFactory().removeSpiral();
            stairArray.removeValue(stair, true);
            stairPool.free(stair);
        }
    }


    public void dispose() {
        for (Stair stair : stairArray) {
            stair.destroyStair();
            stair.dispose();
        }
    }


}
