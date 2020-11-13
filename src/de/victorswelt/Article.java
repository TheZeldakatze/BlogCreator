package de.victorswelt;

import java.util.Date;

public class Article {
	private String fileName;
	private Date created, lastEdited;
	private Author author;
	private String title;
	private String content;
	private boolean edited = false;
	
	public Article() {
		
	}
	
	public Article(String fileName, Author author, String title, Date created, Date edited, String content) {
		this();
		
		this.fileName = fileName;
		this.author = author;
		this.title = title;
		this.created = created;
		this.lastEdited = edited;
		this.content = content;
	}
	
	
	
	public String getOrCreateFileName() {
		// if no file name was yet created, create one now
		if(fileName == null) {
			fileName = title.replace(" ", "-");
			
			// change the file name until it's unique
			if(!ArticleList.getInstance().isNameUnique(fileName)) {
				int i = 0;
				while (!ArticleList.getInstance().isNameUnique(fileName + i)) {
					i++;
				}
				fileName = fileName + i;
			}
		}
		
		return fileName;
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
	
	/**
	 * Returns the filename without creating one if the value is null */
	public String getFileName() {
		return fileName;
	}
}
