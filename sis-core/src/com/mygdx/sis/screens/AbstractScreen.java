package com.mygdx.sis.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.sis.Menu;
import com.mygdx.sis.Sis;

public abstract class AbstractScreen implements Screen { 
	protected Sis game;                  //tutaj ustalam jaka klasa ma rozszerzac screen 
	protected Menu menu;
	//protected MenuScreen ms;
	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	
	public AbstractScreen(Sis game) {
		this.game = game;
		createCamera();
		stage = new Stage(new StretchViewport(Sis.width,Sis.height,camera)); //
		spriteBatch = new SpriteBatch();									 //
		Gdx.input.setInputProcessor(stage);									 //
	}
	public AbstractScreen(Menu menu) {
		this.menu = menu;
		createCamera();
		stage = new Stage(new StretchViewport(Menu.width,Menu.height,camera)); //
		spriteBatch = new SpriteBatch();									 //
		Gdx.input.setInputProcessor(stage);									 //
	}



	private void createCamera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,600,600);
		camera.update();
		
	}
	@Override
	public void render(float delta) {
		clearScreen();
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
	}
	@Override
	public void show() {} //gdy odpalenie gry
	
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
	}
	
	@Override
	public void resume() {
		game.setPaused(false); //gdy wejscie z tła
	
	}
	@Override
	public void pause() {
		game.setPaused(true);//gdy z wlaczonej  gry w cos innego wszedl gracz
		
	}
	@Override
	public void dispose() { //zwalnianie pamieci 
		game.dispose();
		
	}
	@Override
	public void hide() {
		
	}
	@Override
	public void resize(int width, int height) {
		
	}
}
