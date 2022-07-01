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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.UserDAO;
import entities.User;

class UserDAOTest {

	static User user1;
	static User user2;
	static User newUser1;
	
	@BeforeAll
	public static void init() {
		user1 =  new User("user1", "login1", "123");
		user2 = new User("user2", "login2", "987");
		newUser1 =  new User("user1", "login1", "544", 1);
	}
	
	@BeforeEach
	public void init2() {
		UserDAO.list().clear();
	}
	
	@Test
	void createTest() {
		//Teste da mudança no tamanho da lista ao inserir objetos
		UserDAO.addOrEdit(user1);
		assertEquals(1, UserDAO.list().size());
		
		UserDAO.addOrEdit(user2);
		assertEquals(2, user2.getId());	
		
	}
	
	@Test
	void removeTest() {

		assertTrue(UserDAO.list().isEmpty());
		
		UserDAO.addOrEdit(user1);
		UserDAO.addOrEdit(user2);
		
		assertEquals(2, UserDAO.list().size());
		int removed = UserDAO.remove(1);
		
		//Testa se id do objeto removido é o mesmo passado por parâmetro
		assertEquals(1, removed);
		//Mudança no tamanho da lista ao remover um objeto
		assertEquals(1, UserDAO.list().size());
	}

	@Test
	void editTest() {
		
		UserDAO.addOrEdit(user1);
		UserDAO.addOrEdit(user2);
		
		//Mudança na senha ao passar objeto com id existente
		assertEquals("123", user1.getPassword());
		UserDAO.addOrEdit(newUser1);
		assertEquals("544", user1.getPassword());
	}

	@Test
	void listTest() {

		UserDAO.addOrEdit(user1);
		UserDAO.addOrEdit(user2);
		
		//Testa tamanho da lista e se objetos foram inseridos corretamente
		assertEquals(2, UserDAO.list().size());
		assertEquals(user1, UserDAO.list().get(0));
		assertEquals(user2, UserDAO.list().get(1));

	}
}
