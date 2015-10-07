package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Roma-Alisa on 9/27/2015.
 */
public class Font extends Actor{
    private SpriteBatch batch;
    private BitmapFont font;
    private String message;
    private GlyphLayout layout;
    private float deltaFade;
    private boolean isFadeIn;


    public Font(){
        this.create();
    }

    public void create () {

        deltaFade = 0;
        isFadeIn = true;
        batch = new SpriteBatch();
        message = "hhhh";
        font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        //font.setColor(Color.BLUE);
        font.getData().setScale(Gdx.graphics.getWidth() / Constants.STRING_SIZE_RATIO);

    }

    public void act () {
        batch.begin();
        layout = new GlyphLayout(font, message);

        fadeIn();
        fadeOut();

        font.draw(batch, Long.toString(Resources.getScore()),  50, Gdx.graphics.getHeight() - 50);
        batch.end();
    }

    public void setDeltaFade(float delta) {
        this.deltaFade = delta;
    }

    private void fadeIn() {
        if(isFadeIn && deltaFade < 3 ) {
            deltaFade += 0.05f;
            font.getData().setScale(deltaFade);
            return;
        }
        isFadeIn = false;
    }

    private void fadeOut() {
        if(!isFadeIn && deltaFade > 1) {
            deltaFade -= 0.05f;
            font.getData().setScale(deltaFade);
            return;
        }
        isFadeIn = true;
    }

    public void setStringMessage(String message){
        this.message = message;
    }


    public void dispose() {
        batch.dispose();
        font.dispose();

    }
}
