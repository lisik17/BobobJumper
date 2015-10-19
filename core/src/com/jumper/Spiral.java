package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class Spiral extends Actor {
    private BodyDef bodyDef;
    private PolygonShape polygonShape;
    private FixtureDef fixtureDef;
    private Body bodySpiral;

    public Spiral(){
        createBodySpiral();
    }

    @Override
    public void setPosition(float x, float y) {
        bodySpiral.setTransform(x, y, 0);
    }

    private void createBodySpiral(){
        //body def
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0, 0);

        //ball shape
        polygonShape = new PolygonShape();
        polygonShape.setAsBox(1f,1f);

        //fixture def
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        //creating body + fixture
        bodySpiral = Resources.getWorld().createBody(bodyDef);
        bodySpiral.createFixture(fixtureDef);

        bodySpiral.setUserData(Constants.STR_SPIRAL);
    }

    public void destroySpiral(){
        if(bodySpiral != null && !Resources.getWorld().isLocked()) {
            Resources.getWorld().destroyBody(bodySpiral);
            bodySpiral = null;
        }
    }

    public boolean readyToBeDestroyed(){

        return (Resources.getPlayer().getBodyPlayer().getPosition().y -20 > bodySpiral.getPosition().y);
    }

    public void dispose(){
        polygonShape.dispose();
        //batch.dispose();
        //sprite.getTexture().dispose();
    }
}


