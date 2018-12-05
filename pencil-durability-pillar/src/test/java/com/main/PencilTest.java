package com.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PencilTest {
	Pencil pencil;
	
	@Before
	public void setup() {
		pencil = new Pencil();
	}
	
	@Test
	public void can_pencil_send_text_to_paper() {
		Assert.assertEquals("test", pencil.writeToPaper("test"));
	}
	
}
