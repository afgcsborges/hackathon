package com.academiadecodigo.gnunas;

import com.academiadecodigo.gnunas.map.Map;
import com.academiadecodigo.gnunas.player.Player;
import com.academiadecodigo.gnunas.player.PlayerController;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InHerHands extends ApplicationAdapter {
	private Map map;
	SpriteBatch batch;
	private Player player1;
	private PlayerController playerController;
	private Music backgroundMusic;
	private Bullet bullet;


	public InHerHands() {
		map = new Map();
		player1 = new Player();
		playerController = new PlayerController();
		bullet = new Bullet();

	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		map.createMap();
		player1.createPlayer();
		playerController.createPlayerController();
		bullet.createBullet();
	}

	@Override
	public void render () {
		map.renderMap(batch);
		player1.renderPlayer(batch);
		playerController.renderPlayerController(batch);
		bullet.renderBullet(batch);
	}
	
	@Override
	public void dispose () {
		map.disposeMap(batch);
		player1.disposePlayer();
		playerController.disposePlayer();
		bullet.disposeBullet();
	}
}
