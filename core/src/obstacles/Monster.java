package obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Monster implements Obstacle {

    Texture monsterImage;
    Texture[] orcImages;
    Rectangle monster;
    private int attackCounter;

    public Rectangle create(){

        monster = new Rectangle();

        monster.x = 801;
        monster.y= 310;
        monster.width = 155;
        monster.height = 140;
        insertImages();

        return monster;
    }

    @Override
    public void insertImages() {
        monsterImage = new Texture(Gdx.files.internal("orc/orc_idle_0.png"));
        orcImages = new Texture[] {
                new Texture(Gdx.files.internal("orc/orc_idle_0.png")),
                new Texture(Gdx.files.internal("orc/orc_idle_1.png")),
                new Texture(Gdx.files.internal("orc/orc_idle_0.png")),
                new Texture(Gdx.files.internal("orc/orc_idle_1.png")),
                new Texture(Gdx.files.internal("orc/orc_attack_0.png")),
                new Texture(Gdx.files.internal("orc/orc_attack_1.png")),
                new Texture(Gdx.files.internal("orc/orc_attack_2.png"))

        };

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

    public void changeImage() {

        attackCounter++;

        monsterImage = orcImages[attackCounter -1];
        if (attackCounter == 7) {
            attackCounter = 0;
        }
    }
}
