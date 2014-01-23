package com.unknownpotato.javalabpac;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.entities.Entity;
import com.unknownpotato.javalabpac.enums.CollisionType;


public class Game implements ApplicationListener, ContactListener {
	
	private Level level;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		int[][] lvl = new int[17][17];
		
		
		this.level = new Level(lvl);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		this.level.tick();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

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
