package com.unknownpotato.javalabpac;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.utils.Disposable;
import com.unknownpotato.javalabpac.interfaces.Entity;

/**
 * Pool is used to store Entities in level.
 * Simple arraylist or sets are no sufficien enough because 
 * we need to be able to remove Entities and dispose their bodies during iteration
 * @author VoxWave
 *
 * @param <Entity>
 */

public class Pool<E extends Entity> implements Iterable<E>, Disposable {
	
	private final Set<E> entities;
	private final Set<E> toBeAdded;
	private final Set<E> toBeRemoved;
	
	public Pool(){
		this.entities = new HashSet<>();
		this.toBeAdded = new HashSet<>();
		this.toBeRemoved = new HashSet<>();
	}
	
	public void add(E e){
		this.toBeAdded.add(e);
	}
	
	public void remove(E e){
		this.toBeRemoved.add(e);
	}
	
	public void update() {
		this.entities.addAll(this.toBeAdded);
		toBeAdded.clear();
		for(E e: this.toBeRemoved){
			if(this.entities.remove(e)){
				e.dispose();
			}
		}
		this.toBeRemoved.clear();
	}

	@Override
	public Iterator<E> iterator() {
		return entities.iterator();
	}

	@Override
	public void dispose() {
		for (Entity e :this){
			e.dispose();
		}
		this.entities.clear();
	}
	

}
