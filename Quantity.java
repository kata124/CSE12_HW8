/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * 06/03/14
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
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
	
	List<String> emp = Collections.<String>emptyList();
	
	/* CONSTRUCTORS */
	/** 
	 * 0-arg constructor
	 * Creates a Quantity object with default quantity of 1, and no units. 
	 * It should still contain a Map object (a Map with no entries)
	 */
	public Quantity() {
		value = 1;
		units = new HashMap<String, Integer>();
	}
	
	/**
	 * 1-arg constructor
	 * Creates a deep copy of passed in Quantity object.
	 * 
	 * @param quantity - a Quantity object to be deep copied
	 */
	public Quantity(Quantity quantity) {
		value = quantity.getValue();
		units = new HashMap<String, Integer>();
		addUnits(quantity.getUnits());
	}

	/**
	 * 3-arg constructor
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
		if (numerator == null || denominator == null)
			throw new IllegalArgumentException();
		
		this.value = value; //store value
		units = new HashMap<String, Integer>(); //new map
		String currentkey;
		
		//go through numerator adding to map
		for (int index=0; index<numerator.size(); index++) {
			currentkey = numerator.get(index);
			if (currentkey.compareTo("")!=0){//if currentkey is not empty
				if (units.containsKey(currentkey)){ //if already in map, increment	
					units.put(currentkey, units.get(currentkey) + 1);
				}
				else {//else add it to the map	
					units.put(currentkey, 1);
				}			
			}
		}
		
		//go through denominator adding to map
		for (int index=0; index<denominator.size(); index++) {
			currentkey = denominator.get(index);
			if (currentkey.compareTo("")!=0){//if currentkey is not empty
				if (units.containsKey(currentkey))//if already in map, decrement
				{	
					units.put(currentkey, units.get(currentkey) - 1);
					if (units.get(currentkey) == 0)
						units.remove(currentkey);//remove if zero
				}
				else {//else add it to the map	
					units.put(currentkey, -1);
				}
			}
		}
	}
	/* -- END CONSTRUCTORS */

	/* MATH FUNCTIONS */
	public Quantity mul(Quantity otherQ) throws IllegalArgumentException {
		// check if arg is null
		// throw IAE
		if (otherQ == null) {
			throw new IllegalArgumentException();
		}
		
		// valid argument
		double prod;
		prod = this.getValue() * otherQ.getValue();
		Quantity q = new Quantity(this);
		q.setValue(prod);
		q.addUnits(otherQ.getUnits());		
		return q;
	}
	public Quantity div(Quantity otherQ) {
		// check if arg is null or 0
		// throw IAE
		if (otherQ == null || otherQ.getValue() == 0.0) {
			throw new IllegalArgumentException("Cannot div by 0.0");
		}
		
		// valid argument
		double quot;
		quot = this.getValue() / otherQ.getValue();
		Quantity q = new Quantity(this);
		q.setValue(quot);
		q.subUnits(otherQ.getUnits());		
		return q;
	}

	public Quantity add(Quantity otherQ) throws IllegalArgumentException {
		// check if arg is null OR if two Quantity objects have diff units
		// throw IAE
		if (otherQ == null || 
			this.toStringUnits().compareTo(otherQ.toStringUnits()) != 0) {
			throw new IllegalArgumentException("Unit mismatch in add");
		}
		
		// valid argument
		return qMath("add", otherQ);
	}
	
	public Quantity sub(Quantity otherQ) {
		// TODO Auto-generated method stub
		if (otherQ == null || 
				this.toStringUnits().compareTo(otherQ.toStringUnits()) != 0) {
				throw new IllegalArgumentException("Unit mismatch in sub");
		}
		
		// valid argument
		return qMath("sub", otherQ);
	}
	
	public Quantity pow(int power) {
		Quantity q = new Quantity(this);
		
		if (power == 0 || power == -0) {
			return new Quantity();
		}
		//multiply by self multiple times
		Quantity qStat = new Quantity(q);
		for (int i = 2; i <= Math.abs(power); i++ ) {
				q = q.mul(qStat);
		}
		//if negative power flip value
		if (power < 0){
			q.setValue(1/q.getValue());
			//and flip units
			q.flipUnits();
		}
		
		return q;
	}

	public Quantity negate() {
		Quantity q = new Quantity(this);
		q.setValue(q.getValue()*-1);
		return q;
	}
	/* -- END MATH FUNCTIONS */
	
	
	/* OTHER FUNCTIONS */
	public boolean equals(Object checkValue)
	{
		// only compare if Object is Quantity
		if (this instanceof Quantity)
		{
			// if this Quantity and passed in Quantity are the same
			if (this.toString().compareTo(checkValue.toString()) == 0) {
				return true;
			}
		}
		// if not Quantity or not the same...
		return false;	
	}
	
	public int hashCode()
	{
		return this.toString().hashCode();
	}
	
	public String toString() {
		double myValue = this.value;
		Map<String,Integer> myUnits = this.units;
		
		//Get units in order
		TreeSet<String> orderedUnits =
				new TreeSet<String>(myUnits.keySet());
		
		StringBuffer unitsString = new StringBuffer();
		
		for (String key: orderedUnits) {
			int expt = myUnits.get(key);
			unitsString.append(" " + key);
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
		// BASE CASE
		// System.out.println("--normalizedUnit() called--");
		if (!db.containsKey(unitName)) {
			// System.out.println(unitName + " is primitive!");
			return new Quantity(1.0, Arrays.asList(unitName), Arrays.asList(""));
		}
		
		// take out Quantity in db
		Quantity returnQ = new Quantity(db.get(unitName));
		// System.out.println("normalizedUnit-returnQ: " + returnQ.toString());
		// System.out.println("ABOUT TO RETURN " + returnQ + " IN NORMALIZEDUNIT");
		return returnQ.normalize(db);

	}
	
	public Quantity normalize(Map<String,Quantity> db)
	{
		// System.out.println("----normalize() called");
		int pow;
		//Quantity newQ = new Quantity(this);
		Quantity returnQ = new Quantity(this.getValue(), emp, emp);
		
		Set<String> keyset = this.units.keySet();
		
		for (String key : keyset) {
			pow = this.units.get(key);
			
			// System.out.println("key: " + key);
			// System.out.println("returnQ: " + returnQ.toString());
			
			Quantity newQ = Quantity.normalizedUnit(key, db);
			newQ = newQ.pow(pow);
			returnQ = returnQ.mul(newQ);
		}	
		// System.out.println("ABOUT TO RETURN " + returnQ + " in norm()");
		return returnQ;
	}
	/* -- END OTHER FUNCTIONS */

	
	/*-- PRIVATE HELPER METHODS --*/
	private Map<String, Integer> getUnits() {
		return units;
	}

	private double getValue() {
		return value;
	}
	
	private void setValue(double value) {
		this.value = value;
	}
	
	// prints out units of a quantity
	private String toStringUnits() {
		Map<String,Integer> myUnits = this.units;
		
		TreeSet<String> orderedUnits =
				new TreeSet<String>(myUnits.keySet());
		StringBuffer unitsString = new StringBuffer();
		
		for (String key: orderedUnits) {
			int expt = myUnits.get(key);
			unitsString.append(" " + key);
			if (expt !=1)
				unitsString.append("^" + expt);
		}
		
		return unitsString.toString();
	}
	

	/**
	 * qMath
	 * Helper method that performs mathematical operations based on 
	 * appropriate method (e.g. sub(Quantity otherQ) ).
	 * 
	 * @param operation - MUST match method in which qMath is called
	 * @return Quantity
	 */
	private Quantity qMath(String operation, Quantity otherQ) {
		boolean isAdd = operation.compareTo("add") == 0;
		boolean isSub = operation.compareTo("sub") == 0;
		
		double val;
		double thisVal = this.getValue();
		double otherVal = otherQ.getValue();
		
		if (isAdd) {
			val = thisVal + otherVal;
		}
		else if (isSub) {
			val = thisVal - otherVal;
		}
		else {
			// NOTE: this exception is for debugging; it should never occur
			// when running code
			throw new IllegalArgumentException("Called qMath with \"" + operation
					+ "\", not a valid operation String");
		}
		
		Quantity q = new Quantity(this);
		q.setValue(val);
		return q;
	}

	//takes in a units map and adds it to the current units
	private void addUnits(Map<String, Integer> addmap){
		TreeSet<String> orderedUnits =
				new TreeSet<String>(addmap.keySet());
		
		for (String key: orderedUnits) {
			if(units.containsKey(key)){ //if already in map, add exp
				units.put(key, units.get(key)+addmap.get(key));
				if (units.get(key) == 0)
					units.remove(key);//remove if zero
			} else { //else add it to the map
				units.put(key, addmap.get(key));
			}
		}
	}
	//takes in a units map and removes that map from the current units
	private void subUnits(Map<String, Integer> submap) {
		TreeSet<String> orderedUnits =
				new TreeSet<String>(submap.keySet());
		
		for (String key: orderedUnits) {
			if(units.containsKey(key)){ //if already in map, add exp
				units.put(key, units.get(key)-submap.get(key));
				if (units.get(key) == 0)
					units.remove(key);//remove if zero
			} else { //else add it to the map
				units.put(key, -(submap.get(key)));
			}
		}
	}
	//flips the signs of the current units
	private void flipUnits() {
		//Set up array of key values
		TreeSet<String> orderedUnits =
				new TreeSet<String>(units.keySet());
		
		for (String key : orderedUnits)
			units.put(key, units.get(key)*-1);
	}
}

