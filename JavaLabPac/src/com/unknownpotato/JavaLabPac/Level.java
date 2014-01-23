package com.unknownpotato.JavaLabPac;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.JavaLabPac.Interfaces.Tickable;

public class Level implements Tickable {
	
	private World world;
	
	public Level(int[][] lvl){
		this.world = new World(new Vector2(), true);
	}
	
	public void tick(){
		this.world.step(1, 10, 10);
	}

}
