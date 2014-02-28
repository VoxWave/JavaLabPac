package com.unknownpotato.javalabpac;

import com.badlogic.gdx.utils.Disposable;

/**
 * State reperesents the current state of the game.
 * @author VoxWave
 *
 */
public interface State extends Disposable {
	State tick();
	void render();
	void create();
	void resize(int widht, int height);
}
