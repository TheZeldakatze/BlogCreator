package de.victorswelt.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.victorswelt.Article;
import de.victorswelt.Author;
import de.victorswelt.AuthorList;

public class ArticleEditorPane extends JPanel {
	private static final long serialVersionUID = 1L;

	private Article currentArticle;
	
	private JTextField title;
	private JComboBox<Author> authors;
	private JTextArea content;
	private JPanel datePanel;
	private JLabel created, lastEdited;
	private JButton modify;
	
	public ArticleEditorPane() {
		// set the layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// create the components
		title = new JTextField();
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		
		content = new JTextArea();
		content.setBackground(Color.WHITE);
		content.setSize(640, 480);
		content.setLineWrap(true);
		content.setAutoscrolls(true);
		
		authors = new JComboBox<Author>();
		
		datePanel = new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		
		created = new JLabel();
		lastEdited = new JLabel();
		updateTime(null, null);
		
		modify = new JButton("modify");
		
		// add the authors
		authors.addItem(AuthorList.getInstance().getAuthor(-1));
		for(Author a : AuthorList.getInstance().getAuthors()) {
			authors.addItem(a);
		}
		
		// add the listeners
		title.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				action();
			}
			
			public void insertUpdate(DocumentEvent e) {
				action();
			}
			
			public void changedUpdate(DocumentEvent e) {
				action();
			}
			
			private void action() {
				if(currentArticle != null && title.isEnabled()) {
					currentArticle.setTitle(title.getText());
					updateTime(currentArticle.getLastEdited());
				}
			}
		});
		
		content.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				action();
			}
			
			public void insertUpdate(DocumentEvent e) {
				action();
			}
			
			public void changedUpdate(DocumentEvent e) {
				action();
			}
			
			private void action() {
				if(currentArticle != null && content.isEnabled()) {
					currentArticle.setContent(content.getText());
					updateTime(currentArticle.getLastEdited());
				}
			}
		});
		
		authors.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(currentArticle != null && authors.isEnabled()) {
					currentArticle.setAuthor((Author) authors.getSelectedItem());
					updateTime(currentArticle.getLastEdited());
				}
			}
		});
		
		modify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				title.setEnabled(true);
				authors.setEnabled(true);
				content.setEnabled(true);
				modify.setEnabled(false);
			}
			
		});
		
		// populate the JPanel
		datePanel.add(lastEdited);
		datePanel.add(created);
		datePanel.add(modify);
		
		add(datePanel);
		add(createLabelComponentPair("Author: ", authors));
		add(createLabelComponentPair("Title: ", title));
		add(new JLabel("Content: "));
		((JScrollPane) add(new JScrollPane(content))).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		// finalize the initialization by setting the current article to none
		setArticle(null);
	}
	
	public void setArticle(Article a) {
		if(a == null) {
			title.setEnabled(false);
			authors.setEnabled(false);
			content.setEnabled(false);
			authors.setSelectedItem(null);
			modify.setEnabled(false);
			updateTime(null, null);
		} else {
			title.setText(a.getTitle());
			authors.setSelectedItem(a.getAuthor());
			content.setText(a.getContent());
			updateTime(a.getCreated(), a.getLastEdited());

			title.setEnabled(false);
			authors.setEnabled(false);
			content.setEnabled(false);
			modify.setEnabled(true);
		}
		currentArticle = a;
	}
	
	/** 
	 * A convenience method to update the time fields */
	private void updateTime(Date created, Date updated) {
		if(created == null)
			this.created.setText("Created: ---");
		else
			this.created.setText("Created: " + created.toString());
		
		updateTime(updated);
	}
	
	private void updateTime(Date updated) {
		if(updated == null)
			this.lastEdited.setText("Last edit: ---");
		else
			this.lastEdited.setText("Last edit: " + updated.toString());
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
