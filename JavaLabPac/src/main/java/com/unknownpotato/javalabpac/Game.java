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

public class Game implements ApplicationListener, ContactListener {
	
	private Level level;
	private int width;
	private int height;
	private Renderer renderer;
	private Box2DDebugRenderer debug;
	
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

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.height= height;
		this.width = width;
		this.renderer.getView().update(this.width, this.height);

	}

	@Override
	public void render() {
		this.renderer.render(this.level.getEntities());
		this.debug.render(this.level.getWorld(), this.renderer.getView().getCamera().combined.cpy());
		this.level.tick();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.renderer.dispose();
		this.level.dispose();

	}

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		doContact(contact,CollisionType.START);
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		doContact(contact,CollisionType.END);
		
	}
	
	private void doContact(Contact contact, CollisionType type) {
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		Entity entityA = (Entity) bodyA.getUserData();
		Entity entityB = (Entity) bodyB.getUserData();
		entityA.collide(entityB, type);
		entityB.collide(entityA, type);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}


}
