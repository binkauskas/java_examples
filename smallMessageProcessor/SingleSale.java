package smallMessageProcessor;

public class SingleSale extends Sale {
	
	public SingleSale(String type, double value){
		super(type, value);
	}
	
	public double getTotalValue(){
		return this.value;
	}

	@Override
	public int getQuantity() {
		return 1;
	}
	

}
