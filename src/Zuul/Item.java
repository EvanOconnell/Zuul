package Zuul;

/** 
 * A class representing a generic item, containing a weight and a description.
 * -------
 * 
 * @version 3 (02.06.2013) 
 * @author Evan O'Connell
 */
public class Item {
	
	private String description;
	private double weight;
	
	/**
	 * 
	 * @param desc Description of the item, eg "An old lamp". Leave out 
	 * periods in case someone wants to add more to the sentence.
	 * @param weight_lb Item's weight in pounds.
	 */
	public Item(String desc, double weight_kg){
		this.description = desc;
		this.weight = weight_kg;
	}

	/**
	 * @return the description of the item.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the weight in pounds.
	 */
	public double getWeight() {
		return weight;
	}
	
	boolean display_pounds = true;
	
	/**
	 * @return A summary of the item in the form of a sentence.
	 */
	@Override
	public String toString(){
		double pounds = (double) Math.round(weight*2.2*100000)/100000;
		return description+", weighing about "+weight+"kg"+(display_pounds ? " ("+pounds+" lb)" : "");
	}
}
