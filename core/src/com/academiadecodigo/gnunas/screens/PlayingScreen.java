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
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class PlayingScreen extends ScreenAdapter {

    private InHerHands game;
    Texture background1;
    Texture background2;
    Texture frame;
    Texture timerBoard;
    private SpriteBatch batch;
    float yMax, yCoordBg1, yCoordBg2;
    float BACKGROUND_MOVE_SPEED = 100f; // pixels per second. Put your value here.
    float timeElapsed = 0f;
    FreeTypeFontGenerator font;
    private BitmapFont fontBit;
    CharSequence str;

    public PlayingScreen(InHerHands game) {

        this.game = game;
        batch = new SpriteBatch();

        //Creates the infinite Background
        background1 = new Texture(Gdx.files.internal("GameBackground.png"));
        background2 = new Texture(Gdx.files.internal("GameBackground.png")); // identical
        yMax = 1000;
        yCoordBg1 = yMax * (-1);
        yCoordBg2 = 0;

        //Creates the Game Frame
        frame = new Texture(Gdx.files.internal("GameDesign.png"));

        timerBoard = new Texture(Gdx.files.internal("TimerBackground.png"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 58;
        fontBit = generator.generateFont(parameter);

        //font = new BitmapFont();
    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.Q) {
                    game.setScreen(new MenuScreen(game));
                }
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {

        playGame();
    }

    @Override
    public void dispose() {


        batch.dispose();
        font.dispose();

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
        batch.draw(frame, 0, 0);
        batch.draw(timerBoard,550,20);
        drawTimer(batch, timeElapsed);
        batch.end();


    }

    private void drawTimer(SpriteBatch batch, Float time) {

        //int milliseconds = (int) (time * 1000);
        //int seconds = (milliseconds / 1000 );
        //int minutes = (seconds / 60) ;

        //long milliseconds = TimeUnit.;
        //long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);

        // long seconds = (milliseconds / 1000);
        //ong seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);

        //str = string.Format("{0:00}:{1:00}:{2:000}", minutes, seconds, milliseconds );

        int minutes = (int) (time / 60);
        int seconds = (int) (time - (minutes * 60));
        int milis = (int) ((time - (60 * minutes) - seconds)*100);

        String secondsString = "" + seconds;
        if (seconds <10){
            secondsString = "0" + seconds;
        }

        str = minutes + ":" + secondsString + ":" + milis;
        //str = "6:60:60";

        fontBit.draw(batch, str, 572, 123);


    }


}
