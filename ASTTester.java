/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * 06/03/14
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


import junit.framework.TestCase;

/**
 * Title: class ASTTester
 * Description: JUnit (4) test class for AST
 * @author Elliot Humphrey / Kenichi Katayama
 * @version 1.0
 */
public class ASTTester extends TestCase {

	/* instance variables */
	Map<String, Quantity> env = QuantityDB.getDB();
	
	/* CONSTANTS */ 
	// these are used as the values in the quantities (value + units)
	final double ZERO = 0;
	final double NUM1 = 20.0;
	final double NUM2 = 10.0;
	final double NEG1 = NUM1 * -1;
	final double NEG2 = NUM2 * -1;
	
	
	// Quantities for respective constants ^
	Quantity quant0, quant1, quant2, negQuant1, negQuant2;
	
	// Values for respective constants ^
	AST value0, value1, value2, negValue1, negValue2;
	
	// format example
	// 	 NUM1:quant1:value1 --> CONSTANT:Quantity:(AST)Value
	
	
	
	// ans is the Quantity that is used as the correct Quantity to compare to
	// in assertEquals statements (eval() methods return Quantity objects)
	Quantity ans;
	
	Map<String, Quantity> db;
	List<String> emp = new ArrayList<String>();
 	
	

	/* setUp() method */
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		// setup Quantity objects
		quant0 = new Quantity(ZERO, emp, emp );
		quant1 = new Quantity(NUM1, emp, emp );
		quant2 = new Quantity(NUM2, emp, emp );
		negQuant1 = new Quantity(NEG1, emp, emp );
		negQuant2 = new Quantity(NEG2, emp, emp );
		
		ans = null;
		
		// setup Value AST objects
		value0 = new Value(quant0);
		value1 = new Value(quant1);
		value2 = new Value(quant2);
		negValue1 = new Value(negQuant1);
		negValue2 = new Value(negQuant2);
		
		// custom database (from HW8.pdf)
		db = new HashMap<String, Quantity>();
		
		db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
		db.put("day", new Quantity(24, Arrays.asList("hour"), emp));
		db.put("hour", new Quantity(60, Arrays.asList("minute"), emp));
		db.put("minute", new Quantity(60, Arrays.asList("second"), emp));
		db.put("hertz", new Quantity(1, emp, Arrays.asList("second")));
		db.put("kph", new Quantity(1, Arrays.asList("km"), 
									Arrays.asList("hour")));
		
	}
	
	
	/* REQUIRED: TEST AST EVAL METHODS FOR EACH CLASS  */
	/** Test Product */
	public void testProductEval() {
		AST product = new Product(value1, value2);
		// ans = new Quantity(NUM1 * NUM2, emp, emp );
		ans = quant1.mul(quant2);
		assertEquals("Product.eval() should be " + ans.toString(), 
						ans, product.eval(env));	
		
	}
	
	
	/** Test Quotient */
	public void testQuotientEval() {
		AST quotient = new Quotient(value1, value2);
		// ans = new Quantity(NUM1 / NUM2, emp, emp );
		ans = quant1.div(quant2);
		assertEquals("Quotient.eval() should be " + ans.toString(),
						ans, quotient.eval(env));	
		
	}
	
	
	/** Test Sum */
	public void testSumEval() {
		AST sum = new Sum(value1, value2);
		// ans = new Quantity(NUM1 + NUM2, Arrays.asList(""), Arrays.asList("") );
		ans = quant1.add(quant2);
		assertEquals("Sum.eval() should be " + ans.toString(), 
						ans, sum.eval(env));
		
	}
	
	
	/** Test Difference */
	public void testDifferenceEval() {
		// V1 - V2
		AST diff = new Difference(value1, value2);
		// ans = new Quantity(NUM1 - NUM2, Arrays.asList(""), Arrays.asList(""));
		ans = quant1.sub(quant2);
		assertEquals("Difference.eval() should be " + ans.toString(),
						ans, diff.eval(env));
		
		// V2 - V1
		diff = new Difference(value2, value1);
		// ans = new Quantity(NUM2 - NUM1, Arrays.asList(""), Arrays.asList(""));
		ans = quant2.sub(quant1);
		assertEquals("Difference.eval() should be " + ans.toString(),
						ans, diff.eval(env));
	}
	
	
	/** Test Power */
	public void testPowerEval() {
		// test a range of exponents; start with negative
		int exp = -3;
		
		// test -3, -2, -1, 0, 1, 2, 3 as exponents
		while (exp <= 2) {
		AST power = new Power(value1, exp);
		// ans = new Quantity(Math.pow(NUM1, (double) exp), Arrays.asList(""), Arrays.asList(""));
		ans = quant1.pow(exp);
		assertEquals("Power.eval() should be " + ans.toString(),
						ans, power.eval(env));
		exp++;
		}
	}
	
	
	/** Test Negation */
	public void testNegationEval() {
		
		// Test 0 --> should be -0.0
		AST negation = new Negation(value0);
		// ans = new Quantity(ZERO * -1, Arrays.asList(""), Arrays.asList(""));
		ans = quant0.negate();
		assertEquals("Negation.eval() should be " + ans.toString(), 
						ans, negation.eval(env));
		
		
		// Test positive - NUM1 (value1) ... 
		negation = new Negation(value1);
		// ans = new Quantity(NUM1 * -1, Arrays.asList(""), Arrays.asList(""));
		ans = quant1.negate();
		assertEquals("Negation.eval() should be " + ans.toString(),
						ans, negation.eval(env));
		
		// ... and negate again to get back to NUM1/quant1/value1
		AST ansAST = new Value(ans);
		negation = new Negation(ansAST);
		assertEquals("Negation.eval() should be " + quant1.toString(),
						quant1, negation.eval(env));
		

		// Test negative - NEG1 (negValue1)
		negation = new Negation(negValue1);
		// ans = new Quantity(NEG1 * -1, Arrays.asList(""), Arrays.asList(""));
		ans = negQuant1.negate();
		assertEquals("Negation.eval() should be " + ans.toString(), 
						ans, negation.eval(env));
		
		// ... and negate again to get back to NEG1/negQuant1/negValue1
		ansAST = new Value(ans);
		negation = new Negation(ansAST);
		assertEquals("Negation.eval() should be " + negQuant1.toString(),
						negQuant1, negation.eval(env));	
	}
	
	
	/** Test Normalize */
	public void testNormalizeEval() {
		// smoot key not defined
		assertFalse(db.containsKey("smoot"));
		
		Quantity q1 = new Quantity(10.0, Arrays.asList("smoot"), emp);
		AST normalize = new Normalize(new Value(q1));
		assertEquals(q1, normalize.eval(db));
		
		// this will define smoot
		testDefineEval();
		
		Quantity q2 = new Quantity(10.0, Arrays.asList("smoot"), emp);
		Quantity ans = new Quantity(67.0 * 10.0, Arrays.asList("inches"), emp);
		normalize = new Normalize(new Value(q2));
		assertEquals(ans, normalize.eval(db));
		
	}
	
	
	/** Test Define */
	public void testDefineEval() {
		// smoot key not defined
		assertFalse(db.containsKey("smoot"));
		
		// define smoot
		AST val = new Value(new Quantity(67.0, Arrays.asList("inches"), emp));
		AST define = new Define("smoot", val);
		define.eval(db);
		assertTrue(db.containsKey("smoot"));
		assertEquals("Smoot should be defined as 67.0 inches", 
						new Quantity(67.0, Arrays.asList("inches"), emp),
						db.get("smoot"));
	}
	
	
	/** Test Value */
	public void testValueEval() {
		Quantity quant = new Quantity(3.3, Arrays.asList("m"), Arrays.asList("s"));
		Value val = new Value(new Quantity(3.3, Arrays.asList("m"), Arrays.asList("s")));
		assertEquals("Value.eval() should be 3.3 m s^-1", quant, val.eval(env));
		
	}
	/* -- END REQUIRED TESTS -- */
	
	
	/* ADDITIONAL TESTS (IF NEEDED) */

	
}
