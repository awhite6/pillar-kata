package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	Paper paper;
	Pencil pencil;
	Eraser eraser;
	String testText = "test this is test a thing";
	
	@Before 
	public void setup() {
		paper = new Paper(); 
		pencil = new Pencil();
		eraser = new Eraser();
	}
	
	@Test
	public void can_paper_write_text_from_pencil() {
		paper.writeWordsToPaper(pencil.writeToPaper("test"));
		Assert.assertEquals(4, paper.getWordsOnPage().length());
	}
	
	@Test
	public void testing_finding_word_to_erase_or_replace() {
		paper.writeWordsToPaper(testText);
		Assert.assertEquals("test", paper.findWordForEraser("test"));
	}
	
	@Test
	public void can_paper_replace_a_word_with_an_erased_word() {
		paper.writeWordsToPaper(testText + ". blah blah chandler is the mandler");
		Assert.assertEquals("test this is      a thing. blah blah chandler is the mandler", paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("test"), "test"));
		Assert.assertEquals("test this is test a thing. blah      chandler is the mandler", paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("blah"), "blah"));
	}
	
	@Test
	public void can_replace_word_with_new_word() {
		paper.writeWordsToPaper(testText);
		Assert.assertEquals("test this is dog a thing", paper.replaceWordWithNewOrErasedWord("dog", "test"));
	}
	
	@Test
	public void test_collision_between_replaced_word_and_preexisting_words() {
		paper.writeWordsToPaper(testText);
		Assert.assertEquals("test moond@s test a thing", paper.replaceWordWithNewOrErasedWord("chickenwing", "this"));
	}
}
