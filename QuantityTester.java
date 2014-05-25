/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * TODO: DATE
 */

import java.util.Arrays;
import java.util.Map;

import junit.framework.TestCase;


/**
 * Title: class QuantityTester
 * Description: JUnit (4) test class for Quantity
 * @author Elliot Humphrey / Kenichi Katayama
 * @version 1.0
 */
public class QuantityTester extends TestCase {

	/* TODO: instance variables */
	
	
	/* TODO: setUp() method */
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}
	
	
	/* TODO: REQUIRED: TEST 3 constructors */
	/** Test 0-arg */	// Creates quantity of value 1 and empty units map
	public void test0ArgConstructor() {
		Quantity noArg = null;
		try{
			noArg = new Quantity();
		} catch (RuntimeException e)
		{
			fail("Should successfully create a new Quantity object");
		}
		assertEquals("Value should default to 1", 1, noArg.getValue());
		assertNotNull("Should have empty units map", noArg.getUnits());
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
		
		assertEquals("Value zeroArg should equal value oneArg", zeroArg.getValue(), zeroArg.getValue());
		assertEquals("Units zeroArg should equal units oneArg", zeroArg.getUnits(), zeroArg.getUnits());
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
		} catch (IllegalArgumentException e)
		{
			//continue should throw an exception
		}
		try{
			threeArg = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s","s"));
		} catch (RuntimeException e)
		{
			fail("Should successfully create a new Quantity object");
		}
		assertEquals("Value threeArg should be 9.8", 9.8, threeArg.getValue());
		assertNotNull("Units threeArg exists", threeArg.getUnits());
	}
	/* -- END CONSTRUCTOR TESTS -- */
	
	
	/*  REQUIRED: TEST Quantity METHODS */
	//create initial Quantities
	Quantity firstQ = new Quantity(2.0, Arrays.asList("s"), Arrays.asList(""));
	Quantity secondQ = new Quantity(3.0, Arrays.asList("s"), Arrays.asList(""));
	Quantity zeroQ = new Quantity(0, Arrays.asList("s"), Arrays.asList(""));
	Quantity diffUnitsQ = new Quantity(1.0, Arrays.asList("m"), Arrays.asList("s"));

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
		assertEquals("operation is executed properly", resultQ.getValue(), 
				firstQ.getValue()*secondQ.getValue());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", 2.0, firstQ.getValue());
		assertEquals("secondQ is unchanged", 3.0, secondQ.getValue());
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
		Quantity resultQ = firstQ.div(secondQ);
		assertEquals("operation is executed properly", resultQ.getValue(), 
				firstQ.getValue()/secondQ.getValue());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", 2.0, firstQ.getValue());
		assertEquals("secondQ is unchanged", 3.0, secondQ.getValue());
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
		assertEquals("operation is executed properly", resultQ.getValue(), 
				firstQ.getValue()+secondQ.getValue());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", 2.0, firstQ.getValue());
		assertEquals("secondQ is unchanged", 3.0, secondQ.getValue());
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
		assertEquals("operation is executed properly", resultQ.getValue(), 
				firstQ.getValue()-secondQ.getValue());
		//neither of the initial quantities should change
		assertEquals("firstQ is unchanged", 2.0, firstQ.getValue());
		assertEquals("secondQ is unchanged", 3.0, secondQ.getValue());
	}
	
	/** Test negate */
	public void testNegate() {
		
	}
	
	/** Test normalizedUnit */
	public void testNormalizedUnit() {
		
	}
	
	/** Test normalize */
	public void testNormalize() {
		
	}
	
	/** Test pow */
	public void testpow() {
		
	}
	
	/** Test equals */
	public void testEquals() {
		
	}
	
	/** Test hashCode */
	public void testHashCode() {
		
	}
	/* -- END REQUIRED TESTS -- */
	
	
	/* ADDITIONAL TESTS (IF NEEDED) */
	
	
}
