package smallMessageProcessor;

public class SaleOperationRecord {
	/**
	 * Tuple wrapper for storing Sale operation records
	 * Contains toString method for the sake of this exercise
	 * */
	
	private Operation op;
	private double modValue;
	
	public SaleOperationRecord(Operation operation, double modificationValue){
		this.op = operation;
		this.modValue = modificationValue;
	}
	
	public Operation getOperation(){
		return op;
	}
	
	public double getModificationValue(){
		return modValue;
	}
	
	public String toString(){
		return String.format("%s %.2f", op.name(), modValue);
	}
	
}