package com.academiadecodigo.gnunas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

public class InHerHands extends ApplicationAdapter {


    SpriteBatch batch;
    Texture img;
    private int state = 0;
    Texture background1;
    Texture background2;
    Texture frame;
    float yMax, yCoordBg1, yCoordBg2;
    float BACKGROUND_MOVE_SPEED = 100f; // pixels per second. Put your value here.
    float timeElapsed= 0f;

    @Override
    public void create() {

        batch = new SpriteBatch();

        //Creates the infinite Background
        background1 = new Texture(Gdx.files.internal("GameBackground.png"));
        background2 = new Texture(Gdx.files.internal("GameBackground.png")); // identical
        yMax = 1000;
        yCoordBg1 = yMax * (-1);
        yCoordBg2 = 0;

        //Creates the Game Frame
        frame = new Texture(Gdx.files.internal("GameDesign.png"));
    }

    @Override
    public void render() {



        if (state == 0) {
            if(Gdx.input.isKeyPressed(Input.Keys.S)) {
                state = 1;
            }
        }

        if (state == 1) {
            playGame();

        }

        if (state == 2) {
           //TODO: instructions
        }
        if (state == 3) {
            //TODO: highscores
        }
    }

    private void playGame() {

        timeElapsed += Gdx.graphics.getDeltaTime();

        //Scales game speed aka difficulty with time elapsed
        BACKGROUND_MOVE_SPEED += Gdx.graphics.getDeltaTime();
        //System.out.println(BACKGROUND_MOVE_SPEED);

        yCoordBg1 += BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
        yCoordBg2 = yCoordBg1 + yMax;  // We move the background, not the camera
        if (yCoordBg1 >= 0) {
            yCoordBg1 = yMax * (-1);
            yCoordBg2 = 0;
        }
        batch.begin();
        batch.draw(background1, -yCoordBg1, 270);
        batch.draw(background2, -yCoordBg2, 270);
        batch.draw(frame,0,0);
        batch.end();


    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
