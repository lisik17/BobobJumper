package com.jumper;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Roma-Alisa on 10/7/2015.
 */
public class Coin extends Actor{

    private BodyDef bodyDef;
    private CircleShape circleShape;
    private FixtureDef fixtureDef;
    private Body bodyCoin;

    public Coin(){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,0);

        //ball shape
        circleShape = new CircleShape();
        circleShape.setRadius(.5f);

        //fixture def
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .75f;

        //creating body + fixture
        bodyCoin = Resources.getWorld().createBody(bodyDef);
        bodyCoin.createFixture(fixtureDef);

        bodyCoin.setUserData(Constants.STR_COIN);
    }

    @Override
    public void setPosition(float x, float y) {
        bodyCoin.setTransform(x, y, 0);
    }

    public void dispose(){
        circleShape.dispose();
    }
}
