package constants;


/**
 * Categorias aceitas na criação de pratos.
 *
 *
 */
public enum Category {
	DRINKS(0),
	LUNCH(1),
	DINNER(2),
	DESERT(3),
	VEGETARIAN(4);
	
	private int index;
	
	Category(int i) {
		index = i;
	}

	
	public int getIndex() {
		return this.index;
	}

}