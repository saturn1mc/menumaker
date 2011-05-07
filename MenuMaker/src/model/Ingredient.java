/**
 * 
 */
package model;

/**
 * @author cmaurice2
 *
 */
public class Ingredient {
	
	private static int currentID = 0;
	
	private int id;
	private String name;
	private String unit;
	private ShopPoint shopPoint;
	
	public Ingredient(String name, String unit, ShopPoint shopPoint){
		this.id = currentID++;
		
		this.name = name;
		this.unit = unit;
	}
	
	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		Ingredient.currentID = currentID;
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
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public ShopPoint getShopPoint() {
		return shopPoint;
	}
	
	public void setShopPoint(ShopPoint shopPoint) {
		this.shopPoint = shopPoint;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
