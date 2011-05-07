/**
 * 
 */
package model;

/**
 * @author cmaurice2
 *
 */
public class MMRecipeElement {
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
}
