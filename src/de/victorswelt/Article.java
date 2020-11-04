package de.victorswelt;

import java.util.Date;

public class Article {
	private Date created, lastEdited;
	private Author author;
	private String title;
	private String content;
	private boolean edited = false;
	
	public Article() {
		
	}
	
	public Article(Author author, String title, Date created, Date edited, String content) {
		this();
		
		this.author = author;
		this.title = title;
		this.created = created;
		this.lastEdited = edited;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		updateEditedTime();
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		updateEditedTime();
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		updateEditedTime();
		this.content = content;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastEdited() {
		return lastEdited;
	}
	
	public boolean wasEdited() {
		return edited;
	}

	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	public String toString() {
		return title;
	}
	
	private void updateEditedTime() {
		edited = true;
		lastEdited.setTime(System.currentTimeMillis());
	}
}
