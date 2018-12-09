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
		String[] newArray;
		PageData pageData = new PageData();
		List<String> listOfWordsOnPage;
		
		pageData.setNewOrErasedWord(newOrErasedWord);
		pageData.setArrayOfWords(wordsOnPage.split(" "));
		pageData.setWordToReplace(wordToReplace);
		pageData = createNewListOfWordsForPage(pageData); 
		
	
		listOfWordsOnPage = generateNewListOfWordsOnPage(pageData);
		newArray = new String[listOfWordsOnPage.size()];
		
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = listOfWordsOnPage.get(i);
		}
			
		newWordsOnPage = String.join(" ", newArray);
		wordsOnPage = newWordsOnPage;
	}

	private void setPageDataVariables(int j, int numberOfWordsAdded, String newWordToReplace, PageData pageData) {
		pageData.setNewWordToReplace(newWordToReplace);
		pageData.setNumberOfWordsAdded(numberOfWordsAdded);		
		pageData.setIteratorJ(j);
	}
	
	private void updateNewWordToReplace(PageData pageData) {
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		int i = pageData.getIteratorI();
		String newWordToReplace = pageData.getNewWordToReplace();
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		pageData.setNumberOfWordsAdded(numberOfWordsAdded + 1);
		
		newWordToReplace += (" " + listOfWordsOnPage.get(i + (numberOfWordsAdded + 1)));
		pageData.setNewWordToReplace(newWordToReplace);
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
		PageData pageData =  new PageData();
		pageData.setArrayOfWords(wordsOnPage.split(" ")); 
		pageData = createNewListOfWordsForPage(pageData);

		String newWord = "";
		String wordToBeReplaced = pageData.getWordToReplace();

		int iterator = 0;

		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		pageData = addToWordToBeReplaced(wordToInsert, wordToBeReplaced, iterator, whiteSpaceIndex, listOfWordsOnPage);
		wordToBeReplaced = pageData.getWordToReplace();
		iterator = pageData.getMiscIterator();
		
		newWord = generateNewWord(wordToBeReplaced, wordToInsert, iterator);

		removeItemsFromList(listOfWordsOnPage, iterator, whiteSpaceIndex);

		addNewWordToList(listOfWordsOnPage, newWord, whiteSpaceIndex);
		
		wordsOnPage = generateNewWordsOnPage(listOfWordsOnPage);
		
	}
	
	private String generateNewWordsOnPage(List<String> listOfWordsOnPage) {
		
		return String.join(" ", listOfWordsOnPage);
	}
	
	private PageData createNewListOfWordsForPage(PageData pageData) {

		String[] words = pageData.getArrayOfWords();
		String wordToBeReplaced = pageData.getWordToReplace();
		List<String> listOfWords = new ArrayList<String>();
				
		for (int i = 0; i < words.length; i++) {
			
			if (words[i].equals("")) {
				wordToBeReplaced += " ";
				continue;
				
			} else {
				listOfWords.add(words[i]);
				
			}
		}
		
		pageData.setListOfWordsOnPage(listOfWords);
		pageData.setWordToReplace(wordToBeReplaced);
		
		return pageData;
		
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
		data.setMiscIterator(iterator);
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

	private List<String> removeItemsFromList(List<String> listOfWordsOnPage, int iterator, int whiteSpaceIndex) {
		for (int i = 0; i < iterator; i++) {
			listOfWordsOnPage.remove(whiteSpaceIndex);
		}
		
		return listOfWordsOnPage;
	}

	private void addNewWordToList(List<String> listOfWordsOnPage, String newWord, int whiteSpaceIndex) {
		listOfWordsOnPage.add(whiteSpaceIndex, newWord);
	}
	
	private PageData replaceEmptySpaceWithWord(PageData pageData, String newWordToReplace, String newOrErasedWord) {
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		String constructedCollisionWord = pageData.getConstructedCollisionWord();

		int i = pageData.getIteratorI();
		int j = pageData.getIteratorJ();
		
		for (; j < listOfWordsOnPage.get(i).length(); j++) {
			if (newWordToReplace.charAt(j) == listOfWordsOnPage.get(i).charAt(j)) {
				constructedCollisionWord += newOrErasedWord.charAt(j);
			}
		}
		
		pageData.setIteratorJ(j);
		pageData.setConstructedCollisionWord(constructedCollisionWord);
		return pageData;
	}
	
	private void addToWordWithCollisions(PageData pageData) {
		String newWordToReplace = pageData.getNewWordToReplace();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		String constructedCollisionWord = pageData.getConstructedCollisionWord();	
		int i = pageData.getIteratorI();
		int j = pageData.getIteratorJ();
		int k = pageData.getIteratorK();
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
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
		
		pageData.setIteratorJ(j);
		pageData.setIteratorK(k);
		pageData.setConstructedCollisionWord(constructedCollisionWord);
		pageData.setWordToReplace(constructedCollisionWord);		
	}

	private String addCharactersToWord(PageData pageData) {
		String newOrErasedWord = pageData.getNewOrErasedWord();
		String constructedCollisionWord = pageData.getConstructedCollisionWord();
		int j = pageData.getIteratorJ();
		
		for (; j < newOrErasedWord.length(); j++) {
			constructedCollisionWord += newOrErasedWord.charAt(j);
		}

		return constructedCollisionWord;
	}

	private String generateNewWordForReplacement(int numberOfWordsAdded, PageData pageData) {		
		while (numberOfWordsAdded > -1) {			
			int k = -1;
			pageData.setIteratorK(k);
			
			setupToAddToNewWordOnPage(pageData);

			if (isNewWordLongerThanErasedWord(pageData) && isCurrentWordTheLastInTheList(pageData)) {		
				pageData.setConstructedCollisionWord(addCharactersToWord(pageData));
				break;
				
			} else if (isNewWordLongerThanErasedWord(pageData)){
				updateNewWordToReplace(pageData);

				continue;
				
			} else {
				break;
				
			}
		}
		
		return pageData.getConstructedCollisionWord();
	}

	private boolean isNewWordLongerThanErasedWord(PageData pageData) {
		String newOrErasedWord = pageData.getNewOrErasedWord();
		String newWordToReplace = pageData.getNewWordToReplace();
		
		if (newOrErasedWord.length() > newWordToReplace.length()) {
			return true;
		} else {
			return false;
		}
	}

	private PageData setupToAddToNewWordOnPage(PageData pageData) {
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		
		if (numberOfWordsAdded == 0) {
			pageData = startCreatingNewReplacementWord(pageData);

		} else {					
			addToWordWithCollisions(pageData);
		}		
		
		return pageData;
	}

	private boolean isCurrentWordTheLastInTheList(PageData pageData) {
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		int i = pageData.getIteratorI();
		
		if (listOfWordsOnPage.get(i+numberOfWordsAdded) == listOfWordsOnPage.get(listOfWordsOnPage.size()-1)) {
			return true;
		} else {
			return false;
		}
	}

	private PageData startCreatingNewReplacementWord(PageData pageData) {
		String newWordToReplace = pageData.getNewWordToReplace();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		pageData = replaceEmptySpaceWithWord(pageData, newWordToReplace, newOrErasedWord);

		return pageData;
	}

	private List<String> generateNewListOfWordsOnPage(PageData pageData) {
		String constructedCollisionWord = pageData.getConstructedCollisionWord();
		String wordToReplace = pageData.getWordToReplace();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		for (int i = pageData.getArrayOfWords().length - 1; i >= 0; i--) {
			pageData.setIteratorI(i);
			
			if (listOfWordsOnPage.get(i).equalsIgnoreCase(wordToReplace)) { 
				
				if (wordToReplace.length() < newOrErasedWord.length()) {
					String newWordToReplace = listOfWordsOnPage.get(i);		
					int numberOfWordsAdded = 0; 			
					int j = 0;
										
					setPageDataVariables(j, numberOfWordsAdded, newWordToReplace, pageData);
	
					constructedCollisionWord = generateNewWordForReplacement(numberOfWordsAdded, pageData);
					numberOfWordsAdded = pageData.getNumberOfWordsAdded();
					
					addNewWordToList(listOfWordsOnPage, constructedCollisionWord, i);
					
					for (int y = 0; y <= numberOfWordsAdded; y++) {
						listOfWordsOnPage.remove(i+1);
					}
					
					break;
					
				} else {
					updateListOfWordsOnPage(pageData);
					break;
				}
			}
		}
		
		return listOfWordsOnPage;
	}
	
	private void updateListOfWordsOnPage(PageData pageData) {
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		int i = pageData.getIteratorI();
		
		addNewWordToList(listOfWordsOnPage, newOrErasedWord, i);
		removeItemsFromList(listOfWordsOnPage, 1, i + 1);
	}
}
