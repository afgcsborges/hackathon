package obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Barrel implements Obstacle{

    Texture barrelImage;

    public Rectangle create(float x, float y){

        Rectangle barrel = new Rectangle();

        barrel.x = x;
        barrel.y= y;
        barrel.width = 52;
        barrel.height = 60;

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
}
