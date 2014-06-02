/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * May 27, 2014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;


/**
 * Title: class QuantityTester
 * Description: JUnit (4) test class for Quantity
 * @author Elliot Humphrey / Kenichi Katayama
 * @version 1.0
 */
public class QuantityTester extends TestCase {

	/* instance variables */
	Map<String,Quantity> db;
	
	/* setUp() method */
	@Override
	public void setUp() throws Exception {
		super.setUp();
		db = QuantityDB.getDB();
	}
	
	/* REQUIRED: TEST 3 constructors */
	/** Test 0-arg */	// Creates quantity of value 1 and empty units map
	public void test0ArgConstructor() {
		Quantity noArg = null;
		try{
			noArg = new Quantity();
		} catch (RuntimeException e)
		{
			fail("Should successfully create a new Quantity object");
		}
		assertEquals("toString should be 1.0", "1.0", noArg.toString());
	}
	
	// 1: takes Quantity as parameter,
	// makes deep copy of it
	/** Test 1-arg */
	public void test1ArgConstructor() {
		//create a Quantity to duplicate
		Quantity zeroArg = new Quantity();
		Quantity oneArg = null;
		try{
			oneArg = new Quantity(zeroArg);
		} catch (RuntimeException e)
		{
			fail("Should successfully create a new Quantity object");
		}
		assertNotSame("zeroArg should not be oneArg", zeroArg, oneArg);
		assertEquals("zeroArg should equal to oneArg", zeroArg.toString(), zeroArg.toString());
	}

	/** Test 3-arg */
	// 1: double (value)
	// 2: List<String> numerator
	// 3: List<String> denominator
	public void test3ArgConstructor() {
		Quantity threeArg = null;
		try{
			threeArg = new Quantity(9.8, Arrays.asList("m"), null);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e) {
			//continue should throw an exception
		}
		try{
			threeArg = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s","s"));
		} catch (RuntimeException e){
			fail("Should successfully create a new Quantity object");
		}
		assertEquals("Value threeArg should be 9.8 m s^-2", "9.8 m s^-2", threeArg.toString());
		
		threeArg = new Quantity(12.4, Arrays.asList("m"), Arrays.asList("m"));
		assertEquals("Value threeArg should be 12.4", "12.4", threeArg.toString());
		
		threeArg = new Quantity(12.4, Arrays.asList("m"), Arrays.asList(""));
		assertEquals("Value threeArg should be 12.4 m", "12.4 m", threeArg.toString());
	}
	/* -- END CONSTRUCTOR TESTS -- */
	
	
	/*  REQUIRED: TEST Quantity METHODS */
	//create initial Quantities
	Quantity firstQ = new Quantity(2.0, Arrays.asList("s"), Arrays.asList(""));
	Quantity secondQ = new Quantity(3.0, Arrays.asList("s"), Arrays.asList(""));
	Quantity zeroQ = new Quantity(0, Arrays.asList("s"), Arrays.asList(""));
	Quantity diffUnitsQ = new Quantity(1.0, Arrays.asList("m"), Arrays.asList("s", "s"));

	/** Test mul */
	public void testMul() {
		try{
			firstQ.mul(null);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		Quantity resultQ = firstQ.mul(secondQ);
		assertEquals("operation is executed properly", "6.0 s^2", resultQ.toString());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", "2.0 s", firstQ.toString());
		assertEquals("secondQ is unchanged", "3.0 s", secondQ.toString());
	}
	
	/** Test div */
	public void testDiv() {
		try{
			firstQ.div(null);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		try{
			firstQ.div(zeroQ);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		Quantity resultQ = secondQ.div(firstQ);
		assertEquals("operation is executed properly", "1.5", resultQ.toString());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", "2.0 s", firstQ.toString());
		assertEquals("secondQ is unchanged", "3.0 s", secondQ.toString());
	}
	
	/** Test add */
	public void testAdd() {
		try{
			firstQ.add(null);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		try{
			firstQ.add(diffUnitsQ);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		Quantity resultQ = firstQ.add(secondQ);
		assertEquals("operation is executed properly", "5.0 s", resultQ.toString());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", "2.0 s", firstQ.toString());
		assertEquals("secondQ is unchanged", "3.0 s", secondQ.toString());
	}
	
	/** Test sub */
	public void testSub() {
		try{
			firstQ.sub(null);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		try{
			firstQ.sub(diffUnitsQ);
			fail("Should throw an exception");
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		Quantity resultQ = firstQ.sub(secondQ);
		assertEquals("operation is executed properly", "-1.0 s", resultQ.toString());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", "2.0 s", firstQ.toString());
		assertEquals("secondQ is unchanged", "3.0 s", secondQ.toString());
	}
	
	/** Test negate */
	public void testNegate() {
		Quantity resultQ = firstQ.negate();
		assertEquals("operation is executed properly", "-2.0 s", resultQ.toString());
		//initial quantity should not change
		assertEquals("firstQ is unchanged", "2.0 s", firstQ.toString());
	}
	
	/** Test pow */
	public void testPow() {
		Quantity resultQ = firstQ.pow(3);
		assertEquals("operation is executed properly", "8.0 s^3", resultQ.toString());
		//initial quantity should not change
		assertEquals("firstQ is unchanged", "2.0 s", firstQ.toString());
		
		Quantity resultQ2 = diffUnitsQ.pow(3);
		System.out.println(resultQ2.toString());
		assertEquals("operation is executed properly", "8.0 m^3 s^6", resultQ2.toString());
		assertEquals("diffUnitsQ is unchanged", "2.0 s", diffUnitsQ.toString());
		
		Quantity resultQ3 = firstQ.pow(0);
	}
	
	/** Test equals */
	public void testEquals() {
		Quantity decQ = new Quantity(.111112, Arrays.asList("s"), Arrays.asList(""));
		Quantity isEqualQ = new Quantity(.111113, Arrays.asList("s"), Arrays.asList(""));
		Quantity notEqualQ = new Quantity(.111118, Arrays.asList("s"), Arrays.asList(""));
		
		assertTrue("decQ equals isEqualQ", decQ.equals(isEqualQ));
		assertFalse("decQ does not equal notEqualQ", decQ.equals(notEqualQ));
	}
	
	/** Test hashCode */
	public void testHashCode() {
		Quantity copyQ = new Quantity(firstQ);
		assertTrue(copyQ.hashCode() == firstQ.hashCode());
		assertFalse(copyQ.hashCode() == secondQ.hashCode());
	}
	
	/** Test normalizedUnit */
	public void testNormalizedUnit() {
		Quantity checkQ = Quantity.normalizedUnit("km", db);
		assertEquals("checkQ is 1000 meters", "1000 meter", checkQ.toString());
		
		checkQ = Quantity.normalizedUnit("kph", db);
		assertEquals("checkQ is .27 mps", ".27777... meters per second", checkQ.toString());
		
		checkQ = Quantity.normalizedUnit("smoot", db);
		assertEquals("checkQ is 1 smoot", "1 smoot", checkQ.toString());
	}
	
	/** Test normalize */
	public void testNormalize() {
		Quantity normQ = new Quantity(1, Arrays.asList("k"), Arrays.asList("h"));
		Quantity checkQ = normQ.normalize(db);
		assertEquals("checkQ is .27 mps", ".27777... meters per second", checkQ.toString());
	}
	/* -- END REQUIRED TESTS -- */
	
	
	/* ADDITIONAL TESTS (IF NEEDED) */
	
	
}
