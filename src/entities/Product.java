/*******************************************************************************
Autor: Leticia Teixeira Ribeiro dos Santos
Componente Curricular: MI Programação
Concluido em: 11/04/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package entities;

import java.time.LocalDate;

/**
 * 
 * Classe com atributos necessários para criação de produtos.
 *
 */
public class Product {
	private String name;
	private double price;
	private LocalDate expiration;
	private Integer id;
	private double quantity; //para controle de estoque
	private static Integer latestId = 1; //Guarda último id instanciado. Atributo estático da classe.
	
	/**
	 * Construtor da classe.
	 * @param name
	 * @param price
	 * @param expiration
	 */
	public Product(String name, double price, LocalDate expiration, double quantity) {
		this.name = name;
		this.price = price;
		this.expiration = expiration;
		this.id = latestId;
		this.quantity = quantity;
		Product.latestId++;
	}
	/**
	 * Sobrecarga de construtor que recebe id (utilizado para testar edição de objetos)
	 * @param name
	 * @param price
	 * @param expiration
	 * @param id
	 */
	public Product(String name, double price, LocalDate expiration, double quantity, Integer id) {
		this.name = name;
		this.price = price;
		this.expiration = expiration;
		this.id = id;
		this.quantity = quantity;
	}

	
	
	public Product() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public Integer getId() {
		return id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
}
