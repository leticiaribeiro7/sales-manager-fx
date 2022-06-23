package entities;

import constants.UnitOfMeasurement;

/**
 * Classe com atributos necessários para criar ingredientes.
 * @author leticiaribeiro
 *
 */
public class Ingredient {
	Product product;
	double quantity;
	UnitOfMeasurement measurement;
	
	/**
	 * Construtor
	 * @param product
	 * @param quantity
	 * @param measurement
	 */
	public Ingredient(Product product, double quantity, UnitOfMeasurement measurement) {
		this.product = product;
		this.quantity = quantity;
		this.measurement = measurement;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public UnitOfMeasurement getUnit() {
		return this.measurement;
	}
	
}

