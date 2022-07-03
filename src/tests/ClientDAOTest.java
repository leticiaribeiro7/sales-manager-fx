/*******************************************************************************
Autor: Leticia Teixeira Ribeiro dos Santos
Componente Curricular: MI Programação
Concluido em: 03/07/2022
Declaro que este código foi elaborado por mim de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.ClientDAO;
import entities.Client;

class ClientDAOTest {

	static Client client1;
	static Client client2;
	static Client newClient1;
	
	@BeforeAll
	public static void init() {
		client1 =  new Client("client1", "123456789", "email@", "5548854");
		client2 = new Client("client2", "987654321", "email@1", "756456544");
		newClient1 =  new Client("client1", "123456789", "email@2","554854", 1);
	}
	
	@BeforeEach
	public void init2() {
		ClientDAO.list().clear();
	}
	
	@Test
	void createTest() {
		//Teste da mudança no tamanho da lista ao inserir objetos
		ClientDAO.addOrEdit(client1);
		assertEquals(1, ClientDAO.list().size());
		
		ClientDAO.addOrEdit(client2);
		assertEquals(2, client2.getId());	
		
	}
	
	@Test
	void removeTest() {

		assertTrue(ClientDAO.list().isEmpty());
		
		ClientDAO.addOrEdit(client1);
		ClientDAO.addOrEdit(client2);
		
		assertEquals(2, ClientDAO.list().size());
		int removed = ClientDAO.remove(1);
		
		//Testa se id do objeto removido é o mesmo passado por parâmetro
		assertEquals(1, removed);
		//Mudança no tamanho da lista ao remover um objeto
		assertEquals(1, ClientDAO.list().size());
	}

	@Test
	void editTest() {
		
		ClientDAO.addOrEdit(client1);
		ClientDAO.addOrEdit(client2);
		
		//Mudança no email ao passar objeto com id existente
		assertEquals("email@", client1.getEmail());
		ClientDAO.addOrEdit(newClient1);
		assertEquals("email@2", client1.getEmail());
	}

	@Test
	void listTest() {

		ClientDAO.addOrEdit(client1);
		ClientDAO.addOrEdit(client2);
		
		//Testa tamanho da lista e se objetos foram inseridos corretamente
		assertEquals(2, ClientDAO.list().size());
		assertEquals(client1, ClientDAO.list().get(0));
		assertEquals(client2, ClientDAO.list().get(1));

	}
}
