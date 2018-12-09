package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	Paper paper;
	Pencil pencil;
	Eraser eraser;
	String testText = "test this is test a thing";
	String newWords;
	
	@Before 
	public void setup() {
		paper = new Paper(); 
		eraser = new Eraser();
	}
	
	@Test
	public void can_paper_write_text_from_pencil() {
		paper.writeWordsOnPaper("test");
		Assert.assertEquals(4, paper.getWordsOnPage().length());
	}
	
//	@Test
//	public void testing_finding_word_to_erase_or_replace() {
//		paper.writeWordsOnPaper(testText);
//		Assert.assertEquals("test", paper.findWordForEraser("test"));
//	}
	
	@Test
	public void can_paper_replace_a_word_with_an_erased_word() {
		paper.writeWordsOnPaper(testText + ". blah blah chandler is the mandler");
		paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("test"), "test");
		Assert.assertEquals("test this is      a thing. blah blah chandler is the mandler", paper.getWordsOnPage());
	}
	
	@Test
	public void can_replace_word_with_new_word() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord("dog",  "test");
		Assert.assertEquals("test this is dog a thing", paper.getWordsOnPage());
	}
	
	@Test 
	public void can_replace_word_with_word_bigger_than_total_paper_string() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord("banjo kazooie my dude", "test");
		//paper.writeOverErasedWhiteSpace("banjo kazooie", paper.findWhiteSpaceIndexToWriteOver());
		Assert.assertEquals("test this is banjo@k@@@@@e my dude", paper.getWordsOnPage());
	}
	
	@Test
	public void test_collision_between_replaced_word_and_preexisting_words() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord("chicken wing", "this");
		Assert.assertEquals("test chick@@ @@@@ a thing", paper.getWordsOnPage());
	}
	
	@Test 
	public void find_white_space_to_write_over() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("test"), "test");
		Assert.assertEquals(3, paper.findWhiteSpaceIndexToWriteOver());
	}
	
	@Test
	public void write_over_preexisting_white_space() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("this"), "this");
		paper.writeOverErasedWhiteSpace("dog", paper.findWhiteSpaceIndexToWriteOver());
		Assert.assertEquals("test dog is test a thing", paper.getWordsOnPage());
	} 
	
	
	@Test
	public void writing_over_erased_white_space_collides_with_preexising_words() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("this"), "this");
		paper.writeOverErasedWhiteSpace("donkey kong", paper.findWhiteSpaceIndexToWriteOver());
		Assert.assertEquals("test donke@@k@@@t a thing", paper.getWordsOnPage());
	}
	
	@Test
	public void writing_over_white_space_can_extend_beyond_original_erased_white_space() {
		paper.writeWordsOnPaper(testText);
		paper.replaceWordWithNewOrErasedWord(eraser.eraseWord("this"), "this");
		paper.writeOverErasedWhiteSpace("banjo", paper.findWhiteSpaceIndexToWriteOver());	
		Assert.assertEquals("test banjois test a thing", paper.getWordsOnPage());
	}
}
