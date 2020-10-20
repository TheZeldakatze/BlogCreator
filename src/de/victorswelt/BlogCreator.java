package de.victorswelt;

import javax.swing.JFrame;

import de.victorswelt.ui.ArticleEditorPane;

public class BlogCreator {
	public static void main(String args[]) {
		// initialize the singletons
		AuthorList.init();
		ArticleList.init();
		
		new BlogCreator();
	}
	
	public BlogCreator() {
		
		JFrame editorFrame = new JFrame("Blog Creator");
		editorFrame.setSize(640, 480);
		editorFrame.add(new ArticleEditorPane());
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.setVisible(true);
	}
}
