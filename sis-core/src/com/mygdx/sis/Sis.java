package com.mygdx.sis;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g3d.particles.ResourceData.AssetData;
import com.badlogic.gdx.graphics.g2d.BitmapFont; //
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Input.*;
//import com.badlogic.gdx.Input.Keys; 
//import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.sis.screens.GameScreen;
import com.mygdx.sis.screens.MenuScreen;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.sis.Menu;

public class Sis extends Game { //akronim nazwy SneakIntoScience, glowna klasa gry 
	//zamiana extends ApplicationAdapter na Game
	long startTime = TimeUtils.millis();
	long elapsedTime = TimeUtils.timeSinceMillis(startTime);
	protected boolean isGameOver=false;
	SpriteBatch batch;
	Texture img;
	protected BitmapFont font;
	protected Sprite sprite;
	//TextInputListener listener;
	protected Texture kropka;
	protected Texture glowa;
	protected Texture kropka2;
	protected Array<Vector2>position;
	protected Vector2 kropkaPosition;
	protected Vector2 kropka2Position; //
	private float timer=0.1f;
	private int num =3;
	private boolean isLimit;
	private boolean u,d,r=true,l;
	protected int points =0;
	protected int questNum =1;
	public final static String Screen_name = "SneakIntoScience";
	private boolean paused;
	public static float width = 600;
	public static float height = 600;
	public float time =21;
	public boolean timeOut;
	protected String[] pyt = {"Ile to 5+10","ile to 240/12","ile to sqrt(900)","ile to (10^4)^(1/2)"}; //kończy grę gdy koniec pytan
	int[] odpPoprawna = {1,2,1,2}; //ktora odp poprawna 1 lub 2 dla "pyt" o tym samym indeksie
	protected String odp1[] = {"15","25","30","1000"};
	protected String odp2[] = {"14","20","35","100",};
	@Override
	public void create () {
		this.setScreen(new GameScreen(this)); //wywolanie screenu GameScreen
		//String[] pyt = {"pytanie 1","pytanie 2"};
	//	this.setScreen(new GameScreen(this));
		batch = new SpriteBatch();
		img = new Texture("Snake_logo.png");
		//logo = new Texture("SneakIntoScience_projekt_2.png");
		font = new BitmapFont();
		font.setColor(Color.VIOLET);
		sprite = new Sprite(img);
		sprite.setPosition(0, 0);
		//listener = new MyTextInputListener();
		//Gdx.input.getTextInput(listener,"Witaj w grze!",null,"Twoje imie");;
		glowa = new Texture("glowa.png");
		kropka = new Texture("kropka.png");
		kropka2 = new Texture("kropka2.png");
		position = new Array<Vector2>();
		for (int i=0;i<num;i++) {
			
			position.add(new Vector2(50+i*10, 50));
		}

			kropkaPosition = new Vector2(250,250);
			kropka2Position = new Vector2(400,50);

		
		
		
	}
	
	
	private void Limit()
	{
		if(pyt.length==points-1) {
			isLimit = true;
		}
	}
	protected void update(float delta) {
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
			time-=0.1; // zobaczymy czy bedzie poprawnie odejmowac czas
			movement();
		}
		
	}
	protected void checkDot() {
		
		if(position.get(0).x==kropkaPosition.x&&position.get(0).y==kropkaPosition.y) {
			int numerOdp = 1;
			
			if(odpPoprawna[points]==numerOdp) {	
			
			int x=(int)(Math.random()*50)*10;
			int y=(int)(Math.random()*50)*10;
			int x2=(int)(Math.random()*50)*10;
			int y2=(int)(Math.random()*50)*10;
			kropkaPosition.x=x;
			kropkaPosition.y=y;
			kropka2Position.x=x2;
			kropka2Position.y=y2;
			points++;
			questNum++;
			time= (float) 21; //restart zegara
			position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y));
			}
			else {
				isGameOver=true;
				
			}
			
			
			
		}
		if(position.get(0).x==kropka2Position.x&&position.get(0).y==kropka2Position.y) {
			int numerOdp2 = 2;
			if(odpPoprawna[points]==numerOdp2) {
			int x2=(int)(Math.random()*50)*10;
			int y2=(int)(Math.random()*50)*10;
			int x=(int)(Math.random()*50)*10;
			int y=(int)(Math.random()*50)*10;
			kropkaPosition.x=x;
			kropkaPosition.y=y;
			kropka2Position.x=x2;
			kropka2Position.y=y2;
			points++;
			questNum++;
			time= 21; //restart zegara
			position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y));
			}
			else {
				isGameOver=true;
			
			}
		
			
		}
		//if(pyt.length);
	}
	private void movement() {
	//	if(!isGameOver || !timeOut) {
			//waz zastyga gdy game over
		
		
		for(int i=position.size-1;i>0;i--) {
			position.get(i).x=position.get(i-1).x;
			position.get(i).y=position.get(i-1).y;
		}
		if(isGameOver || timeOut) {
		//	//waz znika w kropce
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
		} //dla else w wersji ze znikajacym wezem 
		//}
	}
	
	protected void input() {
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
	@Override
		public void render () {
		
	   	super.render(); //wywołuje przywołany tutaj: "this.setScreen(new GameScreen(this));" screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
      

		batch.begin();
		font.draw(batch, "Witaj w grze SneakIntoScience", 280, 460);
		font.draw(batch, "Punkty: "+points, 280, 500);

		batch.end();
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
		batch.begin();
		for(int i=0; i<position.size;i++ ) {
			
			batch.draw(glowa, position.get(i).x, position.get(i).y);
		}
		batch.draw(kropka, kropkaPosition.x, kropkaPosition.y);
		batch.draw(kropka2, kropka2Position.x, kropka2Position.y);
		font.draw(batch,"pozostały czas wynosi:"+(int)time,220,480);//
		font.draw(batch,"Pytanie nr : "+(int)questNum+": "+pyt[points], 220, 440);
		font.draw(batch,"czerwony: "+odp1[points], 220, 420);
		font.draw(batch,"zielony: "+odp2[points], 350, 420);
	//	font.draw(batch,"zielony: "+Menu.currentquest[points], 350, 400); //testowo, najpierw musi dzialac klasa Menu() aby currentquest nie byl pusty; 
		batch.end();
		update(Gdx.graphics.getDeltaTime());
		input();
		checkDot();
		//isTimeOut();
	//	setPaused(isGameOver); //to moze kraszowac gre, ostroznie
		isTimeOut();
		
	}
	public void isTimeOut () {
		if(time<0.1 &&!isGameOver) {
			//isGameOver=true;
			timeOut=true;
		time=0;
			batch.begin();
			font.draw(batch, "Time is Over!",220,300);
			batch.end();		
		}
		else if(isGameOver) { //gdy isGameOver=true z powodu zlej odp 
			time=0; //zeby czas sie na minusie nie wyswietlał
			batch.begin();
			font.draw(batch, "Game Over",220,400);
			batch.end();
		}
		}
	
	
	// getters and setters
	@Override
	public void dispose () {
		batch.dispose();
		
	}
	public boolean isPaused() { //aktualnie niepotrzebne, zastępuje go "isGameOver"
		return paused;
	}
	public void setPaused(boolean IGO) {
		//this.paused = paused;
		if(IGO) {
	//	pause(); //wbudowana funkcja?
	
		}
		
	}
}
