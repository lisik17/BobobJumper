package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Roma-Alisa on 9/25/2015.
 */
public class Background extends Actor{
    Texture background;
    float currentBgX;

    long lastTimeBg;

    private SpriteBatch batch;

    public Background(){
        currentBgX = 800;

        lastTimeBg = TimeUtils.nanoTime();

        background = new Texture(Gdx.files.internal("background.jpg"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        batch = new SpriteBatch();
    }

    @Override
    public void act(float delta) {
        batch.begin();
        batch.draw(background , 0, currentBgX - 800);
        batch.draw(background, 0, currentBgX - 300);
        batch.end();

        if(TimeUtils.nanoTime() - lastTimeBg > 100000000){
            currentBgX -= 1;
            lastTimeBg = TimeUtils.nanoTime();
        }

// if the seprator reaches the screen edge, move it back to the first position
        if(currentBgX == 0){
            currentBgX = 800;
        }
    }

    public void dispose(){
        background.dispose();
        batch.dispose();
    }
}
