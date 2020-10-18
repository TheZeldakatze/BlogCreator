package de.victorswelt;

import java.util.ArrayList;

public class AuthorList {
	private static final Author NO_AUTHOR = new Author(Integer.MIN_VALUE, "No Author / Deleted");
	private ArrayList<Author> authors;
	
	public AuthorList() {
		authors = new ArrayList<Author>();
		
		// TODO remove
		addAuthor(new Author(1, "Jane Doe"));
		addAuthor(new Author(2, "John Doe"));
	}
	
	public void addAuthor(Author a) {
		authors.add(a);
	}
	
	public Author getAuthor(int id) {
		for(Author a : authors) {
			if(a.getID() == id)
				return a;
		}
		return NO_AUTHOR;
	}
	
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	
	public boolean hasAuthor(int id) {
		for(Author a : authors)
			if(a.getID() == id)
				return true;
		return false;
	}
	
	public int getID(Author a) {
		if(a == NO_AUTHOR)
			return NO_AUTHOR.getID();
		for(Author e : authors)
			if(e == a)
				return e.getID();
		return NO_AUTHOR.getID();
	}
	
	public int getHighestAuthorID() {
		int biggestID = 0;
		for(Author a : authors) {
			if(a.getID() > biggestID)
				biggestID = a.getID();
		}
		
		return biggestID;
	}
	
	// SINGLETON stuff
	private static AuthorList INSTANCE;
	public static void init() {INSTANCE = new AuthorList();}
	public static AuthorList getInstance() {return INSTANCE;}
}
