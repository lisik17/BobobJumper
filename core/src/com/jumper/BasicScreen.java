package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Roma-Alisa on 9/24/2015.
 */
public class BasicScreen extends Game {

    protected World world;
    protected Box2DDebugRenderer debugRenderer;
    protected OrthographicCamera camera;

    public BasicScreen() {
        super();
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //now the ball is displayed
        debugRenderer.render(world,camera.combined);

        //now the ball is falling
        world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
    }

    @Override
    public void create() {
        //world = new World(new Vector2(0,-9.81f),true);
        world = new World(new Vector2(0,-25f),true);
       // world = new World(new Vector2(0,0),true);

        World.setVelocityThreshold(0);

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/Constants.SCREEN_RATIO_WIDTH,Gdx.graphics.getHeight()/Constants.SCREEN_RATIO_HEIGHT);

        Resources.setWorld(world);
        Resources.setCamera(camera);


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public Screen getScreen() {
        return super.getScreen();
    }
}
