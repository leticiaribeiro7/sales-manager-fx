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

import entities.Supplier;
/**
 * 
 * Classe que gerencia lista de fornecedores. Responsável por criar, editar, remover e listar.
 *
 */
public class SupplierDAO{
	
	private static List<Supplier> suppliers = new ArrayList<>();
	
	/**
	 * Método que verifica se id do objeto já existe na lista ou não.
	 * @param supplier
	 */
	public static void addOrEdit(Supplier supplier) {
		for(Supplier sup : suppliers) {
			if(sup.getId() == supplier.getId()) {
				edit(supplier);
			}
		} create(supplier);
	}
	
	/**
	 * Método que insere um fornecedor na lista de fornecedores.
	 * @param supplier
	 */
	private static void create(Supplier supplier) {
		suppliers.add(supplier);
	}
	
	/**
	 * Método que edita os atributos de um objeto Fornecedor.
	 * @param supplier
	 */
	private static void edit(Supplier supplier) {
		
		for( int i = 0; i < suppliers.size(); i++) {
	        if (suppliers.get(i).getId() == supplier.getId()) {
	            suppliers.get(i).setCnpj(supplier.getCnpj());
	            suppliers.get(i).setName(supplier.getName());
	            suppliers.get(i).setAddress(supplier.getAddress());
	            suppliers.get(i).setProductsId(supplier.getProductsId());
	        }
	        else create(supplier);
		}
		
	}
	
	/**
	 * Método que remove um fornecedor da lista.
	 * @param supplier
	 * @return fornecedor removido
	 */
	public static Supplier remove(int id) {
		for(Supplier sup : suppliers) {
			if(sup.getId() == id) {
				suppliers.remove(id);
				return sup;
			}
		}
		return null; // retorna nulo caso não encontre o id do objeto na lista
	}
	/**
	 * Método que lista os fornecedores.
	 * @return lista de fornecedores.
	 */
	public static List<Supplier> list() {
		return suppliers;
	}
	
	
}

