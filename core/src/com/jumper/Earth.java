package com.jumper;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class Earth {

    private BodyDef bodyDef;
    private ChainShape chainShape;
    private FixtureDef fixtureDef;
    private Body bodyEarth;

    public Earth(){
        createBodyEarth();
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

    public void dispose(){
        chainShape.dispose();
    }

}
