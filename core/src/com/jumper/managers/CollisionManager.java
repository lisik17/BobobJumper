package com.jumper.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.jumper.Constants;
import com.jumper.Resources;

/**
 * Created by Roma-Alisa on 10/1/2015.
 */
public class CollisionManager {

    private static CollisionManager instance;
    //private State collisionType;

    private Body bodyA;
    private Body bodyB;
    private State collisionType;

    public enum State{
        COL_COIN,
        COL_STAIR,
        COL_SPIRAL,
        NONE;
    }

    public static CollisionManager getInstance(){
        if(null == instance){
            instance = new CollisionManager();
        }
        return instance;
    }

    public State getCollisionType(){
        return collisionType;
    }

    private void setCollisionBodies(Contact contact){
        bodyA = contact.getFixtureA().getBody();
        bodyB = contact.getFixtureB().getBody();
    }

    public CollisionManager(){
        collisionType = State.NONE;
        setContactListener(Resources.getWorld());
    }



    private void setContactListener(World world) {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                setCollisionState(contact);

                onCoinCollMiddle();
                onStairCollMiddle();
                onSpiralCollMiddle();
            }

            @Override
            public void endContact(Contact contact) {
                setCollisionState(contact);
            }


            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                setCollisionState(contact);

                onCoinCollStart(contact);
                onStairCollStart(contact);
                onSpiralCollStart(contact);
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

                setCollisionState(contact);

                onStairCollPost(contact);
            }

        });
    }

    private void onStairCollPost(Contact contact) {
        if(collisionType == State.COL_STAIR) {
            disableStairContactIfNeeded(contact);
        }
    }

    private void onCoinCollMiddle() {
        if(collisionType == State.COL_COIN){
            SoundManager.getInstance().playSound();
            Resources.setScore(Resources.getScore() + 5);
        }
    }

    private void onStairCollMiddle() {
        if(collisionType == State.COL_STAIR){
            if(playerAboveStair()) {
                Resources.getPlayer().jumpUp();
            }
        }
    }

    private boolean playerAboveStair() {
        return Resources.getPlayer().getBodyPlayer().getLinearVelocity().y < 0;
    }

    private boolean playerAboveSpiral() {
        return Resources.getPlayer().getBodyPlayer().getLinearVelocity().y < 0;
    }


    private void onSpiralCollMiddle(){
        if(collisionType == State.COL_SPIRAL){
            if(playerAboveSpiral()) {
                SoundManager.getInstance().playSound();
                Resources.getPlayer().boost();
            }
        }
    }

    private void setCollisionState(Contact contact) {
        setCollisionBodies(contact);
        detectCollisionState();
    }

    private void onStairCollStart(Contact contact) {
        if (collisionType == State.COL_STAIR) {
            disableStairContactIfNeeded(contact);
        }
    }

    private void disableStairContactIfNeeded(Contact contact) {
        if (!playerAboveStair()) {
            contact.setEnabled(false);
        }
    }

    private void onSpiralCollStart(Contact contact) {
        if(collisionType == State.COL_SPIRAL){
            //bodyA.applyLinearImpulse(0, 30, bodyA.getPosition().x, bodyA.getPosition().y, true);


            //if(bodyA.getPosition().y < bodyB.getPosition().y){
            if(!playerAboveSpiral()){
                contact.setEnabled(false);
            }else{
                Resources.getPlayer().boost();
            }
        }
    }

    private void onCoinCollStart(Contact contact) {
        if(collisionType == State.COL_COIN) {
            bodyB.setLinearVelocity(0, -15);
            bodyB.setAngularVelocity(3);

            Gdx.app.log("app", "colission !!!!");
            Gdx.app.log("app","A "+bodyA.getUserData().toString());
            Gdx.app.log("app","B "+ bodyB.getUserData().toString());


            //Font.applyFontEffect();

            //Resources.getFont().setStateUpdateScore();

            //SoundManager.getInstance().playSound();
            //GameJumper.sound.play();
            //Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/storm.mp3"));
            //sound.play();

            contact.setEnabled(false);
        }
    }

    private void detectCollisionState() {
        if(bodyA != null && bodyB != null){
            if (bodyB.getUserData() == Constants.STR_COIN || bodyA.getUserData() == Constants.STR_COIN) {
                collisionType = State.COL_COIN;
                return;
            }
            if (bodyB.getUserData() == Constants.STR_STAIR || bodyA.getUserData() == Constants.STR_STAIR) {
                collisionType = State.COL_STAIR;
                return;
            }
            if (bodyB.getUserData() == Constants.STR_SPIRAL || bodyA.getUserData() == Constants.STR_SPIRAL) {
                collisionType = State.COL_SPIRAL;
                return;
            }
        }
        collisionType = State.NONE;
    }

    public void dispose(){
        instance = null;
    }


}
