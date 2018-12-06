package com.main;

public class Eraser {
	private int currentEraserHealth;
	
	public Eraser() {
		currentEraserHealth = 60;
	}

	public String eraseWord(String wordToErase) {
		String erasedWord = "";
		for (int i = wordToErase.length() - 1; i >= 0; i--) {
			if (currentEraserHealth > 0) {
				erasedWord += " ";
				currentEraserHealth--;
			}
		}
		
		return erasedWord;
	}
	
	public int getCurrentEraserHealth() {
		return currentEraserHealth;
	}
	
	public void setCurrentEraserHealth(int health) {
		currentEraserHealth = health;
	}
}
