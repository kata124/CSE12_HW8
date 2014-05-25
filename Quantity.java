/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * TODO: DATE
 */


import java.util.Map;
import java.util.List;


/**
 * Title: class Quantity
 * Description: A Quantity object represents a numerical value with 
 * attached units (e.g. 9.8 meters per second squared). It will always contain
 * a value (e.g. 9.8) and some units (e.g. meters per second squared). In some
 * cases, the collection of units might be empty (dimensionless numbers like
 * PI).
 * 
 * @author Elliot Humphrey / Kenichi Katayama
 * @version 1.0
 */
public class Quantity {
	
	/** value for Quantity object */
	private double value;
	/** units for Quantity object */
	private Map<String, Integer> units;
	
	// constant: default value (for 0-arg constructor)
	private final static double DEFAULT_VALUE = 1;

	
	/* CONSTRUCTORS */
	/** 
	 * TODO: 0-arg constructor
	 * Creates a Quantity object with default quantity of 1, and no units. 
	 * It should still contain a Map object (a Map with no entries)
	 */
	public Quantity() {
		
	}
	
	/**
	 * TODO: 1-arg constructor
	 * Creates a deep copy of passed in Quantity object.
	 * 
	 * @param quantity - a Quantity object to be deep copied
	 */
	public Quantity(Quantity quantity) {
		
	}
	
	/**
	 * TODO: 3-arg constructor
	 * Creates a Quantity object with specified value, units with positive
	 * exponents, and units with negative units (e.g. 9.8 m/s^2)
	 * 
	 * @param value - numeric value
	 * @param numerator - units with positive exponents
	 * @param denominator - units with negative exponents
	 * @throws IllegalArgumentException if numerator or denominator is null 
	 */
	public Quantity(double value, List<String> numerator, 
								  List<String> denominator) {
		
	}
	/* -- END CONSTRUCTORS */

	/* GETTERS AND SETTERS */
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Map<String, Integer> getUnits() {
		return units;
	}

	public void setUnits(Map<String, Integer> units) {
		this.units = units;
	}
	/* -- END GETTERS AND SETTERS */

	/* MATH FUNCTIONS */
	public Quantity mul(Quantity otherQ) {
		// TODO Auto-generated method stub
		return null;
	}
	public Quantity div(Quantity otherQ) {
		// TODO Auto-generated method stub
		return null;
	}
	public Quantity add(Quantity otherQ) {
		// TODO Auto-generated method stub
		return null;
	}
	public Quantity sub(Quantity otherQ) {
		// TODO Auto-generated method stub
		return null;
	}
	public Quantity negate(Quantity otherQ) {
		// TODO Auto-generated method stub
		return null;
	}
	public Quantity pow(Quantity otherQ) {
		// TODO Auto-generated method stub
		return null;
	}
	/* -- END MATH FUNCTIONS */
}
