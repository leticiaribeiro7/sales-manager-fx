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

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.SupplierDAO;

import static tests.ProductDAOTest.*;
import entities.Supplier;

class SupplierDAOTest {

	static Supplier supplier1;
	static Supplier supplier2;
	static Supplier supplier3;
	
	static List<Integer> productsId1 = new ArrayList<>();
	static List<Integer> productsId2 = new ArrayList<>();
	static List<Integer> productsId3 = new ArrayList<>();
	
	SupplierDAO manager;
	
	@BeforeAll
	public static void init() {
		
		//Inserção de id's em lista de produtos fornecidos por cada fornecedor
		productsId1.add(product1.getId()); productsId1.add(product3.getId());
		
		productsId2.add(product1.getId());
		
		productsId3.add(product2.getId());

		supplier1 = new Supplier("00001", "supplier1", "Rua C, 10", productsId1);
		supplier2 = new Supplier("00002", "supplier2", "Rua D, 20", productsId2);
		supplier3 = new Supplier("00003", "supplier3", "Rua A, 01", productsId3);
	}
	
	
	@BeforeEach
	void init2() {
		SupplierDAO.list().clear();
	}
	
	@Test
	void createTest() {
		SupplierDAO.addOrEdit(supplier1);
		SupplierDAO.addOrEdit(supplier2);
		
		//Testa tamanho da lista após inserção de fornecedor
		assertEquals(2, SupplierDAO.list().size());
		//Testa se id foi atribuido corretamente
		assertEquals(2, supplier2.getId());
		
	}
	
	@Test
	void editTest() {
		
		SupplierDAO.addOrEdit(supplier3);
		assertEquals("Rua A, 01", supplier3.getAddress());
		
		//Objeto criado com id existente para testar método edit
		Supplier newSupplier3 = new Supplier("00003", "supplier3", "Rua B, 03", productsId3, 3);
		SupplierDAO.addOrEdit(newSupplier3);
		assertEquals("Rua B, 03", supplier3.getAddress());

	}
	@Test
	void removeTest() {
		
		assertTrue(SupplierDAO.list().isEmpty());
		
		SupplierDAO.addOrEdit(supplier1);
		SupplierDAO.addOrEdit(supplier2);
		SupplierDAO.addOrEdit(supplier3);
		Supplier removed = SupplierDAO.remove(2);
		
		//Testa se objeto removido é o mesmo passado como argumento do metodo remove
		assertEquals(supplier2, removed);
		
	}
	
	@Test
	void listTest() {
		
		SupplierDAO.addOrEdit(supplier1);
		SupplierDAO.addOrEdit(supplier3);
		assertFalse(SupplierDAO.list().isEmpty());
		
		//Testa se objetos instanciados são os mesmos inseridos na lista
		assertEquals(supplier1, SupplierDAO.list().get(0));
		assertEquals(supplier3, SupplierDAO.list().get(1));
		
	}
	

}
