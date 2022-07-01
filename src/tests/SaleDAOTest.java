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

import constants.paymentMethod;
import app.model.SaleDAO;
import entities.Order;
import entities.Sale;

import static tests.OrderDAOTest.*;

class SaleDAOTest {

	static List<Order> orderList1;
	static List<Order> orderList2;
	static Sale sale1;
	static Sale sale2;
	static paymentMethod pm1;
	static paymentMethod pm2;
	

	@BeforeAll
	public static void init() {
		
		orderList1 = new ArrayList<>();
		orderList2 = new ArrayList<>();
		
		orderList1.add(order1);
		orderList2.add(order2);
		
		pm1 = paymentMethod.PIX;
		pm2 = paymentMethod.DEBITO;
		
		sale1 = new Sale(orderList1, pm1);
		sale2 = new Sale(orderList2, pm2);
		
	}
	
	@BeforeEach
	void init2() {
		SaleDAO.list().clear();
	}
	
	@Test
	void createTest() {
		assertTrue(SaleDAO.list().isEmpty());
		SaleDAO.addOrEdit(sale1);
		
		//Testa tamanho da lista após inserção de uma nova venda
		assertEquals(1, SaleDAO.list().size());
	}
	
	
	@Test
	void editTest() {
		SaleDAO.addOrEdit(sale1);
		SaleDAO.addOrEdit(sale2);
		
		//Testa método de pagamento antes da edição
		assertEquals(pm2, sale2.getPaymentMethod());
		
		Sale newSale2 = new Sale(orderList2, pm1, 2);
		SaleDAO.addOrEdit(newSale2);
		
		//Testa se método de pagamento foi alterado
		assertEquals(pm1, sale2.getPaymentMethod());
	}
	
	
	@Test
	void removeTest() {
		SaleDAO.addOrEdit(sale1);
		
		//Testa tamanho da lista antes e depois da remoção
		assertEquals(1, SaleDAO.list().size());
		SaleDAO.remove(1);
		assertTrue(SaleDAO.list().isEmpty());
		
		
	}
	@Test
	void saleValueTest() {
		SaleDAO.addOrEdit(sale2);
		
		//Testa valor da venda de acordo com a lista de pedidos
		assertEquals(5, sale2.getPrice());
		
		
	}
	
	@Test
	void listTest() {
		SaleDAO.addOrEdit(sale1);
		SaleDAO.addOrEdit(sale2);
		
		//Testa se vendas cadastradas na lista são as mesmas que foram instanciadas
		assertEquals(sale1, SaleDAO.list().get(0));
		assertEquals(sale2, SaleDAO.list().get(1));
	}

}
