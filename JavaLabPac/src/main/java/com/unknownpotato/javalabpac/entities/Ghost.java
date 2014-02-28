package com.unknownpotato.javalabpac.entities;



import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;
import com.unknownpotato.javalabpac.enums.EntityType;
import com.unknownpotato.javalabpac.gamestates.Level;
import com.unknownpotato.javalabpac.interfaces.Entity;

/**
 * 
 * @author local
 *
 *	this class is the implementation of the ghost in pacman.
 *  It is an entity. when pacman collides with it, pacman should die.
 *
 */

public class Ghost implements Entity {
	
	private EntityType ghost;
	private Body body;
	private Level level;
	private Sprite sprite;
	private Pacman pacman;
	
	public Ghost(Sprite sprite,Pacman pacman,Level level,Vector2 pos){
		this.pacman = pacman;
		this.level = level;
		this.sprite = sprite;
		this.createBody(pos);
		this.ghost = EntityType.GHOST;
	}

	@Override
	public void tick() {
		Vector2 forssa = new Vector2(this.getPos()).scl(-1f).add(this.pacman.getPos());
		forssa.nor();
		forssa.scl(0.0000001f);
		this.body.applyForceToCenter(forssa, true);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void createBody(Vector2 pos) {
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(pos);
		def.fixedRotation = true;	

		this.body = this.level.getWorld().createBody(def);
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
	public Vector2 getPos() {
		// TODO Auto-generated method stub
		return this.body.getWorldCenter();
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return this.body;
	}

	@Override
	public void collide(Entity entityB, CollisionType type) {
		if(entityB.getType() == EntityType.PACMAN && type == CollisionType.START){
			this.level.getStats().reduceLives();
			entityB.setDead(true);
		}
		
	}

	@Override
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return this.sprite;
	}

	@Override
	public void move(Direction direction) {
		
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

	@Override
	public EntityType getType() {
		// TODO Auto-generated method stub
		return this.ghost;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDead(boolean isDead) {
		// TODO Auto-generated method stub
	}

}
