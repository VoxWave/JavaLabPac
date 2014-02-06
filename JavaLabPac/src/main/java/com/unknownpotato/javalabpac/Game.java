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


public class Game implements ApplicationListener, ContactListener {
	
	private Level level;
	private SpriteBatch batch;
	private int width;
	private int height;
	private Pacman pacman;
	private World world;
	private Wall wall;
	private Renderer renderer;
	private Box2DDebugRenderer debug;
	private ArrayList<Entity> entitylist;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		this.renderer = new Renderer();
		this.debug = new Box2DDebugRenderer(true, true, true, true, true, true);
		this.batch = new SpriteBatch();
		this.world = new World(new Vector2() , true);
		Sprite pac = new Sprite(new Texture(Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal)));
		this.pacman = new Pacman(new Vector2(),this.world, pac);
		this.wall = new Wall(new Vector2(0f,10f),this.world, pac);
		this.height = Gdx.graphics.getHeight();
		this.width = Gdx.graphics.getWidth();
		this.entitylist = new ArrayList<Entity>();
		this.entitylist.add(this.pacman);
		this.entitylist.add(wall);
		
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
		// TODO Auto-generated method stub
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		Gdx.gl.glViewport(0, 0, width, height);
//		batch.begin();
//		batch.draw(this.pacman.getSprite().getTexture(), 1f, 1f);
//		batch.end();
		
		this.renderer.render(this.entitylist);
		this.debug.render(this.world, this.renderer.getView().getCamera().combined.cpy());
		this.world.step(1, 10, 10);
		this.pacman.tick();
		System.out.println(this.pacman.getPos());
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
