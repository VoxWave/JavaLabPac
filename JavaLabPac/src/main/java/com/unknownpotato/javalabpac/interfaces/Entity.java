/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unknownpotato.javalabpac.interfaces;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import com.unknownpotato.javalabpac.entities.*;
import com.unknownpotato.javalabpac.enums.CollisionType;
import com.unknownpotato.javalabpac.enums.Direction;
import com.unknownpotato.javalabpac.enums.EntityType;

/**
 * all of the things that appear on the screen (ghost, pacman, pill and wall)
 * implement this interface
 * @author VoxWave
 */
public interface Entity extends Tickable, Disposable {
	
	void createBody(Vector2 pos);
	
	EntityType getType();
	
	Vector2 getPos();
	
	Body getBody();

	void collide(Entity entityB, CollisionType type);
	
	Sprite getSprite();
	
	void move(Direction direction);

	float getSize();

	boolean isDead();
	
	void setDead(boolean isDead);

	float getRotation();
    
}
