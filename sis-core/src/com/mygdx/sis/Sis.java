package com.mygdx.sis;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData.AssetData;
import com.badlogic.gdx.graphics.g2d.BitmapFont; //
import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;

public class Sis extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	Sprite sprite;
	TextInputListener listener;
	Texture logo;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		img = new Texture("Snake_logo.png");
		//logo = new Texture("SneakIntoScience_projekt_2.png");
		font = new BitmapFont();
		font.setColor(Color.VIOLET);
		sprite = new Sprite(img);
		sprite.setPosition(0, 0);
		listener = new MyTextInputListener();
		Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
	
		
		
		
	}
	
	public class MyTextInputListener implements TextInputListener {
		   public void input (String text) {
		   }

		   public void canceled () {
		   }
		}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Witaj w grze SneakIntoScience", 280, 460);
		batch.end();
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
