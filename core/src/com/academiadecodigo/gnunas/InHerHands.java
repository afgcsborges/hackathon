package com.academiadecodigo.gnunas;

import com.academiadecodigo.gnunas.screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InHerHands extends Game {


    SpriteBatch batch;
    Texture img;
    private int state = 0;

    private MenuScreen menuScreen;

    @Override
    public void create() {
        setScreen(new MenuScreen(this));



    /*
        batch = new SpriteBatch();

        //Creates the infinite Background
        background1 = new Texture(Gdx.files.internal("GameBackground.png"));
        background2 = new Texture(Gdx.files.internal("GameBackground.png")); // identical
        yMax = 1000;
        yCoordBg1 = yMax * (-1);
        yCoordBg2 = 0;

        //Creates the Game Frame
        frame = new Texture(Gdx.files.internal("GameDesign.png"));*/
    }

    /*@Override
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
    }*/



    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public enum GameState {
        MAIN_MENU,
        INSTRUCTIONS,
        PLAYING,
        GAME_OVER
    }
}
