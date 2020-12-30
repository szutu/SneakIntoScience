package com.mygdx.sis;

import java.io.OutputStream;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.sis.screens.GameScreen;
import com.mygdx.sis.screens.MenuScreen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Input.*;
//import com.badlogic.gdx.Input.*;
public class Menu extends Game {
	Screen GameScreen;
	// Screen sisScreen; // ---------------------------------------------
	public String sciezka ="data/pytaniaA.txt"; // domyslnie wybrany dzial 
	private Stage stage;
	private boolean m,a,h;
    private Texture myTexture;
   // FileHandle handle = Gdx.files.local("pytania.txt"); //
    //String text = handle.readString();					//
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;
    public final static String math = "Matematyka:  Wcisnij \"m\"" ;
    public String quests=" " ;
    public String kat;
    public String name ="";
    public final static String ang = "Angielski:       Wcisnij \"a\"" ;
    public final static String his = "Historia:         Wcisnij \"h\"" ;
    private float timer=0.1f;
	public final static String Screen_name = "Menu";
	public static float width = 600;
	public static float height = 600;
    public SpriteBatch batch;
    protected boolean startGame;
    static String currentquest[]; //static dodane do proby przesylu
    
    BitmapFont font;
    Texture img;
    TextInputListener listener;
    double time ;
    
  //  sisScreen = new Sis(); // -------------------------------
	private void update(float delta) {  //CZY POTRZEBNE? WYWOŁANIE W render();
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
			 wybor();//odpowiednik funkcji movement w klasie Sis
		}
	}
	private void input() {
		if(Gdx.input.isKeyJustPressed(Keys.M)) {
			m=true;
			a=false;
			h=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			m=false;
			a=true;
			h=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.H)) {
			m=false;
			a=false;
			h=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			startGame = true;		//do przejscia ekranow
			
		}
	}
public void wybor() {
		
	if(m || a || h) {
		
	
		if(m)
		{
		 kat ="Wybrałeś kategorie: matematyka";
		 sciezka="data/pytaniaM.txt";
		}
		if(a){
			 kat ="Wybrałeś kategorie: angielski";
			 sciezka="data/pytaniaA.txt";
		}
		if(h){
			 kat ="Wybrałeś kategorie: historia";
			 sciezka="data/pytaniaH.txt";
		}
	}
	else {
		kat=" jeszcze nie wybrano kategorii";
		
	}
	
		
	}
    @Override
    public void create () {
   // 	FileHandle file = Gdx.files.internal("data/pytania.txt");
   // 	questsss = file.readString();
   // 	file =Gdx.files.external("zczytanepytania.dat");
   // 	String wordsArray[] = questsss.split("\\r?\\n");
   // 	for (String word: wordsArray) {
   //		pytania.add(word);
   // 	}
   
    	this.setScreen(new MenuScreen(this)); //wywolanie screenu MenuScreen
    	listener = new MyTextInputListener();
        batch = new SpriteBatch();
        font = new BitmapFont();
        img = new Texture("Snake_logo.png");
    	Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");

    
    	

    }
    public class MyTextInputListener implements TextInputListener {
		   public void input (String text) {
			   name= text;
		   }

		   public void canceled () {
		   }
		}
    public void category() {
    	
    	 FileHandle file = Gdx.files.internal(sciezka);
 	   	quests = file.readString();
 	   	currentquest = quests.split(System.getProperty("line.separator"));
    }
   public void render() {
	   	super.render(); //wywołuje przywołany tutaj: "this.setScreen(new MenuScreen(this));" screen
		input();
	wybor();
		category();
    	Gdx.gl.glClearColor(1, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Witaj w MENU, wybierz dzial: ", 220, 580);
		font.draw(batch,math , 220, 560);
		font.draw(batch,ang, 220, 540);
		font.draw(batch,his, 220, 520);
		font.draw(batch,kat, 220, 500);
		font.draw(batch,"Witaj w grze: "+name, 220, 480);
	//	font.draw(batch,"pytania to "+quests, 220, 460);
		font.draw(batch,"pytanie ale z pliku "+currentquest[9], 220, 440);
		batch.end();
		update(Gdx.graphics.getDeltaTime()); //CZY POTRZEBNE?

    	}
 

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
    }
    
    
}