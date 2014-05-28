/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * TODO: DATE
 */


import java.util.Map;
import java.util.List;
import java.util.TreeSet;
import java.text.DecimalFormat;

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
	public Quantity pow(int power) {
		// TODO Auto-generated method stub
		return null;
	}
	public Quantity negate() {
		// TODO Auto-generated method stub
		return null;
	}
	/* -- END MATH FUNCTIONS */
	
	
	/* OTHER FUNCTIONS */
	public boolean equals(Object checkValue)
	{
		//TODO
		return false;
	}
	
	public int hashCode()
	{
		return this.toString().hashCode();
	}
	
	public String toString()
	{
		double myValue = this.value;
		Map<String,Integer> myUnits = this.units;
		
		//Get units in order
		TreeSet<String> orderedUnits =
				new TreeSet<String>(myUnits.keySet());
		
		StringBuffer unitsString = new StringBuffer();
		
		for (String key: orderedUnits) {
			int expt = myUnits.get(key);
			if (expt !=1)
				unitsString.append("^" + expt);
		}
		//Used to convert doubles to a string
		//with fixed max num of decimal places
		DecimalFormat df = new DecimalFormat("0.0####");
		
		//Put it all together and return
		return df.format(myValue)+unitsString.toString();
	}
	
	public static Quantity normalizedUnit(String unitName, Map<String,Quantity> db)
	{
		//TODO
		return (Quantity)null;
	}
	public Quantity normalize(Map<String,Quantity> db)
	{
		//TODO
		return (Quantity)null;
	}
	/* -- END OTHER FUNCTIONS */
}
