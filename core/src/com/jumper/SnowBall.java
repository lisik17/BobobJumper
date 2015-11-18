package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Roma-Alisa on 18/11/15.
 */
public class SnowBall extends Actor{
    private BodyDef bodyDef;
    private CircleShape circleShape;
    private FixtureDef fixtureDef;
    private Body bodySnowBall;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    public SnowBall(){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,0);

        //ball shape
        circleShape = new CircleShape();
        circleShape.setRadius(1f);

        //fixture def
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .75f;

        //creating body + fixture
        bodySnowBall = Resources.getWorld().createBody(bodyDef);
        bodySnowBall.createFixture(fixtureDef);

        bodySnowBall.setUserData(Constants.STR_COIN);

        setPicture();
    }

    private void setPicture(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("coin.png"));
        sprite = new Sprite(texture);

        sprite.setSize(2, 2);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setCenter(0, 0);
    }

    public Body getBodySnowBall(){
        return bodySnowBall;
    }


    @Override
    public void setPosition(float x, float y) {
        bodySnowBall.setTransform(x, y, 0);
    }

    public void draw(){
        batch.setProjectionMatrix(Resources.getCamera().combined);
        batch.begin();
        sprite.setPosition(bodySnowBall.getPosition().x - sprite.getWidth() / 2, bodySnowBall.getPosition().y - sprite.getHeight() / 2);
        sprite.setRotation(bodySnowBall.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        batch.end();
    }

    public void destroySnowBall(){
        //if(Resources.getPlayer().getBodyPlayer().getPosition().y - 14 > bodyCoin.getPosition().y) {
        if (bodySnowBall != null && !Resources.getWorld().isLocked()) {
            Resources.getWorld().destroyBody(bodySnowBall);
            bodySnowBall = null;
        }
        //}
    }

    public void dispose(){
        circleShape.dispose();
        batch.dispose();
        sprite.getTexture().dispose();
    }
}
