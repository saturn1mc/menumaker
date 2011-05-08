/**
 * 
 */
package legacy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
		
		BufferedReader reader = new BufferedReader(new FileReader(legacyFile));
		String line = null;
		
		while((line = reader.readLine()) != null){
			String[] split = line.split(";");
		}
		
		
		return data;
	}
}
