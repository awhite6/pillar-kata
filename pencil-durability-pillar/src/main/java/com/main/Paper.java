package com.main;

public class Paper {
	private String wordsOnPage;
	
	public Paper() {
		wordsOnPage = "";
	}
	

	public void writeWordsToPaper(String textFromPencil) {
		wordsOnPage += textFromPencil;
	}
	
	public String findWordForEraser(String wordToErase) {
		String[] arrayOfWords = wordsOnPage.split(" ");
		String foundWord = "";
		for (int i = arrayOfWords.length - 1; i >= 0; i--) {
			if (arrayOfWords[i].equals(wordToErase)) {
				foundWord = arrayOfWords[i];
				break;
			}
		}
		
		return foundWord;
	}
	

	public String getWordsOnPage() {
		return wordsOnPage;
	}
}
