package com.unknownpotato.javalabpac.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class View {
	
	private OrthographicCamera cam;
	private int width;
	private int height;
	
	public View() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(width, height);
		cam.zoom = 0.05f;
	}
	
	public void beforeRender() {
		cam.update();
		cam.apply(Gdx.gl10);
	}
	
	public Camera getCamera() {
		return cam;
	}
	
	public void update(int width, int height) {
		this.width = width;
		this.height = height;
		cam.viewportWidth = width;
		cam.viewportHeight = height;
	}

}
