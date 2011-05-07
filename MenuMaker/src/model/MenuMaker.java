/**
 * 
 */
package model;

import java.util.HashMap;

/**
 * @author cmaurice2
 *
 */
public class MenuMaker {
	private HashMap<Integer, Book> books;
	private HashMap<Integer, ShopPoint> shopPoints;
	private HashMap<Integer, Unit> units;
	private HashMap<Integer, Ingredient> ingredients;
	private HashMap<Integer, Recipe> recipes;
	
	
	public MenuMaker() {
		books = new HashMap<Integer, Book>();
		shopPoints = new HashMap<Integer, ShopPoint>();
		units = new HashMap<Integer, Unit>();
		ingredients = new HashMap<Integer, Ingredient>();
		recipes = new HashMap<Integer, Recipe>();
	}

	public HashMap<Integer, Book> getBooks() {
		return books;
	}
	
	public void addBook(Book book){
		books.put(book.getID(), book);
	}
	
	public void removeBook(Book book){
		books.remove(book);
	}
	
	public HashMap<Integer, ShopPoint> getShopPoints() {
		return shopPoints;
	}
	
	public void addShopPoint(ShopPoint shopPoint){
		shopPoints.put(shopPoint.getID(), shopPoint);
	}
	
	public void removeShopPoint(ShopPoint shopPoint){
		shopPoints.remove(shopPoint);
	}
	
	public HashMap<Integer, Unit> getUnits() {
		return units;
	}
	
	public void addUnit(Unit unit){
		units.put(unit.getID(), unit);
	}
	
	public void removeUnit(Unit unit){
		units.remove(unit);
	}
	
	public HashMap<Integer, Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void addIngredient(Ingredient ingredient){
		ingredients.put(ingredient.getID(), ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient){
		ingredients.remove(ingredient);
	}
	
	public HashMap<Integer, Recipe> getRecipes() {
		return recipes;
	}
	
	public void addRecipe(Recipe recipe){
		recipes.put(recipe.getID(), recipe);
	}
	
	public void removeRecipe(Recipe recipe){
		recipes.remove(recipe);
	}
	
	public void loadData(String filename){
		//TODO
	}
}
