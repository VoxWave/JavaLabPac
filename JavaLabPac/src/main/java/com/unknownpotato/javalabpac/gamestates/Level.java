package com.unknownpotato.javalabpac.gamestates;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.unknownpotato.javalabpac.Pool;
import com.unknownpotato.javalabpac.State;
import com.unknownpotato.javalabpac.Stats;
import com.unknownpotato.javalabpac.entities.Ghost;
import com.unknownpotato.javalabpac.entities.Pacman;
import com.unknownpotato.javalabpac.entities.Pill;
import com.unknownpotato.javalabpac.entities.Wall;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.EntityType;
import com.unknownpotato.javalabpac.interfaces.Entity;
import com.unknownpotato.javalabpac.interfaces.Tickable;
import com.unknownpotato.javalabpac.rendering.Renderer;

/**
 * 
 * @author VoxWave
 *
 * Level contains a list of entities that make up the level.
 * basically it contains the  pacman, walls, pills and ghost.
 * this class is for easy rendering and ticking of entities.
 * this class also contains a Stats object which keeps track of pacmans lives and the score.
 */
public class Level implements ContactListener, State {
	
	private World world;
	private Pacman pacman;
	private Stats stats;
	private Sprite pacsprite;
	private Sprite wall;
	private Sprite pill;
	private Sprite ghost;
	private int pillCount;
	private Pool<Entity> entitypool;
	private Renderer renderer;
	private Box2DDebugRenderer debug;
	
	public Level(Sprite pacman, Sprite wall, Sprite pill, Sprite ghost){
		this.pillCount = 0;
		this.renderer = new Renderer();
		this.debug = new Box2DDebugRenderer(true, true, true, true, true, true);
		this.ghost = ghost;
		this.stats = new Stats(3);
		this.entitypool = new Pool<Entity>();
		this.world = new World(new Vector2(), true);
		this.world.setContactListener(this);
		this.pacsprite = pacman;
		this.pacman = new Pacman(new Vector2(1,0),this, this.pacsprite);
		this.entitypool.add(this.pacman);
		this.wall = wall;
		this.pill = pill;
		prepareMap();
		this.entitypool.update();
	}
	
	/**
	 * prepareMap is a method for creating the map (placing walls pills and such)
	 */
	public void prepareMap() {
		
		createBoundaries();
		addPills();
		addWalls();
		addGhosts();
		
	}
	
	/**
	 * addPills adds all of the pills in their right places.
	 */
	
	public void addPills(){
		addPill(3f,0f);
		addPill(5f,0f);
		addPill(7f,0f);
		addPill(9f,0f);
		addPill(11f,0f);
		addPill(13f,0f);
		addPill(15f,0f);
		addPill(17f,0f);
	}
	
	/**
	 * addWalls is a hardcoded method for adding walls of of the level layout.
	 * I apologize for the lenght of it. too lazy to add for loop and such
	 */
	
	public void addWalls(){
		this.entitypool.add(new Wall(new Vector2(1f,2f), this, this.wall));
		this.entitypool.add(new Wall(new Vector2(3f,2f), this, this.wall));
		this.entitypool.add(new Wall(new Vector2(-1f,2f), this, this.wall));
	}
	/**
	 * addPill creates and adds one pill into the entity pool.
	 * the method also increments the pillcount so that we know when pacman has eaten all of the pills
	 * @param x
	 * @param y
	 */
	
	public void addPill(float x, float y){
		this.entitypool.add(new Pill(new Vector2(x,y),this,this.pill));
		this.pillCount++;
	}
	
	public void createBoundaries(){
		 //add the upper walls
		for(float i = -19f; i<21f ; i+=2){
			this.entitypool.add(new Wall(new Vector2(i,14f),this, this.wall));
		}
		//add the lower walls
		for(float i = -19f; i<21f ; i+=2){
			this.entitypool.add(new Wall(new Vector2(i,-14f),this, this.wall));
		}
		//add the right walls
		for(float i = -14f; i<14f ; i+=2){
			this.entitypool.add(new Wall(new Vector2(-19f, i),this, this.wall));
		}
		//add the left walls
		for(float i = -14f; i<14f ; i+=2){
			this.entitypool.add(new Wall(new Vector2(19f, i),this, this.wall));
		}
	}
	
	public void addGhosts(){
		this.entitypool.add(new Ghost(ghost, pacman, this, new Vector2(13f,8f)));
	}
	
	/**
	 * Tick is called in the game render loop.
	 * tick advances the logic of the game and the physics engine.
	 */

	public State tick(){
		this.world.step(1, 10, 10);
		for(Entity e:this.entitypool){
			e.tick();
		}
		this.entitypool.update();
			return this;
		}
	
	public World getWorld(){
		return world;
		
	}

	public Stats getStats() {
		// TODO Auto-generated method stub
		return this.stats;
	}

	public Pool<Entity> getEntities() {
		// TODO Auto-generated method stub
		return this.entitypool;
	}
	
	/**
	 * see Game dispose method
	 */

	@Override
	public void dispose() {
		for(Entity e : this.entitypool){
			e.dispose();
		}
		this.world.dispose();
		this.renderer.dispose();
		this.debug.dispose();
	}
	
	/**
	 * in this method we are supposed to dispose of entities that are marked for removal
	 * such entities include pill (when pacman collides with pill, pill marks itself to be removed)
	 */
	
	public void removeDeadEntities(){
		ArrayList<Entity> toBeRemoved = new ArrayList<Entity>();
		for(Entity e:this.entitypool){
			if(e.isDead()){
				toBeRemoved.add(e);
			}
		}
		for(Entity e : toBeRemoved){
			this.entitypool.remove(e);
			e.dispose();
		}
	}
	
	/**
	 * this method is called when an collision between two bodies starts.
	 * see doContact for more detail on what happens
	 */

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		doContact(contact,CollisionType.START);
	}
	
	/**
	 * this method is called when an collision between two bodies ends
	 * see doContact for more detail on what happens.
	 */

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		doContact(contact,CollisionType.END);
		
	}
	
	/**
	 * whenever two bodies collision starts or end this method is called.
	 * both entities (bodies have user data which is the entity object) collide methods are called.
	 * 
	 * @param contact
	 * @param type
	 */
	
	private void doContact(Contact contact, CollisionType type) {
		if (contact.getFixtureA() == null || contact.getFixtureB() == null) {
            return;
    }
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();
		Entity entityA = (Entity) bodyA.getUserData();
		Entity entityB = (Entity) bodyB.getUserData();
		entityA.collide(entityB, type);
		entityB.collide(entityA, type);
	}
	
	/**
	 * I dont know what this method does mostly because I don't need it in this project.
	 */

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * I dont know what this method does mostly because I don't need it in this project.
	 */

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		this.renderer.render(this.getEntities());
		this.debug.render(this.getWorld(), this.renderer.getView().getCamera().combined.cpy());
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int widht, int height) {
		// TODO Auto-generated method stub
		
	}

	public void onDeathReset() {
		// TODO Auto-generated method stub
		this.pacman = new Pacman(new Vector2(1,0), this, this.pacsprite);
		this.entitypool.add(this.pacman);
		for(Entity e:this.entitypool){
			if(e.getType() == EntityType.GHOST || e.getType() == EntityType.PACMAN){
				this.entitypool.remove(e);
			}
		}
		this.addGhosts();
//		this.entitypool.update();
	}
	



}
