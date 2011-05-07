/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author cmaurice2
 *
 */
public class Recipe {
	
	private static int currentID = 0;
	
	private int id;
	private String name;
	private Book book;
	private int page;
	
	private ArrayList<RecipeElement> elements;
	
	public Recipe(String name, Book book, int page) {
		this.id = currentID++;
		
		this.name = name;
		this.book = book;
		this.page = page;
		
		this.elements = new ArrayList<RecipeElement>();
	}

	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		Recipe.currentID = currentID;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ArrayList<RecipeElement> getElements() {
		return elements;
	}

	public void addIngredient(RecipeElement element){
		elements.add(element);
	}
	
	public void removeIngredient(RecipeElement element){
		elements.remove(element);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
