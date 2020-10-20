package de.victorswelt;

import java.util.ArrayList;

public class ArticleList {
	private ArrayList<Article> articleList;
	
	private ArticleList() {
		articleList = new ArrayList<Article>();
	}
	
	public void addArticle(Article a) {
		articleList.add(a);
	}
	
	public ArrayList<Article> getArticles() {
		return articleList;
	}
	
	private static ArticleList INSTANCE;
	public static void init() {
		INSTANCE = new ArticleList();
	}
	public static ArticleList getInstance() {
		return INSTANCE;
	}
}
