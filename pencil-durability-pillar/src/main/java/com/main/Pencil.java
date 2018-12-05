package com.main;

public class Pencil {
	private int maxPencilHealth = 60;
	private int currentPencilHealth;
	
	public Pencil() {
		setCurrentPencilHealth();
	}

	public String writeToPaper(String textFromPencil) {
		String sendToPaper = "";
		
		for (int i = 0; i < textFromPencil.length(); i++) {
			if (Character.isWhitespace(textFromPencil.charAt(i))) {
				sendToPaper += textFromPencil.charAt(i);
			} else if (Character.isUpperCase(textFromPencil.charAt(i))) {
				sendToPaper += textFromPencil.charAt(i);
				currentPencilHealth -= 2;
			} else {
				sendToPaper += textFromPencil.charAt(i);
				currentPencilHealth--;
			}
		}

		return sendToPaper;
	}

	public int getCurrentPencilHealth() {
		// TODO Auto-generated method stub
		return currentPencilHealth;
	}
	
	public void setCurrentPencilHealth() {
		this.currentPencilHealth = maxPencilHealth;
	}

	public void sharpenPencil() {
		setCurrentPencilHealth();
	}

}
