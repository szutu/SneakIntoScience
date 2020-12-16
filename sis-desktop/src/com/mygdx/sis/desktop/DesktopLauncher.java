package com.mygdx.sis.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.sis.Sis;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		 config.title = "SneakIntoScience";   //
		 config.resizable= false;			//
	        config.width = 800;			  //
	        config.height = 640;			 //
	        new LwjglApplication(new Sis(), config);
	}
}
