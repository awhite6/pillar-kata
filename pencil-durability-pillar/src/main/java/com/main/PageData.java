package com.main;

import java.util.List;

public class PageData {
	private String wordToReplace = "";
	private String newWordsOnPage = "";
	private String constructedCollisionWord = "";
	private String newWordToReplace = "";
	private String newOrErasedWord = "";

	private int iteratorI;
	private int iteratorJ;
	private int iteratorK;
	private int miscIterator;
	private int numberOfWordsAdded;
	
	private String[] arrayOfWords;
	private String[] newArray;
	
	private List<String> listOfWordsOnPage;
	
	public String getNewWordsOnPage() {
		return newWordsOnPage;
	}

	public void setNewWordsOnPage(String newWordsOnPage) {
		this.newWordsOnPage = newWordsOnPage;
	}

	public String getConstructedCollisionWord() {
		return constructedCollisionWord;
	}

	public void setConstructedCollisionWord(String constructedCollisionWord) {
		this.constructedCollisionWord = constructedCollisionWord;
	}

	public String[] getArrayOfWords() {
		return arrayOfWords;
	}

	public void setArrayOfWords(String[] arrayOfWords) {
		this.arrayOfWords = arrayOfWords;
	}

	public String[] getNewArray() {
		return newArray;
	}

	public void setNewArray(String[] newArray) {
		this.newArray = newArray;
	}

	public int getIteratorI() {
		return iteratorI;
	}
	
	public void setIteratorI(int iterator) {
		this.iteratorI = iterator;
	}
	
	public String getWordToReplace() {
		return wordToReplace;
	}
	
	public void setWordToReplace(String wordToReplace) {
		this.wordToReplace = wordToReplace;
	}
	
	public List<String> getListOfWordsOnPage() {
		return listOfWordsOnPage;
	}
	
	public void setListOfWordsOnPage(List<String> listOfWordsOnPage) {
		this.listOfWordsOnPage = listOfWordsOnPage;
	}
	
	public int getIteratorJ() {
		return iteratorJ;
	}
	
	public void setIteratorJ(int j) {
		this.iteratorJ = j;
	}

	public int getIteratorK() {
		// TODO Auto-generated method stub
		return iteratorK;
	}
	
	public void setIteratorK(int k) {
		iteratorK = k;
	}

	public int getNumberOfWordsAdded() {
		// TODO Auto-generated method stub
		return numberOfWordsAdded;
	}
	
	public void setNumberOfWordsAdded(int numberOfWordsAdded) {
		this.numberOfWordsAdded = numberOfWordsAdded;
	}

	public String getNewWordToReplace() {
		return newWordToReplace;
	}
	
	public void setNewWordToReplace(String newWordToReplace) {
		this.newWordToReplace = newWordToReplace;
	}

	public String getNewOrErasedWord() {
		return newOrErasedWord;
	}
	
	public void setNewOrErasedWord(String newOrErasedWord) {
		this.newOrErasedWord = newOrErasedWord;
	}
	
	public int getMiscIterator() {
		return miscIterator;
	}

	public void setMiscIterator(int miscIterator) {
		this.miscIterator = miscIterator;
	}
}
