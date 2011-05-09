/**
 * 
 */
package legacy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import model.MMBook;
import model.MMData;
import model.MMIngredient;
import model.MMRecipe;
import model.MMRecipeElement;
import model.MMShopPoint;
import model.MMUnit;

/**
 * @author cmaurice2
 * 
 */
public class MMLegacyParser {

	/*
	 * ************************************************************
	 * Legacy format
	 * ************************************************************ Empty lines
	 * [aprox. 2 lines] Shop points [inline : 1 line, N columns, Data -> Col
	 * 12..N] Ingredients [inline : 1 line, N columns, Data -> Col 12..N] Units
	 * [inline : 1 line, N columns, Data -> Col 12..N] Recipes Header [1 line]
	 * Recipes [M lines : Col 9 = Recipe name, Col 10 = Book name, Col 11 =
	 * Page, Col 12..N = Quantities]
	 */

	private static MMLegacyParser singleton;

	private static final int STATE_SHOP = 1;
	private static final int STATE_INGREDIENT = 2;
	private static final int STATE_UNIT = 3;
	private static final int STATE_RECIPE_HEADER = 4;
	private static final int STATE_RECIPE = 5;
	private static final int STATE_FINISHED = 6;

	private static final int COL_INLINE_LIST_START = 11;
	private static final int COL_RECIPE_NAME = 8;
	private static final int COL_BOOK_NAME = 9;
	private static final int COL_PAGE_NUM = 10;

	private int currentState;
	private MMData data;

	private ArrayList<MMShopPoint> shopsList;
	private ArrayList<MMUnit> unitsList;
	private ArrayList<MMIngredient> ingredientsList;

	private Hashtable<String, MMShopPoint> existingShopPoints;
	private Hashtable<String, MMUnit> existingUnits;
	private Hashtable<String, MMBook> existingBooks;

	private MMLegacyParser() {
		shopsList = new ArrayList<MMShopPoint>();
		unitsList = new ArrayList<MMUnit>();
		ingredientsList = new ArrayList<MMIngredient>();
		existingShopPoints = new Hashtable<String, MMShopPoint>();
		existingUnits = new Hashtable<String, MMUnit>();
		existingBooks = new Hashtable<String, MMBook>();
	}

	public static MMLegacyParser getInstance() {
		if (singleton == null) {
			singleton = new MMLegacyParser();
		}

		return singleton;
	}

	public MMData parseLegacyFile(File legacyFile) throws IOException {
		data = new MMData();
		currentState = STATE_SHOP;
		ingredientsList.clear();
		existingShopPoints.clear();
		existingUnits.clear();
		existingBooks.clear();

		BufferedReader reader = new BufferedReader(new FileReader(legacyFile));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String[] split = line.split(";");

			switch (currentState) {

			case STATE_SHOP:
				if (split.length != 0) {
					handleShopLine(split);
					currentState = STATE_INGREDIENT;
				}
				break;

			case STATE_INGREDIENT:
				handleIngredientLine(split);
				currentState = STATE_UNIT;
				break;

			case STATE_UNIT:
				handleUnitLine(split);
				currentState = STATE_RECIPE_HEADER;
				break;

			case STATE_RECIPE_HEADER:
				currentState = STATE_RECIPE;
				break;

			case STATE_RECIPE:
				if (split[8].isEmpty() || split[8].equals("X")) {
					currentState = STATE_FINISHED;
				} else {
					handleRecipeLine(split);
				}
				break;

			default:
				break;
			}
		}

		reader.close();

		return data;
	}

	private void handleShopLine(String[] split) {
		for (int i = COL_INLINE_LIST_START; i < split.length; i++) {
			if (split[i] != null && !split[i].isEmpty()) {
				String compStr = split[i].replaceAll("--", "-");
				MMShopPoint shopPoint = existingShopPoints.get(compStr);

				if (shopPoint == null) {
					String[] shopSplit = compStr.split("-");

					if (shopSplit.length < 2) {
						shopPoint = new MMShopPoint(split[i], 0);
					} else {
						shopPoint = new MMShopPoint(shopSplit[1],
								Integer.parseInt(shopSplit[0]));
					}

					existingShopPoints.put(compStr, shopPoint);
					data.addShopPoint(shopPoint);
				}

				shopsList.add(shopPoint);
			} else {
				break;
			}
		}
	}

	private void handleUnitLine(String[] split) {
		for (int i = COL_INLINE_LIST_START; i < split.length; i++) {
			if (split[i] != null && !split[i].isEmpty()) {
				String compStr = split[i].toLowerCase();
				MMUnit unit = existingUnits.get(compStr);
				if (unit == null) {
					unit = new MMUnit(split[i]);
					existingUnits.put(compStr, unit);
				}

				int pos = i - COL_INLINE_LIST_START;
				ingredientsList.get(pos).setUnit(unit);
				unitsList.add(unit);
				data.addUnit(unit);
			} else {
				break;
			}
		}
	}

	private void handleIngredientLine(String[] split) {
		for (int i = COL_INLINE_LIST_START; i < split.length; i++) {
			if (split[i] != null && !split[i].isEmpty()) {
				int pos = i - COL_INLINE_LIST_START;
				MMIngredient ingredient = new MMIngredient(split[i], null, shopsList.get(pos));
				ingredientsList.add(ingredient);
				data.addIngredient(ingredient);
			} else {
				break;
			}
		}
	}
	
	private void handleRecipeLine(String[] split) {
		if (split.length > COL_RECIPE_NAME && split[COL_RECIPE_NAME] != null && !split[COL_RECIPE_NAME].isEmpty()
				&& !split[COL_RECIPE_NAME].toLowerCase().equals("x")) {
			String name = split[COL_RECIPE_NAME];
			String bookName = split[COL_BOOK_NAME];
			
			int page;
			
			try{
				page = Integer.parseInt(split[COL_PAGE_NUM]);
			}
			catch(NumberFormatException nfe){
				page = 0;
			}

			if(bookName.isEmpty()){
				bookName = "Other";
			}
			
			MMBook book = existingBooks.get(bookName);
			if (book == null) {
				book = new MMBook(bookName, "?");
				existingBooks.put(bookName, book);
				data.addBook(book);
			}

			MMRecipe recipe = new MMRecipe(name, book, page);

			for (int i = COL_INLINE_LIST_START; i < split.length; i++) {
				if (split[i] != null && !split[i].isEmpty()) {
					int pos = i - COL_INLINE_LIST_START;
					recipe.addElement(new MMRecipeElement(ingredientsList
							.get(pos), Float.parseFloat(split[i].replace(",", "."))));
				}
			}
			
			data.addRecipe(recipe);
		}
	}
}
