package com.main;

public class PencilDurabilityPillar {
	private static final String MAIN_MENU_WELCOME = "Welcom to the pencil durability kata!";
	
	private static final String GET_NEW_PIECE_OF_PAPER = "Get a new piece of paper";
	private static final String QUIT = "quit";
	private static final String[] MAIN_MENU_OPTIONS = { GET_NEW_PIECE_OF_PAPER, QUIT };
	
	private static final String WRITE_TO_PAPER = "Write something on the paper";
	private static final String ERASE_A_WORD = "Erase a word from the paper";
	private static final String SHARPEN_PENCIL = "Sharpen your pencil";
	private static final String GET_NEW_PENCIL = "Get a new pencil";
	private static final String READ_PAPER = "Read whats written on the paper";
	private static final String GO_BACK = "Go back to the previous menu";
	private static final String[] PAPER_MENU_OPTIONS = { WRITE_TO_PAPER, ERASE_A_WORD, SHARPEN_PENCIL, GET_NEW_PENCIL, READ_PAPER, GO_BACK };
	
	private Menu menu;
	private Paper paper;
	private Pencil pencil;
	private Eraser eraser;
	
	public PencilDurabilityPillar(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		while (true) {
			menu.displayMessage(MAIN_MENU_WELCOME);
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if (choice.equals(GET_NEW_PIECE_OF_PAPER)) {
				paper = new Paper();
				pencil = new Pencil();
				eraser = new Eraser();
				
				while (true) {
					choice = (String) menu.getChoiceFromOptions(PAPER_MENU_OPTIONS);
					if (choice.equals(WRITE_TO_PAPER)) {
						System.out.println("please type what you would like to write to the paper");
						paper.writeWordsOnPaper(pencil.write(menu.getUserInput()));
					} else if (choice.equals(ERASE_A_WORD)) {
						System.out.println("erase from that paper boy");
					} else if (choice.equals(SHARPEN_PENCIL)) {
						pencil.sharpenPencil();
					} else if (choice.equals(GET_NEW_PENCIL)) {
						pencil = new Pencil();
						eraser = new Eraser();
					} else if (choice.equals(READ_PAPER)) {
						menu.displayMessage(paper.getWordsOnPage());
					} else if (choice.equals(GO_BACK)) {
						break;
					}
				}
				
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
