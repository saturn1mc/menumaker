/**
 * 
 */
package model;

import java.util.Hashtable;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author cmaurice2
 * 
 */
public class MMIngredient implements Comparable<MMIngredient>{

	public static final String NODE_NAME_INGREDIENT = "ingredient";
	public static final String ATTR_NAME_ID = "id";
	public static final String ATTR_NAME_NAME = "name";
	public static final String ATTR_NAME_UNIT = "unit";
	public static final String ATTR_NAME_SHOP = "shop";

	private static int currentID = 0;

	private int id;
	private String name;
	private MMUnit unit;
	private MMShopPoint shopPoint;

	public MMIngredient(String name, MMUnit unit, MMShopPoint shopPoint) {
		this.id = currentID++;

		this.name = name;
		this.unit = unit;
		this.shopPoint = shopPoint;
	}

	public MMIngredient(Element ingredientElement,
			Hashtable<Integer, MMUnit> units,
			Hashtable<Integer, MMShopPoint> shopPoints) {
		
		this.id = Integer.parseInt(ingredientElement.getAttributeValue(ATTR_NAME_ID));
		this.name = ingredientElement.getAttributeValue(ATTR_NAME_NAME);
		
		this.unit = units.get(Integer.parseInt(ingredientElement.getAttributeValue(ATTR_NAME_UNIT)));
		this.shopPoint = shopPoints.get(Integer.parseInt(ingredientElement.getAttributeValue(ATTR_NAME_SHOP)));
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

	public MMUnit getUnit() {
		return unit;
	}

	public void setUnit(MMUnit unit) {
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
	
	@Override
	public int compareTo(MMIngredient o) {
		return this.toString().compareTo(o.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MMIngredient){
			if(((MMIngredient)obj).getID() == id){
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

	public Element toXML() {
		Element ingredientElement = new Element(NODE_NAME_INGREDIENT);

		ingredientElement.setAttribute(new Attribute(ATTR_NAME_ID, Integer
				.toString(id)));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_NAME, name));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_UNIT, Integer
				.toString(unit.getID())));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_SHOP, Integer
				.toString(shopPoint.getID())));

		return ingredientElement;
	}
}
