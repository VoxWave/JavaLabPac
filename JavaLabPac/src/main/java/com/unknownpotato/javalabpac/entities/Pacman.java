package com.unknownpotato.javalabpac.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.Game;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;
import com.unknownpotato.javalabpac.enums.EntityType;
import com.unknownpotato.javalabpac.gamestates.Level;
import com.unknownpotato.javalabpac.interfaces.Entity;

/**
 * 
 * @author local
 *
 *	Pacman is the hero of the game.
 *	pacman collects pills and avoids the ghosts.
 *	pacman is an entity
 *	the pacman class handles pacmans moving logic and contains pacmans body and pacmans sprite.
 */

public class Pacman implements Entity {
	
	private Sprite sprite;
	private Body body;
	private Level level;
	private World world;
	private Texture texture;
	private boolean isDead;
	
	public Pacman(Vector2 pos, Level level, Sprite sprite){
		
		/**
		 * load up our image
		 */
		this.sprite = sprite;
		this.isDead = false;
//		FileHandle file = Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal);
//		this.texture = new Texture(file);
//		this.sprite = new Sprite(texture);
		this.level = level;
		this.world = this.level.getWorld();
		
		/**
		 * Here we create a body for pacman.
		 * Box2d physics engine uses the body in its calculations
		 */
		this.createBody(pos);
		

		
	}

	@Override
	public Vector2 getPos() {
		return this.body.getWorldCenter();
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void collide(Entity entityB, CollisionType type) {
		
	}

	@Override
	public Sprite getSprite() {
		return this.sprite;
	}
	public Texture getTexture(){
		return this.texture;
	}

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		switch(direction){
		case UP:
			this.body.applyForceToCenter(new Vector2(0f,0.0000001f), true);
			break;
		case DOWN:
			this.body.applyForceToCenter(new Vector2(0f,-0.0000001f), true);
			break;
		case LEFT:
			this.body.applyForceToCenter(new Vector2(-0.0000001f,0f), true);
			break;
		case RIGHT:
			this.body.applyForceToCenter(new Vector2(0.0000001f,0f), true);
			break;
		default:
			break;
		}
	}

	@Override
	public void tick() {
		Vector2 amount = new Vector2();
		if (Gdx.input.isKeyPressed(Keys.W)) {
			this.move(Direction.UP);
		}
		else if (Gdx.input.isKeyPressed(Keys.A)) {
			this.move(Direction.LEFT);
		}
		else if (Gdx.input.isKeyPressed(Keys.S)) {
			this.move(Direction.DOWN);
		}
		else if (Gdx.input.isKeyPressed(Keys.D)) {
			this.move(Direction.RIGHT);
		}
	}

	@Override
	public void createBody(Vector2 pos) {
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(pos);
		def.fixedRotation = true;	

		this.body = this.world.createBody(def);
		body.setUserData(this);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(1.0f);
		FixtureDef fixdef = new FixtureDef();
		fixdef.density = 0.000001f;
		fixdef.shape = shape;
		body.createFixture(fixdef);
		shape.dispose();
	}

	@Override
	public float getSize() {
		return 2;
	}

	@Override
	public float getRotation() {
		return this.body.getLinearVelocity().angle();
	}

	@Override
	public void dispose() {
		this.world.destroyBody(this.body);
	}

	@Override
	public EntityType getType() {
		return EntityType.PACMAN;
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public void setDead(boolean isDead) {
		this.isDead = isDead;
		
	}

}
