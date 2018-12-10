package com.main;

import java.util.ArrayList;
import java.util.List;

public class Paper {
	private String wordsOnPage;
	private List<String> arrayOfWords = new ArrayList<String>();
	
	public Paper() {
		wordsOnPage = "";
	}
	  
	public String getWordsOnPage() {
		return wordsOnPage; 
	}
	
	private String[] createArrayFromList(List<String> listOfWordsOnPage) {
		String[] newArray = new String[listOfWordsOnPage.size()];
		
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = listOfWordsOnPage.get(i);
		}	
		
		return newArray;  
	}

	private void setPageDataVariables(int j, int numberOfWordsAdded, String newWordToReplace, PageData pageData) {
		pageData.setNewWordToReplace(newWordToReplace);
		pageData.setNumberOfWordsAdded(numberOfWordsAdded);		
		pageData.setIteratorJ(j);
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
		
		if (arrayOfWords.size() == 0) {
			pageData.setArrayOfWords(wordsOnPage.split(" "));
		} else {
			pageData.setArrayOfWords(arrayOfWords);
		}
		
		pageData.setWordToReplace(wordToReplace);
		
		initializeListOfWordsForPage(pageData); 
	
		listOfWordsOnPage = generateNewListOfWordsOnPage(pageData);
		newArray = createArrayFromList(listOfWordsOnPage);

		newWordsOnPage = String.join(" ", newArray);
		
		arrayOfWords = listOfWordsOnPage;
				
		wordsOnPage = newWordsOnPage;
	}
	
	private void updateNewWordToReplace(PageData pageData) {
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		int i = pageData.getIteratorI();
		String newWordToReplace = pageData.getNewWordToReplace();
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		pageData.setNumberOfWordsAdded(numberOfWordsAdded + 1);
		
		pageData.setNewWordToReplace(newWordToReplace += (" " + listOfWordsOnPage.get(i + (numberOfWordsAdded + 1))));
	}

	public int findWhiteSpaceIndexToWriteOver() {
		String[] wordsToSearchThrough = createArrayFromList(arrayOfWords);
		int indexCount = wordsToSearchThrough.length - 1;
		
		for (int i = wordsToSearchThrough.length- 1; i >= 0; i--) {
			int numberOfCharactersInWord = 0;
			for (int j = 0; j < wordsToSearchThrough[i].length(); j++) {
				if (wordsToSearchThrough[i].charAt(j) != ' ') {
					indexCount--;
					numberOfCharactersInWord++;
					break;
				}
			}
			
			if (numberOfCharactersInWord == 0) {
				break;
			} 
		}
		
		return indexCount;
		
	}

	public void writeOverErasedWhiteSpace(String wordToInsert, int whiteSpaceIndex) {
		PageData pageData =  new PageData();
		pageData.setArrayOfWords(arrayOfWords); 
		initializeListOfWordsForPage(pageData);

		String newWord = "";
		String wordToBeReplaced = pageData.getWordToReplace();
		
		int iterator;

		List<String> listOfWordsOnPage = arrayOfWords;
		
		addToWordToBeReplaced(wordToInsert, whiteSpaceIndex, pageData);
		wordToBeReplaced = pageData.getWordToReplace();
		iterator = pageData.getMiscIterator();
		
		newWord = generateNewWord(wordToBeReplaced, wordToInsert, iterator);

		removeItemsFromList(listOfWordsOnPage, iterator, whiteSpaceIndex);  

		addNewWord(listOfWordsOnPage, newWord, whiteSpaceIndex);
		
		wordsOnPage = generateNewWordsOnPage(listOfWordsOnPage);
		
	}
	
	private String generateNewWordsOnPage(List<String> listOfWordsOnPage) {
		
		return String.join(" ", listOfWordsOnPage);
	}
	
	private void initializeListOfWordsForPage(PageData pageData) {

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
	}
	
	
	private String generateNewWord(String wordToBeReplaced, String wordToInsert, int iterator) {
		String newWord = "";
		int j = 0;
		
		if (iterator > 0) {
			
			while (j < wordToBeReplaced.length()) {	
				newWord += addCharacterToNewWord(wordToInsert, wordToBeReplaced, j);
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

	private String addCharacterToNewWord(String wordToInsert, String wordToBeReplaced, int j) {
		if (wordToBeReplaced.charAt(j) != ' ' && wordToInsert.length() > j) {
			return "@";
		} else if (wordToInsert.length() > j){
			return String.valueOf(wordToInsert.charAt(j));
		} else if (wordToBeReplaced.charAt(j) != ' '){
			return String.valueOf(wordToBeReplaced.charAt(j));
		}	
		return "";
	}

	private void replaceEmptySpaceWithWord(PageData pageData, String newWordToReplace, String newOrErasedWord) {
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		String wordWithCharacterCollisions = pageData.getConstructedCollisionWord();

		int i = pageData.getIteratorI();
		int j = pageData.getIteratorJ();
		
		for (; j < listOfWordsOnPage.get(i).length(); j++) {
			if (newWordToReplace.charAt(j) == listOfWordsOnPage.get(i).charAt(j)) {
				wordWithCharacterCollisions += newOrErasedWord.charAt(j);
			}
		}
		
		pageData.setIteratorJ(j);
		pageData.setConstructedCollisionWord(wordWithCharacterCollisions);
	}
	
	private void addToWordWithCollisions(PageData pageData) {
		String newWordToReplace = pageData.getNewWordToReplace();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		String wordWithCharacterCollisions = pageData.getConstructedCollisionWord();	
		int i = pageData.getIteratorI();
		int j = pageData.getIteratorJ();
		int k = pageData.getIteratorK();
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		for (; j < newWordToReplace.length(); j++ ) {
			String currentWord = listOfWordsOnPage.get(i+numberOfWordsAdded);
			
			if (k >= 0 && isNewWordLessThanJ(newOrErasedWord, j) && (isCurrentCharInCurrentWordNotASpace(currentWord, k))) {
				wordWithCharacterCollisions += "@";

			} else if (newOrErasedWord.length() > j) {
				wordWithCharacterCollisions += newOrErasedWord.charAt(j);
			} else {
				wordWithCharacterCollisions += newWordToReplace.charAt(j); 
			}
			k++;
		}
		
		pageData.setIteratorJ(j);
		pageData.setIteratorK(k);
		pageData.setConstructedCollisionWord(wordWithCharacterCollisions);
		pageData.setWordToReplace(wordWithCharacterCollisions);		
	}

	private boolean isNewWordLessThanJ(String newOrErasedWord, int j) {
		if (newOrErasedWord.length() > j) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isCurrentCharInCurrentWordNotASpace(String currentWord, int k) {
		if (currentWord.charAt(k) != ' ') {
			return true;
		} else {
			return false;
		}
	}

	private void addToWordToBeReplaced(String wordToInsert, int whiteSpaceIndexInList, PageData pageData) {
		String wordToBeReplaced = pageData.getArrayOfWords()[whiteSpaceIndexInList];
		List<String> listOfWordsOnPage = arrayOfWords;
		int iterator = pageData.getMiscIterator();
		
		while (true) {
				
			if (wordToInsert.length() > wordToBeReplaced.length()) {
				
				if (iterator == 0) {					
					wordToBeReplaced += determineWhatIsAdded(whiteSpaceIndexInList, listOfWordsOnPage, iterator);		
				} else {			
					wordToBeReplaced += determineWhatIsAdded(whiteSpaceIndexInList, listOfWordsOnPage, iterator);
				}	
				
				iterator++;
				
			} else {
				break;
				
			}
		}
		
		pageData.setListOfWordsOnPage(listOfWordsOnPage);
		pageData.setWordToReplace(wordToBeReplaced);
		pageData.setMiscIterator(iterator);		
	}
	
	private String determineWhatIsAdded(int whiteSpaceIndexInList, List<String> listOfWordsOnPage, int iterator) {
		String addToWord;
		if (isIndexLastWordInList(whiteSpaceIndexInList, listOfWordsOnPage.size())) {
			addToWord = " ";
		} else if (iterator == 0) {
			addToWord = " " + listOfWordsOnPage.get(whiteSpaceIndexInList + 1);
			listOfWordsOnPage.remove(whiteSpaceIndexInList + 1);
		} else {
			addToWord = " " + listOfWordsOnPage.get(whiteSpaceIndexInList + iterator);
		}
		
		return addToWord;
	}

	private boolean isIndexLastWordInList(int whiteSpaceIndexInList, int sizeOfList) {
		if (whiteSpaceIndexInList + 1 >= sizeOfList) {
			return true;
		} else {
			return false;
		}
	}

	private String addCharactersToWord(PageData pageData) {
		String newOrErasedWord = pageData.getNewOrErasedWord();
		String wordWithCharacterCollisions = pageData.getConstructedCollisionWord();
		int j = pageData.getIteratorJ();
		
		for (; j < newOrErasedWord.length(); j++) {
			wordWithCharacterCollisions += newOrErasedWord.charAt(j);
		}

		return wordWithCharacterCollisions;
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

	private void setupToAddToNewWordOnPage(PageData pageData) {
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		
		if (numberOfWordsAdded == 0) {
			startCreatingNewReplacementWord(pageData);

		} else {					
			addToWordWithCollisions(pageData);
		}			
	}

	private void startCreatingNewReplacementWord(PageData pageData) {
		String newWordToReplace = pageData.getNewWordToReplace();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		replaceEmptySpaceWithWord(pageData, newWordToReplace, newOrErasedWord);
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

	private boolean isNewWordLongerThanErasedWord(PageData pageData) {
		String newOrErasedWord = pageData.getNewOrErasedWord();
		String newWordToReplace = pageData.getNewWordToReplace();
		
		if (newOrErasedWord.length() > newWordToReplace.length()) {
			return true;
		} else {
			return false;
		}
	}

	private List<String> generateNewListOfWordsOnPage(PageData pageData) {
		String wordToReplace = pageData.getWordToReplace();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		for (int i = pageData.getListOfWordsOnPage().size() - 1; i >= 0; i--) {
			pageData.setIteratorI(i);
			
			if (isWordToReplaceLessThanNewWord(wordToReplace, newOrErasedWord) 
					&& doesWordToReplaceMatchCurrentInList(listOfWordsOnPage.get(i), wordToReplace)) {
				
				String newWordToReplace = listOfWordsOnPage.get(i);		
				int numberOfWordsAdded = 0; 			
				int j = 0;
				setPageDataVariables(j, numberOfWordsAdded, newWordToReplace, pageData);
				addWordWithCollisionsToListOfWordsOnPage(pageData);
				break;
				
			} else if (listOfWordsOnPage.get(i).equalsIgnoreCase(wordToReplace)){
				addNewWordsToListOfWordsOnPage(pageData);
				break;
			}
		}
		
		return listOfWordsOnPage;
	}
	
	private boolean doesWordToReplaceMatchCurrentInList(String currentWordInList, String wordToReplace) {
		if (currentWordInList.equalsIgnoreCase(wordToReplace)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isWordToReplaceLessThanNewWord(String wordToReplace, String newOrErasedWord) {
		if (wordToReplace.length() < newOrErasedWord.length()) {
			return true;
		} else {
			return false;
		}
	}

	private void addWordWithCollisionsToListOfWordsOnPage(PageData pageData) {
		String wordWithCharacterCollisions = pageData.getConstructedCollisionWord();
		int numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		int i = pageData.getIteratorI();
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		
		wordWithCharacterCollisions = generateNewWordForReplacement(numberOfWordsAdded, pageData);
		numberOfWordsAdded = pageData.getNumberOfWordsAdded();
		
		addNewWord(listOfWordsOnPage, wordWithCharacterCollisions, i);
		
		removeOldWordsFromPage(numberOfWordsAdded, i, listOfWordsOnPage);
	}

	private void addNewWordsToListOfWordsOnPage(PageData pageData) {
		List<String> listOfWordsOnPage = pageData.getListOfWordsOnPage();
		String newOrErasedWord = pageData.getNewOrErasedWord();
		int i = pageData.getIteratorI();
		
		addNewWord(listOfWordsOnPage, newOrErasedWord, i);
		removeItemsFromList(listOfWordsOnPage, 1, i + 1);
	}
	
	private void addNewWord(List<String> listOfWordsOnPage, String newWord, int whiteSpaceIndex) {
		listOfWordsOnPage.add(whiteSpaceIndex, newWord);
	}
	
	private void removeOldWordsFromPage(int numberOfWordsAdded, int i, List<String> listOfWordsOnPage) {
		for (int y = 0; y <= numberOfWordsAdded; y++) {
			listOfWordsOnPage.remove(i+1);
		}
	}

	private List<String> removeItemsFromList(List<String> listOfWordsOnPage, int iterator, int whiteSpaceIndex) {
		for (int i = 0; i < iterator; i++) {
			if (whiteSpaceIndex >= listOfWordsOnPage.size()) {
				continue;
			} else {
				listOfWordsOnPage.remove(whiteSpaceIndex);
			}
		}
		
		if (iterator == 0) {
			listOfWordsOnPage.remove(whiteSpaceIndex);
		}
		
		return listOfWordsOnPage;
	}

}
