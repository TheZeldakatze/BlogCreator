package de.victorswelt;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import de.victorswelt.ui.ArticleEditorPane;
import de.victorswelt.ui.ArticleSelectorPane;
import de.victorswelt.ui.FSOperationPane;

public class BlogCreator {
	public static final boolean DOS_FS_COMPATEBILITY = false;
	
	public static void main(String args[]) {
		// initialize the singletons
		AuthorList.init();
		ArticleList.init();
		DatabaseManager.init();
		
		
		new BlogCreator();
	}
	
	public BlogCreator() {
		ArticleEditorPane editorPane = new ArticleEditorPane();
		ArticleSelectorPane selectorPane = new ArticleSelectorPane(editorPane);
		FSOperationPane fsOperationPane = new FSOperationPane();
		
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
		
		JFrame fsOperatorFrame = new JFrame("Blog Creator - File Operations");
		fsOperatorFrame.add(fsOperationPane);
		fsOperatorFrame.pack();
		fsOperatorFrame.setVisible(true);
	}
}
