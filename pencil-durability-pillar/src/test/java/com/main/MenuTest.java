package com.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class MenuTest {
	Menu menu;
	private ByteArrayOutputStream output;

	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
		menu = getMenuForTesting();
	}
	
	@Test
	public void display_menu_options_and_prompts_user_to_make_choice() {
		Object[] options = new Object[] {  new Integer(3), "Blind", "Mice" };

		menu.getChoiceFromOptions(options);

		String expected = "\n"+
		 		  "1) "+options[0].toString()+"\n" + 
				  "2) "+options[1].toString()+"\n" +
				  "3) "+options[2].toString()+"\n\n" +
				  "Please choose an option >>> ";

		Assert.assertEquals(expected, output.toString());
	}
	
	@Test
	public void returns_object_corresponding_to_user_choice() {
		Integer expected = new Integer(456);
		Integer[] options = new Integer[] {  new Integer(123), expected, new Integer(789) };
		Menu menu = getMenuForTestingWithUserInput("2\n");

		Integer result = (Integer)menu.getChoiceFromOptions(options);
		
		Assert.assertEquals(expected, result);	  
	}
	

	private Menu getMenuForTestingWithUserInput(String userInput) {
		ByteArrayInputStream input = new ByteArrayInputStream(String.valueOf(userInput).getBytes());
		return new Menu(input, output);
	}
	
	private Menu getMenuForTesting() {
		return getMenuForTestingWithUserInput("1\n");
	}
	
}
