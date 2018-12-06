package com.main;

public class PencilDurabilityPillar {
	private static final String MAIN_MENU_WELCOME = "Welcom to the pencil durability kata!";
	private static final String PROMPT_TO_SELECT_OPTION = "Please select an option!";
	private static final String GET_NEW_PIECE_OF_PAPER = "Get a new piece of paper";
	private static final String QUIT = "quit";
	private static final String[] MAIN_MENU_OPTIONS = { GET_NEW_PIECE_OF_PAPER, QUIT };
	
	private Menu menu;
	
	public PencilDurabilityPillar(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if (choice.equals(GET_NEW_PIECE_OF_PAPER)) {
				System.out.println("congrats");
			} else if (choice.equals(QUIT)) {
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		PencilDurabilityPillar cli = new PencilDurabilityPillar(menu);
		cli.run();
	}  
} 
