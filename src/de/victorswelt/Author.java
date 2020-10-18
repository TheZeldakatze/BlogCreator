package de.victorswelt;

public class Author {
	private int id;
	private String name;
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
