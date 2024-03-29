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
public class MMUnit implements Comparable<MMUnit>{
	public static final String NODE_NAME_UNIT = "unit";
	
	public static final String ATTR_NAME_ID = "id";
	public static final String ATTR_NAME_NAME = "name";
	
	private static int currentID = 0;

	private int id;
	private String name;

	public MMUnit(String name) {
		this.id = currentID++;
		this.name = name;
	}
	
	public MMUnit(Element unitElement) {
		this.id = Integer.parseInt(unitElement.getAttributeValue(ATTR_NAME_ID));
		this.name = unitElement.getAttributeValue(ATTR_NAME_NAME);
	}

	public static int getCurrentID() {
		return currentID;
	}

	public static void setCurrentID(int currentID) {
		MMUnit.currentID = currentID;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		MMData.setModificationsSaved(false);
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int compareTo(MMUnit o) {
		return this.toString().compareTo(o.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MMUnit){
			if(((MMUnit)obj).getID() == id){
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
	
	public Element toXML(){
		Element unitElement = new Element(NODE_NAME_UNIT);
		
		unitElement.setAttribute(new Attribute(ATTR_NAME_ID, Integer.toString(id)));
		unitElement.setAttribute(new Attribute(ATTR_NAME_NAME, name));
		
		return unitElement;
	}
}
