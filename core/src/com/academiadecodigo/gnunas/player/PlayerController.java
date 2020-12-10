package com.academiadecodigo.gnunas.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerController {
    private Texture controllerImage;
    private Rectangle controller;
    private OrthographicCamera camera;



    public void createPlayerController(){
        controllerImage = new Texture(Gdx.files.internal("player.jpg"));
        controller = new Rectangle();
        controller.x= 20 + 240;
        controller.y= 20 + 80;
        controller.width = 50;
        controller.height = 50;

        camera= new OrthographicCamera();
        camera.setToOrtho(false,800,290);
    }

    public void renderPlayerController(SpriteBatch batch){
        // Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // camera.update();
        //batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(controllerImage, controller.x, controller.y);
        batch.end();

       /* if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.x = touchPos.x - 50 / 2;
        }*/

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) controller.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) controller.y -= 200 * Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) controller.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) controller.x -= 200 * Gdx.graphics.getDeltaTime();

        if(controller.y > 230 - 50/2-30) controller.y = 230 - 50/2-30;
        if(controller.y < 25) controller.y = 25;

        if(controller.x > 530 - 50/2-30) controller.x = 530 - 50/2-30;
        if(controller.x < 25) controller.x = 25;

    }

    public void disposePlayer(){
        controllerImage.dispose();

    }
}
