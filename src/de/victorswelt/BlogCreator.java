package de.victorswelt;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import de.victorswelt.ui.ArticleEditorPane;
import de.victorswelt.ui.ArticleSelectorPane;

public class BlogCreator {
	public static void main(String args[]) {
		// initialize the singletons
		AuthorList.init();
		DatabaseManager.init();
		ArticleList.init();
		
		
		new BlogCreator();
	}
	
	public BlogCreator() {
		ArticleEditorPane editorPane = new ArticleEditorPane();
		ArticleSelectorPane selectorPane = new ArticleSelectorPane(editorPane);
		
		JFrame editorFrame = new JFrame("Blog Creator");
		editorFrame.setSize(640, 480);
		editorFrame.add(editorPane);
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.setVisible(true);
		editorFrame.setLocationRelativeTo(null);
		
		JFrame selectorFrame = new JFrame("Blog Creator - Articles");
		selectorFrame.setLayout(new BoxLayout(selectorFrame.getContentPane(), BoxLayout.Y_AXIS));
		selectorFrame.setSize(240, 480);
		selectorFrame.add(selectorPane);
		selectorFrame.setVisible(true);
		selectorFrame.setLocationRelativeTo(editorFrame);
	}
}
