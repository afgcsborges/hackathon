package obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public interface Obstacle {
     Rectangle create(float x, float y);
     void insertImages();
     void render(SpriteBatch batch, LinkedList<Rectangle> list);
}
