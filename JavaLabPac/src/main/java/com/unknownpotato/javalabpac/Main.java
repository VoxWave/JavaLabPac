package com.unknownpotato.javalabpac;

import java.util.Scanner;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.utils.GdxNativesLoader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner lukija = new Scanner(System.in);
//		System.out.println("Please input your player name:");
//		String nimi = lukija.nextLine();
		System.out.println("Starting the game...");
		GdxNativesLoader.load();
		new LwjglApplication(new Game(new String()), "Game", 800, 600, false);
	}

}
