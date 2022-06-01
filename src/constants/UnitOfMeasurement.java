package constants;


/**
 * Unidades de medidas aceitas pelo programa na criação de ingredientes
 * @author leticiaribeiro
 *
 */
public enum UnitOfMeasurement {
	KG(0),
	L(1);

	private int index;
	
	UnitOfMeasurement(int i) {
		index = i;
	}
	
	public int getIndex() {
		return this.index;
	}
}
