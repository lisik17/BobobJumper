package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;



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
    private static boolean applyFontEffect;
    private State state;


    public Font(){
        state = State.NONE;
        this.create();
    }

    private enum State{
        NONE,GAME_OVER,UPDATE_SCORE;
    }

    public void create () {

        TECore tc = new TECore();
        tc.render();

        deltaFade = 0;
        isFadeIn = true;
        applyFontEffect = false;

        batch = new SpriteBatch();
        message = "";
        font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        font.setColor(Color.GREEN);
        font.getData().setScale(Gdx.graphics.getWidth() / Constants.STRING_SIZE_RATIO);

        layout = new GlyphLayout(font, message);

    }

    public void act () {
        batch.begin();

       // TECore te = new TECore();
        //te.render();
        //layout.setText(font, message);
        //layout = new GlyphLayout(font, message);

        if(state == State.UPDATE_SCORE) {
            //if (applyFontEffect) {
                //fadeIn();
                //fadeOut();

            //this.addAction(Actions.moveTo(Constants.SCREEN_PIXELS_SIZE_WIDTH * (.4f), Constants.SCREEN_PIXELS_SIZE_HEIGHT * (.3f), .5f));
            state = State.NONE;
            //}
        }

        font.draw(batch, Long.toString(Resources.getScore()), Constants.SCREEN_PIXELS_SIZE_WIDTH * .1f, Constants.SCREEN_PIXELS_SIZE_HEIGHT * .9f);

        if(state == State.GAME_OVER) {
            setStringMessage("your score is : " + Resources.getScore());
            layout.setText(font, message);
            font.draw(batch, message, Constants.SCREEN_PIXELS_SIZE_WIDTH / 2 - layout.width / 2, Constants.SCREEN_PIXELS_SIZE_HEIGHT * .6f);

            setStringMessage("game over");
            layout.setText(font, message);
            font.draw(batch,message, Constants.SCREEN_PIXELS_SIZE_WIDTH/2 - layout.width / 2, Constants.SCREEN_PIXELS_SIZE_HEIGHT/2);
        }

        batch.end();
    }



    public void setStateGameOver(){
        state = State.GAME_OVER;
    }

    public void setStateUpdateScore(){
        state = State.UPDATE_SCORE;
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

    public static void applyFontEffect(){
        applyFontEffect = true;

        Timer timer = new Timer();
        Task task = timer.scheduleTask(new Task() {
            @Override
            public void run () {
                //Gdx.app.log("TimerTest", "ping");
                applyFontEffect = false;

            }
        }, 1.55f);


    }

    public void setStringMessage(String message){
        this.message = message;
    }


    public void dispose() {
        batch.dispose();
        font.dispose();

    }
}
