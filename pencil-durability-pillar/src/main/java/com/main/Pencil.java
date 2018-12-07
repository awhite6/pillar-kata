package com.main;

public class Pencil {
	private int maxPencilHealth = 60;
	private int currentPencilHealth;
	private int pencilLength;
	
	public Pencil() {
		setCurrentPencilHealth(maxPencilHealth); 
		setPencilLength();
	}  

	public String write(String textFromPencil) {
		String sendToPaper = "";
		char currentCharacter;
		
		for (int i = 0; i < textFromPencil.length(); i++) {
			currentCharacter = getCurrentCharacter(textFromPencil, i);
			
			if (currentPencilHealth > 0) {
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
		}

		return sendToPaper;
	}
	
	private char getCurrentCharacter(String text, int i) {
		return text.charAt(i);
	}
	
	public void sharpenPencil() {
		if (pencilLength > 0) {
			setCurrentPencilHealth(maxPencilHealth);
			pencilLength--;
		}
	}

	public int getCurrentPencilHealth() {
		return currentPencilHealth;
	}
	
	public void setCurrentPencilHealth(int health) {
		this.currentPencilHealth = health;
	}


	public int getPencilLength() {
		return pencilLength;
	}
	
	private void setPencilLength() {
		double length = (Math.random() * 5)  + 1;
		pencilLength = (int) length;
	}

}
