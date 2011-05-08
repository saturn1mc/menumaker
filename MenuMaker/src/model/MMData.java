/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * @author cmaurice2
 * 
 */
public class MMData {

	public static final String FOLDER_CONF = "/config/";
	public static final String CONFIG_FILE = "mmdata.xml";

	public static final String NODE_NAME_ROOT = "menumaker";
	public static final String NODE_NAME_BOOKS = "books";
	public static final String NODE_NAME_SHOPS = "shops";
	public static final String NODE_NAME_UNITS = "units";
	public static final String NODE_NAME_INGREDIENTS = "ingredients";
	public static final String NODE_NAME_RECIPES = "recipes";

	public static final String ATTR_NAME_CURR_ID = "currentid";

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

	public String getConfigFile() throws IOException {
		File configDir = new File("." + FOLDER_CONF);

		if (!configDir.exists()) {
			configDir.mkdirs();
		}

		return configDir.getPath() + File.separator + CONFIG_FILE;
	}

	public HashMap<Integer, MMBook> getBooks() {
		return books;
	}

	public void addBook(MMBook book) {
		books.put(book.getID(), book);
	}

	public void removeBook(MMBook book) {
		books.remove(book);
	}

	public HashMap<Integer, MMShopPoint> getShopPoints() {
		return shopPoints;
	}

	public void addShopPoint(MMShopPoint shopPoint) {
		shopPoints.put(shopPoint.getID(), shopPoint);
	}

	public void removeShopPoint(MMShopPoint shopPoint) {
		shopPoints.remove(shopPoint);
	}

	public HashMap<Integer, MMUnit> getUnits() {
		return units;
	}

	public void addUnit(MMUnit unit) {
		units.put(unit.getID(), unit);
	}

	public void removeUnit(MMUnit unit) {
		units.remove(unit);
	}

	public HashMap<Integer, MMIngredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(MMIngredient ingredient) {
		ingredients.put(ingredient.getID(), ingredient);
	}

	public void removeIngredient(MMIngredient ingredient) {
		ingredients.remove(ingredient);
	}

	public HashMap<Integer, MMRecipe> getRecipes() {
		return recipes;
	}

	public void addRecipe(MMRecipe recipe) {
		recipes.put(recipe.getID(), recipe);
	}

	public void removeRecipe(MMRecipe recipe) {
		recipes.remove(recipe);
	}

	@SuppressWarnings("unchecked")
	public void loadData() throws JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(new File(getConfigFile()));
		
		Element rootNode = document.getRootElement();
		
		//Books
		Element booksElement = rootNode.getChild(NODE_NAME_BOOKS);
		MMBook.setCurrentID(Integer.parseInt(booksElement.getAttributeValue(ATTR_NAME_CURR_ID)));
		
		List<Element> booksList = booksElement.getChildren(MMBook.NODE_NAME_BOOK);
		for(Element bookElement : booksList){
			addBook(new MMBook(bookElement));
		}
		
		//Shops
		Element shopsElement = rootNode.getChild(NODE_NAME_SHOPS);
		MMShopPoint.setCurrentID(Integer.parseInt(shopsElement.getAttributeValue(ATTR_NAME_CURR_ID)));
		
		List<Element> shopsList = shopsElement.getChildren(MMShopPoint.NODE_NAME_SHOP);
		for(Element shopElement : shopsList){
			addShopPoint(new MMShopPoint(shopElement));
		}
		
		//Units
		Element unitsElement = rootNode.getChild(NODE_NAME_UNITS);
		MMUnit.setCurrentID(Integer.parseInt(unitsElement.getAttributeValue(ATTR_NAME_CURR_ID)));
		
		List<Element> unitsList = unitsElement.getChildren(MMUnit.NODE_NAME_UNIT);
		for(Element unitElement : unitsList){
			addUnit(new MMUnit(unitElement));
		}
		
		//Ingredients
		Element ingredientsElement = rootNode.getChild(NODE_NAME_INGREDIENTS);
		MMIngredient.setCurrentID(Integer.parseInt(ingredientsElement.getAttributeValue(ATTR_NAME_CURR_ID)));
		
		List<Element> ingredientsList = ingredientsElement.getChildren(MMIngredient.NODE_NAME_INGREDIENT);
		for(Element ingredientElement : ingredientsList){
			addIngredient(new MMIngredient(ingredientElement, units, shopPoints));
		}
		
		//Recipes
		Element recipesElement = rootNode.getChild(NODE_NAME_RECIPES);
		MMRecipe.setCurrentID(Integer.parseInt(recipesElement.getAttributeValue(ATTR_NAME_CURR_ID)));
		
		List<Element> recipesList = recipesElement.getChildren(MMRecipe.NODE_NAME_RECIPE);
		for(Element recipeElement : recipesList){
			addRecipe(new MMRecipe(recipeElement, books, ingredients));
		}
	}

	public void saveData() throws FileNotFoundException, IOException {
		// Root node & Document
		Element rootNode = new Element(NODE_NAME_ROOT);
		Document xmlDoc = new Document(rootNode);

		// Books
		Element booksNode = new Element(NODE_NAME_BOOKS);
		booksNode.setAttribute(new Attribute(ATTR_NAME_CURR_ID, Integer
				.toString(MMBook.getCurrentID())));

		for (Entry<Integer, MMBook> entry : books.entrySet()) {
			booksNode.addContent(entry.getValue().toXML());
		}

		rootNode.addContent(booksNode);

		// Shops
		Element shopsNode = new Element(NODE_NAME_SHOPS);
		shopsNode.setAttribute(new Attribute(ATTR_NAME_CURR_ID, Integer
				.toString(MMShopPoint.getCurrentID())));

		for (Entry<Integer, MMShopPoint> entry : shopPoints.entrySet()) {
			shopsNode.addContent(entry.getValue().toXML());
		}

		rootNode.addContent(shopsNode);

		// Units
		Element unitsNode = new Element(NODE_NAME_UNITS);
		unitsNode.setAttribute(new Attribute(ATTR_NAME_CURR_ID, Integer
				.toString(MMUnit.getCurrentID())));

		for (Entry<Integer, MMUnit> entry : units.entrySet()) {
			unitsNode.addContent(entry.getValue().toXML());
		}

		rootNode.addContent(unitsNode);
		
		// Ingredients
		Element ingredientsNode = new Element(NODE_NAME_INGREDIENTS);
		ingredientsNode.setAttribute(new Attribute(ATTR_NAME_CURR_ID, Integer
				.toString(MMIngredient.getCurrentID())));

		for (Entry<Integer, MMIngredient> entry : ingredients.entrySet()) {
			ingredientsNode.addContent(entry.getValue().toXML());
		}

		rootNode.addContent(ingredientsNode);

		// Recipes
		Element recipesNode = new Element(NODE_NAME_RECIPES);
		recipesNode.setAttribute(new Attribute(ATTR_NAME_CURR_ID, Integer
				.toString(MMRecipe.getCurrentID())));

		for (Entry<Integer, MMRecipe> entry : recipes.entrySet()) {
			recipesNode.addContent(entry.getValue().toXML());
		}

		rootNode.addContent(recipesNode);

		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		xmlOutputter.output(xmlDoc, new FileOutputStream(getConfigFile()));
	}
}