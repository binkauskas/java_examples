package smallMessageProcessor;

public abstract class Sale {
	protected String type;
	//could probably use int here as value is usually expressed in pennies
	protected double value;
	
	public Sale(String type, double value){
		this.type = type;
		this.value = value;
	}
	
	public abstract int getQuantity();
	
	public abstract double getTotalValue();
	
	public String getType(){
		return type;
	}
	
	public double getValue(){
		return value;
	}
	
	public void performOperation(Operation op, double modificationValue){
		switch (op){
			case ADD:
				value += modificationValue;
				break;
			case SUBTRACT:
				value -= modificationValue;
				break;
			case MULTIPLY:
				value *= modificationValue;
				break;
		}
	}
}
