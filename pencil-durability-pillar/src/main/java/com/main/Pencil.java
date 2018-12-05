package com.main;

public class Pencil {
	private int maxPencilHealth = 60;
	private int currentPencilHealth;
	private int pencilLength = 5;
	
	public Pencil() {
		setCurrentPencilHealth();
	}

	public String writeToPaper(String textFromPencil) {
		String sendToPaper = "";
		char currentCharacter;
		
		for (int i = 0; i < textFromPencil.length(); i++) {
			currentCharacter = getCurrentCharacter(textFromPencil, i);
			
			if (Character.isWhitespace(currentCharacter)) {
				sendToPaper += currentCharacter;
			} else if (Character.isUpperCase(currentCharacter)) {
				sendToPaper += currentCharacter;
				currentPencilHealth -= 2;
			} else {
				sendToPaper += currentCharacter;
				currentPencilHealth--;
			}
		}

		return sendToPaper;
	}
	
	private char getCurrentCharacter(String text, int i) {
		return text.charAt(i);
	}
	
	public void sharpenPencil() {
		if (pencilLength > 0) {
			setCurrentPencilHealth();
			pencilLength--;
		}
	}

	public int getCurrentPencilHealth() {
		return currentPencilHealth;
	}
	
	public void setCurrentPencilHealth() {
		this.currentPencilHealth = maxPencilHealth;
	}


	public int getPencilLength() {
		return pencilLength;
	}

}
