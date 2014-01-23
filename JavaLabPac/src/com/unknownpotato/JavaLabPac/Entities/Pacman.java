package com.unknownpotato.JavaLabPac.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.unknownpotato.JavaLabPac.Enums.CollisionType;

public class Pacman implements Entity {
	
	private Sprite sprite;
	
	public Pacman(){
		FileHandle file = Gdx.files.getFileHandle("rsc/Pacman.png", FileType.Internal);
		Texture texture = new Texture(file);
		this.sprite = new Sprite(texture);
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

}
