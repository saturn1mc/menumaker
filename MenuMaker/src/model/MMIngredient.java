/**
 * 
 */
package model;

/**
 * @author cmaurice2
 *
 */
public class MMIngredient {
	
	private static int currentID = 0;
	
	private int id;
	private String name;
	private String unit;
	private MMShopPoint shopPoint;
	
	public MMIngredient(String name, String unit, MMShopPoint shopPoint){
		this.id = currentID++;
		
		this.name = name;
		this.unit = unit;
	}
	
	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		MMIngredient.currentID = currentID;
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
	
	public MMShopPoint getShopPoint() {
		return shopPoint;
	}
	
	public void setShopPoint(MMShopPoint shopPoint) {
		this.shopPoint = shopPoint;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
