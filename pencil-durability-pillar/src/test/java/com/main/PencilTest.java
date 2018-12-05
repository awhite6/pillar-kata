package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PencilTest {
	Pencil pencil;
	int pencilHealthAtFull;
	
	@Before
	public void setup() {
		pencil = new Pencil();
		pencilHealthAtFull = pencil.getCurrentPencilHealth();
	}
	
	@Test
	public void can_pencil_send_text_to_paper() {
		Assert.assertEquals("test", pencil.writeToPaper("test"));
	}
	
	@Test
	public void does_pencil_lose_health_as_it_writes() {
		pencil.writeToPaper("test");
		Assert.assertTrue(pencil.getCurrentPencilHealth() < pencilHealthAtFull);
	}
	
	@Test
	public void do_uppercase_letters_subtract_two_points_from_health() {
		pencil.writeToPaper("T");
		Assert.assertEquals(pencilHealthAtFull - 2, pencil.getCurrentPencilHealth());
	}
	
}
