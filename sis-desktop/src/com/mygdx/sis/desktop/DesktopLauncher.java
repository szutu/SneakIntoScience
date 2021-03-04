package com.mygdx.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Mygame;
/**
 * this class Launch the game (class Mygame)
 * @author Jakub Szutenberg 172434 G3
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		 config.resizable= false;			
	     config.width = 600;			  
	     config.height = 600;			
	     new LwjglApplication(new Mygame(), config);       
	}
}

