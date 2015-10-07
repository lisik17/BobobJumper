package com.jumper;

import com.badlogic.gdx.graphics.g2d.Batch;
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

    public Body getBodyPlayer() {
        return bodyPlayer;
    }

    public Player(){
        createBodyStage();
        this.setPosition(5, 10);
        bodyPlayer.setUserData(Constants.STR_PLAYER);
    }

    @Override
    public void setPosition(float x, float y) {
        bodyPlayer.setTransform(x, y, 0);
    }

    private void createBodyStage(){
        //body def
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);

        polygonShape = new PolygonShape();
        polygonShape.setAsBox(Constants.PLAYER_WIDTH / 2f, Constants.PLAYER_HEIGHT / 2f);

        //fixture def
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .05f;

        //creating body + fixture
        bodyPlayer = Resources.getWorld().createBody(bodyDef);
        bodyPlayer.createFixture(fixtureDef);

        Resources.setPlayerBody(bodyPlayer);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        
        super.act(delta);
        
/*        Vector2 linearVelocity = bodyPlayer.getLinearVelocity();
        float angle = linearVelocity.angle();
        bodyPlayer.setTransform(bodyPlayer.getPosition(), (float) (angle * MathUtils.degreesToRadians));*/
    }


    public void dispose(){
        polygonShape.dispose();
        //circleShape.dispose();
    }
}
