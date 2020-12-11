package obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

import static com.academiadecodigo.gnunas.screens.PlayingScreen.BACKGROUND_MOVE_SPEED;

public class Wall implements Obstacle{

    Texture wallImage;
    public Rectangle create(float x, float y){

        Rectangle wall = new Rectangle();

        wall.x = x;
        wall.y= y;
        wall.width = 25;
        wall.height = 270;

        return wall;
    }

    @Override
    public void insertImages() {
        wallImage = new Texture(Gdx.files.internal("Wall.png"));
    }

    @Override
    public void render(SpriteBatch batch, LinkedList<Rectangle> brickWalls) {
        batch.begin();

        for (Rectangle wall : brickWalls) {
            batch.draw(wallImage, wall.x, wall.y);
            wall.x -= BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        batch.end();
    }
}
