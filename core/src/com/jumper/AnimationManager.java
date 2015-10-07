package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Roma-Alisa on 10/1/2015.
 */
public class AnimationManager extends Actor {
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Animation animation;
    private float timePassed;
    private Sprite sprite;
    float ballCordsPixelsX;
    float ballCordsPixelsY;

    public AnimationManager(){
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("shooter.pack"));
        animation = new Animation(1/3f,atlas.getRegions());
        sprite = new Sprite();

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        drawAnimation();
    }

    private void drawAnimation() {
        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        // sprite = new Sprite(animation.getKeyFrame(timePassed,true));
        sprite.setRegion(animation.getKeyFrame(timePassed,true));
        metersToPixels();
        //float ballCordsPixelsY = Resources.getPlayerBody().getPosition().y * Constants.SCREEN_RATIO_HEIGHT ;
        batch.draw(sprite,ballCordsPixelsX,ballCordsPixelsY-Resources.getCamera().position.y*Constants.SCREEN_RATIO_HEIGHT,20,20);
        batch.end();
    }

    private void metersToPixels() {
        ballCordsPixelsX = Resources.getPlayerBody().getPosition().x * Constants.SCREEN_RATIO_WIDTH + Gdx.graphics.getWidth() / 2f ;
        ballCordsPixelsY = Resources.getPlayerBody().getPosition().y * Constants.SCREEN_RATIO_HEIGHT + Gdx.graphics.getHeight()/ 2f;

        ballCordsPixelsX = ballCordsPixelsX - Constants.PLAYER_WIDTH*Constants.SCREEN_RATIO_WIDTH/2f;
        ballCordsPixelsY = ballCordsPixelsY - Constants.PLAYER_HEIGHT*Constants.SCREEN_RATIO_HEIGHT/2f;
    }

    public void dispose(){
        sprite.getTexture().dispose();
        batch.dispose();
        atlas.dispose();
    }

}
