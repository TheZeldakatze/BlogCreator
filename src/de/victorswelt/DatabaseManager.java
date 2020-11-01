package de.victorswelt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class DatabaseManager {
	private File websiteRoot, websiteInfoFile, articleFolder, authorFolder, templateFolder;
	
	private DatabaseManager() {
		load(new File("website/website.bcp"));
	}
	
	private void load(File infoFile) {
		websiteInfoFile = infoFile;
		websiteRoot = websiteInfoFile.getParentFile();
		articleFolder = accessAndCreateFolder(websiteRoot, "articles");
		authorFolder = accessAndCreateFolder(websiteRoot, "authors");
		templateFolder = accessAndCreateFolder(websiteRoot, "template");
		
		// load every author
		for(File f : authorFolder.listFiles()) {
			if(!f.isDirectory())
				loadAuthorFromFile(f);
		}
	}
	
	private void loadAuthorFromFile(File f) {
		try {
			Properties p = new Properties();
			
			// load the author from the file
			FileInputStream fis = new FileInputStream(f);
			p.load(fis);
			fis.close();
			
			// read the author's information
			int id = Integer.parseInt(p.getProperty("id"));
			String name = p.getProperty("name");
			
			// create the author object if the name is not null, otherwise show a warning
			if(name != null)
				AuthorList.getInstance().addAuthor(new Author(id, name));
			else
				System.out.println("Error loading author! Can't read name (File name:  \"" + f.getName() + "\") " + name);
		} catch(NumberFormatException e) {
			System.out.println("Error loading author! Can't read id (File name:  \"" + f.getName() + "\"). Printing stack trace:");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Article createArticle(String base64) {
		return null;
	}
	
	private String packArticle(Article article) {
		return null;
	}
	
	/**
	 * A utility function to access and, if they don't exist, create folders */
	public File accessAndCreateFolder(File parent, String subfolder) {
		File f = new File(parent, subfolder);
		if(!f.exists())
			f.mkdir();
		return f;
	}
	
	// SINGLETON stuff
	private static DatabaseManager INSTANCE;
	public static DatabaseManager getInstance() {return INSTANCE;}
	public static void init() {INSTANCE = new DatabaseManager();}
}
