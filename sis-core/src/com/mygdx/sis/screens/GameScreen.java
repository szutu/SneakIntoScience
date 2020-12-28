package com.mygdx.sis.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.sis.Sis;

public class GameScreen extends AbstractScreen{

	SpriteBatch batch;
	private Texture bad;
	public GameScreen(Sis game) {
		super(game);
		init();
	}
	private void init() {
		bad = new Texture("badlogic.jpg"); 
		
	}
	private void create() {
		batch = new SpriteBatch();
		
	}
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.begin();
	batch.draw(bad,0,0);
	batch.end();
		spriteBatch.begin();
		spriteBatch.draw(bad,0,0);
		spriteBatch.end();
		
	}
	

}
