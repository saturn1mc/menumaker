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
public class MMShopPoint {
	public static final String NODE_NAME_SHOP = "shop";
	
	public static final String ATTR_NAME_ID = "id";
	public static final String ATTR_NAME_NAME = "name";
	public static final String ATTR_NAME_PRIO = "priority";
	
	
	private static int currentID;
	
	private int id;
	private String name;
	private int priority;
	
	public MMShopPoint(String name, int priority) {
		this.id = currentID++;
		this.name = name;
		this.priority = priority;
	}
	
	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		MMShopPoint.currentID = currentID;
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
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	@Override
	public String toString() {
		return priority + " - " + name;
	}
	
	public Element toXML(){
		Element shopElement = new Element(NODE_NAME_SHOP);
		
		shopElement.setAttribute(new Attribute(ATTR_NAME_ID, Integer.toString(id)));
		shopElement.setAttribute(new Attribute(ATTR_NAME_NAME, name));
		shopElement.setAttribute(new Attribute(ATTR_NAME_PRIO, Integer.toString(priority)));
		
		return shopElement;
	}
}
