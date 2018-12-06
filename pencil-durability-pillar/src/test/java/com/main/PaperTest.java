package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaperTest {
	Paper paper;
	Pencil pencil;
	Eraser eraser;
	
	@Before 
	public void setup() {
		paper = new Paper();
		pencil = new Pencil();
		eraser = new Eraser();
	}
	
	@Test
	public void can_paper_write_text_from_pencil() {
		paper.writeWordsToPage(pencil.writeToPaper("test"));
		Assert.assertEquals(4, paper.getWordsOnPage().length());
	}
	
}
