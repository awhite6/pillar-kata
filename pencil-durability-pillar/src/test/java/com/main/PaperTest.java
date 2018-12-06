package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	Paper paper;
	Pencil pencil;
	Eraser eraser;
	String testText = "Test this is test a thing";
	
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
	public void testing_finding_word_to_erase() {
		paper.writeWordsToPaper(testText);
		Assert.assertEquals("test", paper.findWordForEraser("test"));
	}
	
}
