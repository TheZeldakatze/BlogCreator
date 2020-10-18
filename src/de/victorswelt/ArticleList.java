package de.victorswelt;

import java.util.ArrayList;

public class ArticleList {
	private ArrayList<Article> articleList;
	
	
	private static ArticleList INSTANCE;
	public static void init() {
		INSTANCE = new ArticleList();
	}
	public static ArticleList getInstance() {
		return INSTANCE;
	}
}
