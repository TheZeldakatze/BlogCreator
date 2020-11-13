package de.victorswelt;

import java.util.ArrayList;

public class ArticleList {
	private ArrayList<Article> articleList;
	
	private ArticleList() {
		articleList = new ArrayList<Article>();
		
		// create some test articles
		// TODO remove
		/*articleList.add(new Article(AuthorList.getInstance().getAuthor(0), "Lorem Ipsum", ""));
		articleList.add(new Article(AuthorList.getInstance().getAuthor(1), "The red fox", "The red fox jumps over the brown bear"));
		articleList.add(new Article(AuthorList.getInstance().getAuthor(2), "I like trains", "I like trains. Trains are cool."));*/
	}
	
	public void addArticle(Article a) {
		articleList.add(a);
	}
	
	public ArrayList<Article> getArticles() {
		return articleList;
	}
	
	public boolean isNameUnique(String name) {
		for(Article a : articleList) {
			if(name.equalsIgnoreCase(a.getFileName()))
				return false;
		}
		
		return true;
	}
	
	private static ArticleList INSTANCE;
	public static void init() {
		INSTANCE = new ArticleList();
	}
	public static ArticleList getInstance() {
		return INSTANCE;
	}
}
