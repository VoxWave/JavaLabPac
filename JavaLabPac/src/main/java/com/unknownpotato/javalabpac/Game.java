package com.unknownpotato.javalabpac;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.unknownpotato.javalabpac.gamestates.Level;
import com.unknownpotato.javalabpac.gamestates.NameGetter;
import com.unknownpotato.javalabpac.rendering.Renderer;

/**
 * Game is basically the libGDX equivalent of a main method.
 * the render method is called everytime the system renders the screen. 
 * we also run the logic part of the game in the render method.
 * 
 * 
 * @author VoxWave
 * 
 *
 */

public class Game implements ApplicationListener {
	
	private int width;
	private int height;
	private boolean renderDebug;
	private Level level;
	private String nimi;
	
	
	public Game(String nimi){
		this.nimi=nimi;
	}
	
	/**
	 * create is sort of comparable to a constructor.
	 * basically we setup everything needed in it
	 */
	@Override
	public void create() {
		
		//these are the two renderers we use. 
		//renderer renders sprites
		//debug renders the physics engines bodies (its used for debuging ofc) 
		this.renderDebug = true;
		
		
		//load up all the sprites
		Sprite pac = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal)));
		Sprite pill = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pill.png", FileType.Internal)));
		Sprite ghost = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Ghost.png", FileType.Internal)));
		Sprite wall = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Wall.png", FileType.Internal)));
		
		this.level = new Level(pac, wall, pill, ghost);
		this.level.create();
		
		this.height = Gdx.graphics.getHeight();
		this.width = Gdx.graphics.getWidth();

	}
	
	/**
	 * resize method is called when you resize the game window.
	 * if the game has things that are dependent on the screen size, 
	 * you can do changes here to prevent a complete breakdown of the gui.
	 * (breakdown in this case does not mean exceptions)
	 */

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.height= height;
		this.width = width;
		this.level.resize(this.width, this.height);

	}
	
	/**
	 * render is called everytime the window renders.
	 * here we render the sprites and the debug view as well as tick foward our game logic.
	 */

	@Override
	public void render() {
		this.level.tick();
		this.level.render();
		if(this.level.getStats().getLives() <= 0){
			Gdx.app.exit();
		}
		
	}
	
	/**
	 * android specific method. the game does not use it as it is a desktop game.
	 */

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * android specific method. the game does not use it as it is a desktop game.
	 */
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * due to the way libGDX is designed, we need to dispose some of the unused resources manually.
	 * failing to do so results in memory leaks.
	 */

	@Override
	public void dispose() {
		this.level.dispose();
	}

	


}
