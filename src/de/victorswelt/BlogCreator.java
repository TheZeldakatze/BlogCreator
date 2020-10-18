package de.victorswelt;

import javax.swing.JFrame;

import de.victorswelt.ui.ArticleEditorPane;

public class BlogCreator extends JFrame {
	public static void main(String args[]) {
		// initialize the singletons
		ArticleList.init();
		AuthorList.init();
		
		new BlogCreator();
	}
	
	public BlogCreator() {
		setSize(640, 480);
		add(new ArticleEditorPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
