package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EraserTest {
	Eraser eraser;
	String testString;
	
	@Before
	public void setup() {
		eraser = new Eraser();
		testString = "This is a test for testing purposes!";
	}
	
	@Test
	public void can_eraser_erase_words() {
		Assert.assertEquals("    ", eraser.eraseWord("test"));
	}
}
