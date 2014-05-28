/* CSE 12 Homework 8
 * Elliot Humphrey / Kenichi Katayama
 * cs12edl / cs12edu
 * A09307269 / A09244911
 * Section A00 (for both)
 * TODO: DATE
 */


import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Title: class UnicalcTester
 * Description: JUnit (4) test class for Unicalc
 * @author Elliot Humphrey / Kenichi Katayama
 * @version 1.0
 */
public class UnicalcTester extends TestCase {

	/* TODO: instance variables */
	Map<String,Quantity> env;
	Unicalc unicalc;
	
	List<String> emp = new ArrayList<String>();
	AST tree;
	
	
	/* TODO: setUp() method */
	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		unicalc = new Unicalc();
		
		
		env = QuantityDB.getDB();
		
		
	}
	
	
	/* TODO: REQUIRED: TEST Unicalc METHODS */
	/** Test S */
	public void testS() {
		// def W L
		
		String input = "def smoot 67.0 inches";
	
		
		
	}
	
	/** Test L */
	public void testL() {
		
	}
	
	/** Test E */
	public void testE() {
		
	}
	
	/** Test P */
	public void testP() {
		// P := K * P | K / P | K
		String input = "- 5.0 inches * 4.0 / 2.0 inches ^ 2";
		unicalc.tokenize(input);
		// System.out.println(unicalc.P());
		// Product(Negation(Product(Value(5.0),Value(1.0 inches))),Quotient(Value(4.0),Product(Value(2.0),Power(Value(1.0 inches),2))))
		AST t1 = new Value(new Quantity(1.0, Arrays.asList("inches"), emp));
		AST t2 = new Power(t1, 2);
		AST t3 = new Value(new Quantity(2.0, emp, emp));
		AST t4 = new Product(t3, t2);
		AST t5 = new Value(new Quantity(4.0, emp, emp));
		AST t6 = new Quotient(t5, t4);
		AST t7 = new Value(new Quantity(5.0, emp, emp));
		AST t8 = new Value(new Quantity(1.0, Arrays.asList("inches"), emp));
		AST t9 = new Product(t7, t8);
		AST t10 = new Negation(t9);
		AST tree = new Product(t10, t6);
		assertTrue(tree.equals(unicalc.P()));
		
		
	}
	
	/** Test K */
	public void testK() {
		// K := - K | Q
		String input = "- 5.0 inches ^ 2";
		unicalc.tokenize(input);
		
		//System.out.println(unicalc.K());
		AST t1 = new Value(new Quantity(1.0, Arrays.asList("inches"), emp) );
		AST t2 = new Power(t1, 2);
		AST t3 = new Value(new Quantity(5.0, emp, emp) );
		AST t4 = new Product(t3, t2);
		AST tree = new Negation(t4);
		assertTrue(tree.equals(unicalc.K()));
	}
	
	/** Test Q */
	public void testQ() {
		// Q:= R | R Q
		
		String input = "5.0 inches ^ 2";
		unicalc.tokenize(input);
		
		// System.out.println(unicalc.Q());
		AST tree = new Product(new Value( new Quantity(5.0, emp, emp) ), 
								new Power( new Value( new Quantity(1.0, Arrays.asList("inches"), emp)), 2)
					);
		assertTrue(tree.equals(unicalc.Q()));
		
		
	}
	
	/** Test R */
	public void testR() {
//		// assertTrue(env.containsKey("inches"));
//		
		
		String input = "5.0 inches ^ 2";
		unicalc.tokenize(input);
	
		// R := V
		tree = new Value(new Quantity(5.0, emp, emp));
		//System.out.println(unicalc.R());
		assertTrue(tree.equals(unicalc.R()));
		
		// R := V ^ J
		//System.out.println(unicalc.R() );
		tree = new Power(new Value(new Quantity(1.0, Arrays.asList("inches"), emp)), 2);
		assertTrue(tree.equals(unicalc.R()));

	}
	/* -- END REQUIRED TESTS -- */
	

	/* ADDITIONAL TESTS IF NEEDED */ 
	
}
