package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EraserTest {
	Eraser eraser;
	String testString;
	int eraserAtFullHealth;
	
	@Before
	public void setup() {
		eraser = new Eraser();
		testString = "This is a test for testing purposes!";
		eraserAtFullHealth = eraser.getCurrentEraserHealth();
	} 
	
	@Test
	public void can_eraser_erase_words() {
		Assert.assertEquals("    ", eraser.eraseWord("test"));
	}
	
	@Test
	public void does_eraser_lose_health_as_it_erases() {
		eraser.eraseWord(testString);
		Assert.assertEquals(24, eraser.getCurrentEraserHealth());
	}
	
	@Test
	public void test_eraser_cant_erase_when_health_is_zero() {
		eraser.setCurrentEraserHealth(0);
		eraser.eraseWord(testString);
		Assert.assertEquals(0, eraser.getCurrentEraserHealth());
	}
	
	@Test
	public void test_eraser_stops_erasing_when_health_hits_zero() {
		eraser.setCurrentEraserHealth(3); 
		Assert.assertEquals("t   ", eraser.eraseWord("test"));
	}
}
