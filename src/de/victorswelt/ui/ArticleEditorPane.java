package de.victorswelt.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.victorswelt.Article;
import de.victorswelt.Author;
import de.victorswelt.AuthorList;

public class ArticleEditorPane extends JPanel {
	private Article currentArticle;
	
	private JTextField title;
	private JComboBox<Author> authors;
	private TextArea content;
	
	
	public ArticleEditorPane() {
		// set the layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// create the components
		title = new JTextField();
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		
		content = new TextArea();
		content.setBackground(Color.WHITE);
		content.setSize(640, 480);
		
		authors = new JComboBox<Author>();
		
		// add the authors
		for(Author a : AuthorList.getInstance().getAuthors()) {
			authors.addItem(a);
		}
		
		// populate the JPanel
		add(createLabelComponentPair("Author: ", authors));
		add(createLabelComponentPair("Title: ", title));
		add(new JLabel("Content: "));
		add(content);
		
		// finalize the initialization by setting the current article to none
		setArticle(null);
	}
	
	public void setArticle(Article a) {
		currentArticle = a;
		
		if(a == null) {
			title.setEnabled(false);
			authors.setEnabled(false);
			content.setEnabled(false);
			authors.setSelectedItem(null);
		} else {
			title.setEnabled(true);
			authors.setEnabled(true);
			content.setEnabled(true);
		}
	}
	
	/**
	 * A utility function to create label-component pairs*/
	private JPanel createLabelComponentPair(String label, Component c) {
		JPanel container = new JPanel();
		JLabel l = new JLabel(label);
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(l);
		container.add(c);
		
		container.setMaximumSize(new Dimension(Integer.MAX_VALUE, Math.max(l.getHeight(), c.getHeight())));
		container.setBorder(new LineBorder(Color.BLUE));
		
		return container;
	}
}
