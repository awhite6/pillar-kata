package com.main;

public class Eraser {

	public String eraseWord(String wordToErase) {
		String erasedWord = "";
		char[] wordArray = wordToErase.toCharArray();
		for (char c : wordArray) {
			erasedWord += " ";
		}
		
		return erasedWord;
	}

}
