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
public class MMRecipeElement {
	public static final String NODE_NAME_ELEMENT = "element";
	
	public static final String ATTR_NAME_INGREDIENT = "ingredient";
	public static final String ATTR_NAME_QTY = "quantity";	
	
	private MMIngredient ingredient;
	private float quantity;
	
	public MMRecipeElement(MMIngredient ingredient, float quantity){
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	
	public MMRecipeElement(Element elementElement,
			Hashtable<Integer, MMIngredient> ingredients) {

		this.ingredient = ingredients.get(Integer.parseInt(elementElement.getAttributeValue(ATTR_NAME_INGREDIENT)));
		this.quantity = Float.parseFloat(elementElement.getAttributeValue(ATTR_NAME_QTY));
	}

	public MMIngredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(MMIngredient ingredient) {
		this.ingredient = ingredient;
	}
	
	public float getQuantity() {
		return quantity;
	}
	
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return quantity + " " + ingredient.getUnit().toString() + " of " + ingredient.toString();
	}
	
	public Element toXML(){
		Element ingredientElement = new Element(NODE_NAME_ELEMENT);
		
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_INGREDIENT, Integer.toString(ingredient.getID())));
		ingredientElement.setAttribute(new Attribute(ATTR_NAME_QTY, Float.toString(quantity)));
		
		return ingredientElement;
	}
}
