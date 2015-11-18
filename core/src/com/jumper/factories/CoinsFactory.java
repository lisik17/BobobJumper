package com.jumper.factories;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.jumper.Camera;
import com.jumper.Constants;
import com.jumper.Resources;
import com.jumper.entities.Coin;

/**
 * Created by Roma-Alisa on 10/7/2015.
 */
public class CoinsFactory extends Actor {

    private Array<Coin> coinArray;
    private Pool<Coin> coinPool;

    private Coin coin;
    private int maxCoinCoordinateY;

    public CoinsFactory(){
        coinArray = new Array<Coin>();
        coinPool = new Pool<Coin>() {
            @Override
            protected Coin newObject() {
                return new Coin();
            }
        };

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
            coin = coinPool.obtain();
            coin.init(MathUtils.random(-5,5),this.maxCoinCoordinateY + 5 + Constants.SPACE_BETWEEN_STAIRS_Y);
            maxCoinCoordinateY = maxCoinCoordinateY + Constants.SPACE_BETWEEN_COINS_Y;
            coinArray.add(coin);
        }
    }

    private void removeCoin(){
        coin = coinArray.get(0);

        if(Resources.getPlayer().getBodyPlayer().getPosition().y - 20 > coin.getBodyCoin().getPosition().y) {
            coinArray.removeValue(coin,true);
            coinPool.free(coin);
        }
    }

    public void dispose() {
        for(Coin coin : coinArray){
            coin.destroyCoin();
            coin.dispose();
        }
    }
}
