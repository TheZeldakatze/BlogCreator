package de.victorswelt;

public class Article {
	private Author author;
	private String title;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		System.out.println(title);
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		System.out.println(author);
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		System.out.println(content);
		this.content = content;
	}
}
