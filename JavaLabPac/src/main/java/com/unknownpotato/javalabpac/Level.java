package com.unknownpotato.javalabpac;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.entities.Pacman;
import com.unknownpotato.javalabpac.entities.Wall;
import com.unknownpotato.javalabpac.interfaces.Tickable;

public class Level implements Tickable {
	
	private World world;
	private Pacman pacman;
	private Stats stats;
	
	public Level(Sprite pacman, Sprite wall, Sprite pill, Sprite ghost){
		this.world = new World(new Vector2(), true);
		this.pacman = new Pacman(new Vector2(),this.world, pacman);
		Wall seina = new Wall(new Vector2(0f,14f),this.world, wall);
	}
	
	public void tick(){
		this.world.step(1, 10, 10);
		pacman.tick();
	}
	
	public World getWorld(){
		return world;
		
	}

	public Stats getStats() {
		// TODO Auto-generated method stub
		return this.stats;
	}

}
