package com.jumper.factories;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.jumper.Resources;
import com.jumper.entities.SnowBall;
import java.util.Random;

/**
 * Created by Roma-Alisa on 18/11/15.
 */
public class SnowBallFactory extends Actor {

    private Array<SnowBall> snowBallArray;
    private Pool<SnowBall> snowBallPool;

    private SnowBall snowBall;
    private Random random;

    public SnowBallFactory() {
        snowBallArray = new Array<SnowBall>();
        snowBallPool = new Pool<SnowBall>() {
            @Override
            protected SnowBall newObject() {
                return new SnowBall();
            }
        };
        random = new Random();
    }

    public void addSnowBall(float x, float y){
        if(random.nextInt(7) != 2){
            return;
        }

        snowBall = snowBallPool.obtain();
        snowBall.init(x,y);
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
            snowBallArray.removeValue(snowBall, true);
            snowBallPool.free(snowBall);
        }
    }

    public void dispose(){
        for(SnowBall snowBall : snowBallArray){
            snowBall.destroySnowBall();
            snowBall.dispose();
        }
    }



}
