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

/**
 * 
 * Classe com atributos necessários para criação de fornecedores.
 *
 */
public class Supplier {
	private String cnpj;
	private String name;
	private String address;
	private List<Integer> productsId = new ArrayList<>();
	private Integer id;
	private static Integer latestId = 1;
	
	
	/**
	 * Construtor da classe.
	 * @param cnpj
	 * @param name
	 * @param address
	 * @param productsId
	 */
	public Supplier(String cnpj, String name, String address, List<Integer> productsId) {
		this.cnpj = cnpj;
		this.name = name;
		this.address = address;
		this.id = latestId;
		this.productsId = productsId;
		Supplier.latestId++;
	}

	
	/**
	 * Sobrecarga de construtor que recebe id (utilizado para testar edição de objetos).
	 * @param cnpj
	 * @param name
	 * @param address
	 * @param productsId
	 * @param id
	 */
	public Supplier(String cnpj, String name, String address, List<Integer> productsId, Integer id) {
		this.cnpj = cnpj;
		this.name = name;
		this.address = address;
		this.productsId = productsId;
		this.id = id;
	}



	public Supplier() {
		this.id = latestId;
		Supplier.latestId++;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public List<Integer> getProductsId() {
		return productsId;
	}
	
	public void setProductsId(List<Integer> productsId) {
		this.productsId = productsId;
	}
}
