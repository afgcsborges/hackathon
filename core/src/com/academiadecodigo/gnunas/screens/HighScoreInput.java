package com.academiadecodigo.gnunas.screens;

import com.academiadecodigo.gnunas.InHerHands;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;

public class HighScoreInput extends ScreenAdapter {

    private final InHerHands game;
    private String score;
    String username = "";
    FileHandle highscore;
    private boolean possible = false;

    public HighScoreInput(InHerHands game, String score) {

        this.score = score;

        this.game = game;

        highscore = Gdx.files.local("highscores.txt");


    }


    @Override
    public void show() {

        String ola = "";

        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String text) {

                String currentScores = highscore.readString();

                String[] scores = currentScores.split("#");

                String score1 = scores[0];
                String score2 = scores[1];
                String score3 = scores[2];
                String score4 = scores[3];
                String score5 = scores[4];
                String score1int = score1.split("-")[1];
                String score2int = score2.split("-")[1];
                String score3int = score3.split("-")[1];
                String score4int = score4.split("-")[1];
                String score5int = score5.split("-")[1];
                String[] scores1divided = score1int.split(":");
                String[] scores2divided = score2int.split(":");
                String[] scores3divided = score3int.split(":");
                String[] scores4divided = score4int.split(":");
                String[] scores5divided = score5int.split(":");
                int finalScore1 = Integer.parseInt(scores1divided[0] + scores1divided[1] + scores1divided[2]);
                int finalScore2 = Integer.parseInt(scores2divided[0] + scores2divided[1] + scores2divided[2]);
                int finalScore3 = Integer.parseInt(scores3divided[0] + scores3divided[1] + scores3divided[2]);
                int finalScore4 = Integer.parseInt(scores4divided[0] + scores4divided[1] + scores4divided[2]);
                int finalScore5 = Integer.parseInt(scores5divided[0] + scores5divided[1] + scores5divided[2]);

                String[] scores6divided = score.split(":");
                System.out.println(scores6divided[0]);
                System.out.println(scores6divided[1]);
                System.out.println(scores6divided[2]);

                int finalScore6 = Integer.parseInt(scores6divided[0] + scores6divided[1] + scores6divided[2]);

                if (finalScore6 > finalScore5 ){
                    finalScore5 = finalScore6;
                    score5 = text + "-" + score;
                    score5int = score5.split("-")[1];
                    scores5divided = score5int.split(":");
                    finalScore5 = Integer.parseInt(scores5divided[0] + scores5divided[1] + scores5divided[2]);
                } else {
                    possible = true;
                    return;
                }

                if (finalScore5 > finalScore4) {
                    finalScore6 = finalScore4;
                    finalScore4 = finalScore5;
                    finalScore5 = finalScore6;

                    scores6divided = scores4divided;
                    scores4divided = scores5divided;
                    scores5divided = scores6divided;

                    String score6int= score4int;
                    score4int = score5int;
                    score5int = score6int;

                    String score6 = score4;
                    score4 = score5;
                    score5 = score6;
                }

                if (finalScore4 > finalScore3) {
                    finalScore6 = finalScore3;
                    finalScore3 = finalScore4;
                    finalScore4 = finalScore6;

                    scores6divided = scores3divided;
                    scores3divided = scores4divided;
                    scores4divided = scores6divided;

                    String score6int= score3int;
                    score3int = score4int;
                    score4int = score6int;

                    String score6 = score3;
                    score3 = score4;
                    score4 = score6;
                }

                if (finalScore3 > finalScore2) {
                    finalScore6 = finalScore2;
                    finalScore2 = finalScore3;
                    finalScore3 = finalScore6;

                    scores6divided = scores2divided;
                    scores2divided = scores3divided;
                    scores3divided = scores6divided;

                    String score6int= score2int;
                    score2int = score3int;
                    score3int = score6int;

                    String score6 = score2;
                    score2 = score3;
                    score3 = score6;
                }

                if (finalScore2 > finalScore1) {
                    finalScore6 = finalScore1;
                    finalScore1 = finalScore2;
                    finalScore2 = finalScore6;

                    scores6divided = scores1divided;
                    scores1divided = scores2divided;
                    scores2divided = scores6divided;

                    String score6int= score1int;
                    score1int = score2int;
                    score2int = score6int;

                    String score6 = score1;
                    score1 = score2;
                    score2 = score6;
                }



                String toWrite = score1 + "#" + score2 + "#" +score3 + "#" +score4 + "#" +score5 + "#";
                username = text;
                highscore.writeString(toWrite, false);
                possible = true;

            }

            @Override
            public void canceled() {

            }
        }, "Name yourself", "batata", "batata");
    }

    @Override
    public void render(float delta) {
        if (possible) {
            game.setScreen(new MenuScreen(game));
        }
    }
}
