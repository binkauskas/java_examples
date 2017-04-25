package smallMessageProcessor;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SaleOperationTest {

	private double delta = Double.MIN_VALUE;
	Sale singleSale;

	@Before
	public void setUp() throws Exception {
		singleSale = new SingleSale("apples", 20);
	}

	@After
	public void tearDown() throws Exception {
		singleSale = null;
	}
	
	@Test
	public void testAdd(){
		singleSale.performOperation(Operation.ADD, 20);
		assertEquals(40, singleSale.getValue(), delta);
	}
	
	@Test
	public void testSub(){
		singleSale.performOperation(Operation.SUBTRACT, 2);
		assertEquals(18, singleSale.getValue(), delta);
	}
	
	@Test
	public void testMultiply(){
		singleSale.performOperation(Operation.MULTIPLY, 0);
		assertEquals(0, singleSale.getValue(), delta);
	}


}
