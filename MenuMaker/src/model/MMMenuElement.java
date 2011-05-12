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
public class MMMenuElement {

	public static final String NODE_NAME_MENU_ELEMENT = "element";
	public static final String ATTR_NAME_PERIOD = "period";
	public static final String ATTR_NAME_RECIPE = "recipe";
	public static final String ATTR_NAME_COMMENT = "comment";
	
	public static final String ATTR_VALUE_NO_RECIPE = "N/A";

	private int period;
	private MMRecipe recipe;
	private String comment;

	public MMMenuElement(int period, MMRecipe recipe, String comment) {
		this.period = period;
		this.recipe = recipe;
		this.comment = comment;
	}

	public MMMenuElement(Element menuElementElement,
			Hashtable<Integer, MMRecipe> recipes) {
		this.period = Integer.parseInt(menuElementElement
				.getAttributeValue(ATTR_NAME_PERIOD));
		this.recipe = recipes.get(Integer.parseInt(menuElementElement
				.getAttributeValue(ATTR_NAME_PERIOD)));
		this.comment = menuElementElement.getAttributeValue(ATTR_NAME_COMMENT);
	}
	
	public Element toXML() {
		Element menuElementElement = new Element(NODE_NAME_MENU_ELEMENT);

		menuElementElement.setAttribute(new Attribute(ATTR_NAME_PERIOD, Integer
				.toString(period)));
		
		if(recipe != null){
			menuElementElement.setAttribute(new Attribute(ATTR_NAME_RECIPE, Integer
				.toString(recipe.getID())));
		}
		else{
			menuElementElement.setAttribute(new Attribute(ATTR_NAME_RECIPE, ATTR_VALUE_NO_RECIPE));
		}
		
		menuElementElement.setAttribute(new Attribute(ATTR_NAME_COMMENT, comment));

		return menuElementElement;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public MMRecipe getRecipe() {
		return recipe;
	}

	public void setRecipe(MMRecipe recipe) {
		this.recipe = recipe;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
