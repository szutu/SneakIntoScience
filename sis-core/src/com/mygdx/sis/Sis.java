package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont; 
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screens.GameScreen;
/**
 * this class contain the whole mechanics of the game
 * @author Jakub Szutenberg 172434 G3 
 *
 */
public class Sis extends Game { 
	protected boolean isGameOver = false; 
	public SpriteBatch batch;
	public Texture background;
	protected BitmapFont font;
	protected Sprite sprite;
	public Sound getPoint;
	public Sound lostGame;
	public Sound victory;
	public Texture win;
	public Texture dot;
	public Texture head;
	public Texture endGame;
	public Texture timeEnd;
	public Texture dot2;
	public Array<Vector2>position;
	protected Vector2 dotPosition;
	protected Vector2 dot2Position; 
	public float timer=0.1f;
	public int initialSnakeLong=3;// initial snake long
	public boolean isLimit;
	public boolean up,down,right=true,left; 
	protected int points =0;
	public int questNumber =1;
	public int previousQuestion =1; 
	public boolean paused;
	public static float WIDTH = 600; 
	public static float HEIGHT = 600;
	public float timeLeft =21; //timeLeft
	public boolean timeOut;
	public boolean played =false;
	public boolean stopGame;
	public String correctAnswer[];
	protected String answer1[]; 
	protected String answer2[];
	public Menu menu;
	public GameScreen gamescreen;
	public String questions[];
	public String quests;
	public boolean isRestart;
	/**
	 * create objects which are used in this class
	 */
	@Override
	public void create () {
		menu = new Menu(); 
		gamescreen= new GameScreen(this);
		this.setScreen(gamescreen);
		batch = new SpriteBatch();
		getPoint = Gdx.audio.newSound(Gdx.files.internal("data/gp.mp3"));
		lostGame = Gdx.audio.newSound(Gdx.files.internal("data/lostGame.mp3"));
		victory = Gdx.audio.newSound(Gdx.files.internal("data/victory.mp3"));
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		background = new Texture("sis_bg.png"); //background
		head = new Texture("glowa.png");
		dot = new Texture("kropka.png");
		dot2 = new Texture("kropka2.png");
		win=new Texture("win.png");
		position = new Array<Vector2>();
		for (int i=0;i<initialSnakeLong;i++) {	
			position.add(new Vector2(50+i*10, 50)); //initiation of the snake 
		}
			dotPosition = new Vector2(250,250);
			dot2Position = new Vector2(400,50);			
	}
	/**
	 * this method import selected chapter from class Menu
	 * @param answer1 : answer one 
	 * @param answer2 : answer two
	 * @param goodAnswer :show which answer from above is correct
	 */
	public void setAnswer(String answer1[],String answer2[], String goodAnswer[]) {
		this.answer1=answer1;
		this.answer2=answer2;
		this.correctAnswer=goodAnswer;
	}
	/**
	 * this method import questions from class Menu 
	 * @param question : this is imported table of questions
	 */
	public void setQuestion(String question[]) {
		this.questions=question;
	}
	/**
	 * this method check if the limit of points is reached
	 */
	public void Limit()
	{
		if(12==points+1) {
			isLimit = true;
		}
	}
	/**
	 * this method update time and method movement 
	 * @param delta : this is a value of time to be subtracted
	 */
	public void update(float delta) {
		if(!stopGame) {
		timer -=delta;
		if(timer<=0 ) {
			timer=0.1f;
			timeLeft-=0.1; 
			movement();
		}
		}				
	}
	/**
	 * this method check if user selected correct answer 
	 */
	public void checkDot() {		
		if(position.get(0).x==dotPosition.x&&position.get(0).y==dotPosition.y) {
			String answerId = "1";			
			if(correctAnswer[points].compareTo(answerId) ==0) {				
				setNextRound(); 
			}			
			else {
				isGameOver=true;
			}			
		}		
		if(position.get(0).x==dot2Position.x&&position.get(0).y==dot2Position.y) {
			String answerId2 = "2";
			if(correctAnswer[points].compareTo(answerId2)==0) {
				setNextRound(); 
			}		
			else {
				isGameOver=true;			
			}			
		}		
	}
	/**
	 * this method initiate another round if user got point
	 */
	public void setNextRound() {
		int x2=(int)(Math.random()*50)*10; 
		int y2=(int)(Math.random()*40)*10; 
		int x=(int)(Math.random()*50)*10;
		int y=(int)(Math.random()*40)*10;  
		dotPosition.x=x;
		dotPosition.y=y;
		dot2Position.x=x2;
		dot2Position.y=y2;
		points++;
		questNumber++;
		played=false;
		timeLeft= 21; //restart of the clock 
		position.add(new Vector2(position.get(position.size-1).x,position.get(position.size-1).y));
	}
	/**
	 * this method make snake unable to leave specified area
	 */
	public void limitArea() {
		if(position.get(0).x==-10) {
			up=false;
			down=false;
			right=true;
			left=false;
		}
		if(position.get(0).x==610) {
			up=false;
			down=false;
			right=false;
			left=true;		
		}		
		if(position.get(0).y==-10) {
			up=true;
			down=false;
			right=false;
			left=false;
		}
		if(position.get(0).y==410) {
			up=false;
			down=true;
			right=false;
			left=false;
		}
	}
	/**
	 * this method is responsible for snake movement
	 */
	public void movement() {	
		for(int i=position.size-1;i>0;i--) {
			position.get(i).x=position.get(i-1).x;
			position.get(i).y=position.get(i-1).y;
		}		
		limitArea();  
		snakePosition();
	}
	/**
	 * this method determine snake position depending on the input value 
	 */
	public void snakePosition() {
		if((isGameOver || timeOut ||isLimit)) {
			//snake disappear in (getting small as one dot)
		}					
		else {
		if(up)
		{
			position.get(0).y+=10;
		}
		if(down){
			position.get(0).y-=10;
		}
		if(right){
			position.get(0).x+=10;
		}
		if(left){
			position.get(0).x-=10;
		}
		} 
	}
	/**
	 * this method listen to user inputs and assigns appropriate values depending on the input value 
	 */
	public void input() {
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			up=true;
			down=false;
			right=false;
			left=false;
			
		}		
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			up=false;
			down=true;
			right=false;
			left=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			up=false;
			down=false;
			right=false;
			left=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			up=false;
			down=false;
			right=true;
			left=false;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			stopGame=true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			stopGame=false;
		}
		if((Gdx.input.isKeyJustPressed(Keys.R))&&(isGameOver||isLimit||timeOut)) {
			stopGame=false;
			isRestart=true;
		}	
	}
	/**
	 * this method render game on screen, in this case method render() call render() from class GameScreen
	 */
		public void render () {
	   	super.render(); //calls render from screen GameScreen   	
	}
		/**
		 * this method is responsible for what is displayed on the screen
		 */
	public void showGame() {
		drawBackground();
		fontColorSetter();
		drawRound();
	}
	/**
	 * this method draw current round 
	 */
	public void drawRound() {
		batch.begin();
		for(int i=0; i<position.size;i++ ) {	
			batch.draw(head, position.get(i).x, position.get(i).y);
		};
		font.draw(batch, "Witaj w grze SneakIntoScience", 200, 590); //
		font.draw(batch, "Punkty: "+points, 515, 590);  //
		batch.draw(dot, dotPosition.x, dotPosition.y);
		batch.draw(dot2, dot2Position.x, dot2Position.y);
		font.draw(batch,"pozostaly czas: "+(int)timeLeft,10,590);//
		getSelectedChapter();
		batch.end();		
	}
	/**
	 * this method draw Q&A of current round
	 */
	public void getSelectedChapter() {
	font.draw(batch,"pytanie nr:  "+questions[points], 100, 540);
	batch.draw(dot,100,488);
	font.draw(batch,"czerwony: "+answer1[points], 120, 500);
	batch.draw(dot2,370,488);
	font.draw(batch,"zielony: "+answer2[points], 390, 500);
	}
	/**
	 * this method set the background image
	 */
	public void drawBackground() {
		sprite = new Sprite(background);	
		sprite.setPosition(0, 0);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	/**
	 * this method determine font color depending on the timeLeft
	 */
	public void fontColorSetter() {
		if((int)timeLeft<6) {
			font.setColor(Color.RED);
		}
		if((int)timeLeft>20) {
			font.setColor(Color.BLACK);
		}
	}
	/**
	 * this method show timeOut message when time is out 
	 */
	public void drawTimeOut() {
		batch.begin();
		timeEnd = new Texture("timeOver.png");
		sprite = new Sprite(timeEnd);
		sprite.setSize(400, 400);
		sprite.setPosition(75,15);
		sprite.draw(batch);
		batch.end();
	}
	/**
	 * this method determine what happen in cases of end game
	 */
	public void isTimeOut () {
		Limit();
		if(timeLeft<0.1 &&!isGameOver) { //when user lost due to time out 
			timeLeft=0;
			timeOut=true;
			drawTimeOut();		
		}
		else if(isGameOver&&!timeOut) { //when user lost due to wrong answer
			timeLeft=0; 
			endGame = new Texture("gameover2.png");
			sprite = new Sprite(endGame);
			batch.begin();
			sprite.setSize(400, 400);
			sprite.setPosition(75,75);
			sprite.draw(batch);
			batch.end();
			
		}	
		else if(isLimit) {		//when user win due to reach limit of points
			sprite = new Sprite(win);
			batch.begin();
			sprite.setSize(420, 380);
			sprite.setPosition(75,75);
			sprite.draw(batch);
			batch.end();		
		}	
		restart();
		}
	/**
	 * this method restart the game by zeroing variables or setting their initial values
	 */
	public void restart() {
		 if(isRestart&&isGameOver) { 
			timeLeft=21; 
			points=0;
			initialSnakeLong=3;
			isGameOver=false;
			isRestart=false;
			isLimit=false;
			played=false;
			timeOut=false;
			movement();
			fontColorSetter();		
	}
		 else if(isRestart&&timeOut) {
				timeLeft=21; 
				points=0;
				initialSnakeLong=3;
				isGameOver=false;
				isRestart=false;
				isLimit=false;
				played=false;
				timeOut=false;
				movement();
				fontColorSetter();	
		 }
		 else if(isRestart&&isLimit) {
				timeLeft=21; 
				points=0;
				initialSnakeLong=3;
				isGameOver=false;
				isRestart=false;
				isLimit=false;
				played=false;
				timeOut=false;
				movement();
				fontColorSetter();	
		 }
	}
	/**
	 * this method dispose sounds
	 */
	@Override
	public void dispose () {
		lostGame.dispose();
		getPoint.dispose();		
	}
	/**
	 * this method determine which sound should be played
	 * @param x :show what is current quest number
	 */
	public void playSounds(int x) {
		if((isGameOver || timeOut)&&!played) {	
			lostGame.play(50,2,0);
			played =true;
			}	
		if((isLimit)&&!played) {	
			victory.play(50,2,0);
			played =true;
		}		
		if((previousQuestion<x)&&!played) {
			getPoint.play();
			played=true;
		}
	}
}
