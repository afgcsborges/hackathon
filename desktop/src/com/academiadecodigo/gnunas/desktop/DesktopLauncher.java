package com.academiadecodigo.gnunas.desktop;

import com.academiadecodigo.gnunas.BootStrap;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.academiadecodigo.gnunas.InHerHands;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "InHerHands";
		config.width = 800;
		config.height = 600;

		//BootStrap bootStrap = new BootStrap();
		//bootStrap.instantiate();


		new LwjglApplication(new InHerHands(), config);
	}
}
