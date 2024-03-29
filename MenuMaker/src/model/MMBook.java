/**
 * 
 */
package model;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author cmaurice2
 *
 */
public class MMBook implements Comparable<MMBook>{

	public static final String NODE_NAME_BOOK = "book";
	public static final String ATTR_NAME_ID = "id";
	public static final String ATTR_NAME_NAME = "name";
	public static final String ATTR_NAME_AUTHOR = "author";
	
	private static int currentID = 0;
	
	private int id;
	private String name;
	private String author;
	
	
	public MMBook(String name, String author) {
		this.id = currentID++;
		this.name = name;
		this.author = author;
	}
	
	public MMBook(Element bookElement){
		this.id = Integer.parseInt(bookElement.getAttributeValue(ATTR_NAME_ID));
		this.name = bookElement.getAttributeValue(ATTR_NAME_NAME);
		this.author = bookElement.getAttributeValue(ATTR_NAME_AUTHOR);
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
		MMData.setModificationsSaved(false);
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		MMData.setModificationsSaved(false);
		this.author = author;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(MMBook o) {
		return this.toString().compareTo(o.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MMBook){
			if(((MMBook)obj).getID() == id){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public Element toXML(){
		Element bookElement = new Element(NODE_NAME_BOOK);
		
		bookElement.setAttribute(new Attribute(ATTR_NAME_ID, Integer.toString(id)));
		bookElement.setAttribute(new Attribute(ATTR_NAME_NAME, name));
		bookElement.setAttribute(new Attribute(ATTR_NAME_AUTHOR, author));
		
		return bookElement;
	}
}
