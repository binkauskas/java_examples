package smallMessageProcessor;

public class MultipleSale extends Sale{
	
	private int quantity;
	public MultipleSale(String type, double value, int quantity){
		super(type, value);
		this.quantity = quantity;
	}
	@Override
	public int getQuantity() {
		return quantity;
	}
	@Override
	public double getTotalValue() {
		return quantity * value;
	}

}
