package test.java.com.unknownpotato.javalabpactests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.unknownpotato.javalabpac.entities.Pacman;
import com.unknownpotato.javalabpac.enums.Direction;

public class PacmanTest {
	
	private World world;
	private Pacman pacman;
	

	@Before
	public void setUp() throws Exception {
		this.world = new World(new Vector2(),true);
		this.pacman = new Pacman(new Vector2() , this.world);
	}

	@Test
	public void pacmanMoveswhenmovedtest() {
		String posStart = this.pacman.getPos().toString();
		this.pacman.move(Direction.UP);
		this.world.step(1, 10, 10);
		String posEnd = this.pacman.getPos().toString();
		assertFalse(posStart.toString().equals(posEnd.toString()));
	}

}
