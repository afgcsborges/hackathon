package com.academiadecodigo.gnunas;
import static com.academiadecodigo.gnunas.player.Player.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bullet extends Sprite {
    private Texture bulletImage;
    private Rectangle bullet;



    public void createBullet(){
        bulletImage = new Texture(Gdx.files.internal("player.jpg"));
        bullet = new Rectangle();
        bullet.x = player.x;
        bullet.y= player.y;
        bullet.width = 10;
        bullet.height = 10;

    }
    public void renderBullet(SpriteBatch batch){
        batch.begin();
        batch.draw(bulletImage, bullet.x, bullet.y);
        batch.end();
    }
    public void disposeBullet(){
        bulletImage.dispose();

    }
}
