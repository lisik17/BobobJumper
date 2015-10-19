package com.jumper;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class SpiralFactory {
    private Array<Spiral> spiralArray;
    private Spiral spiral;

    public SpiralFactory(){
        spiralArray = new Array<Spiral>();
    }

    public void addSpiral(Spiral spiral, float x, float y){
        spiral.setPosition(x,y);
        spiralArray.add(spiral);
    }

    public void removeSpiral(){
        if(spiralArray.size == 0){
            return;
        }
        spiral = spiralArray.get(0);
        if( spiral.readyToBeDestroyed()){
            spiralArray.removeValue(spiral,true);
            spiral.destroySpiral();
            spiral.dispose();
        }
    }

    public void dispose(){
        for(Spiral spiral: spiralArray){
            spiral.dispose();
        }
    }

}
