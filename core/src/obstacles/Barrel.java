package obstacles;

import com.academiadecodigo.gnunas.screens.PlayingScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Barrel implements Obstacle{

    Texture barrelImage;
    Rectangle barrel;
    private PlayingScreen game;

    public Rectangle create(){

        barrel = new Rectangle();

        barrel.x = 801;
        barrel.y= 310;
        barrel.width = 52;
        barrel.height = 60;

        insertImages();

        return barrel;
    }

    @Override
    public void insertImages() {
        barrelImage = new Texture(Gdx.files.internal("Barrel.png"));
    }

    @Override
    public void render(SpriteBatch batch, LinkedList<Rectangle> barrels) {
        batch.begin();

        for (Rectangle barrel : barrels) {
            batch.draw(barrelImage, barrel.x, barrel.y);
            barrel.x -= 100 * Gdx.graphics.getDeltaTime();
        }

        batch.end();
    }

    @Override
    public Texture getImage() {
        return barrelImage;
    }

    @Override
    public Rectangle getRectangle() {
        return barrel;
    }

    @Override
    public void setGame(PlayingScreen playingScreen) {
        this.game = playingScreen;
    }
}
