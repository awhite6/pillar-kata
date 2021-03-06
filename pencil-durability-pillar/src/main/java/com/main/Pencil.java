package com.main;

public class Pencil {
	private int maxPencilHealth = 60;
	private int currentPencilHealth;
	private int pencilLength;
	
	public Pencil() {
		setCurrentPencilHealth(maxPencilHealth); 
		setPencilLength();
	}  

	public String write(String userInput) {
		String sendToPaper = "";
		char currentCharacter;
		
		for (int i = 0; i < userInput.length(); i++) {
			currentCharacter = getCurrentCharacter(userInput, i);
			
			if (canPencilWrite()) {
				
				if (isCharacterWhiteSpace(currentCharacter)) { 
					sendToPaper += currentCharacter;
					
				} else if (isCharacterUpperCase(currentCharacter)) {
					sendToPaper += currentCharacter;
					currentPencilHealth -= 2;
					
				} else {
					sendToPaper += currentCharacter;
					currentPencilHealth--;
					
				}
			} else {
				sendToPaper += " ";
			}
		}

		return sendToPaper;
	}
	
	private boolean canPencilWrite() {
		if (currentPencilHealth > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isCharacterWhiteSpace(char currentCharacter) {
		if (Character.isWhitespace(currentCharacter)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isCharacterUpperCase(char currentCharacter) {
		if (Character.isUpperCase(currentCharacter)) {
			return true;
		} else {
			return false;
		}
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
