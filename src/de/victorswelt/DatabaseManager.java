package de.victorswelt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DatabaseManager {
	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E MM dd HH:mm:ss z yyyy");
	private File websiteRoot, websiteInfoFile, articleFolder, authorFolder, templateFolder;
	
	private DatabaseManager() {
		load(new File("website/website.bcp"));
	}
	
	public void save() {
		
		// save every article that was changed
		for(Article a : ArticleList.getInstance().getArticles()) {
			if(a.wasEdited()) {
				Properties p = new Properties();
				
				// collect the information
				p.setProperty("title", a.getTitle());
				p.setProperty("author", "" + AuthorList.getInstance().getID(a.getAuthor()));
				p.setProperty("created", DATE_FORMAT.format(a.getCreated()));
				p.setProperty("modified", DATE_FORMAT.format(a.getLastEdited()));
				p.setProperty("content", a.getContent());
				
				// save the properties to a file
				File f = new File(articleFolder, a.getFileName());
				try {
					FileOutputStream fos = new FileOutputStream(f);
					p.store(fos, null);
					fos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
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
		
		// load every article
		for(File f : articleFolder.listFiles()) {
			if(!f.isDirectory())
				loadArticleFromFile(f);
		}
	}
	
	private void loadArticleFromFile(File f) {
		try {
			Properties p = new Properties();
			
			// load the article from the file
			FileInputStream fis = new FileInputStream(f);
			p.load(fis);
			fis.close();
			
			// get the information
			String title = p.getProperty("title");
			int authorID = Integer.parseInt(p.getProperty("author"));
			Author author = AuthorList.getInstance().getAuthor(authorID);
			Date created = parseDate(p.getProperty("created"));
			Date modified = parseDate(p.getProperty("modified"));
			String content = p.getProperty("content");
			
			// create an object
			ArticleList.getInstance().addArticle(new Article(f.getName(), author, title, created, modified, content));
			
		} catch(NumberFormatException e) {
			System.out.println("Error loading article! Can't read author id (File name:  \"" + f.getName() + "\"). Printing stack trace:");
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
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
	
	/**
	 * A utility function to access and, if they don't exist, create folders */
	private static File accessAndCreateFolder(File parent, String subfolder) {
		File f = new File(parent, subfolder);
		if(!f.exists())
			f.mkdir();
		return f;
	}
	
	private static Date parseDate(String in) {
		Date d = null;
		
		try {
			d = DATE_FORMAT.parse(in);
		} catch (ParseException e) {
			// try initializing the input string as a millisecond time
			d = new Date(Long.parseLong(in));
		}
		return d;
	}
	
	// SINGLETON stuff
	private static DatabaseManager INSTANCE;
	public static DatabaseManager getInstance() {return INSTANCE;}
	public static void init() {INSTANCE = new DatabaseManager();}
}
