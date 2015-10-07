package com.jumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Roma-Alisa on 10/1/2015.
 */
public class CollisionManager {

    public CollisionManager(){

        setContactListener(Resources.getWorld());
    }

    private void setContactListener(World world) {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                // Check to see if the collision is between the second sprite and the bottom of the screen
                // If so apply a random amount of upward force to both objects... just because
                if(contact.getFixtureA().getBody() != null && contact.getFixtureB().getBody() != null){
                    //Gdx.app.log("app",(String)contact.getFixtureA().getBody().getUserData());
                    //Gdx.app.log("app B",(String)contact.getFixtureB().getBody().getUserData());
                    if(contact.getFixtureB().getBody().getUserData() == "coin"){
                        //Gdx.app.log("app B","!!!");
                       // contact.setEnabled(false);
                        Resources.setScore(Resources.getScore()+5);
                        contact.getFixtureB().getBody().applyForceToCenter(0,10,true);
                        contact.getFixtureB().getBody().applyAngularImpulse(200, true);
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                //Gdx.app.log("app",(String)contact.getFixtureA().getBody().getUserData());
//Gdx.app.log("app B",(String)contact.getFixtureB().getBody().getUserData());
                if(contact.getFixtureA().getBody() != null && contact.getFixtureB().getBody() != null)
                    if (contact.getFixtureB().getBody().getUserData() == Constants.STR_COIN) {
                        //Gdx.app.log("app B", "!!!");
                        ///contact.getFixtureB().getBody().setLinearVelocity(1,1);

                        contact.getFixtureB().getBody().setLinearVelocity(0,-15);

/*                        contact.getFixtureB().getBody().setType(BodyDef.BodyType.DynamicBody);
                        contact.getFixtureB().getBody().applyAngularImpulse(100, true);
                        contact.getFixtureA().getBody().applyAngularImpulse(100, true);*/
                        //contact.getFixtureB().getBody().applyAngularImpulse(new Vector2(200,200),contact.getFixtureB().getBody().getPosition());
                        contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(10, 10), contact.getFixtureB().getBody().getPosition(), true);

                        //contact.getFixtureB().getBody().app

                        contact.setEnabled(false);
                    }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }

    });
    }


}
