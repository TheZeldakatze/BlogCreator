package de.victorswelt.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.victorswelt.Article;
import de.victorswelt.ArticleList;
import de.victorswelt.AuthorList;

public class ArticleSelectorPane extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton createArticle;
	
	private JScrollPane listScrollPane;
	private JList<Article> list;
	private DefaultListModel<Article> listModel;
	
	public ArticleSelectorPane(ArticleEditorPane articleEditorPane) {
		// set the Layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// create the objects
		list = new JList<Article>(listModel = new DefaultListModel<Article>());
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listScrollPane = new JScrollPane(list);
		
		createArticle = new JButton("Create an Article");
		createArticle.setMaximumSize(new Dimension(Integer.MAX_VALUE, createArticle.getHeight()));
		
		// populate the list
		for(Article a : ArticleList.getInstance().getArticles()) {
			listModel.addElement(a);
		}
		
		// add the handlers
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				articleEditorPane.setArticle(list.getSelectedValue());
			}
		});
		
		createArticle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Article a = new Article(AuthorList.NO_AUTHOR, "New Article", "");
				ArticleList.getInstance().addArticle(a);
				listModel.addElement(a);
			}
		});
		
		// add the objects to this panel
		add(listScrollPane);
		add(createArticle);
	}
}
