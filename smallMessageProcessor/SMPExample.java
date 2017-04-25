package smallMessageProcessor;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SMPExample {

	public static void main(String[] args) {
		//Run some sample JUnit tests
		Result result = JUnitCore.runClasses(SaleOperationTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Were tests successful? " + result.wasSuccessful());

		//Display application and output
		SmallMessageProcessor smp = new SaleStore();
		for (int i = 0; i < 15; i++){
			smp.singleSale("apples", 10+i);
		}
		for (int i = 0; i < 15; i++){
			smp.singleSale("peaches", 10+i);
		}
		for (int i = 0; i < 15; i++){
			smp.multipleSale("apples", 10+i, i);
		}
		//only 5 peaches add operations should be performed as per exercise definition
		for (int i = 0; i < 20; i++){
			smp.adjustAllSales("peaches", Operation.ADD, 7);
		}
	}
}
