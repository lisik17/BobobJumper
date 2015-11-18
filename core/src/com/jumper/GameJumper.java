package com.jumper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneAsyncLoader;
import com.gushikustudios.rube.loader.RubeSceneLoader;

import java.io.File;
import java.nio.file.Paths;

import sun.rmi.runtime.Log;


public class GameJumper extends BasicScreen implements Screen{

	//private Stair stair;
	private StairsFactory stairsFactory;
	private SpiralFactory spiralFactory;
	private CoinsFactory coinsFactory;
	private SnowBallFactory snowBallFactory;

	private Player player;
	private Wall wall;
	private Background background;
	private CollisionManager collisionManager;
	private Earth earth;
	private SoundManager soundManager;
	//private AnimationManager animation;
	private Font font;

	public static Sound sound;


	RubeSceneLoader loader;
	RubeScene scene;
	private AssetManager mAssetManager;

	public GameJumper() {
		//Gdx.input.setCatchMenuKey(true);
		//Resources.setMenuInstance(context);
	}

	@Override
	//public void create() {
	public void show(){
		super.create();

		//SoundManager.getInstance().playSound();

		player = new Player();
		wall = new Wall();
		Resources.setScore(0);

		stairsFactory = new StairsFactory();
		spiralFactory = new SpiralFactory();
		coinsFactory = new CoinsFactory();
		snowBallFactory = new SnowBallFactory();


		soundManager = SoundManager.getInstance();

		Resources.setSpiralFactory(spiralFactory);
		Resources.setSnowBallFactory(snowBallFactory);

		background = new Background();
		collisionManager = new CollisionManager();

		AssetManager assetManager = new AssetManager();

		SoundManager.loadSounds();

/*
		if(assetManager.isLoaded("sound/coin.mp3")) {
			sound = assetManager.get("sound/coin.mp3", Sound.class);
		}else{
			Gdx.app.log("app","not loaded yet");
		}
*/


		//animation = new AnimationManager();
		font = new Font();
		font.setStateGamePlay();
		Resources.setFont(font);

		earth = new Earth();


		Gdx.input.setInputProcessor(new InputController());

		//loadRUBEscen();

	}

	private void loadRUBEscen() {
		loader = new RubeSceneLoader();
		scene = loader.loadScene(Gdx.files.internal("palm.json"));

		world = scene.getWorld();

		mAssetManager = new AssetManager();
		mAssetManager.setLoader(RubeScene.class, new RubeSceneAsyncLoader(new InternalFileHandleResolver()));
	}

	@Override
	public void render(float delta) {



		super.render();
		//background.act(10f);
		stairsFactory.act(10f);
		spiralFactory.act(10f);
		coinsFactory.act(10f);
		snowBallFactory.act(10f);
		wall.act(10f);
		player.act(10f);
		earth.act(10f);
		//animation.act(10f);
		font.act();

		//player.destroyPlayer();

		//Camera.moveDown(camera);
		Camera.act();

		Gdx.input.setCatchBackKey(true);

	}


/*	@Override
	public void show() {

	}*/



	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		player.dispose();

		stairsFactory.dispose();
		coinsFactory.dispose();
		snowBallFactory.dispose();
		spiralFactory.dispose();

		wall.dispose();
		//background.dispose();
		earth.dispose();
		//collisionManager.dispose();
		//animation.dispose();

		super.dispose();
	}
}
