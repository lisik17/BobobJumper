package com.jumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.jumper.Constants;
import com.jumper.Resources;

import static com.jumper.Constants.SPIRAL_PIC_WIDTH;

/**
 * Created by Roma-Alisa on 19/10/15.
 */
public class Spiral extends Actor implements Pool.Poolable{
    private BodyDef bodyDef;
    private PolygonShape polygonShape;
    private FixtureDef fixtureDef;
    private Body bodySpiral;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    public Spiral(){
        createBodySpiral();
    }

    @Override
    public void setPosition(float x, float y) {
        bodySpiral.setTransform(x, y, 0);
        setPicture();
    }

    private void createBodySpiral(){
        //body def
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0, 0);

        //ball shape
        polygonShape = new PolygonShape();
        polygonShape.setAsBox(Constants.SPIRAL_WIDTH, Constants.SPIRAL_WIDTH);

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

    public void act(float delta) {
        draw();
    }

    public boolean readyToBeDestroyed(){

        return (Resources.getPlayer().getBodyPlayer().getPosition().y -5 > bodySpiral.getPosition().y);
    }

    private void setPicture(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(Constants.PIC_SPIRAL));
        sprite = new Sprite(texture);

        sprite.setSize(SPIRAL_PIC_WIDTH, SPIRAL_PIC_WIDTH);
        sprite.setOrigin(sprite.getWidth()/ 2, sprite.getHeight()/ 2);
        sprite.setCenter(0, 0);
    }

    public void draw(){
        batch.setProjectionMatrix(Resources.getCamera().combined);
        batch.begin();
        sprite.setPosition(bodySpiral.getPosition().x - sprite.getWidth() / 2, bodySpiral.getPosition().y - sprite.getHeight() / 2);
        sprite.setRotation(bodySpiral.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        batch.end();
    }

    public void dispose(){
        polygonShape.dispose();
        batch.dispose();
        sprite.getTexture().dispose();
    }

    public void init(float x, float y) {
        setPosition(x, y);
    }

    @Override
    public void reset() {
        setPosition(0, -10);
        bodySpiral.setLinearVelocity(0, 0);
        bodySpiral.setAngularVelocity(0);
    }
}


