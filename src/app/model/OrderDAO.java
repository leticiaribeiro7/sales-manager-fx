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

package app.model;

import java.util.ArrayList;
import java.util.List;

import entities.Order;

/**
 * 
 * Classe que gerencia a lista de pedidos. Responsável por criar, editar, remover e listar.
 *
 */
public class OrderDAO {
	
	private static List<Order> orders = new ArrayList<>();
	
	/**
	 * Método que verifica se id do objeto passado por parâmetro já existe na lista ou não.
	 * @param order
	 */
	public static void addOrEdit(Order order) {
		for (Order ord : orders) {
			if (ord.getId() == order.getId()) {
				edit(order);
				return;
			}
		} create(order);
	}
	
	/**
	 * Método que insere um pedido na lista de pedidos.
	 * @param order
	 */
	private static void create(Order order) {
		orders.add(order);
	}
	
	/**
	 * Método que edita os atributos de um objeto Pedido.
	 * @param order
	 */
	private static void edit(Order order) {
		
		for (int i = 0; i < orders.size(); i++) {
	        if (orders.get(i).getId().equals(order.getId())) {
	            orders.get(i).setName(order.getName());
	            orders.get(i).setPrice(order.getPrice());
	            orders.get(i).setDescription(order.getDescription());
	            orders.get(i).setCategory(order.getCategory());
	            orders.get(i).setIngredients(order.getIngredients());
	            
	        }
		}
	}
	
	/**
	 * Método que remove um pedido da lista de pedidos
	 * @param order
	 * @return pedido removido.
	 */
	public static Order remove(int id) {
		for (Order ord : orders) {
			if (ord.getId() == id) {
				orders.remove(ord);
				return ord;
			}
		}
		return null; // retorna nulo caso não encontre o id do objeto na lista
	}

	/**
	 * Método para listar pedidos
	 * @return lista de pedidos
	 */
	public static List<Order> list() {
		return orders;
	}
	
	
}
