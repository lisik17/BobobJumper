package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class Player extends Actor {
    private BodyDef bodyDef;

    private Body bodyPlayer;
    private PolygonShape polygonShape;

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;

    public Body getBodyPlayer() {
        return bodyPlayer;
    }

    public Player(){
        createBodyStage();
        this.setPosition(-8, 0);
        bodyPlayer.setUserData(Constants.STR_PLAYER);
        setPicture();

        Resources.setPlayer(this);
    }

    @Override
    public void setPosition(float x, float y) {
        bodyPlayer.setTransform(x, y, 0);
    }

    private void createBodyStage(){
        //body def
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0f);

        polygonShape = new PolygonShape();
        polygonShape.setAsBox(Constants.PLAYER_WIDTH / 2f, Constants.PLAYER_HEIGHT / 2f);

        //fixture def
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        //fixtureDef.density = 2.5f;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1f;

        //creating body + fixture
        bodyPlayer = Resources.getWorld().createBody(bodyDef);
        bodyPlayer.createFixture(fixtureDef);
    }

    private void setPicture(){
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("bobo.png"));
        sprite = new Sprite(texture);

        sprite.setSize(6, 6);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setCenter(0, 0);
    }

    public void draw(){
        batch.setProjectionMatrix(Resources.getCamera().combined);
        batch.begin();
        sprite.setPosition(bodyPlayer.getPosition().x - sprite.getWidth() / 2, bodyPlayer.getPosition().y - sprite.getHeight() / 2);
        sprite.setRotation(bodyPlayer.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void act(float delta) {
        
        super.act(delta);

        jumpLeftRight();

        draw();

        //Gdx.app.log("maxf", String.valueOf(Camera.cordsToMeters(Resources.getCamera()).y) + "   " + Resources.getPlayer().getBodyPlayer().getPosition().y);
/*        Vector2 linearVelocity = bodyPlayer.getLinearVelocity();
        float angle = linearVelocity.angle();
        bodyPlayer.setTransform(bodyPlayer.getPosition(), (float) (angle * MathUtils.degreesToRadians));*/
    }

    private void jumpLeftRight() {
        float val = Gdx.input.getAccelerometerX() / Constants.GRAVITY_EARTH;

        bodyPlayer.setTransform(bodyPlayer.getPosition().x + val, bodyPlayer.getPosition().y, 0);

        correctOutOfBorders();



        bodyPlayer.setAngularVelocity(0);
    }

    private void correctOutOfBorders() {
        if(bodyPlayer.getPosition().x < -Constants.SCREEN_SIZE_WIDTH/2f + 2){
            bodyPlayer.setTransform(Constants.SCREEN_SIZE_WIDTH/2f - 2, bodyPlayer.getPosition().y, 0);
        }
        if(bodyPlayer.getPosition().x > Constants.SCREEN_SIZE_WIDTH/2f - 2){
            bodyPlayer.setTransform(-Constants.SCREEN_SIZE_WIDTH/2f + 2, bodyPlayer.getPosition().y, 0);
        }
    }

    public void jumpUp(){
        bodyPlayer.setLinearVelocity(0, 0);
        bodyPlayer.applyLinearImpulse(0f, 23f, bodyPlayer.getPosition().x, bodyPlayer.getPosition().y, true);
    }

    public void startNewGame(){
        //Gdx.app.log("app","game over!!");
        //((Game) Gdx.app.getApplicationListener()).setScreen(new GameJumper());
    }

    public void dispose(){
        polygonShape.dispose();
        batch.dispose();
        sprite.getTexture().dispose();
    }


}
