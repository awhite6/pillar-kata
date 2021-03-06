package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PencilTest {
	Pencil pencil;
	int pencilHealthAtFull;
	int pencilAtFullLength;
	
	@Before
	public void setup() {  
		pencil = new Pencil();
		pencilHealthAtFull = pencil.getCurrentPencilHealth(); 
		pencilAtFullLength = pencil.getPencilLength();
	}
	
	@Test  
	public void can_pencil_send_text_to_paper() {
		Assert.assertEquals("test", pencil.write("test"));
	}
	
	@Test
	public void does_pencil_lose_health_as_it_writes() {
		pencil.write("test");
		Assert.assertEquals(pencilHealthAtFull - 4,  pencil.getCurrentPencilHealth());
	}
	
	@Test 
	public void testing_when_pencil_is_writing_and_runs_out_of_health() {
		pencil.setCurrentPencilHealth(3);
		Assert.assertEquals("tes ", pencil.write("test"));
	}
	
	@Test
	public void test_pencil_cant_write_with_zero_health() {
		pencil.setCurrentPencilHealth(0);
		Assert.assertEquals("    ", pencil.write("test"));
	}
	
	@Test
	public void do_uppercase_letters_subtract_two_points_from_health() {
		pencil.write("T");
		Assert.assertEquals(pencilHealthAtFull - 2, pencil.getCurrentPencilHealth());
	}
	
	@Test
	public void does_losing_health_ignore_white_space() {
		pencil.write("t t");
		Assert.assertEquals(pencilHealthAtFull - 2, pencil.getCurrentPencilHealth());
	}
	
	@Test
	public void does_pencil_health_refill_when_sharpened() {
		pencil.write("This is a test for testing purposes!");
		pencil.sharpenPencil();
		Assert.assertEquals(pencilHealthAtFull, pencil.getCurrentPencilHealth());
	}
	
	@Test
	public void does_sharpening_pencil_decrease_pencil_length() {
		pencil.sharpenPencil();
		Assert.assertEquals(pencilAtFullLength - 1, pencil.getPencilLength());
	}
	
	@Test 
	public void can_pencil_sharpen_when_length_is_zero() {
		sharpenPencilMultipleTimes();
		Assert.assertEquals(0, pencil.getPencilLength());
	}
	
	@Test 
	public void does_pencil_have_length_when_created() {
		Assert.assertTrue(pencil.getPencilLength() > 0);
	}
	
	private void sharpenPencilMultipleTimes() {
		for (int i = 0; i < 9; i++) {
			pencil.sharpenPencil();
		}
	}
}
