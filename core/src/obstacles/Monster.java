package obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Monster implements Obstacle {

    Texture monsterImage;
    Rectangle monster;

    public Rectangle create(){

        monster = new Rectangle();

        monster.x = 801;
        monster.y= 310;
        monster.width = 52;
        monster.height = 60;
        insertImages();

        return monster;
    }

    @Override
    public void insertImages() {
        monsterImage = new Texture(Gdx.files.internal("Barrel.png"));
    }

    @Override
    public void render(SpriteBatch batch, LinkedList<Rectangle> barrels) {
        batch.begin();

        for (Rectangle barrel : barrels) {
            batch.draw(monsterImage, barrel.x, barrel.y);
            barrel.x -= 100 * Gdx.graphics.getDeltaTime();
        }

        batch.end();
    }

    @Override
    public Texture getImage() {
        return monsterImage;
    }

    @Override
    public Rectangle getRectangle() {
        return monster;
    }
}
