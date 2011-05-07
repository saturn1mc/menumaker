/**
 * 
 */
package model;

/**
 * @author cmaurice2
 * 
 */
public class MMUnit {
	private static int currentID = 0;

	private int id;
	private String name;

	public MMUnit(String name) {
		this.id = currentID++;
		this.name = name;
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
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
