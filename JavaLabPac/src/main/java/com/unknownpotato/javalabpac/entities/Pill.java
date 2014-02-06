package com.unknownpotato.javalabpac.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;
import com.unknownpotato.javalabpac.enums.EntityType;
import com.unknownpotato.javalabpac.interfaces.Entity;

public class Pill implements Entity {
	
	private EntityType pill;

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBody(Vector2 pos) {
		// TODO Auto-generated method stub
		
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
		return null;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collide(Entity entityB, CollisionType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void move(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

}
