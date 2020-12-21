package com.mygdx.sis.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.sis.Menu;
import com.mygdx.sis.Sis;

public class MenuScreen extends AbstractScreen {

	 SpriteBatch batch;
	    //ShapeRenderer shapeRenderer;
	    BitmapFont font;
	    Texture img;
	 Texture bad;
	public MenuScreen(Menu menu) {
		super(menu);
		init();
	}
	
	private void init() {
		bad = new Texture("badlogic.jpg"); 
		 font = new BitmapFont();

			
	}
	public void render(float delta) {
		super.render(delta);
		
		
	    	Gdx.gl.glClearColor(1, 1, 1, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
			spriteBatch.begin();
			font.draw(batch, "Witaj w MENU, wybierz dzial: ", 220, 580);
			spriteBatch.draw(bad, 80,50);
			spriteBatch.end();
		
	


}

}