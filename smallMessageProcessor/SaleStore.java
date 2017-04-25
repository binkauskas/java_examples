package smallMessageProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SaleStore implements SmallMessageProcessor{
	
	/*Eg. sales = { apples:[sale1,sale2, ...]}*/
	private Map<String, Collection<Sale>> sales;
	
	/*Eg. modificationRecords = { apples:[modification1,modification2, ...]}*/
	private Map<String, Collection<SaleOperationRecord>> modificationRecords;
	
	private int messageCount;
	
	private boolean acceptingMessages;
	
	public SaleStore(){
		messageCount = 0;
		sales = new HashMap<String, Collection<Sale>>();
		modificationRecords = new HashMap<String, Collection<SaleOperationRecord>>();
		acceptingMessages = true;
	};

	
	private void incrementAndCheckCount(){
		//TODO increment message Count and check against 10 and 50
		messageCount += 1;
		if (messageCount % 10 == 0){
			outputSalesReport();
		}
		if (messageCount % 50 == 0){
			System.out.println("\nPausing. No longer accepting messages");
			acceptingMessages = false;
			outputSalesModificationReport();
		}
	}
	
	private void outputSalesReport(){
		System.out.println("\nSalesReport:\nType, NoOfSales, TotalValue");
		for (String key : sales.keySet()){
			int noOfSales = 0;
			double totalValue = 0;
			for (Sale s : sales.get(key)){
				noOfSales += s.getQuantity();
				totalValue += s.getTotalValue();
			}
			System.out.println(String.format("%s %d %.2f", key, noOfSales, totalValue));
		}
	}
	
	private void outputSalesModificationReport(){
		System.out.println("SalesModificationReport:\nType, Operation, ModValue");
		for (String key : modificationRecords.keySet()){
			System.out.println(key);
			for (SaleOperationRecord s : modificationRecords.get(key)){
				System.out.println(String.format("   %s", s.toString()));
			}
		}
	}
	
	private void processSale(Sale sale){
			if (sales.containsKey(sale.getType())){
				sales.get(sale.getType()).add(sale);
			}else{
				ArrayList<Sale> firstEntry = new ArrayList<Sale>();
				firstEntry.add(sale);
				sales.put(sale.getType(), firstEntry);
			}
			incrementAndCheckCount();
	}

	@Override
	public void singleSale(String type, double value) {
		if(acceptingMessages){
			processSale(new SingleSale(type, value));
		}
	}

	@Override
	public void multipleSale(String type, double value, int quantity) {
		if(acceptingMessages){
			processSale(new MultipleSale(type, value, quantity));
		}
		
	}

	@Override
	public void adjustAllSales(String type, Operation op, double modificationValue) {
		if(acceptingMessages){
		// Perform operation on every sale of this type
		if (sales.containsKey(type)){
			Iterator<Sale> salesIterator = sales.get(type).iterator();
			while (salesIterator.hasNext()){
				Sale s = salesIterator.next();
				s.performOperation(op, modificationValue);
			}
		}//TODO find out how operation before sale case is treated
		
		//Record operation
		SaleOperationRecord operation = new SaleOperationRecord(op, modificationValue);
		if (modificationRecords.containsKey(type)){
			modificationRecords.get(type).add(operation);
		}else{
			ArrayList<SaleOperationRecord> firstEntry = new ArrayList<SaleOperationRecord>();
			firstEntry.add(operation);
			modificationRecords.put(type, firstEntry);
		}
		incrementAndCheckCount();
	}
	}
}
