/**
 * 
 */
package model;

import java.util.HashMap;

/**
 * @author cmaurice2
 *
 */
public class MMData {
	private HashMap<Integer, MMBook> books;
	private HashMap<Integer, MMShopPoint> shopPoints;
	private HashMap<Integer, MMUnit> units;
	private HashMap<Integer, MMIngredient> ingredients;
	private HashMap<Integer, MMRecipe> recipes;
	
	
	public MMData() {
		books = new HashMap<Integer, MMBook>();
		shopPoints = new HashMap<Integer, MMShopPoint>();
		units = new HashMap<Integer, MMUnit>();
		ingredients = new HashMap<Integer, MMIngredient>();
		recipes = new HashMap<Integer, MMRecipe>();
	}

	public HashMap<Integer, MMBook> getBooks() {
		return books;
	}
	
	public void addBook(MMBook book){
		books.put(book.getID(), book);
	}
	
	public void removeBook(MMBook book){
		books.remove(book);
	}
	
	public HashMap<Integer, MMShopPoint> getShopPoints() {
		return shopPoints;
	}
	
	public void addShopPoint(MMShopPoint shopPoint){
		shopPoints.put(shopPoint.getID(), shopPoint);
	}
	
	public void removeShopPoint(MMShopPoint shopPoint){
		shopPoints.remove(shopPoint);
	}
	
	public HashMap<Integer, MMUnit> getUnits() {
		return units;
	}
	
	public void addUnit(MMUnit unit){
		units.put(unit.getID(), unit);
	}
	
	public void removeUnit(MMUnit unit){
		units.remove(unit);
	}
	
	public HashMap<Integer, MMIngredient> getIngredients() {
		return ingredients;
	}
	
	public void addIngredient(MMIngredient ingredient){
		ingredients.put(ingredient.getID(), ingredient);
	}
	
	public void removeIngredient(MMIngredient ingredient){
		ingredients.remove(ingredient);
	}
	
	public HashMap<Integer, MMRecipe> getRecipes() {
		return recipes;
	}
	
	public void addRecipe(MMRecipe recipe){
		recipes.put(recipe.getID(), recipe);
	}
	
	public void removeRecipe(MMRecipe recipe){
		recipes.remove(recipe);
	}
	
	public void loadData(){
		//TODO
	}
	
	public void saveData(){
		//TODO
	}
}
