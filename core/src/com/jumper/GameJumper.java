package com.jumper;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneAsyncLoader;
import com.gushikustudios.rube.loader.RubeSceneLoader;


public class GameJumper extends BasicScreen {

	//private Stair stair;
	private StairsFactory stairsFactory;
	private CoinsFactory coinsFactory;
	private Player player;
	private Wall wall;
	private Background background;
	private CollisionManager collisionManager;
	//private AnimationManager animation;
	private Font font;

	RubeSceneLoader loader;
	RubeScene scene;
	private AssetManager mAssetManager;

	@Override
	public void create() {
		super.create();

		player = new Player();
		wall = new Wall();
		stairsFactory = new StairsFactory();
		coinsFactory = new CoinsFactory();
		background = new Background();
		collisionManager = new CollisionManager();
		//animation = new AnimationManager();
		font = new Font();


		Gdx.input.setInputProcessor(new InputController(player.getBodyPlayer(), camera));

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
	public void render() {

		super.render();
		background.act(10f);
		stairsFactory.act(10f);
		coinsFactory.act(10f);
		wall.act(10f);
		player.act(10f);
		//animation.act(10f);
		font.act();

		//Camera.moveDown(camera);
		Camera.act();

	}



	@Override
	public void dispose() {
		player.dispose();
		stairsFactory.dispose();
		coinsFactory.dispose();
		wall.dispose();
		background.dispose();
		//animation.dispose();

		super.dispose();
	}
}
