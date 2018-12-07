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
	
	
	public void replaceWordWithNewOrErasedWord(String newOrErasedWord, String wordToReplace) {
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
							if (listOfWordsOnPage.get(i+numberOfWordsAdded) == listOfWordsOnPage.get(listOfWordsOnPage.size()-1)) {
								for (; j < newOrErasedWord.length(); j++) {
									constructedCollisionWord += newOrErasedWord.charAt(j);
								}
								break;
							} else {
								numberOfWordsAdded++;
								newWordToReplace += (" " + listOfWordsOnPage.get(i + numberOfWordsAdded));
								continue;
							}
						} else {
							break;
						}
					}
					
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
		wordsOnPage = newWordsOnPage;
	}

	public int findWhiteSpaceIndexToWriteOver() {
		char[] charactersOnPage = wordsOnPage.toCharArray();
		int indexCount = 0;
		for (int i = 0; i < charactersOnPage.length; i++) {
			if (charactersOnPage[i] == ' ' && charactersOnPage[i+1] == ' ') {
				indexCount++;
				break;
			} else if (charactersOnPage[i] == ' ') {
				indexCount++;
				continue;
			} else {
				continue;
			}
		}
		
		return indexCount;
	}

	public void writeOverErasedWhiteSpace(String wordToInsert, int whiteSpaceIndex) {
		PageData data =  new PageData();
		String[] wordsOnPageArray = wordsOnPage.split(" ");
		String newWord = "";

		int iterator = 0;

		data = createNewListOfWordsForPage(wordsOnPageArray);
		List<String> listOfWordsOnPage = data.getListOfWordsOnPage();
		String wordToBeReplaced = data.getWordToReplace();
		
		data = addToWordToBeReplaced(wordToInsert, wordToBeReplaced, iterator, whiteSpaceIndex, listOfWordsOnPage);
		wordToBeReplaced = data.getWordToReplace();
		iterator = data.getIterator();
		
		newWord = generateNewWord(wordToBeReplaced, wordToInsert, iterator);

		removeWhiteSpaceFromList(listOfWordsOnPage, iterator, whiteSpaceIndex);

		addNewWordToList(listOfWordsOnPage, newWord, whiteSpaceIndex);
		
		wordsOnPage = generateNewWordsOnPage(listOfWordsOnPage);
		
	}
	
	private String generateNewWordsOnPage(List<String> listOfWordsOnPage) {
		
		return String.join(" ", listOfWordsOnPage);
	}
	
	private PageData createNewListOfWordsForPage(String words[]) {
		PageData data = new PageData();
		String wordToBeReplaced = data.getWordToReplace();
		List<String> listOfWords = new ArrayList<String>();
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals("")) {
//				whiteSpaceCount++;
				wordToBeReplaced += " ";
				
				continue;
			} else {
				listOfWords.add(words[i]);
			}
		}
		
		data.setListOfWordsOnPage(listOfWords);
		data.setWordToReplace(wordToBeReplaced);
		
		return data;
		
	}
	
	private PageData addToWordToBeReplaced(String wordToInsert, String wordToBeReplaced, int iterator, int whiteSpaceIndex, List<String> listOfWordsOnPage) {
		PageData data = new PageData();
		
		while (true) {
			if (wordToInsert.length() >= wordToBeReplaced.length()) {
				if (iterator == 0) {
					wordToBeReplaced += listOfWordsOnPage.get(whiteSpaceIndex + iterator);
				} else {
					wordToBeReplaced += " " + listOfWordsOnPage.get(whiteSpaceIndex + iterator);
				}
				iterator++;
			} else {
				break;
			}
		}
		
		data.setWordToReplace(wordToBeReplaced);
		data.setIterator(iterator);
		return data;
		
	}
	
	private String generateNewWord(String wordToBeReplaced, String wordToInsert, int iterator) {
		String newWord = "";
		int j = 0;
		
		if (iterator > 0) {
			while (j < wordToBeReplaced.length()) {
				if (wordToBeReplaced.charAt(j) != ' ' && wordToInsert.length() > j) {
					newWord += "@";
				} else if (wordToInsert.length() > j){
					newWord += wordToInsert.charAt(j);
				} else {
					newWord += wordToBeReplaced.charAt(j);
				}
				j++;
			}
		} else {
			while (j < wordToInsert.length()) {
				newWord += wordToInsert.charAt(j);
				j++;
			}
		}
		
		return newWord;
	}

	private void removeWhiteSpaceFromList(List<String> listOfWordsOnPage, int iterator, int whiteSpaceIndex) {
		for (int i = 0; i < iterator; i++) {
			listOfWordsOnPage.remove(whiteSpaceIndex);
		}
	}

	private void addNewWordToList(List<String> listOfWordsOnPage, String newWord, int whiteSpaceIndex) {
		listOfWordsOnPage.add(whiteSpaceIndex, newWord);
	}
	
}
