package com.unknownpotato.javalabpac.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.unknownpotato.javalabpac.State;
import com.unknownpotato.javalabpac.Stats;

/**
 * turha luokka koska ei toimi. pitäisi olla scoreboard mutta liian raivostuttavan ****** gui ******
 * joten ei jaksa. 
 * @author local
 *
 */

public class NameGetter implements State {
	
	
	private Stage stage;
	private Stats stats;
	private SpriteBatch batch;
	private Skin skin;

	public NameGetter(Stats stats){
		this.stats = stats;
	}

	@Override
	public void dispose() {
		this.stage.dispose();

	}

	@Override
	public State tick() {
		// TODO Auto-generated method stub
		this.stage.act(Gdx.graphics.getDeltaTime());
		return this;
	}

	@Override
	public void render() {
		this.stage.draw();

	}

	@Override
	public void create() {
		this.batch = new SpriteBatch(); 
		this.stage = new Stage();
		this.skin = new Skin();
		Gdx.input.setInputProcessor(stage);

		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		
		skin.add("default", new BitmapFont());

		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		
		
		
		Table table = new Table();
		table.setFillParent(true);
		
		this.stage.addActor(table);
		final TextButton button = new TextButton("FUQ YOU LIBGDXGUISE FOEMAKING SHITY STAFUFF YOU SUCKSORT", skin);
		table.add(button);
//		TextField dago = new TextField("VITUNHOMOSAATANAPELLE", this.skin);
//		table.add(dago);
	}

	@Override
	public void resize(int widht, int height) {
		// TODO Auto-generated method stub
		
	}

}
