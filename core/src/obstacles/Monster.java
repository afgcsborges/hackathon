package obstacles;

import com.academiadecodigo.gnunas.screens.PlayingScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Monster implements Obstacle {

    Texture monsterImage;
    Texture[] orcImages;
    Rectangle monster;
    private int attackCounter;
    private Music monsterRoarSound;
    private Music monsterKnifeSound;
    private PlayingScreen game;

    public Rectangle create(){

        monster = new Rectangle();
        monsterRoarSound = Gdx.audio.newMusic(Gdx.files.internal("orc_roar.wav"));

        monsterKnifeSound = Gdx.audio.newMusic(Gdx.files.internal("knife2.wav"));

        monster.x = 801;
        monster.y= 310;
        monster.width = 130;
        monster.height = 120;
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
        monsterRoarSound.play();
        monsterKnifeSound.play();
        return monsterImage;
    }

    @Override
    public Rectangle getRectangle() {
        return monster;
    }

    @Override
    public void setGame(PlayingScreen playingScreen) {
        this.game = playingScreen;
    }

    public void changeImage() {

        attackCounter++;

        monsterImage = orcImages[attackCounter -1];
        if (attackCounter == 7) {
            attackCounter = 0;

        }
    }
}
