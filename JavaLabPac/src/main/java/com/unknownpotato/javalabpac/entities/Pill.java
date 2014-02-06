package com.unknownpotato.javalabpac.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.Level;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;
import com.unknownpotato.javalabpac.enums.EntityType;
import com.unknownpotato.javalabpac.interfaces.Entity;

public class Pill implements Entity {
	
	private EntityType pill;
	private Level level;
	private World world;
	private Sprite sprite;
	private Body body;
	
	public Pill(Vector2 pos, World world, Sprite sprite) {
		this.world = world;
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
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.StaticBody;
		def.position.set(pos);

		body = this.world.createBody(def);
		body.setUserData(this);

		PolygonShape rockBox = new PolygonShape();
		rockBox.setAsBox(1f, 1f);
		body.createFixture(rockBox, 0.0f);
		rockBox.dispose();
		
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
		if(type == CollisionType.START) {
			if(entityB.getType() == EntityType.PACMAN) {
				this.level.getStats().addScore();
			}
		}
		
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
