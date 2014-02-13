package com.unknownpotato.javalabpac;

/**
 * 
 * @author VoxWave
 *
 * This class keeps track of your score and pacmans lives.
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
	
	public void addScore(){
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
