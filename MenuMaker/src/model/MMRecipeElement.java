/**
 * 
 */
package model;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author cmaurice2
 *
 */
public class MMRecipeElement {
	public static final String NODE_NAME_ELEMENT = "element";
	
	public static final String ATTR_NAME_INGREDIENT = "ingredient";
	public static final String ATTR_NAME_QTY = "quantity";	
	
	private MMIngredient ingredient;
	private int quantity;
	
	public MMRecipeElement(MMIngredient ingredient, int quantity){
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	
	public MMIngredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(MMIngredient ingredient) {
		this.ingredient = ingredient;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return quantity + " " + ingredient.getUnit().toString() + " of " + ingredient.toString();
	}
	
	public Element toXML(){
		Element ingredientElement = new Element(NODE_NAME_ELEMENT);
		
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_INGREDIENT, Integer.toString(ingredient.getID())));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_QTY, Integer.toString(quantity)));
		
		return ingredientElement;
	}
}
