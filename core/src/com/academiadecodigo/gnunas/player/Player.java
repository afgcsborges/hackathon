package com.academiadecodigo.gnunas.player;

import com.academiadecodigo.gnunas.screens.PlayingScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Sprite {
    private Texture playerImage;
    private Rectangle player;
    private OrthographicCamera camera;
    private boolean jumping = false;
    private boolean backToGround = false;
    private PlayingScreen game;
    private boolean ducking = false;
    private PlayingScreen.HerDecision decision = PlayingScreen.HerDecision.NONE;

    private Texture[] leftMove;
    private Texture[] rightMove;
    private int counter;
    private boolean movingFront = true;
    private Music walkingSound;
    private Music jumpingSound;

    public void createPlayer(PlayingScreen game){
        this.game = game;


        leftMove = new Texture[] {
                new Texture(Gdx.files.internal("Man/man_left_run.png")),
                new Texture(Gdx.files.internal("Man/man_left_run_1.png")),
                new Texture(Gdx.files.internal("Man/man_left_still.png")),

        };

        rightMove = new Texture[] {
                new Texture(Gdx.files.internal("Man/man_right_run.png")),
                new Texture(Gdx.files.internal("Man/man_right_run_1.png")),
                new Texture(Gdx.files.internal("Man/man_right_still.png"))
        };
        playerImage = new Texture(Gdx.files.internal("Man/man_right_still.png"));
        player = new Rectangle();
        player.x = 100;
        player.y= 280 + 30;
        player.width = 51;
        player.height = 73;

        camera= new OrthographicCamera();
        camera.setToOrtho(false,800,290);
    }

    public void renderPlayer(SpriteBatch batch){

        counter ++;

        if (counter%5 == 0) {
            playerImage = movingFront ? rightMove[(counter/5) - 1] : leftMove[(counter/5) - 1];

        }

        if (counter==15) counter = 0;


        if(ducking){
            playerImage = new Texture(Gdx.files.internal("Man/man_crouch.png"));
            player.height = 25;
        }

        batch.begin();
        batch.draw(playerImage, player.x, player.y);
       // walkingSound = Gdx.audio.newMusic(Gdx.files.internal("walking.mp3"));
       // walkingSound.setLooping(true);
       // walkingSound.play();
        batch.end();


        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            movingFront = true;

            player.x += 250 * Gdx.graphics.getDeltaTime();


        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            movingFront = false;

            player.x -= 250 * Gdx.graphics.getDeltaTime();


        }

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    ducking = false;
                    playerImage = new Texture(Gdx.files.internal("Man/man_right_still.png"));
                    player.height = 50;
                }
                return true;
            }
        });


        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){

            if (decision == PlayingScreen.HerDecision.SHOOT){
                game.shootBullet();
            }

            if (decision == PlayingScreen.HerDecision.DUCK){
                ducking = true;
            }

            if(decision == PlayingScreen.HerDecision.JUMP) {
                if (!backToGround) {
                    jumping = true;

                }
            }
        }
        if(jumping) {
            jumpingSound = Gdx.audio.newMusic(Gdx.files.internal("jump.wav"));
            jumpingSound.play();
            jumpingSound = Gdx.audio.newMusic(Gdx.files.internal("jump.wav"));
            jumpingSound.play();
            player.y += 10 * Gdx.graphics.getDeltaTime()*50;

            if (player.y >= 500) {
                jumping = false;
                backToGround = true;
            }
        }
        if (backToGround == true) {
            player.y -= 10 * Gdx.graphics.getDeltaTime()*50;
            if (player.y <= 310) {
                backToGround = false;
                player.y = 310;
            }
        }

        if(player.y > 580 - 50/2-30) player.y = 580 - 50/2-30;
        if(player.y < 290 - 50/2+10) player.y = 290 - 50/2+10;
        if(player.x > 800-50/2-45) player.x =800-50/2-45;
        if(player.x < 25) player.x = 25;

        //shoot


    }


    public void disposePlayer(){
        playerImage.dispose();

    }

    public Rectangle getPlayer() {
        return player;
    }

    @Override
    public float getX() {
        return player.getX();
    }

    @Override
    public float getY() {
        return player.getY();
    }

    public void setDecision(PlayingScreen.HerDecision decision) {
        this.decision = decision;
    }


}
