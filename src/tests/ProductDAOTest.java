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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.ProductDAO;
import entities.Product;

class ProductDAOTest {
	
	static LocalDate exp = LocalDate.of(2023, 5, 3);
	
	//Declaração e instância de objetos estáticos para uso em outras classes de teste
	static Product product1 = new Product("Carne", 50, exp, 50.0);
	static Product product2 = new Product("Macarrão", 4, exp, 40.0);
	static Product product3 = new Product("Tomate", 0.60, exp, 20.0);
	static Product product4 = new Product("Coca-cola", 3, exp, 50.0);
	
	
	@BeforeEach
	public  void init() {
		ProductDAO.list().clear();
	}
	
	
	@Test
	void createTest() {
		
		ProductDAO.addOrEdit(product1);
		ProductDAO.addOrEdit(product2);
		ProductDAO.addOrEdit(product3);
		
		LocalDate hj = LocalDate.now();
		long diff = ChronoUnit.DAYS.between(exp, hj);
		
		//Testa tamanho da lista após inserção de produtos
		assertEquals(3, ProductDAO.list().size());
		
	}
	
	@Test
	void editTest() {
		
		ProductDAO.addOrEdit(product1);
		ProductDAO.addOrEdit(product2);
		
		//Testa preço do produto antes e depois da edição
		assertEquals(4, product2.getPrice());
		
		//Produto criado com id já existente na lista
		Product newProduct2 = new Product("Macarrão", 5, exp, 50.0, 2);
		ProductDAO.addOrEdit(newProduct2);
		
		assertEquals(5, product2.getPrice());
		
	}
	
	@Test
	void removeTest() {
		assertTrue(ProductDAO.list().isEmpty());
		
		ProductDAO.addOrEdit(product1);
		ProductDAO.addOrEdit(product2);
		ProductDAO.addOrEdit(product3);
		
		assertEquals(3, ProductDAO.list().size());
		
		//Testa se houve alteração no tamanho da lista após remoção
		Product removed = ProductDAO.remove(2);
		assertEquals(product2, removed);
		assertEquals(2, ProductDAO.list().size());
	}
	
	@Test
	void listTest() {
		
		ProductDAO.addOrEdit(product1);
		ProductDAO.addOrEdit(product3);
		ProductDAO.addOrEdit(product2);
		
		//Testa se produtos cadastrados na lista são os mesmos que foram instanciados
		assertEquals(product1, ProductDAO.list().get(0));
		assertEquals(product3, ProductDAO.list().get(1));
		assertEquals(product2, ProductDAO.list().get(2));


	}

}
