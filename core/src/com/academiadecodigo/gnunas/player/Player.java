package com.academiadecodigo.gnunas.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntFloatMap;

public class Player extends Sprite {
    private Texture playerImage;
    public static Rectangle player;
    private OrthographicCamera camera;
    private boolean jumping = false;
    private boolean backToGround = false;


    public void createPlayer(){
        playerImage = new Texture(Gdx.files.internal("player.jpg"));
        player = new Rectangle();
        player.x = 100;
        player.y= 280 + 40;
        player.width = 50;
        player.height = 50;

        camera= new OrthographicCamera();
        camera.setToOrtho(false,800,290);
    }

    public void renderPlayer(SpriteBatch batch){
       // Gdx.gl.glClearColor(0, 0, 0.2f, 1);
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // camera.update();
        //batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(playerImage, player.x, player.y);
        batch.end();

       /* if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.x = touchPos.x - 50 / 2;
        }*/
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.x += 250 * Gdx.graphics.getDeltaTime();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.x -= 250 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if(!backToGround) {
                jumping = true;
            }
        }
        if(jumping) {
            player.y += 10 * Gdx.graphics.getDeltaTime()*50;

            if (player.y >= 500) {
                jumping = false;
                backToGround = true;
            }
        }
        if (backToGround == true) {
            player.y -= 10 * Gdx.graphics.getDeltaTime()*50;
            if (player.y <= 320) {
                backToGround = false;
                player.y = 320;
            }
        }

        if(player.y > 580 - 50/2-30) player.y = 580 - 50/2-30;
        if(player.y < 290 - 50/2+10) player.y = 290 - 50/2+10;
        if(player.x > 800-50/2-45) player.x =800-50/2-45;
        if(player.x < 25) player.x = 25;
    }


    public void disposePlayer(){
        playerImage.dispose();

    }

}
