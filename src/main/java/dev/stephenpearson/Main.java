package dev.stephenpearson;

import javax.swing.SwingUtilities;

public class Main {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(BlackJack::new);
		System.out.println();
	}

}
