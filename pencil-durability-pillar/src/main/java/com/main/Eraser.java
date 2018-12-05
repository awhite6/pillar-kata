package com.main;

public class Eraser {

	public String eraseWord(String wordToErase) {
		String erasedWord = "";
		for (int i = 0; i < wordToErase.length(); i++) {
			erasedWord += " ";
		}
		
		return erasedWord;
	}

}
