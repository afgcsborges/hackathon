package com.academiadecodigo.gnunas.screens;

import com.academiadecodigo.gnunas.InHerHands;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class InstructionsScreen extends ScreenAdapter {

    private final InHerHands game;
    private Texture menuBackground;
    private SpriteBatch batch;


    public InstructionsScreen(InHerHands game) {


        this.game = game;

        batch = new SpriteBatch();

        menuBackground = new Texture(Gdx.files.internal("MenuInstructions.png"));
    }


    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ESCAPE) {
                    game.setScreen(new MenuScreen(game));
                }
                return true;
            }
        });

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
