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

import entities.Product;

/**
 * 
 * Classe que gerencia a lista de produtos. Responsável por criar, editar, remover e listar.
 *
 */
public class ProductDAO {
	
	private static List<Product> products = new ArrayList<>();
	
	/**
	 * 
	 * Método que verifica se id do objeto passado por parâmetro já existe na lista ou não.
	 * @param product
	 */
	public static void addOrEdit(Product product) {
		for(Product prod : products) {
			if(prod.getId() == product.getId()) {
				edit(product);
				return;
			}
		} create(product);
	}
	
	
	/**
	 * Método que insere um produto na lista de produtos.
	 * @param product
	 */
	private static void create(Product product) {
		 products.add(product);
	}
	
	/**
	 * Método que edita atributos de um objeto Produto.
	 * @param product
	 */
	private static void edit(Product product) {
		
		for( int i = 0; i < products.size(); i++) {
	        if (products.get(i).getId().equals(product.getId())) {
	            products.get(i).setName(product.getName());
	            products.get(i).setPrice(product.getPrice());
	            products.get(i).setExpiration(product.getExpiration());
	        }
		}	
	}
	
	/**
	 * Método que remove um produto da lista.
	 * @param product
	 * @return produto removido.
	 */
	public static Product remove(Integer id) {
		for(Product prod : products) {
			if(prod.getId() == id) {
				products.remove(prod);
				return prod;
			}
		}
		return null; // retorna nulo caso não encontre o id do objeto na lista
	}

	/**
	 * Método que lista os produtos.
	 * @return lista de produtos.
	 */
	public static List<Product> list() {
		return products;
	}
	
	
	
}
