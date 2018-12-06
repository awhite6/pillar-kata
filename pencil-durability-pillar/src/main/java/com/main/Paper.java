package com.main;

public class Paper {
	private String wordsOnPage;
	
	public Paper() {
		wordsOnPage = "";
	}
	
	public String getWordsOnPage() {
		return wordsOnPage;
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
	
	public String replaceWordWithNewOrErasedWord(String newOrErasedWord, String wordToReplace) {
		String newWordsOnPage;
		String[] arrayOfWords = wordsOnPage.split(" ");
		
		for (int i = arrayOfWords.length - 1; i >= 0; i--) {
			if (arrayOfWords[i].equalsIgnoreCase(wordToReplace)) { 
				if (wordToReplace.length() < newOrErasedWord.length()) {
					String newWordToReplace = arrayOfWords[i] + " " + arrayOfWords[i+1];
					String constructedCollisionWord = "";
					int j = 0;
					for (; j < arrayOfWords[i].length(); j++) {
						if (newWordToReplace.charAt(j) == arrayOfWords[i].charAt(j)) {
							constructedCollisionWord += newOrErasedWord.charAt(j);
						}

					}
					int k = -1;
					for (; j < newWordToReplace.length(); j++ ) {
						
						if (k >= 0 && newOrErasedWord.length() > j && (arrayOfWords[i+1].charAt(k) != ' ')) {
							constructedCollisionWord += "@";
	
						} else if (newOrErasedWord.length() > j) {
							constructedCollisionWord += newOrErasedWord.charAt(j);
						} else {
							constructedCollisionWord += newWordToReplace.charAt(j);
						}
						
						k++;
					}
					arrayOfWords[i] = constructedCollisionWord;
					arrayOfWords[i+1] = "";
					break;
					
				} else {
					arrayOfWords[i] = newOrErasedWord;
					break;
				}
			}
		}
 
		newWordsOnPage = String.join(" ", arrayOfWords);
		return newWordsOnPage;
	}


//	public String replaceWordWithNewWord(String newWord, String wordToReplace) {
//		String newWordsOnPage;
//		String[] arrayOfWords = wordsOnPage.split(" ");
//		
//		for (int i = arrayOfWords.length - 1; i >= 0; i--) {
//			if (arrayOfWords[i].equalsIgnoreCase(wordToReplace)) {
//				arrayOfWords[i] = newWord;
//				break;
//			}
//		}
//
//		newWordsOnPage = String.join(" ", arrayOfWords);
//		return newWordsOnPage;
//	}
}
