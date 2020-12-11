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

public class GameOverScreen extends ScreenAdapter {

    private final InHerHands game;
    private Texture menuBackground;
    private SpriteBatch batch;
    private float score;
    private BitmapFont fontBit;
    CharSequence str;
    String username = "";
    String scoreSource = "";


    public GameOverScreen(InHerHands game, float score) {

        this.score = score;

        this.game = game;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 58;
        fontBit = generator.generateFont(parameter);

        batch = new SpriteBatch();

        menuBackground = new Texture(Gdx.files.internal("DeathBackground.png"));
    }


    @Override
    public void show() {

        /*String ola = "";

        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String text) {
                username = text;

            }

            @Override
            public void canceled() {

            }
        },ola,ola,ola);*/

    }

    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            game.setScreen(new PlayingScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MenuScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.H)){
            game.setScreen(new HighScoreInput(game,scoreSource));
        }

        batch.begin();
        batch.draw(menuBackground,0,0);
        drawTimer(batch, score);
        batch.end();




    }

    @Override
    public void dispose() {

        menuBackground.dispose();
        batch.dispose();

    }

    private void drawTimer(SpriteBatch batch, Float time) {


        int minutes = (int) (time / 60);
        int seconds = (int) (time - (minutes * 60));
        int milis = (int) ((time - (60 * minutes) - seconds) * 100);

        String secondsString = "" + seconds;
        if (seconds < 10) {
            secondsString = "0" + seconds;
        }

        String milisString = "" + milis;
        if (milis < 10) {
            milisString = "0" + milis;
        }


        str = minutes + ":" + secondsString + ":" + milisString;
        scoreSource = minutes + ":" + secondsString + ":" + milisString;
        fontBit.draw(batch, str, 110, 250);
    }
}

