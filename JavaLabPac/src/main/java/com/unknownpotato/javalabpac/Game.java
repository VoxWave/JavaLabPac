package com.unknownpotato.javalabpac;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.entities.Pacman;
import com.unknownpotato.javalabpac.entities.Wall;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.interfaces.Entity;
import com.unknownpotato.javalabpac.rendering.Renderer;

/**
 * 
 * @author VoxWave
 * 
 * Game is basically the libGDX equivalent of a main method.
 * the render method is called everytime the system renders the screen. 
 * we also run the logic part of the game in the render method.
 *
 */

public class Game implements ApplicationListener {
	
	private Level level;
	private int width;
	private int height;
	private Renderer renderer;
	private Box2DDebugRenderer debug;
	
	
	/**
	 * create is sort of comparable to a constructor.
	 * basically we setup everything needed in it
	 */
	@Override
	public void create() {
		
		//these are the two renderers we use. 
		//renderer renders sprites
		//debug renders the physics engines bodies (its used for debuging ofc) 
		this.renderer = new Renderer();
		this.debug = new Box2DDebugRenderer(true, true, true, true, true, true);
		
		//load up all the sprites
		Sprite pac = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal)));
		Sprite pill = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal)));
		Sprite ghost = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal)));
		Sprite wall = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal)));
		
		this.level = new Level(pac, wall, pill, ghost);
		
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
		this.renderer.getView().update(this.width, this.height);

	}
	
	/**
	 * render is called everytime the window renders.
	 * here we render the sprites and the debug view as well as tick foward our game logic.
	 */

	@Override
	public void render() {
		this.renderer.render(this.level.getEntities());
		this.debug.render(this.level.getWorld(), this.renderer.getView().getCamera().combined.cpy());
		this.level.tick();
		System.out.println(this.level.getStats());
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
	 * due to the way libGDX is designed, we need to dispose unused resources manually.
	 * failing to do so results in memory leaks.
	 */

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.renderer.dispose();
		this.level.dispose();

	}

	


}
