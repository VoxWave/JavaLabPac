package com.unknownpotato.javalabpac.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxNativesLoader;

public class WallTest {
	
	@BeforeClass
	public static void setUpBeforeClass(){
		GdxNativesLoader.load();
	}

	private World world;
	private Wall wall;
	

	@Before
	public void setUp() throws Exception {
//		GdxNativesLoader.load();
		this.world = new World(new Vector2(),true);
		this.wall = new Wall(new Vector2() , this.world, null);
	}

	@Test
	public void bodyIsinthecorrectworldtest() {
		World world2 = this.wall.getBody().getWorld();
		assertTrue(world2 == this.world);
	}

}
