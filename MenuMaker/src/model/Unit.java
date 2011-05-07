/**
 * 
 */
package model;

/**
 * @author cmaurice2
 * 
 */
public class Unit {
	private static int currentID = 0;

	private int id;
	private String name;

	public Unit(String name) {
		this.id = currentID++;
		this.name = name;
	}

	public static int getCurrentID() {
		return currentID;
	}

	public static void setCurrentID(int currentID) {
		Unit.currentID = currentID;
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

	@Override
	public String toString() {
		return name;
	}
}
