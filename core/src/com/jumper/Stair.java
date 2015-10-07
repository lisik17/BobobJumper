package com.jumper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Rectangle;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Stair extends Actor{

    private BodyDef bodyDef;
    private PolygonShape polygonShape;
    private FixtureDef fixtureDef;
    private Body bodyStair;

    public Stair(){
        createBodyStage(5 / 2f, .5f / 2f);
        bodyStair.setUserData(Constants.STR_STAIR);

    }

    @Override
    public void setPosition(float x, float y) {
        bodyStair.setTransform(x, y, 0);
    }

    @Override
    public void setSize(float width, float height) {
        bodyStair.destroyFixture(bodyStair.getFixtureList().first());

        polygonShape = new PolygonShape();
        polygonShape.setAsBox(width/2f, height/2f);


        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .05f;

        bodyStair = Resources.getWorld().createBody(bodyDef);
        bodyStair.createFixture(fixtureDef);

        bodyStair.setUserData(Constants.STR_STAIR);
    }

    private void createBodyStage(float halfWidth,float halfHeight){
        //body def
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,0);

        //ball shape
        polygonShape = new PolygonShape();
        polygonShape.setAsBox(halfWidth,halfHeight);

        //fixture def
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .75f;

        //creating body + fixture
        bodyStair = Resources.getWorld().createBody(bodyDef);
        bodyStair.createFixture(fixtureDef);

        bodyStair.setUserData(Constants.STR_STAIR);
    }

    public void dispose(){
        polygonShape.dispose();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
