package com.unknownpotato.javalabpac;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.utils.GdxNativesLoader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello elcipse c:");
		GdxNativesLoader.load();
		new LwjglApplication(new Game(), "Game", 800, 600, false);
	}

}
