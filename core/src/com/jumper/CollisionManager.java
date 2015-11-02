package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Roma-Alisa on 10/1/2015.
 */
public class CollisionManager {

    private Body bodyA;
    private Body bodyB;
    private State state;

    private enum State{
        COL_COIN,COL_STAIR,COL_SPIRAL,NONE;
    }

    private void setCollisionBodies(Contact contact){
        bodyA = contact.getFixtureA().getBody();
        bodyB = contact.getFixtureB().getBody();
    }

    public CollisionManager(){

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
        if(state == State.COL_STAIR) {
            disableStairContactIfNeeded(contact);
        }
    }

    private void onCoinCollMiddle() {
        if(state == State.COL_COIN){
            Resources.setScore(Resources.getScore() + 5);
        }
    }

    private void onStairCollMiddle() {
        if(state == State.COL_STAIR){
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
        if(state == State.COL_SPIRAL){
            if(playerAboveSpiral()) {
                Resources.getPlayer().boost();
            }
        }
    }

    private void setCollisionState(Contact contact) {
        setCollisionBodies(contact);
        detectCollisionState();
    }

    private void onStairCollStart(Contact contact) {
        if (state == State.COL_STAIR) {
            disableStairContactIfNeeded(contact);
        }
    }

    private void disableStairContactIfNeeded(Contact contact) {
        if (!playerAboveStair()) {
            contact.setEnabled(false);
        }
    }

    private void onSpiralCollStart(Contact contact) {
        if(state == State.COL_SPIRAL){
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
        if(state == State.COL_COIN){
            bodyB.setLinearVelocity(0, -15);
            bodyB.setAngularVelocity(3);

            //Font.applyFontEffect();
            Resources.getFont().setStateUpdateScore();

            contact.setEnabled(false);
        }
    }

    private void detectCollisionState() {
        if(bodyA != null && bodyB != null){
            if (bodyB.getUserData() == Constants.STR_COIN || bodyA.getUserData() == Constants.STR_COIN) {
                state = State.COL_COIN;
                return;
            }
            if (bodyB.getUserData() == Constants.STR_STAIR || bodyA.getUserData() == Constants.STR_STAIR) {
                state = State.COL_STAIR;
                return;
            }
            if (bodyB.getUserData() == Constants.STR_SPIRAL || bodyA.getUserData() == Constants.STR_SPIRAL) {
                state = State.COL_SPIRAL;
                return;
            }
        }
        state = State.NONE;
    }


}
