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
import java.util.ArrayList;
import java.util.List;

import constants.paymentMethod;

/**
 * 
 * Classe com atributos necessários para criação de vendas.
 * 
 */
public class Sale {
	
	private LocalDate date;
	private List<Order> orders = new ArrayList<>();
	private double price;
	private paymentMethod paymentMethod;
	private Client client;
	private Integer id;
	private static Integer latestId = 1;
	
	/**
	 * Construtor da classe
	 * @param orders
	 * @param paymentMethod
	 */
	public Sale(List<Order> orders, paymentMethod paymentMethod) {
		this.date = LocalDate.now();
		this.orders = orders;
		this.setPrice();  // O preço total é atribuído automaticamente de acordo a soma dos pedidos.
		this.paymentMethod = paymentMethod;
		this.id = latestId;
		Sale.latestId++;
	}
	
	/**
	 * Sobrecarga de construtor que recebe id (utilizado para testar edição de objetos)
	 * @param orders
	 * @param paymentMethod
	 * @param id
	 */
	public Sale(List<Order> orders, paymentMethod paymentMethod, Integer id) {
		this.orders = orders;
		this.paymentMethod = paymentMethod;
		this.id = id;
		this.setPrice();
		
	}

	/**
	 * Sobrecarga de construtor que não recebe parâmetros, para uso na interface gráfica
	 */
	public Sale() {
		this.id = latestId;
		Sale.latestId++;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice() {
		this.price = 0.0; // Zerando valor do atributo antes de calcular o preço
		for (Order o : orders) {
			this.price += o.getPrice();
		}
	}
	
	public paymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(paymentMethod pm) {
		this.paymentMethod = pm;
	}
	
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List <Order> orders) {
		this.orders = orders;
	}
	
	public Integer getId() {
		return this.id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * Atualiza o inventário de produtos a cada venda realizada.
	 */
	public void updateInventory() {
		for (Order o : orders) {
			for (Ingredient ing : o.getIngredients()) {
				
				Product product = ing.getProduct();
				Double qtdIngredient = ing.getQuantity();
				Double qtdFinal = product.getQuantity() - qtdIngredient;
				
				product.setQuantity(qtdFinal);
			}
		}
	}


}
