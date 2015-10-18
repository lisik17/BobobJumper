package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Roma-Alisa on 9/25/2015.
 */

public class Background extends Actor{
    private Texture background;
    private float currentBgY;
    private Sprite sprite;


    private long lastTimeBg;

    private SpriteBatch batch;

    public Background(){
        currentBgY = Constants.SCREEN_PIXELS_SIZE_WIDTH;

        lastTimeBg = TimeUtils.nanoTime();

        background = new Texture(Gdx.files.internal("sky.png"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        sprite = new Sprite(background);
        sprite.setSize(Constants.SCREEN_PIXELS_SIZE_WIDTH,Constants.SCREEN_PIXELS_SIZE_HEIGHT);
        batch = new SpriteBatch();
    }

    @Override
    public void act(float delta) {
        batch.begin();
        sprite.setPosition(0, currentBgY - Constants.SCREEN_PIXELS_SIZE_HEIGHT);
        sprite.draw(batch);
        sprite.setPosition(0, currentBgY);
        sprite.draw(batch);
        batch.end();

        if(TimeUtils.nanoTime() - lastTimeBg > 100000000){
            currentBgY -= 2;
            lastTimeBg = TimeUtils.nanoTime();
        }

// if the seprator reaches the screen edge, move it back to the first position
        if(currentBgY <= 0){
            currentBgY = Constants.SCREEN_PIXELS_SIZE_HEIGHT;
        }
    }

    public void dispose(){
        sprite.getTexture().dispose();
        background.dispose();
        batch.dispose();
    }
}
