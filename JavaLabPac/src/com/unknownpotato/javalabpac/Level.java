package com.unknownpotato.javalabpac;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.entities.Pacman;
import com.unknownpotato.javalabpac.interfaces.Tickable;

public class Level implements Tickable {
	
	private World world;
	private Pacman pacman;
	
	public Level(int[][] lvl){
		this.world = new World(new Vector2(), true);
		this.pacman = new Pacman(new Vector2(),this.world);
	}
	
	public void tick(){
		this.world.step(1, 10, 10);
		pacman.tick();
	}
	
	public World getWorld(){
		return world;
		
	}

}
