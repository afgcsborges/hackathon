package com.academiadecodigo.gnunas.screens;

import obstacles.*;
import com.academiadecodigo.gnunas.InHerHands;
import com.academiadecodigo.gnunas.player.Player;
import com.academiadecodigo.gnunas.player.PlayerController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class PlayingScreen extends ScreenAdapter {

    private InHerHands game;
    Texture background1;
    Texture background2;
    Texture frame;
    Texture timerBoard;
    Texture bulletImage;

    Texture wallImage;
    Texture barrelImage;

    Texture herBoard;
    Texture jump, duck, shoot;
    private SpriteBatch batch;
    float yMax, yCoordBg1, yCoordBg2;
    public static float BACKGROUND_MOVE_SPEED = 100f; // pixels per second. Put your value here.
    float timeElapsed = 0f;
    FreeTypeFontGenerator font;
    private BitmapFont fontBit;
    CharSequence str;
    private Player player1;
    private PlayerController playerController;
    //Game Objects
    private List<Rectangle> bullets;
    private List<Obstacle> obstacles;

    private HerDecision decision = HerDecision.NONE;
    private Rectangle buttonDuck, buttonShoot, buttonJump;

    private int difficulty = 0;
    private int level = 600;

    private int orcCounter = 0;

    LinkedList<Rectangle> disposeBullet;
    LinkedList<Obstacle> disposeWall;

    private float lastShot = 0f;


    public PlayingScreen(InHerHands game) {

        this.game = game;
        batch = new SpriteBatch();
        bullets = new LinkedList<Rectangle>();
        obstacles = new LinkedList<>();
        disposeBullet = new LinkedList<>();
        disposeWall = new LinkedList<>();


        //Creates the infinite Background
        background1 = new Texture(Gdx.files.internal("GameBackground.png"));
        background2 = new Texture(Gdx.files.internal("GameBackground.png")); // identical
        yMax = 1000;
        yCoordBg1 = yMax * (-1);
        yCoordBg2 = 0;

        //Creates the Game Frame
        frame = new Texture(Gdx.files.internal("GameDesign.png"));


        timerBoard = new Texture(Gdx.files.internal("TimerBackground.png"));
        herBoard = new Texture(Gdx.files.internal("buttons/layout_player2.png"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 58;
        fontBit = generator.generateFont(parameter);

        duck = new Texture(Gdx.files.internal("buttons/button_duck_up.png"));
        buttonDuck = new Rectangle();
        buttonDuck.x = 230;
        buttonDuck.y = 160;
        buttonDuck.width = 100;
        buttonDuck.height = 60;


        jump = new Texture(Gdx.files.internal("buttons/button_jump_up.png"));
        buttonJump = new Rectangle();
        buttonJump.x = 445;
        buttonJump.y = 40;
        buttonJump.width = 60;
        buttonJump.height = 100;

        shoot = new Texture(Gdx.files.internal("buttons/button_shoot_up.png"));
        buttonShoot = new Rectangle();
        buttonShoot.x = 45;
        buttonShoot.y = 40;
        buttonShoot.width = 60;
        buttonShoot.height = 100;


        player1 = new Player();
        playerController = new PlayerController();

        player1.createPlayer(this);
        playerController.createPlayerController();


        wallImage = new Texture(Gdx.files.internal("Wall.png"));
        barrelImage = new Texture(Gdx.files.internal("Barrel.png"));


        bulletImage = new Texture(Gdx.files.internal("bullet.png"));
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
        BACKGROUND_MOVE_SPEED += Gdx.graphics.getDeltaTime() * 2;
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
        batch.draw(herBoard, 17, 20);
        batch.draw(timerBoard, 550, 20);
        batch.draw(jump, buttonJump.x, buttonJump.y);
        batch.draw(duck, buttonDuck.x, buttonDuck.y);
        batch.draw(shoot, buttonShoot.x, buttonShoot.y);
        drawTimer(batch, timeElapsed);
        batch.end();
        renderPlayers();
        renderBullets();
        renderObstacles();
        checkDecision();
        checkCollision();

        batch.begin();
        batch.draw(frame, 0, 0);
        batch.end();
    }

    private void checkCollision() {


        for (Obstacle obstacle : obstacles) {

            if (obstacle instanceof Monster) {
                for (Rectangle bullet : bullets) {
                    if (bullet.overlaps(obstacle.getRectangle())) {
                        disposeWall.add(obstacle);
                        disposeBullet.add(bullet);
                    }
                }
            }


            if (obstacle instanceof Monster) {

                player1.getPlayer().x -= Gdx.graphics.getDeltaTime() * 2250;
            }
            if (obstacle instanceof Wall || obstacle instanceof Barrel) {

                player1.getPlayer().x -= Gdx.graphics.getDeltaTime() * 250;
            }

            if (player1.getPlayer().overlaps(obstacle.getRectangle())) {
                game.setScreen(new GameOverScreen(game, timeElapsed));
            }
            if (obstacle instanceof Monster) {

                player1.getPlayer().x += Gdx.graphics.getDeltaTime() * 2250;
            }
            if (obstacle instanceof Wall || obstacle instanceof Barrel) {

                player1.getPlayer().x += Gdx.graphics.getDeltaTime() * 250;
            }
        }

        for (Rectangle bullet : bullets){
            if (bullet.x >= 800) {
                disposeBullet.add(bullet);
            }
        }

        for (Obstacle obstacle : disposeWall) {
            obstacles.remove(obstacle);
        }
        for (Rectangle bullet : disposeBullet) {
            bullets.remove(bullet);
        }


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

        fontBit.draw(batch, str, 572, 123);



        difficulty++;
        //Launching obstacles
        if (difficulty == 1 || difficulty % level == 0) {

            level-=15;
            if( level <100) {
                level = 100;
            }



            Obstacle obstacle = ObstacleFactory.createObstacle();
            obstacle.create();

            obstacles.add(obstacle);
        }
    }


    public void renderPlayers() {

        player1.renderPlayer(batch);
        playerController.renderPlayerController(batch);
    }


    public void shootBullet() {

        if (bullets.size() < 3 && (timeElapsed - lastShot > 5/10f)) {
            lastShot = timeElapsed;
            Bullet bullet = new Bullet();
            bullets.add(bullet.createBullet(player1.getX(), player1.getY()));
        }

    }

    public void renderBullets() {
        batch.begin();
        for (Rectangle bullet : bullets) {
            batch.draw(bulletImage, bullet.x, bullet.y);
            bullet.x += 300 * Gdx.graphics.getDeltaTime();
        }
        batch.end();
    }

    public void renderObstacles() {

        batch.begin();

        orcCounter++;

        for (Obstacle obstacle : obstacles) {
            batch.draw(obstacle.getImage(), obstacle.getRectangle().x, obstacle.getRectangle().y);
            obstacle.getRectangle().x -= BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();

            if (obstacle instanceof Wall && orcCounter % 10 == 0) {
                Wall wall = (Wall) obstacle;
                wall.changeImage();
            }

            if (obstacle instanceof Monster && orcCounter % 7 == 0) {
                Monster monster = (Monster) obstacle;
                monster.changeImage();
            }
        }

        batch.end();

    }

    private void checkDecision() {

        HerDecision current = decision;
        if (playerController.getRectangle().overlaps(buttonJump)) {
            current = HerDecision.JUMP;
        } else if (playerController.getRectangle().overlaps(buttonDuck)) {
            current = HerDecision.DUCK;
        } else if (playerController.getRectangle().overlaps(buttonShoot)) {
            current = HerDecision.SHOOT;
        } else {
            current = HerDecision.NONE;
        }


        if (current != decision) {
            decision = current;
            player1.setDecision(current);
            pressAndRelease();
        }
    }

    private void pressAndRelease() {

        jump = new Texture(Gdx.files.internal("buttons/button_jump_up.png"));
        duck = new Texture(Gdx.files.internal("buttons/button_duck_up.png"));
        shoot = new Texture(Gdx.files.internal("buttons/button_shoot_up.png"));

        if (decision == HerDecision.NONE) {
            return;
        } else if (decision == HerDecision.JUMP) {
            jump = new Texture(Gdx.files.internal("buttons/button_jump_down.png"));
        } else if (decision == HerDecision.DUCK) {
            duck = new Texture(Gdx.files.internal("buttons/button_duck_down.png"));
        } else {
            shoot = new Texture(Gdx.files.internal("buttons/button_shoot_down.png"));
        }
    }


    public HerDecision getDecision() {
        return decision;
    }

    public enum HerDecision {
        NONE,
        JUMP,
        DUCK,
        SHOOT
    }
}
