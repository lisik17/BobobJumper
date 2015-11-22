package com.jumper.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jumper.Constants;
import com.jumper.Player;
import com.jumper.Resources;

/**
 * Created by Roma-Alisa on 22/11/15.
 */
public class JumpEffect extends Actor {

    private ParticleEffect effect;
    private SpriteBatch batch;
    private State state;

    private enum State{
        NONE, PLAY_EFFECT;
    }

    public JumpEffect(){
        batch = new SpriteBatch();
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/flame.p"), Gdx.files.internal("effects"));
        effect.setPosition(xMetersToPixels(Resources.getPlayer().getBodyPlayer().getPosition().x),
                yMetersToPixels(Resources.getPlayer().getBodyPlayer().getPosition().y));
        effect.start();

        state = State.NONE;
    }

    public void setEffectStatePlay(){
        state = State.PLAY_EFFECT;
    }

    @Override
    public void act(float delta) {

        batch.begin();

        playEffect(delta);

        batch.end();

    }

    private void playEffect(float delta) {
        if(state == State.PLAY_EFFECT) {
            if (effect.isComplete()) {
                effect.reset();
                state = State.NONE;
                return;
            }
            effect.setPosition(xMetersToPixels(Resources.getPlayer().getBodyPlayer().getPosition().x),
                    yMetersToPixels(0));
            effect.draw(batch, delta);
            //
        }
    }

    private float yMetersToPixels(float yMeters) {
        return -1.5f * Constants.SCREEN_RATIO_HEIGHT + Gdx.graphics.getHeight() / 2f;
    }

    private float xMetersToPixels(float xMeters) {
        return xMeters * Constants.SCREEN_RATIO_WIDTH + Gdx.graphics.getWidth() / 2f;
    }

    public void dispose(){
        batch.dispose();
        effect.dispose();
    }
}
