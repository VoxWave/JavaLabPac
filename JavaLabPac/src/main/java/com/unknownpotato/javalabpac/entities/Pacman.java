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
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.Game;
import com.unknownpotato.javalabpac.Level;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;

public class Pacman implements Entity {
	
	private Sprite sprite;
	private Body body;
	private Level level;
	private World world;
	private Texture texture;
	
	public Pacman(Vector2 pos,World world){
		
		/**
		 * load up our image
		 */
		
//		FileHandle file = Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal);
//		this.texture = new Texture(file);
//		this.sprite = new Sprite(texture);
		
		this.world = world;
		
		/**
		 * Here we create a body for pacman.
		 * Box2d physics engine uses the body in its calculations
		 */
		
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(pos);
		def.fixedRotation = true;	
		
		/**
		 * the box2d world creates the body in it self.
		 */

		this.body = this.world.createBody(def);
		body.setUserData(this);

		CircleShape shape = new CircleShape();
		shape.setRadius(1);
		this.body.createFixture(shape, 0.00001f);
		shape.dispose();
		
	}

	@Override
	public Vector2 getPos() {
		return this.body.getPosition();
	}

	@Override
	public Body getBody() {
		return this.getBody();
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
	public void createBody(Vector2 pos) {
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(pos);
		def.fixedRotation = true;	

		this.body = this.world.createBody(def);
		body.setUserData(this);

		CircleShape shape = new CircleShape();
		shape.setRadius(1);
		this.body.createFixture(shape, 0.00001f);
		shape.dispose();
	}

	@Override
	public float getSize() {
		return 2;
	}

}
