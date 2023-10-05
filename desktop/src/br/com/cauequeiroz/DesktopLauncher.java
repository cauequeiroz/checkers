package br.com.cauequeiroz;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import br.com.cauequeiroz.engine.Checkers;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("Checkers");
		config.setWindowedMode(480, 480);
		config.setResizable(false);
		config.setForegroundFPS(60);

		new Lwjgl3Application(new Checkers(), config);
	}
}
