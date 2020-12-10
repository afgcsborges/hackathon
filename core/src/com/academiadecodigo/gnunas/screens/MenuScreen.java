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

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.S) {
                    game.setScreen(new PlayingScreen(game));
                }
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(menuBackground,0,0);
        batch.end();


    }

    @Override
    public void dispose() {

        menuBackground.dispose();
        batch.dispose();

    }
}
