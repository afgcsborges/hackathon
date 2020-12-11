package com.academiadecodigo.gnunas.screens;

import com.academiadecodigo.gnunas.InHerHands;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class HighScoreScreen extends ScreenAdapter {

    private final InHerHands game;
    private Texture menuBackground;
    private SpriteBatch batch;
    FileHandle highscore;
    String first,second,third,fourth,fifth;
    private BitmapFont fontBit;
    CharSequence str;

    public HighScoreScreen(InHerHands game) {


        this.game = game;

        batch = new SpriteBatch();

        menuBackground = new Texture(Gdx.files.internal("MenuScores.png"));
        highscore = Gdx.files.local("highscores.txt");
        String currentScores = highscore.readString();

        String[] scores = currentScores.split("#");
        first=scores[0];
        second=scores[1];
        third=scores[2];
        fourth=scores[3];
        fifth=scores[4];


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 40;
        fontBit = generator.generateFont(parameter);






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


        str = first;
        fontBit.draw(batch, str, 330, 390);
        str=second;
        fontBit.draw(batch, str, 310, 330);
        str=third;
        fontBit.draw(batch, str, 300, 270);
        str=fourth;
        fontBit.draw(batch, str, 295, 210);
        str=fifth;
        fontBit.draw(batch, str, 280, 150);

        batch.end();




    }

    @Override
    public void dispose() {

        menuBackground.dispose();
        batch.dispose();

    }
}
