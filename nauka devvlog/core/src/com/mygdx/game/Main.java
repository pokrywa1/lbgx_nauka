package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Main extends ApplicationAdapter {

	private Music music;
	private Texture playerTexture, platformTexture;
	private JumpPlayer player;
	private Array<Platform> platformArray;
	private OrthographicCamera camera;
	private float gravity = -20;

	SpriteBatch batch;


	
	@Override
	public void create () {
		loadData();
		init();


	}

	private void init() {
		batch = new SpriteBatch();
		music.play();
		camera = new OrthographicCamera(480,800);
		player = new JumpPlayer(playerTexture);
		platformArray = new Array<Platform>();

		for (int i = 1; i<100;i++){
			Platform p = new Platform(platformTexture);
			p.x = MathUtils.random(800);
			p.y = 200*i;
			platformArray.add(p);
		}
	}

	private void loadData() {
		playerTexture = new Texture("character.png");
		platformTexture = new Texture("platform.png");
		music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		for(Platform p : platformArray){
			p.draw(batch);
		}
		player.draw(batch);
		batch.end();
	}

	private void update() {
		handleInput();

		camera.update();
		camera.position.set(player.x + player.width/2, player.y + 300, 0);
		player.y += player.jumpVelocity *Gdx.graphics.getDeltaTime();

		if(player.y>0){
			player.jumpVelocity += gravity;
		}else{
			player.y = 0;
			player.canJump = true;
			player.jumpVelocity = 0;
		}

		for(Platform p : platformArray){
			isPlayerOnPlatform(p);
			if(isPlayerOnPlatform(p)){
			    player.canJump = true;
			    player.jumpVelocity = 0;
			    player.y = p.y + p.height;
            }
		}

	}

	private boolean isPlayerOnPlatform(Platform p) {
		return player.jumpVelocity <= 0 && player.overlaps(p)&&  !(player.y <=p.y);
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x -= 15;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.x += 15;

		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			player.jump();
		}

	}

}
