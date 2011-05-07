/**
 * 
 */
package model;

/**
 * @author cmaurice2
 *
 */
public class MMBook {

	private static int currentID = 0;
	
	private int id;
	private String name;
	private String author;
	
	
	public MMBook(String name, String author) {
		this.id = currentID++;
		this.name = name;
		this.author = author;
	}
	
	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		MMBook.currentID = currentID;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return name + " - by " + author;
	}
}
