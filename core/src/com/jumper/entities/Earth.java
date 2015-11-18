package com.jumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jumper.Constants;
import com.jumper.Resources;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class Earth {

    private BodyDef bodyDef;
    private ChainShape chainShape;
    private FixtureDef fixtureDef;
    private Body bodyEarth;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    public Earth(){

        createBodyEarth();
        setPicture();
    }

    private void createBodyEarth(){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);

        chainShape = new ChainShape();
        chainShape.createChain(new float[]{-11,-10,11,-10});

        fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.density = 0;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        bodyEarth = Resources.getWorld().createBody(bodyDef);
        bodyEarth.createFixture(fixtureDef);

        bodyEarth.setUserData(Constants.STR_STAIR);
    }

    public void act(float delta) {
        draw();
    }

    private void setPicture(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("grass.png"));
        sprite = new Sprite(texture);

        sprite.setSize(24, 3);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setCenter(0, 0);
    }

    public void draw(){
        batch.setProjectionMatrix(Resources.getCamera().combined);
        batch.begin();
        sprite.setPosition(bodyEarth.getPosition().x - sprite.getWidth() / 2, bodyEarth.getPosition().y -10 - sprite.getHeight() / 2);
        sprite.setRotation(bodyEarth.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        batch.end();
    }

    public void dispose(){
        chainShape.dispose();
        batch.dispose();
        sprite.getTexture().dispose();
    }
}



