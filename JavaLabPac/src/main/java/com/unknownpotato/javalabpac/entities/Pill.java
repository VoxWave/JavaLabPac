package com.unknownpotato.javalabpac.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.unknownpotato.javalabpac.Level;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;
import com.unknownpotato.javalabpac.enums.EntityType;
import com.unknownpotato.javalabpac.interfaces.Entity;

/**
 * 
 * @author local
 *
 * pills are what pacman eats. 
 * pills are sensors that detect collision with pacman, 
 * then increase the score and destroy themselves
 *
 */

public class Pill implements Entity {
	
	private EntityType pill;
	private Level level;
	private Sprite sprite;
	private Body body;
	
	public Pill(Vector2 pos, Level level, Sprite sprite) {
		this.level = level;
		this.sprite = sprite;
		this.createBody(pos);
	}

	@Override
	public void tick() {
		// we do nothing
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void createBody(Vector2 pos) {
		BodyDef bodyDef = new BodyDef();

		bodyDef.type = BodyType.StaticBody;

		bodyDef.position.set(pos);

		// Create our body in the world using our body definition
		body = this.level.getWorld().createBody(bodyDef);
		body.setUserData(this);

		CircleShape shape = new CircleShape();
		shape.setRadius(0.5f);
		FixtureDef def = new FixtureDef();
		def.isSensor = true;
		def.density = 0.0f;
		def.shape = shape;
		body.createFixture(def);
		shape.dispose();
		
	}
	
	/**
	 * This method is needed for identifying what type of entity has collided with an entity.
	 * this is because we need to know if a ghost or pacman collided with a pill so that we can
	 * deal with the collision appropriately.
	 */

	@Override
	public EntityType getType() {
		// TODO Auto-generated method stub
		return this.pill;
	}

	@Override
	public Vector2 getPos() {
		// TODO Auto-generated method stub
		return this.body.getPosition();
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collide(Entity entityB, CollisionType type) {
		System.out.println(entityB);
		if(type == type.START) {
			if(entityB.getType() == EntityType.PACMAN) {
				this.level.getStats().addScore();
			}
		}
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return this.sprite;
	}

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getSize() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

}
