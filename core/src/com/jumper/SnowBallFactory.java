package com.jumper;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Roma-Alisa on 18/11/15.
 */
public class SnowBallFactory extends Actor {
    private Array<SnowBall> snowBallArray;
    private SnowBall snowBall;
    private Random random;

    public SnowBallFactory() {
        snowBallArray = new Array<SnowBall>();
        random = new Random();
    }

    public void addSnowBall(float x, float y){
        if(random.nextInt(7) != 2){
            return;
        }

        snowBall = new SnowBall();
        snowBall.setPosition(x, y);
        snowBallArray.add(snowBall);
    }

    @Override
    public void act(float delta){
        super.act(delta);

        removeSnowBall();
    }


    private void removeSnowBall(){
        if(snowBallArray.size < 1){
            return;
        }

        snowBall = snowBallArray.get(0);

        if(Resources.getPlayer().getBodyPlayer().getPosition().y - 20 > snowBall.getBodySnowBall().getPosition().y){
            snowBallArray.removeValue(snowBall,true);
            snowBall.destroySnowBall();
            snowBall.dispose();
        }
    }

    public void dispose(){
        for(SnowBall snowBall : snowBallArray){
            snowBall.dispose();
        }
    }



}
