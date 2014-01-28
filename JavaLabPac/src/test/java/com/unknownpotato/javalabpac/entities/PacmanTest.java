package com.unknownpotato.javalabpac.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.unknownpotato.javalabpac.enums.Direction;

public class PacmanTest {
	
	private World world;
	private Pacman pacman;
	
	public void moveStep(Direction direction){
		this.pacman.move(direction);
		this.world.step(1, 10, 10);
	}
	

	@Before
	public void setUp() throws Exception {
		GdxNativesLoader.load();
		this.world = new World(new Vector2(),true);
		this.pacman = new Pacman(new Vector2() , this.world);
	}

	@Test
	public void pacmanMoveswhenmovedtest() {
		String posStart = this.pacman.getPos().toString();
		moveStep(Direction.UP);
		String posEnd = this.pacman.getPos().toString();
		assertFalse(posStart.toString().equals(posEnd.toString()));
	}
	
	@Test
	public void pacmanMovesUP() {
		float x1 = this.pacman.getPos().x;
		float y1 = this.pacman.getPos().y;
		
		moveStep(Direction.UP);
		
		float x2 = this.pacman.getPos().x;
		float y2 = this.pacman.getPos().y;
		
		assertTrue(x2 == x1 && y2 > y1);
	}
	
	@Test
	public void pacmanMovesDOWN() {
		float x1 = this.pacman.getPos().x;
		float y1 = this.pacman.getPos().y;
		boolean isCorrect = false;
		
		moveStep(Direction.DOWN);
		
		float x2 = this.pacman.getPos().x;
		float y2 = this.pacman.getPos().y;
		
		if(x2 == x1 && y2 < y1){
			isCorrect = true;
		}
		assertTrue(isCorrect);
	}
	
	@Test
	public void pacmanMovesLEFT() {
		float x1 = this.pacman.getPos().x;
		float y1 = this.pacman.getPos().y;
		boolean isCorrect = false;
		
		moveStep(Direction.LEFT);
		
		float x2 = this.pacman.getPos().x;
		float y2 = this.pacman.getPos().y;
		
		if(x2 < x1 && y2 == y1){
			isCorrect = true;
		}
		assertTrue(isCorrect);
	}
	
	@Test
	public void pacmanMovesRIGHT() {
		float x1 = this.pacman.getPos().x;
		float y1 = this.pacman.getPos().y;
		boolean isCorrect = false;
		
		moveStep(Direction.RIGHT);
		
		float x2 = this.pacman.getPos().x;
		float y2 = this.pacman.getPos().y;
		
		if(x2 > x1 && y2 == y1){
			isCorrect = true;
		}
		assertTrue(isCorrect);
	}

}
