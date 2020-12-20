package com.mygdx.sis.screens;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.sis.Sis;

public class ScreenTample extends AbstractScreen{

	
	private Texture bad;
	public ScreenTample(Sis game) {
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
