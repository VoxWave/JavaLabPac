package com.unknownpotato.javalabpac.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
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
	
	@BeforeClass
	public static void setUpBeforeClass(){
		GdxNativesLoader.load();
	}
	

	@Before
	public void setUp() throws Exception {
		this.world = new World(new Vector2(),true);
		this.pacman = new Pacman(new Vector2() , this.world, null);
	}

	@Test
	public void pacmanMoveswhenmovedtest() {
		String posStart = this.pacman.getPos().toString();
		moveStep(Direction.UP);
		String posEnd = this.pacman.getPos().toString();
		assertFalse(posStart.toString().equals(posEnd.toString()));
	}
	
	@Test
	public void pacmanDoesNotMoveAtStarttest() {
		float x1 = this.pacman.getPos().x;
		float y1 = this.pacman.getPos().y;
		this.world.step(1, 10, 10);
		float x2 = this.pacman.getPos().x;
		float y2 = this.pacman.getPos().y;
	}
	
	public boolean pacmanMoved(Direction dir) {
		float x1 = this.pacman.getPos().x;
		float y1 = this.pacman.getPos().y;
		
		moveStep(dir);
		
		float x2 = this.pacman.getPos().x;
		float y2 = this.pacman.getPos().y;
		
		switch (dir) {
			case UP: return (x2 == x1 && y2 > y1);
			case DOWN: return (x2 == x1 && y2 < y1);
			case LEFT: return (x2 < x1 && y2 == y1);
			case RIGHT: return (x2 > x1 && y2 == y1);
			default: return false;
		}
		
	}
	
	@Test
	public void pacmanMovesUP() {
		assertTrue(pacmanMoved(Direction.UP));
	}
	
	@Test
	public void pacmanMovesDOWN() {
		assertTrue(pacmanMoved(Direction.DOWN));
	}
	
	@Test
	public void pacmanMovesLEFT() {
		assertTrue(pacmanMoved(Direction.LEFT));
	}
	
	@Test
	public void pacmanMovesRIGHT() {
		assertTrue(pacmanMoved(Direction.RIGHT));
	}
	
	//this next test may seem like a stupid test but I actually found error by writing this test :D
	//I had accidently made getBody call itself recursively.
	
	@Test
	public void pacIsincorrectworld(){
		World world2 = this.pacman.getBody().getWorld();
		assertTrue(world2 == this.world);
	}

}
