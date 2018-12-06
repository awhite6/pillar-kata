package com.main;

public class Paper {
	private String wordsOnPage;
	
	public Paper() {
		wordsOnPage = "";
	}
	
	public String getWordsOnPage() {
		
		return wordsOnPage;
	}

	public void writeWordsToPage(String textFromPencil) {
		wordsOnPage += textFromPencil;
	}
}
