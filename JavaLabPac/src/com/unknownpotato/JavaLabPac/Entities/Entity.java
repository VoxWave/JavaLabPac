/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unknownpotato.JavaLabPac.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.unknownpotato.JavaLabPac.Enums.CollisionType;

/**
 *
 * @author local
 */
public interface Entity {
	
	Vector2 getPos();
	
	Body getBody();

	void collide(Entity entityB, CollisionType type);
	
	Sprite getSprite();
    
}
