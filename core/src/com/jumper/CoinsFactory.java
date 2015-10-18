package com.jumper;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Roma-Alisa on 10/7/2015.
 */
public class CoinsFactory extends Actor {
    private Array<Coin> coinArray;
    private Coin coin;
    private int maxCoinCoordinateY;


    public CoinsFactory(){
        coinArray = new Array<Coin>();

        this.maxCoinCoordinateY = 5;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        addCoin();
        removeCoin();

        for(Coin coin : coinArray){
            coin.draw();
        }
    }

    private void addCoin(){
        if(Camera.cordsToMeters(Resources.getCamera()).y > this.maxCoinCoordinateY - 20){
            coin = new Coin();
            coin.setPosition(MathUtils.random(-5,5),this.maxCoinCoordinateY + 5 + Constants.SPACE_BETWEEN_STAIRS_Y);
            maxCoinCoordinateY = maxCoinCoordinateY + Constants.SPACE_BETWEEN_COINS_Y;
            coinArray.add(coin);
        }
    }

    private void removeCoin(){
        if(coinArray.size > Constants.MAXIMUM_COINS) {
            coinArray.first().dispose();
            coinArray.removeIndex(0);
        }
    }

    public void dispose() {
        for(Coin coin : coinArray){
            coin.dispose();
        }
    }
}
