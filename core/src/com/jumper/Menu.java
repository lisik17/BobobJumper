package com.jumper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by Roma-Alisa on 20/10/15.
 */
public class Menu implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit;
    private BitmapFont white,black;
    private TextButton.TextButtonStyle textButtonStyle;

    public Menu(){}

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas(Gdx.files.internal("button/button.pack"));
        skin = new Skin(atlas);

        white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

        table = new Table(skin);
        table.setBounds(0,0,Constants.SCREEN_PIXELS_SIZE_WIDTH,Constants.SCREEN_PIXELS_SIZE_HEIGHT);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("soundOff");
        textButtonStyle.down = skin.getDrawable("soundOff");
        textButtonStyle.checked = skin.getDrawable("soundOff");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        buttonPlay = new TextButton("Start new GAME",textButtonStyle);
        buttonPlay.pad(20);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameJumper());
            }
        });

        buttonExit = new TextButton("Start new GAME",textButtonStyle);
        buttonExit.pad(20);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        table.add(buttonPlay);
        table.row();
        //table.getCell(buttonPlay).spaceBottom(15);
        table.add(buttonExit);

        stage.addActor(table);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        black.dispose();
        white.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
