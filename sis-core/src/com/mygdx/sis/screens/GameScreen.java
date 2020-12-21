package com.mygdx.sis.screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.sis.Sis;

public class GameScreen extends AbstractScreen{

	
	private Texture bad;
	public GameScreen(Sis game) {
		super(game);
		init();
	}
	private void init() {
		bad = new Texture("badlogic.jpg"); 
		
	}
	public void render(float delta) {
		super.render(delta);
		
		spriteBatch.begin();
		spriteBatch.draw(bad,0,0);
		spriteBatch.end();
		
	}
	

}
