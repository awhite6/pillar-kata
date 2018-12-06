package com.main;

import java.util.ArrayList;
import java.util.List;

public class Paper {
	private String wordsOnPage;
	
	public Paper() {
		wordsOnPage = "";
	}
	
	public String getWordsOnPage() {
		return wordsOnPage;
	}
	 

	public void writeWordsOnPaper(String textFromPencil) {
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
		String[] newArray;
		List<String> listOfWordsOnPage = new ArrayList<String>();
		for (String s : arrayOfWords) {
			listOfWordsOnPage.add(s);
		}
		
		for (int i = arrayOfWords.length - 1; i >= 0; i--) {
			if (listOfWordsOnPage.get(i).equalsIgnoreCase(wordToReplace)) { 
				
				if (wordToReplace.length() < newOrErasedWord.length()) {
					String newWordToReplace = listOfWordsOnPage.get(i);
					String constructedCollisionWord = "";
					int numberOfWordsAdded = 0;
					int j = 0;
					
					while (numberOfWordsAdded > -1) {
						int k = -1;

						if (numberOfWordsAdded == 0) {
							for (; j < listOfWordsOnPage.get(i).length(); j++) {
								if (newWordToReplace.charAt(j) == listOfWordsOnPage.get(i).charAt(j)) {
									constructedCollisionWord += newOrErasedWord.charAt(j);
								}
							}
						} else {			
							for (; j < newWordToReplace.length(); j++ ) {
								
								if (k >= 0 && newOrErasedWord.length() > j && (listOfWordsOnPage.get(i+numberOfWordsAdded).charAt(k) != ' ')) {
									constructedCollisionWord += "@";
			
								} else if (newOrErasedWord.length() > j) {
									constructedCollisionWord += newOrErasedWord.charAt(j);
								} else {
									constructedCollisionWord += newWordToReplace.charAt(j);
								}
								k++;
							}	
						}
						if (newOrErasedWord.length() > newWordToReplace.length()) {
							numberOfWordsAdded++;
							newWordToReplace += (" " + listOfWordsOnPage.get(i + numberOfWordsAdded));
							continue;
						} else {
							break;
						}
					}
//					int k = 1;
//					while (newOrErasedWord.length() > newWordToReplace.length()) {
//						newWordToReplace += (" " + listOfWordsOnPage.get(i + k));
//						k++;
//					}
//					
//					int j = 0;
//					for (; j < listOfWordsOnPage.get(i).length(); j++) {
//						if (newWordToReplace.charAt(j) == listOfWordsOnPage.get(i).charAt(j)) {
//							constructedCollisionWord += newOrErasedWord.charAt(j);
//						}
//
//					}
//					k = -1;
//					for (; j < newWordToReplace.length(); j++ ) {
//						
//						if (k >= 0 && newOrErasedWord.length() > j && (listOfWordsOnPage.get(i+1).charAt(k) != ' ')) {
//							constructedCollisionWord += "@";
//	
//						} else if (newOrErasedWord.length() > j) {
//							constructedCollisionWord += newOrErasedWord.charAt(j);
//						} else {
//							constructedCollisionWord += newWordToReplace.charAt(j);
//						}
//						
//						k++;
//					}
					listOfWordsOnPage.add(i, constructedCollisionWord);
					for (int y = 0; y <= numberOfWordsAdded; y++) {
						listOfWordsOnPage.remove(i+1);
					}
					break;
					
				} else {
					listOfWordsOnPage.add(i, newOrErasedWord);
					listOfWordsOnPage.remove(i + 1);
					break;
				}
			}
		}

		newArray = new String[listOfWordsOnPage.size()];
		
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = listOfWordsOnPage.get(i);
		}
		
		newWordsOnPage = String.join(" ", newArray);
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
