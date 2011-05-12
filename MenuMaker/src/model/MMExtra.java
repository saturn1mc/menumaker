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
public class MMExtra extends MMRecipeElement{

	public static final String ATTR_NAME_COMMENT = "comment";

	private String comment;

	public MMExtra(MMIngredient ingredient, double quantity, String comment) {
		super(ingredient, quantity);
		this.comment = comment;
	}

	public MMExtra(Element extraElement,
			Hashtable<Integer, MMIngredient> ingredients) {
		super(extraElement, ingredients);

		this.comment = extraElement.getAttributeValue(ATTR_NAME_COMMENT);
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public Element toXML() {
		Element extraElement = super.toXML();
		
		extraElement.setAttribute(new Attribute(ATTR_NAME_COMMENT, comment));
		
		return extraElement;
	}
}
