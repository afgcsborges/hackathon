package obstacles;

import com.academiadecodigo.gnunas.InHerHands;
import com.academiadecodigo.gnunas.screens.PlayingScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;


public class Wall implements Obstacle{

    Texture wallImage;
    Rectangle wall;
    private  int attackCounter = 0;
    Texture[] wallImages;
    private Music birdSound;
    private PlayingScreen game;


    public Rectangle create(){


        wall = new Rectangle();
        birdSound = Gdx.audio.newMusic(Gdx.files.internal("bird_sound.wav"));
        wall.x = 801;
        wall.y= 350;
        wall.width = 180;
        wall.height = 134;
        insertImages();

        return wall;
    }

    @Override
    public void insertImages() {

        wallImage = new Texture(Gdx.files.internal("Phoenix/phoenix_down.png"));
        wallImages = new Texture[] {
                new Texture(Gdx.files.internal("Phoenix/phoenix_down.png")),
                new Texture(Gdx.files.internal("Phoenix/phoenix_up.png")),

        };
    }

    @Override
    public void render(SpriteBatch batch, LinkedList<Rectangle> brickWalls) {
        batch.begin();

        for (Rectangle wall : brickWalls) {
            batch.draw(wallImage, wall.x, wall.y);
            wall.x -= game.getBACKGROUND_MOVE_SPEED() * Gdx.graphics.getDeltaTime();
        }

        batch.end();
    }

    @Override
    public Texture getImage() {
        birdSound.play();
        return wallImage;
    }

    @Override
    public Rectangle getRectangle() {
        return wall;
    }

    public void changeImage() {

        attackCounter++;

        wallImage = wallImages[attackCounter -1];
        if (attackCounter == 2) {
            attackCounter = 0;
        }
    }

    @Override
    public void setGame(PlayingScreen game) {
        this.game = game;
    }
}
