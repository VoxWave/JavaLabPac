package com.unknownpotato.javalabpac.rendering;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.unknownpotato.javalabpac.entities.Entity;

public class Renderer implements Disposable {
	
	private SpriteBatch batch;
	private View view;
	private int width;
	private int height;
	
	public Renderer(){
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		view = new View();
	}
	
	public View getView() {
		return view;
	}
	
	public void render(List<Entity> list) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glViewport(0, 0, width, height);
		batch.setProjectionMatrix(view.getCamera().combined);

		batch.begin();
		for (Entity entity : list) {
			Vector2 pos = entity.getPos();
			Sprite sprite = entity.getSprite();
			float w = entity.getSize();
			float h = entity.getSize();
			// edit to make sprites scale smaller
			batch.draw(sprite, pos.x - w, pos.y - h, w, h , w,
					h, 1f, 1f, entity.getRotation());
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}

}
