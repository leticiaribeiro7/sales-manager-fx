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
/**
 * 
 * Subclasse Employee que herda da superclasse User
 *
 */
public class Employee extends User {

	/**
	 * Construtor da classe.
	 * @param login
	 * @param password
	 */
	public Employee(String login, String password) {
		super(login, password);
	}
	
	/**
	 * Sobrecarga de construtor que herda da superclasse User
	 * @param login
	 * @param password
	 * @param id
	 */
	public Employee(String login, String password, int id) {
		super(login, password, id);
	}
	
	public Integer getId() {
		return id;
	}

}
