package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.managers.GameInputProcessor;
import com.mygdx.managers.GameKeys;
import com.mygdx.managers.GameStateManager;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public static int HEIGHT;
	public static int WIDTH;

	private GameStateManager gsm;

	public static OrthographicCamera cam;

	@Override
	public void create () {
		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");

		HEIGHT = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();

		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();

		Gdx.input.setInputProcessor(new GameInputProcessor());

		gsm = new GameStateManager();
		gsm.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();



		GameKeys.update(); // перезаписть нажатий
//		batch.begin();
//		batch.draw(img, 350, 150);
//		batch.end();
	}
	
	@Override
	public void dispose () {


//		batch.dispose();
//		img.dispose();
	}
}
