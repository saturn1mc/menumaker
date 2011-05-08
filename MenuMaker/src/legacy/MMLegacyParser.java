/**
 * 
 */
package legacy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import model.MMBook;
import model.MMData;
import model.MMShopPoint;
import model.MMUnit;

/**
 * @author cmaurice2
 *
 */
public class MMLegacyParser {
	
	private static MMLegacyParser singleton;
	
	private static final int STATE_SHOP = 1;
	private static final int STATE_INGREDIENT = 2;
	private static final int STATE_UNIT = 3;
	private static final int STATE_RECIPE_HEADER = 4;
	private static final int STATE_RECIPE = 5;
	private static final int STATE_FINISHED = 6;
	
	
	private int currentState;
	private MMData data;
	private Hashtable<String, MMShopPoint> existingShopPoints;
	private Hashtable<String, MMUnit> existingUnits;
	private Hashtable<String, MMBook> existingBooks;
	
	private MMLegacyParser(){
		
	}
	
	public static MMLegacyParser getInstance(){
		if(singleton == null){
			singleton = new MMLegacyParser();
		}
		
		return singleton;
	}
	
	/*
	 * ************************************************************
	 * Legacy format
	 * ************************************************************
	 * Empty lines [aprox. 2 lines]
	 * Shop points [inline : 1 line, N columns, Data -> Col 12..N]
	 * Ingredients [inline : 1 line, N columns, Data -> Col 12..N]
	 * Units [inline : 1 line, N columns, Data -> Col 12..N]
	 * Recipes Header [1 line]
	 * Recipes [M lines : 	Col 9 = Recipe name, 
	 * 						Col 10 = Book name, 
	 * 						Col 11 = Page, 
	 * 						Col 12..N = Quantities]
	 */
	public MMData parseLegacyFile(File legacyFile) throws IOException{
		data = new MMData();
		currentState = STATE_SHOP;
		
		BufferedReader reader = new BufferedReader(new FileReader(legacyFile));
		String line = null;
		
		while((line = reader.readLine()) != null){
			String[] split = line.split(";");
			
			switch(currentState){
			
			case STATE_SHOP:
				if(split.length !=0){
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
				if(split[8].isEmpty() || split[8].equals("X")){
					currentState = STATE_FINISHED;
				}
				else{
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

	private void handleRecipeLine(String[] split) {
		// TODO Auto-generated method stub
		
	}

	private void handleUnitLine(String[] split) {
		// TODO Auto-generated method stub
		
	}

	private void handleIngredientLine(String[] split) {
		// TODO Auto-generated method stub
		
	}

	private void handleShopLine(String[] split) {
		// TODO Auto-generated method stub
		
	}
}
