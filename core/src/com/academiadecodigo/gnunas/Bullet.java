package com.academiadecodigo.gnunas;
import static com.academiadecodigo.gnunas.player.Player.*;

import com.academiadecodigo.gnunas.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.css.Rect;

import java.util.Iterator;
import java.util.LinkedList;


public class Bullet extends Sprite {

    public Rectangle createBullet(float x, float y){

        Rectangle bullet = new Rectangle();

        bullet.x = x + 47;
        bullet.y= y + 25;
        bullet.width = 17;
        bullet.height = 9;

        return  bullet;

    }
}
