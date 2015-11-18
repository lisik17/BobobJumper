package com.jumper.factories;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class SpiralFactory {
    private Array<com.jumper.entities.Spiral> spiralArray;
    private com.jumper.entities.Spiral spiral;

    public SpiralFactory(){
        spiralArray = new Array<com.jumper.entities.Spiral>();
    }

    public void addSpiral(com.jumper.entities.Spiral spiral, float x, float y){
        spiral.setPosition(x, y);
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

    public void act(float delta) {
        for(com.jumper.entities.Spiral spiral : spiralArray){
            spiral.act(10f);
        }
    }

    public void dispose(){
        for(com.jumper.entities.Spiral spiral: spiralArray){
            spiral.dispose();
        }
    }

}
