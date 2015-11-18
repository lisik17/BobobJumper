package com.jumper.factories;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.jumper.entities.Spiral;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class SpiralFactory {

    private Array<Spiral> spiralArray;
    private Pool<Spiral> spiralPool;

    private Spiral spiral;

    public SpiralFactory(){
        spiralArray = new Array<Spiral>();
        spiralPool = new Pool<Spiral>() {
            @Override
            protected Spiral newObject() {
                return new Spiral();
            }
        };
    }

    public void addSpiral(float x, float y, boolean stairNotMovingLeftRight){
        if (canCreateSpiralOnStair(stairNotMovingLeftRight)) {
            spiral = spiralPool.obtain();
            spiral.init(x,y);
            spiralArray.add(spiral);
        }
    }

    private boolean canCreateSpiralOnStair(boolean stairNotMovingLeftRight) {
        return MathUtils.random(2) == 1 && stairNotMovingLeftRight;
    }

    public void removeSpiral(){
        if(spiralArray.size == 0){
            return;
        }
        spiral = spiralArray.get(0);

        if( spiral.readyToBeDestroyed()){
            spiralArray.removeValue(spiral, true);
            spiralPool.free(spiral);
        }
    }

    public void act(float delta) {
        for(Spiral spiral : spiralArray){
            spiral.act(10f);
        }
    }

    public void dispose(){
        for(Spiral spiral: spiralArray){
            spiral.destroySpiral();
            spiral.dispose();
        }
    }

}
