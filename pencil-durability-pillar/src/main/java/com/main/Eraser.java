package com.main;

public class Eraser {
	private int currentEraserHealth;
	
	public Eraser() {
		currentEraserHealth = 60;
	}

	public String eraseWord(String wordToErase) {
		String erasedWord = "";
		for (int i = 0; i < wordToErase.length(); i++) {
			erasedWord += " ";
			currentEraserHealth--;
		}
		
		return erasedWord;
	}
	
	public int getCurrentEraserHealth() {
		return currentEraserHealth;
	}
}
