package com.unknownpotato.javalabpac;

/**
 * 
 * @author VoxWave
 *
 * This class keeps track of your score and pacmans lives.
 * this classes methods are pretty self explanatory.
 */

public class Stats {
	
	private long score;
	private long lives;
	
	public long getScore(){
		return score;
	}
	
	public long getLives(){
		return lives;  
	}
	
	public void incrementScore(){
		this.score++;
	}
	
	public void reduceLives(){
		lives--;
	}
	
	@Override
	public String toString(){
		return this.lives+", "+this.score;
	}

}
