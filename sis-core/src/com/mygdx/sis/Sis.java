package com.mygdx.game;

import java.awt.Font;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.g3d.particles.ResourceData.AssetData;
import com.badlogic.gdx.graphics.g2d.BitmapFont; //
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.Input.Keys; 
//import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Menu;

public class Sis extends Game { 
	long startTime = TimeUtils.millis();
	long elapsedTime = TimeUtils.timeSinceMillis(startTime);
	protected boolean isGameOver=false;
	public SpriteBatch batch;
	public Texture background;
	protected BitmapFont font;
	protected Sprite sprite;
	public Sound getPoint;
	public Sound lostGame;
	public Texture win;
	public Texture kropka;
	public Texture glowa;
	public Texture endGame;
	public Texture kropka2;
	public Array<Vector2>position;
	protected Vector2 kropkaPosition;
	protected Vector2 kropka2Position; //
	public float timer=0.1f;
	public int num =3;// początkowa długość węża
	public boolean isLimit;
	public boolean u,d,r=true,l;
	protected int points =0;
	public int questNum =1;
	public int prevQ =1;
	public final static String Screen_name = "SneakIntoScience";
	public boolean paused;
	public static float width = 600;
	public static float height = 600;
	public float time =21;
	public boolean timeOut;
	public boolean played =false;
	//protected String[] pyt = {"Ile to 5+10","ile to 240/12","ile to sqrt(900)","ile to (10^4)^(1/2)"}; //kończy grę gdy koniec pytan
	int[] odpPoprawna = {1,2,1,2}; //ktora odp poprawna 1 lub 2 dla "pyt" o tym samym indeksie
	protected String odp1[] = {"15","25","30","1000"};
	int[] odpPoprawnaA = {1,2,1,2,1,2,1,2,1,2,1}; //testowo
	protected String odpA1[] = {"good","bad","good","bad","good","bad","good","bad","good","bad","good"}; //testowo
	protected String odpA2[] = {"bad","good","bad","good","bad","good","bad","good","bad","good","bad"};  //testowo
	protected String odp2[] = {"14","20","35","100",};
	public Menu menu; //obiekt klasy menu?
	public GameScreen gamescreen;
	@Override
	public void create () {
		menu = new Menu();
		gamescreen= new GameScreen(this);
		this.setScreen(gamescreen);
		batch = new SpriteBatch();
		getPoint = Gdx.audio.newSound(Gdx.files.internal("data/gp.mp3"));
		lostGame = Gdx.audio.newSound(Gdx.files.internal("data/lostGame.mp3"));
		//logo = new Texture("SneakIntoScience_projekt_2.png");
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		background = new Texture("sis_bg.png"); //background
		//listener = new MyTextInputListener();
		//Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
		glowa = new Texture("glowa.png");
		kropka = new Texture("kropka.png");
		kropka2 = new Texture("kropka2.png");
		win=new Texture("win.png");
		position = new Array<Vector2>();
		for (int i=0;i<num;i++) {
			
			position.add(new Vector2(50+i*10, 50)); //poczatkowe inicjowanie weza 
		}

			kropkaPosition = new Vector2(250,250);
			kropka2Position = new Vector2(400,50);			
	}
	
	
	private void Limit()
	{
		if(menu.currentquest.length==points+2) {
			isLimit = true;
		}
	}
	
	public void update(float delta) {
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
			time-=0.1; 
			movement();
			
		}				
	}
	
	public void checkDot() {
		
		if(position.get(0).x==kropkaPosition.x&&position.get(0).y==kropkaPosition.y) {
			int numerOdp = 1;
			
			if(odpPoprawnaA[points]==numerOdp) {	
			
			int x=(int)(Math.random()*50)*10;
			int y=(int)(Math.random()*40)*10;
			int x2=(int)(Math.random()*50)*10;
			int y2=(int)(Math.random()*40)*10;
			kropkaPosition.x=x;
			kropkaPosition.y=y;
			kropka2Position.x=x2;
			kropka2Position.y=y2;
			points++;
			questNum++;
			time= (float) 21; //restart zegara
			position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y)); //aktualizacja dlugosci weza
			}
			
			else {
				isGameOver=true;
			}			
		}
		
		if(position.get(0).x==kropka2Position.x&&position.get(0).y==kropka2Position.y) {
			int numerOdp2 = 2;
			if(odpPoprawnaA[points]==numerOdp2) {
			int x2=(int)(Math.random()*50)*10; 
			int y2=(int)(Math.random()*40)*10; 
			int x=(int)(Math.random()*50)*10;
			int y=(int)(Math.random()*40)*10;  
			kropkaPosition.x=x;
			kropkaPosition.y=y;
			kropka2Position.x=x2;
			kropka2Position.y=y2;
			points++;
			questNum++;
			time= 21; //restart zegara
			position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y)); //aktualizacja dlugosci weza
			}
			
			else {
				isGameOver=true;			
			}			
		}		
	}
	
	public void limitArea() {
		if(position.get(0).x==-10) {
			u=false;
			d=false;
			r=true;
			l=false;
		}
		if(position.get(0).x==610) {
			u=false;
			d=false;
			r=false;
			l=true;
			
		}
		
		if(position.get(0).y==-10) {
			u=true;
			d=false;
			r=false;
			l=false;
		}
		if(position.get(0).y==410) {
			u=false;
			d=true;
			r=false;
			l=false;
		}
	}
	
	public void movement() {
		
		for(int i=position.size-1;i>0;i--) {
			position.get(i).x=position.get(i-1).x;
			position.get(i).y=position.get(i-1).y;
		}		
		limitArea(); // ograniczenie poruszania sie w zakresie ekranu
		sneakPosition();

	}
	public void sneakPosition() {
		if((isGameOver || timeOut ||isLimit)) {
			//waz znika w kropce, brak instrukcji dla odswiezania pozycji weza	
		}
					
		else {
		if(u)
		{
			position.get(0).y+=10;
		}
		if(d){
			position.get(0).y-=10;
		}
		if(r){
			position.get(0).x+=10;
		}
		if(l){
			position.get(0).x-=10;
		}
		} 
	}
	public void input() {
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			u=true;
			d=false;
			r=false;
			l=false;
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			u=false;
			d=true;
			r=false;
			l=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			u=false;
			d=false;
			r=false;
			l=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			u=false;
			d=false;
			r=true;
			l=false;
		}
	}
	
		public void render () {
	   	super.render(); //wywołuje przywołany tutaj: "this.setScreen(new GameScreen(this));" screen
	   	
/*	   //stary render gdy nie bylo jeszcze screenow
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		showGame();
		update(Gdx.graphics.getDeltaTime());
		input();
		checkDot();
		//isTimeOut();
	//	setPaused(isGameOver); //to moze kraszowac gre, ostroznie
		isTimeOut();
		playSounds(questNum);	
*/	
	}
	public void showGame() {
		drawBackground();
		fontColorSetter();
		drawRound();
	}
	
	public void drawRound() {
		batch.begin();
		for(int i=0; i<position.size;i++ ) {	
			batch.draw(glowa, position.get(i).x, position.get(i).y);
		}
		
		font.draw(batch, "Witaj w grze SneakIntoScience", 200, 590); //
		font.draw(batch, "Punkty: "+points, 515, 590);  //
		batch.draw(kropka, kropkaPosition.x, kropkaPosition.y);
		batch.draw(kropka2, kropka2Position.x, kropka2Position.y);
		font.draw(batch,"pozostały czas: "+(int)time,10,590);//
		//font.draw(batch,"Pytanie nr : "+(int)questNum+": "+pyt[points], 220, 570); //stara wersja pytan z Sis nie z Menu
		batch.draw(kropka,200,528);
		font.draw(batch,"czerwony: "+odpA1[points], 220, 540);
		batch.draw(kropka2,330,528);
		font.draw(batch,"zielony: "+odpA2[points], 350, 540);
		getSelectedChapter();
		batch.end();
		
	}
	public void wybor() {  //testowo (nie dziala) analogicznie wybor jak w Menu ,bo nie przesyla wybranego dzialu do Sis
		
		if(menu.m || menu.a || menu.h) {		
		
			if(menu.m)
			{
			 menu.sciezka="data/pytaniaM.txt";			 			 			
			}
			if(menu.a){	
				 menu.sciezka="data/pytaniaA.txt";
			}
			
			if(menu.h){
				 menu.sciezka="data/pytaniaH.txt";	
			}
		}
	}
	
    public void category() {	//testowo
    	FileHandle file = Gdx.files.internal(menu.sciezka);
 	   	menu.quests = file.readString();
 	   	menu.currentquest = menu.quests.split(System.getProperty("line.separator"));
    }
	public void getSelectedChapter() {
		category();
		wybor();
		font.draw(batch,"pytanie wybrane z menu: "+menu.currentquest[points], 350, 400);
	}
	
	public void drawBackground() {
		sprite = new Sprite(background);	
		sprite.setPosition(0, 0);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	
	public void fontColorSetter() {
		if((int)time<6) {
			font.setColor(Color.RED);
		}
		if((int)time>20) {
			font.setColor(Color.BLACK);
		}
	}
	
	public void isTimeOut () {
		Limit();
		if(time<0.1 &&!isGameOver) {
			timeOut=true;
			time=0;
			batch.begin();
			font.draw(batch, "Time is Over!",220,300);
			batch.end();		
		}
		else if(isGameOver) { //gdy isGameOver=true z powodu zlej odp 
			time=0; //zeby czas sie na minusie nie wyswietlał
			endGame = new Texture("gameover2.png");
			sprite = new Sprite(endGame);
			batch.begin();
			sprite.setSize(400, 400);
			sprite.setPosition(75,75);
			sprite.draw(batch);
			
			batch.end();
			
		}
		if(isLimit) {
			
			//font.draw(batch," Wygrales",200,200);
			sprite = new Sprite(win);
			batch.begin();
			sprite.setSize(420, 380);
			sprite.setPosition(75,75);
			sprite.draw(batch);
			batch.end();
		}
		}
	
	
	// getters and setters
	@Override
	public void dispose () {
		lostGame.dispose();
		getPoint.dispose();
		
	}
	public boolean isPaused() { //aktualnie niepotrzebne, zastępuje go "isGameOver"
		return paused;
	}
	public void setPaused(boolean IGO) {
		//this.paused = paused;
		if(IGO) {
		this.pause(); //wbudowana funkcja?
	
		}
		
	}
	public void playSounds(int x) {
		
		if((isGameOver || timeOut)&&!played) {
	
				
				lostGame.play(50,2,0);
				played =true;
			//	setPaused(isGameOver);	

			}
		
		
		if(prevQ<x) {
			getPoint.play();
		}
		prevQ=x;
	}
}
