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

import constants.Category;
import constants.UnitOfMeasurement;
import app.model.OrderDAO;
import entities.Ingredient;
import entities.Order;

import static tests.ProductDAOTest.*;


class OrderDAOTest {
	
	static List<Ingredient> ingredientsList1;
	static List<Ingredient> ingredientsList2;

	
	//Declaração e instância de objetos estáticos para uso em outras classes de teste
	static Order order1 = new Order("Espaguete", 18.00, Category.LUNCH, "descrição", ingredientsList1);
	static Order order2 = new Order("Coca-Cola", 5.00, Category.DRINKS, "descrição", ingredientsList2);
	
	@BeforeAll
	public static void init() {
		
		Ingredient ing1 = new Ingredient(product2, 0.02, UnitOfMeasurement.KG);
		Ingredient ing2 = new Ingredient(product1, 0.03, UnitOfMeasurement.KG);
		
		ingredientsList1 = new ArrayList<>();
		ingredientsList2 = new ArrayList<>();
		
		ingredientsList1.add(ing1);
		ingredientsList1.add(ing2);
		
	}
	
	@BeforeEach
	void init2() {
		OrderDAO.list().clear();
	}
	
	
	@Test
	void createTest() {
		
		OrderDAO.addOrEdit(order1);
		OrderDAO.addOrEdit(order2);
		
		//Testa tamanho da lista após inserção de pedidos
		assertEquals(2, OrderDAO.list().size());
		
	}
	
	@Test
	void editTest() {

		OrderDAO.addOrEdit(order1);
		OrderDAO.addOrEdit(order2);
		
		assertEquals(18, OrderDAO.list().get(0).getPrice());
		
		//Objeto criado com id já existente na lista para testar edição
		Order newOrder2 = new Order("Espaguete", 18.00, Category.LUNCH, ingredientsList1, 1);
		OrderDAO.addOrEdit(newOrder2);
		
		//Testa se valor do pedido foi alterado
		assertEquals(18.00, OrderDAO.list().get(0).getPrice());
		
	}
	
	@Test
	void removeTest() {

		assertTrue(OrderDAO.list().isEmpty());
		
		OrderDAO.addOrEdit(order1);
		OrderDAO.addOrEdit(order2);
		assertEquals(2, OrderDAO.list().size());
		
		Order removed = OrderDAO.remove(2);
		
		//Testa se objeto removido foi o mesmo passado como argumento para método remove
		assertEquals(order2, removed);
	}
	
	@Test
	void listTest() {
		
		OrderDAO.addOrEdit(order1);
		OrderDAO.addOrEdit(order2);
		
		//Testa se vendas cadastradas na lista são as mesmas que foram instanciadas
		assertEquals(order1, OrderDAO.list().get(0));
		assertEquals(order2, OrderDAO.list().get(1));

	}


}
