package com.academiadecodigo.gnunas.screens;

import com.academiadecodigo.gnunas.InHerHands;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends ScreenAdapter {

    private final InHerHands game;
    private Texture menuBackground;
    private SpriteBatch batch;


    public MenuScreen(InHerHands game) {

        this.game = game;

        batch = new SpriteBatch();

        menuBackground = new Texture(Gdx.files.internal("MenuBackground.png"));
    }


    @Override
    public void show() {



    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(menuBackground,0,0);
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.setScreen(new PlayingScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.H)){
            game.setScreen(new HighScoreScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            game.setScreen(new InstructionsScreen(game));
        }
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ESCAPE) {
                    System.exit(0);
                }
                return true;
            }
        });


    }

    @Override
    public void dispose() {

        menuBackground.dispose();
        batch.dispose();

    }
}
