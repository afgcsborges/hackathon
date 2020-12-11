package obstacles;

import com.academiadecodigo.gnunas.screens.PlayingScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public interface Obstacle {
     Rectangle create();
     void insertImages();
     void render(SpriteBatch batch, LinkedList<Rectangle> list);
     Texture getImage();

     Rectangle getRectangle();

    void setGame(PlayingScreen playingScreen);
}
