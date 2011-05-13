/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author cmaurice2
 *
 */
public class MMRecipe implements Comparable<MMRecipe>{
	
	public static final String NODE_NAME_RECIPE = "recipe";
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

	@SuppressWarnings("unchecked")
	public MMRecipe(Element recipeElement, Hashtable<Integer, MMBook> books,
			Hashtable<Integer, MMIngredient> ingredients) {
		
		this.id = Integer.parseInt(recipeElement.getAttributeValue(ATTR_NAME_ID));
		this.name = recipeElement.getAttributeValue(ATTR_NAME_NAME);
		this.book = books.get(Integer.parseInt(recipeElement.getAttributeValue(ATTR_NAME_BOOK)));
		this.page = Integer.parseInt(recipeElement.getAttributeValue(ATTR_NAME_PAGE));
		
		this.elements = new ArrayList<MMRecipeElement>();
		Element elementsNode = recipeElement.getChild(NODE_NAME_ELEMENTS);
		List<Element> elementList = elementsNode.getChildren(MMRecipeElement.NODE_NAME_ELEMENT);
		
		for(Element elementElement : elementList){
			addElement(new MMRecipeElement(elementElement, ingredients));
		}
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
		MMData.setModificationsSaved(false);
		this.name = name;
	}

	public MMBook getBook() {
		return book;
	}

	public void setBook(MMBook book) {
		MMData.setModificationsSaved(false);
		this.book = book;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		MMData.setModificationsSaved(false);
		this.page = page;
	}

	public ArrayList<MMRecipeElement> getElements() {
		return elements;
	}

	public void addElement(MMRecipeElement element){
		MMData.setModificationsSaved(false);
		elements.add(element);
	}
	
	public void setElements(ArrayList<MMRecipeElement> elements) {
		this.elements = elements;
	}
	
	public void removeElement(MMRecipeElement element){
		MMData.setModificationsSaved(false);
		elements.remove(element);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(MMRecipe o) {
		return this.toString().compareTo(o.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MMRecipe){
			if(((MMRecipe)obj).getID() == id){
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
		Element recipeElement = new Element(NODE_NAME_RECIPE);
		
		recipeElement.setAttribute(new Attribute(ATTR_NAME_ID, Integer.toString(id)));
		recipeElement.setAttribute(new Attribute(ATTR_NAME_NAME, name));
		recipeElement.setAttribute(new Attribute(ATTR_NAME_BOOK, Integer.toString(book.getID())));
		recipeElement.setAttribute(new Attribute(ATTR_NAME_PAGE, Integer.toString(page)));
		
		Element elementsElement = new Element(NODE_NAME_ELEMENTS);
		for(MMRecipeElement element : elements){
			elementsElement.addContent(element.toXML());
		}
		
		recipeElement.addContent(elementsElement);
		
		return recipeElement;
	}
}
