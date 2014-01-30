package com.unknownpotato.javalabpac.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;

public class Wall implements Entity {
	
	private World world;
	private Sprite sprite;
	private Body body;

	public Wall(Vector2 pos, World world, Sprite sprite){
		this.world = world;
		this.sprite = sprite;
		this.createBody(pos);
		
	}

	@Override
	public void tick() {
		//
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

	@Override
	public Vector2 getPos() {

		return this.body.getPosition();
	}

	@Override
	public Body getBody() {

		return this.body;
	}

	@Override
	public void collide(Entity entityB, CollisionType type) {
		//wall does not need any collide logic
		//collide is called
		
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public void move(Direction direction) {
		// a wall does not move.
		
	}

	@Override
	public float getSize() {
		
		return 0;
	}

	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
