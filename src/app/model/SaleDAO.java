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

import entities.Sale;

/**
 * 
 * Classe que gerencia a lista de vendas. Responsável por criar, editar, remover e listar.
 *
 */
public class SaleDAO {
	private static List<Sale> sales = new ArrayList<>();
	
	/**
	 * Método que verifica se id do objeto passado por parâmetro já existe na lista ou não.
	 * @param sale
	 */
	public static void addOrEdit(Sale sale) {
		for(Sale s : sales) {
			if(s.getId() == sale.getId()) {
				edit(sale);
				return;
			}
		} create(sale);
	}
	
	/**
	 * Método que insere uma venda na lista de vendas.
	 * @param sale
	 */
	private static void create(Sale sale) {
		sales.add(sale);
	}
	
	/**
	 * Método que edita os atributos de um objeto Venda.
	 * @param sale
	 */
	private static void edit(Sale sale) {
		
		for( int i = 0; i < sales.size(); i++) {
	        if (sales.get(i).getId() == sale.getId()) {
	            sales.get(i).setDate(sale.getDate());
	            sales.get(i).setOrders(sale.getOrders());
	            sales.get(i).setPaymentMethod(sale.getPaymentMethod());
	        }
	        else create(sale);
		}
	
}
	/**
	 * Método que remove uma venda da lista.
	 * @param id
	 */
	public static Sale remove(int id) {
		for(Sale sale : sales) {
			if(sale.getId() == id) {
				sales.remove(sale);
				return sale;
			}
		}
		return null; // retorna nulo caso não encontre o id do objeto na lista
	}
	/**
	 * Método que lista as vendas.
	 * @return lista de vendas.
	 */
	public static List<Sale> list() {
		return sales;
	}
	
	
	
}

