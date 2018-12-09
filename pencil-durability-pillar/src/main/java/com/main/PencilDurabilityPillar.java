package com.main;

public class PencilDurabilityPillar {
	private static final String MAIN_MENU_WELCOME = "Welcom to the pencil durability kata!";
	
	private static final String GET_NEW_PIECE_OF_PAPER = "Get a new piece of paper";
	private static final String QUIT = "quit";
	private static final String[] MAIN_MENU_OPTIONS = { GET_NEW_PIECE_OF_PAPER, QUIT };
	
	private static final String WRITE_TO_PAPER = "Write something on the paper";
	private static final String ERASE_A_WORD = "Erase a word from the paper";
	private static final String FILL_IN_WHITE_SPACE = "Write over the last erased space";
	private static final String SHARPEN_PENCIL = "Sharpen your pencil"; 
	private static final String GET_NEW_PENCIL = "Get a new pencil"; 
	private static final String READ_PAPER = "Read whats written on the paper";
	private static final String GO_BACK = "Go back to the previous menu";
	private static final String[] PAPER_MENU_OPTIONS = { WRITE_TO_PAPER, ERASE_A_WORD, FILL_IN_WHITE_SPACE, SHARPEN_PENCIL, GET_NEW_PENCIL, READ_PAPER, GO_BACK };
	
	private static final String LEAVE_BLANK = "Leave the word erased";
	private static final String REPLACE_WORD = "Replace the word with a new one";
	private static final String[] ERASER_OPTIONS = { LEAVE_BLANK, REPLACE_WORD };
	
	private static final String ENTER_WORD = "Please enter a word";
	private static final String WRITE_TO_PAPER_INSTRUCTIONS = "Please type what you would like to write to the paper";
	private static final String ERASE_WORD_INSTRUCTIONS = "Please enter a word you'd like to erase";
	private static final String LEAVE_BLANK_OR_REPLACE_WORD = "would you like to leave blank or replace the word?";
	
	private Menu menu;
	private Paper paper;
	private Pencil pencil;
	private Eraser eraser;
	
	public PencilDurabilityPillar(Menu menu) {
		this.menu = menu;
	}
	
	private void eraseWord(Menu menu, Paper paper, String choice) {
		menu.displayMessage(ERASE_WORD_INSTRUCTIONS);
		
		String eraseThis = menu.getUserInput();
		
		menu.displayMessage(LEAVE_BLANK_OR_REPLACE_WORD);
		choice = (String) menu.getChoiceFromOptions(ERASER_OPTIONS);
		
		if (choice.equals(LEAVE_BLANK)) {
			paper.replaceWordWithNewOrErasedWord(eraser.eraseWord(eraseThis), eraseThis);
			
		} else if (choice.equals(REPLACE_WORD)) {
			menu.displayMessage(ENTER_WORD);
			paper.replaceWordWithNewOrErasedWord(menu.getUserInput(), eraseThis);
		}
	}
	
	private void writeToPaper(Menu menu, Paper paper) {
		menu.displayMessage(WRITE_TO_PAPER_INSTRUCTIONS);
		paper.writeWordsOnPaper(pencil.write(menu.getUserInput()));
	}
	
	private void fillInPreviouslyErasedSpot(Menu menu, Paper paper, Pencil pencil) {
		menu.displayMessage(ENTER_WORD);
		paper.writeOverErasedWhiteSpace(pencil.write(menu.getUserInput()), paper.findWhiteSpaceIndexToWriteOver());
	}
	
	private void displayPaperMenu(String choice) {
		paper = new Paper();
		pencil = new Pencil();
		eraser = new Eraser();
		boolean whileTrue = true;
		
		while (whileTrue) {
			choice = (String) menu.getChoiceFromOptions(PAPER_MENU_OPTIONS);
			
			switch (choice) {
			
				case WRITE_TO_PAPER:
					writeToPaper(menu, paper);
					break;
					
				case ERASE_A_WORD:
					eraseWord(menu, paper, choice);
					break;
				
				case FILL_IN_WHITE_SPACE:
					fillInPreviouslyErasedSpot(menu, paper, pencil);
					break;
					
				case SHARPEN_PENCIL:
					pencil.sharpenPencil();
					break;
				
				case GET_NEW_PENCIL:
					pencil = new Pencil();
					eraser = new Eraser();
					break;
				
				case READ_PAPER:
					menu.displayMessage(paper.getWordsOnPage());
					break;
					
				case GO_BACK:
					whileTrue = false;
					break;
			}
		}
	}

	public void run() {
		while (true) {
			menu.displayMessage(MAIN_MENU_WELCOME);
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if (choice.equals(GET_NEW_PIECE_OF_PAPER)) {
				
				displayPaperMenu(choice);
				
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
