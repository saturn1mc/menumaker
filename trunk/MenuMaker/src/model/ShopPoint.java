/**
 * 
 */
package model;

/**
 * @author cmaurice2
 *
 */
public class ShopPoint {
	private static int currentID;
	
	private int id;
	private String name;
	private int priority;
	
	public ShopPoint(String name, int priority) {
		this.id = currentID++;
		this.name = name;
		this.priority = priority;
	}
	
	public static int getCurrentID() {
		return currentID;
	}
	
	public static void setCurrentID(int currentID) {
		ShopPoint.currentID = currentID;
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
}
