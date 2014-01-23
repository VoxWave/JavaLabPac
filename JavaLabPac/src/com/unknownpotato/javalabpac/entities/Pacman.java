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
import com.unknownpotato.javalabpac.Level;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;

public class Pacman implements Entity {
	
	private Sprite sprite;
	private Body body;
	private Level level;
	
	public Pacman(){
		FileHandle file = Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal);
		Texture texture = new Texture(file);
		this.sprite = new Sprite(texture);
		
		
	}

	@Override
	public Vector2 getPos() {
		// TODO Auto-generated method stub
		return this.body.getPosition();
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return this.getBody();
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
		switch(direction){
		case UP:
			this.body.applyForceToCenter(new Vector2(0,1), true);
			break;
		case DOWN:
			this.body.applyForceToCenter(new Vector2(0,-1), true);
			break;
		case LEFT:
			this.body.applyForceToCenter(new Vector2(-1,0), true);
			break;
		case RIGHT:
			this.body.applyForceToCenter(new Vector2(1,0), true);
			break;
		default:
			break;
		}
	}

	@Override
	public void tick() {
		Vector2 amount = new Vector2();
		// TODO Auto-generated method stub
		if (Gdx.input.isKeyPressed(Keys.W)) {
			this.move(Direction.UP);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			this.move(Direction.LEFT);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			this.move(Direction.DOWN);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			this.move(Direction.RIGHT);
		}
	}

	@Override
	public Entity createBody(Vector2 pos) {
		// TODO Auto-generated method stub
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(pos);

		def.linearDamping = 0.25f;
		def.fixedRotation = true;	

		this.body = this.level.getWorld().createBody(def);
		body.setUserData(this);

		CircleShape shape = new CircleShape();
		shape.setRadius(1);
		this.body.createFixture(shape, 0.00001f);
		shape.dispose();
		return this;
	}

}
