package de.victorswelt;

public class Article {
	private Author author;
	private String title;
	private String content;
	
	public Article() {
		
	}
	
	public Article(Author author, String title, String content) {
		this();
		
		this.author = author;
		this.title = title;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return title;
	}
}
