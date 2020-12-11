package com.academiadecodigo.gnunas.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerController {
    private Texture controllerImage;
    private Rectangle controller;
    private OrthographicCamera camera;
    private Texture[] upMove;
    private Texture[] downMove;
    private Texture[] leftMove;
    private Texture[] rightMove;
    private int counter;



    public void createPlayerController(){

        upMove = new Texture[] {
                new Texture(Gdx.files.internal("Woman/woman_up_run.png")),
                new Texture(Gdx.files.internal("Woman/woman_up_run_1.png")),
                new Texture(Gdx.files.internal("Woman/woman_up_still.png"))
        };

        downMove = new Texture[] {
                new Texture(Gdx.files.internal("Woman/woman_down_run.png")),
                new Texture(Gdx.files.internal("Woman/woman_down_run_1.png")),
                new Texture(Gdx.files.internal("Woman/woman_down_still.png"))
        };

        leftMove = new Texture[] {
                new Texture(Gdx.files.internal("Woman/woman_left_run.png")),
                new Texture(Gdx.files.internal("Woman/woman_left_run_1.png")),
                new Texture(Gdx.files.internal("Woman/woman_left_still.png"))
        };

        rightMove = new Texture[] {
                new Texture(Gdx.files.internal("Woman/woman_right_run.png")),
                new Texture(Gdx.files.internal("Woman/woman_right_run_1.png")),
                new Texture(Gdx.files.internal("Woman/woman_right_still.png"))
        };



        controllerImage = new Texture(Gdx.files.internal("Woman/woman_down_still.png"));
        controller = new Rectangle();
        controller.x= 20 + 240;
        controller.y= 30;
        controller.width = 40;
        controller.height = 68;

        camera= new OrthographicCamera();
        camera.setToOrtho(false,800,290);
    }

    public void renderPlayerController(SpriteBatch batch){

        batch.begin();
        batch.draw(controllerImage, controller.x, controller.y);
        batch.end();


        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            counter ++;

            controller.y += 200 * Gdx.graphics.getDeltaTime();

            if (counter%5 == 0) {
                controllerImage = upMove[(counter/5) - 1];
            }

            if (counter==15) counter = 0;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            counter ++;

            controller.y -= 200 * Gdx.graphics.getDeltaTime();

            if (counter%5 == 0) {
                controllerImage = downMove[(counter / 5) - 1];
            }
            if (counter == 15) counter = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){


            counter ++;

            controller.x += 200 * Gdx.graphics.getDeltaTime();

            if (counter%5 == 0) {
                controllerImage = rightMove[(counter/5) - 1];
            }

            if (counter==15) counter = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){


            counter ++;

            controller.x -= 200 * Gdx.graphics.getDeltaTime();

            if (counter%5 == 0) {
                controllerImage = leftMove[(counter / 5) - 1];
            }
            if (counter == 15) counter = 0;
        }

        if(controller.y > 230 - 50/2-30) controller.y = 230 - 50/2-30;
        if(controller.y < 25) controller.y = 25;

        if(controller.x > 530 - 50/2-30) controller.x = 530 - 50/2-30;
        if(controller.x < 25) controller.x = 25;

    }

    public void disposePlayer(){
        controllerImage.dispose();

    }

    public Rectangle getRectangle() {
        return controller;
    }
}
