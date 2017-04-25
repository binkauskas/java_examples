package smallMessageProcessor;

public interface SmallMessageProcessor {
	
	public void singleSale(String type, double value);
	
	public void multipleSale(String type, double value, int quantity);
	
	public void adjustAllSales(String type, Operation op, double modificationValue);

}
