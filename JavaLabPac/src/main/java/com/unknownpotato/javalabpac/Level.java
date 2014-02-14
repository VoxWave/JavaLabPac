package com.unknownpotato.javalabpac;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.unknownpotato.javalabpac.entities.Pacman;
import com.unknownpotato.javalabpac.entities.Pill;
import com.unknownpotato.javalabpac.entities.Wall;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.interfaces.Entity;
import com.unknownpotato.javalabpac.interfaces.Tickable;

/**
 * 
 * @author VoxWave
 *
 * Level contains a list of entities that make up the level.
 * basically it contains the  pacman, walls, pills and ghost.
 * this class is for easy rendering and ticking of entities.
 * this class also contains a Stats object which keeps track of pacmans lives and the score.
 */
public class Level implements Tickable, Disposable, ContactListener {
	
	private World world;
	private Pacman pacman;
	private Stats stats;
	private Sprite pacsprite;
	private Sprite wall;
	private Sprite pill;
	private Sprite ghost;
	private ArrayList<Entity> entitylist;
	
	public Level(Sprite pacman, Sprite wall, Sprite pill, Sprite ghost){
		this.stats = new Stats();
		this.entitylist = new ArrayList<Entity>();
		this.world = new World(new Vector2(), true);
		this.world.setContactListener(this);
		this.pacsprite = pacman;
		this.pacman = new Pacman(new Vector2(),this.world, this.pacsprite);
		this.entitylist.add(this.pacman);
		this.wall = wall;
		this.pill = pill;
		this.entitylist.add(new Pill(new Vector2(4f,4f), this, this.pill));
		createWalls();
	}
	
	/**
	 * createWalls is a method for creating the wall I.E creating the map.
	 * it is currently hardcoded
	 */
	private void createWalls() {
		 //lisätään yläseinämä
		for(float i = -21f; i<21f ; i+=2){
			this.entitylist.add(new Wall(new Vector2(i,14f),this.world, this.wall));
		}
		//lisätään alaseinämä
		for(float i = -21f; i<21f ; i+=2){
			this.entitylist.add(new Wall(new Vector2(i,-14f),this.world, this.wall));
		}
		//lisätään oikeaseinämä
		for(float i = -14f; i<14f ; i+=2){
			this.entitylist.add(new Wall(new Vector2(-19f, i),this.world, this.wall));
		}
		//lisätään vasenseinämä
		for(float i = -14f; i<14f ; i+=2){
			this.entitylist.add(new Wall(new Vector2(19f, i),this.world, this.wall));
		}
		
	}

	public void tick(){
		updateEntities();
		this.world.step(1, 10, 10);
		pacman.tick();
	}
	
	public World getWorld(){
		return world;
		
	}

	public Stats getStats() {
		// TODO Auto-generated method stub
		return this.stats;
	}

	public List<Entity> getEntities() {
		// TODO Auto-generated method stub
		return this.entitylist;
	}

	@Override
	public void dispose() {
		for(Entity e : this.entitylist){
			e.dispose();
		}
		this.world.dispose();
	}
	
	public void updateEntities(){
		
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
