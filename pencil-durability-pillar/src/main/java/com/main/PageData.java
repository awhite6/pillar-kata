package com.main;

import java.util.List;

public class PageData {
	private int iterator;
	private String wordToReplace = "";
	private List<String> listOfWordsOnPage;
	
	public int getIterator() {
		return iterator;
	}
	
	public void setIterator(int iterator) {
		this.iterator = iterator;
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
	
	
}
