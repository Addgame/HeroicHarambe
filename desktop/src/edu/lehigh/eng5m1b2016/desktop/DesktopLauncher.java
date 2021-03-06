package edu.lehigh.eng5m1b2016.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.lehigh.eng5m1b2016.MyGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        MyGame game = new MyGame();
        config.title = game.mGameTitle;
        config.width = game.mWidth;
        config.height = game.mHeight;
        new LwjglApplication(game, config);
    }
}
