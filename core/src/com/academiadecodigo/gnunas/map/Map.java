package com.academiadecodigo.gnunas.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    private Texture map;

    public void createMap() {

        map = new Texture("InHerHands_Layout_1.png");
    }

    public void renderMap(SpriteBatch batch) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(map, 0, 0);
        batch.end();
    }

    public void disposeMap(SpriteBatch batch) {
        batch.dispose();
    }
}
