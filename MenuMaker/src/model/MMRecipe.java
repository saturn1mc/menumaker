/**
 * 
 */
package model;

import java.util.ArrayList;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author cmaurice2
 *
 */
public class MMRecipe {
	
	public static final String NODE_NAME_INGREDIENT = "ingredient";
	public static final String NODE_NAME_ELEMENTS = "elements";
	
	public static final String ATTR_NAME_ID = "id";
	public static final String ATTR_NAME_NAME = "name";
	public static final String ATTR_NAME_BOOK = "book";
	public static final String ATTR_NAME_PAGE = "page";
	
	private static int currentID = 0;
	
	private int id;
	private String name;
	private MMBook book;
	private int page;
	
	private ArrayList<MMRecipeElement> elements;
	
	public MMRecipe(String name, MMBook book, int page) {
		this.id = currentID++;
		
		this.name = name;
		this.book = book;
		this.page = page;
		
		this.elements = new ArrayList<MMRecipeElement>();
	}

	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		MMRecipe.currentID = currentID;
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

	public MMBook getBook() {
		return book;
	}

	public void setBook(MMBook book) {
		this.book = book;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ArrayList<MMRecipeElement> getElements() {
		return elements;
	}

	public void addIngredient(MMRecipeElement element){
		elements.add(element);
	}
	
	public void removeIngredient(MMRecipeElement element){
		elements.remove(element);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Element toXML(){
		Element ingredientElement = new Element(NODE_NAME_INGREDIENT);
		
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_ID, Integer.toString(id)));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_NAME, name));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_BOOK, Integer.toString(book.getID())));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_PAGE, Integer.toString(page)));
		
		Element elementsElement = new Element(NODE_NAME_ELEMENTS);
		for(MMRecipeElement element : elements){
			elementsElement.addContent(element.toXML());
		}
		
		ingredientElement.addContent(elementsElement);
		
		return ingredientElement;
	}
}
