package obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

import static com.academiadecodigo.gnunas.screens.PlayingScreen.BACKGROUND_MOVE_SPEED;

public class Wall implements Obstacle{

    Texture wallImage;
    Rectangle wall;

    public Rectangle create(){

        wall = new Rectangle();

        wall.x = 801;
        wall.y= 310;
        wall.width = 25;
        wall.height = 270;
        insertImages();

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

    @Override
    public Texture getImage() {
        return wallImage;
    }

    @Override
    public Rectangle getRectangle() {
        return wall;
    }
}
