package com.academiadecodigo.gnunas.screens;

import obstacles.Barrel;
import obstacles.Bullet;
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
import obstacles.Wall;

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
    Texture jump,duck;
    private SpriteBatch batch;
    float yMax, yCoordBg1, yCoordBg2;
    public static float BACKGROUND_MOVE_SPEED = 100f; // pixels per second. Put your value here.
    float timeElapsed = 0f;
    FreeTypeFontGenerator font;
    private BitmapFont fontBit;
    private BitmapFont shootDecision, jumpDecision, duckDecision;
    CharSequence str;
    private Player player1;
    private PlayerController playerController;
    //Game Objects
    private List<Rectangle> bullets;
    private List<Rectangle> brickWalls;
    private List<Rectangle> barrels;

    private HerDecision decision = HerDecision.NONE;
    private com.badlogic.gdx.math.Rectangle buttonJump;
    private Rectangle buttonDuck;

    private int obstacleCounter = 0;
    private int difficulty = 15;

    private List<Integer> used;


    public PlayingScreen(InHerHands game) {

        this.game = game;
        batch = new SpriteBatch();
        bullets = new LinkedList<Rectangle>();
        barrels = new LinkedList<>();
        brickWalls = new LinkedList<>();
        used = new LinkedList<>();


        //Creates the infinite Background
        background1 = new Texture(Gdx.files.internal("GameBackground.png"));
        background2 = new Texture(Gdx.files.internal("GameBackground.png")); // identical
        yMax = 1000;
        yCoordBg1 = yMax * (-1);
        yCoordBg2 = 0;

        //Creates the Game Frame
        frame = new Texture(Gdx.files.internal("GameDesign.png"));


        timerBoard = new Texture(Gdx.files.internal("TimerBackground.png"));
        herBoard = new Texture(Gdx.files.internal("rectangle_510_210.png"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font2.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 58;
        fontBit = generator.generateFont(parameter);
        shootDecision = generator.generateFont(parameter);
        duckDecision = generator.generateFont(parameter);
        jumpDecision = generator.generateFont(parameter);

        duck = new Texture(Gdx.files.internal("button.png"));
        buttonDuck = new Rectangle();
        buttonDuck.x = 210;
        buttonDuck.y = 220;
        buttonDuck.width=150;
        buttonDuck.height=50;



        jump = new Texture(Gdx.files.internal("button.png"));
        buttonJump = new Rectangle();
        buttonJump.x = 380;
        buttonJump.y = 150;
        buttonJump.width = 150;
        buttonJump.height = 50;


        //font = new BitmapFont();

        player1 = new Player();
        playerController = new PlayerController();

        player1.createPlayer(this);
        playerController.createPlayerController();
        //bullet.createBullet();

        wallImage = new Texture(Gdx.files.internal("Wall.png"));
        barrelImage = new Texture(Gdx.files.internal("Barrel.png"));
        bulletImage = new Texture(Gdx.files.internal("player.jpg"));


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
        batch.draw(duck,buttonDuck.x,buttonDuck.y);

        drawTimer(batch, timeElapsed);
        shootDecision.draw(batch, "Shoot", 35, 150);
        duckDecision.draw(batch, "Duck", 210, 200);
        jumpDecision.draw(batch, "Jump", 380, 150);
        batch.end();
        renderPlayers();
        renderBullets();
        renderBarrels();
        renderWalls();
        checkDecision();

        batch.begin();
        batch.draw(frame, 0, 0);
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
        int milis = (int) ((time - (60 * minutes) - seconds) * 100);

        String secondsString = "" + seconds;
        if (seconds < 10) {
            secondsString = "0" + seconds;
        }

        str = minutes + ":" + secondsString + ":" + milis;
        //str = "6:60:60";

        fontBit.draw(batch, str, 572, 123);

        //Launching walls
        if(obstacleCounter == 0 || (seconds % (difficulty * obstacleCounter) == 0 && !used.contains(seconds))){
            used.add(seconds);
            obstacleCounter++;
            difficulty--;
            Wall wall = new Wall();
            brickWalls.add(wall.create(801, 310));
            System.out.println("wall created");
        }

        //Launching barrels
        if(obstacleCounter == 0 || (seconds % (difficulty * obstacleCounter) == 0 && !used.contains(seconds))){
            used.add(seconds);
            obstacleCounter++;
            difficulty--;
            Barrel barrel = new Barrel();
            barrels.add(barrel.create(801, 310));
            System.out.println("barrel created");
        }


    }


    public void renderPlayers() {

        player1.renderPlayer(batch);
        playerController.renderPlayerController(batch);
    }


    public void shootBullet() {

        Bullet bullet = new Bullet();
        System.out.println(player1.getX());
        bullets.add(bullet.createBullet(player1.getX(),player1.getY()));

    }

   public void renderBullets(){
        batch.begin();
        for (Rectangle bullet : bullets) {
            batch.draw(bulletImage,bullet.x,bullet.y);
            bullet.x += 100 * Gdx.graphics.getDeltaTime();
        }
        batch.end();
    }

    public void renderBarrels(){
        batch.begin();

        for (Rectangle barrel : barrels) {
            batch.draw(barrelImage, barrel.x, barrel.y);
            barrel.x -= 100 * Gdx.graphics.getDeltaTime();
        }

        batch.end();
    }
    public void renderWalls() {

        batch.begin();

        for (Rectangle wall : brickWalls) {
            batch.draw(wallImage, wall.x, wall.y);
            wall.x -= BACKGROUND_MOVE_SPEED * Gdx.graphics.getDeltaTime();
        }

        batch.end();

    }

    private void checkDecision() {
        //TODO: se estiver em cima de x entao decision = x
        HerDecision current = decision;
        if (playerController.getRectangle().overlaps(buttonJump)) {
            current = HerDecision.JUMP;
        }

        if(playerController.getRectangle().overlaps(buttonDuck)){
            current=HerDecision.DUCK;
        }


        if (current != decision) {
            decision = current;
            player1.setDecision(current);
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
