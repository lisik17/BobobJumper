package com.jumper.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Stair extends Actor implements Pool.Poolable{

    private BodyDef bodyDef;
    private PolygonShape polygonShape;
    private FixtureDef fixtureDef;
    private Body bodyStair;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    private float stairWidth;

    public Stair(){
        createBodyStage(5 / 2f, .5f / 2f);
        setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
        bodyStair.setUserData(Constants.STR_STAIR);

        //Gdx.app.log("app", "c'tor");
    }

    @Override
    public void setPosition(float x, float y) {
        bodyStair.setTransform(x, y, 0);
    }

    @Override
    public void setSize(float width, float height) {
        bodyStair.destroyFixture(bodyStair.getFixtureList().first());

        stairWidth = width;

        polygonShape = new PolygonShape();
        polygonShape.setAsBox(width/2f, height/2f);


        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;

        bodyStair = Resources.getWorld().createBody(bodyDef);
        bodyStair.createFixture(fixtureDef);

        bodyStair.setUserData(Constants.STR_STAIR);

        setPicture();

        chooseStairToMoveLeftRight();
    }

    private void chooseStairToMoveLeftRight() {
        if(MathUtils.random(5) == 2){
            bodyStair.setLinearVelocity(3,0);
        }
    }

    private void createBodyStage(float halfWidth,float halfHeight){
        //body def
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0, 0);

        //ball shape
        polygonShape = new PolygonShape();
        polygonShape.setAsBox(halfWidth,halfHeight);

        //fixture def
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;

        //creating body + fixture
        bodyStair = Resources.getWorld().createBody(bodyDef);
        bodyStair.createFixture(fixtureDef);

        bodyStair.setUserData(Constants.STR_STAIR);

        chooseStairToMoveLeftRight();
    }

    public void draw(){
        batch.setProjectionMatrix(Resources.getCamera().combined);
        batch.begin();
        sprite.setPosition(bodyStair.getPosition().x - sprite.getWidth() / 2, bodyStair.getPosition().y - sprite.getHeight() / 2);
        sprite.setRotation(bodyStair.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        batch.end();
    }

    private void setPicture(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal(Constants.PIC_STAIR));
        sprite = new Sprite(texture);

        sprite.setSize(8, 4);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setCenter(0, 0);
    }



    public Body getStairBody(){
        return this.bodyStair;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void init(float x, float y){
        setPosition(x, y);
        //setSize(Constants.STAIR_LENGTH, Constants.STAIR_WIDTH);
    }

    @Override
    public void reset() {
        setPosition(-10, -10);
        bodyStair.setAngularVelocity(0);
        bodyStair.setLinearVelocity(0, 0);
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        stairMoveLeftRight();
    }

    private void stairMoveLeftRight() {
        if(stairGettingOutOfBorder()){
            bodyStair.setLinearVelocity(-bodyStair.getLinearVelocity().x,0);
        }
    }

    private boolean stairGettingOutOfBorder() {
        return bodyStair.getPosition().x > (Constants.SCREEN_SIZE_WIDTH - Constants.STAIR_LENGTH)/2f
           || bodyStair.getPosition().x < (-Constants.SCREEN_SIZE_WIDTH + Constants.STAIR_LENGTH)/2f;
    }

    public boolean readyToBeDestroyed(){
        if(Resources.getPlayer().getBodyPlayer().getPosition().y - 14 > bodyStair.getPosition().y){
            rotateBeforeDestroy();
            if(Resources.getPlayer().getBodyPlayer().getPosition().y -20 > bodyStair.getPosition().y) {
                return true;
            }
        }
        return false;
    }

    private void rotateBeforeDestroy() {
        if(bodyStair.getAngularVelocity() == 0) {
            bodyStair.setAngularVelocity(MathUtils.random(-3, 3));
            bodyStair.setLinearVelocity(0, -5);
        }
    }

    public void destroyStair(){
        if(bodyStair != null && !Resources.getWorld().isLocked()) {
            Resources.getWorld().destroyBody(bodyStair);
            bodyStair = null;
        }
    }


    public boolean notMovingLeftRight(){
        return bodyStair.getLinearVelocity().x == 0;
    }


    public float getY(){
        return bodyStair.getPosition().y;
    }

    public void dispose(){
        polygonShape.dispose();
        batch.dispose();
        sprite.getTexture().dispose();
    }
}
