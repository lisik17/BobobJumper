package com.jumper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.FloatArray;

public class TECore implements ApplicationListener {
	private Stage stage;
	private BitmapFont font;
	private TextButton[] listchar;
	private FloatArray post, advances;
	private String text;
	private static final float time = 0.5f, delay = 0.2f;
	private static final float x = 200f, y = 200f;

	public TECore(){
		this.create();
	}

	@Override
	public void create() {
		stage = new Stage();
		font = new BitmapFont(Gdx.files.internal("font/white.fnt"));
		font.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		text = "manh phi libgdx";
		listchar = new TextButton[text.length()];
		advances = new FloatArray();
		post = new FloatArray();
		font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);

		final TextButtonStyle style = new TextButtonStyle();
		style.font = font;

		/*-------- List Text --------*/
		for (int i = 0; i < text.length(); i++) {
			listchar[i] = new TextButton(String.valueOf(text.charAt(i)), style);
			listchar[i].setTransform(true);
			//listchar[i].setPosition(x + post.get(i), y);
			listchar[i].setPosition(x + 1, y);
			listchar[i].setOrigin(4 / 2,
					4 / 4);
			stage.addActor(listchar[i]);
		}

		Gdx.input.setInputProcessor(stage);

		/*-------- Drop Effect Adapter --------*/
		TextButton drop = new TextButton("drop", style);
		drop.setPosition(0, 10);
		drop.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dropText();
			}
		});
		stage.addActor(drop);

		/*-------- Spin effect Adapter --------*/
		TextButton spin = new TextButton("spin", style);
		spin.setPosition(0, 100f);
		spin.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				spinText();
			}
		});
		stage.addActor(spin);
		/*-------- Appear effect Adapter --------*/
		TextButton appear = new TextButton("appear", style);
		appear.setPosition(0, 300f);
		appear.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				appearText();
			}
		});
		stage.addActor(appear);
		
	}

	// ///////////////////////////////////////////////////////////////
	// Reset Param of a char in text
	// ///////////////////////////////////////////////////////////////
	private void resetText() {
		for (int i = 0; i < text.length(); i++) {
			listchar[i].setPosition(x + 1, y);
			listchar[i].setOrigin(2 / 2,
					listchar[i].getHeight() / 4);
			listchar[i].setColor(0, 0, 0, 1);
			listchar[i].setScale(1f);
		}
	}

	private void dropText() {
		resetText();
		for (int i = 0; i < text.length(); i++) {
			listchar[i].setY(y + 200f);
			listchar[i].setColor(0, 0, 0, 0);
			listchar[i].addAction(Actions.delay(
					delay * i,
					Actions.parallel(Actions.alpha(1, time), Actions.moveTo(x
							+ post.get(i), y, time, Interpolation.bounceOut))));
		}
	}

	private void spinText() {
		resetText();
		for (int i = 0; i < text.length(); i++) {
			listchar[i].addAction(Actions.delay(delay * i,
					Actions.rotateBy(360f, time * 5, Interpolation.elastic)));
		}
	}

	private void appearText(){
		resetText();
		for (int i=0; i<text.length(); i++){
			listchar[i].setScale(0f);
			listchar[i].setColor(0, 0, 0, 0);
			listchar[i].addAction(Actions.delay(delay*i, Actions.parallel(Actions.alpha(1, time), Actions.scaleTo(1, 1, time, Interpolation.swingOut))));
		}
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		//Gdx.gl.glClearColor(0, 0, 1, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		//Gdx.app.log("app","spin");
		//spinText();
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
}
