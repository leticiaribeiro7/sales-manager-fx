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
import java.util.ArrayList;
import java.util.List;

import constants.Category;

/**
 * 
 * Classe com atributos necessários para criação de pedidos.
 *
 */
public class Order {
	
	private String name;
	private Double price;
	private String description;
	private Category category;
	private List<Ingredient> ingredients = new ArrayList<>();
	private static Integer latestId = 1;  //Guarda último id instanciado. Atributo estático da classe.
	private Integer id;
	
	
	
	public Order(String name, Double price, Category category, String description, List<Ingredient> ingredients) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.ingredients = ingredients;
		this.id = latestId;
		Order.latestId++;
	}
	
	
	/**
	 * Sobrecarga de construtor que recebe id (utilizado para testar edição de objetos)
	 * @param name
	 * @param price
	 * @param description
	 * @param category
	 * @param products
	 * @param id
	 */
	public Order(String name, Double price, Category category, List<Ingredient> ingredients, Integer id) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.ingredients = ingredients;
		this.id = id;
	}


	public Order() {
		this.id = latestId;
		Order.latestId++;
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
