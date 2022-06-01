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
 * Classe geral com atributos necessários para criação de usuários
 * que é herdada pelas especializações Employee e Manager.
 *
 */
public class User {
	protected String login;
	protected String password;
	protected Integer id;
	private static Integer latestID = 1;
	
	/**
	 * Construtor da classe.
	 * @param login
	 * @param password
	 */
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.id = latestID;
		latestID++;
	}
	/**
	 * Sobrecarga de construtor que recebe id (utilizado para testar edição de objetos).
	 * @param login
	 * @param password
	 * @param id
	 */
	public User(String login, String password, int id) {
		this.login = login;
		this.password = password;
		this.id = id;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String senha) {
		this.password = senha;
	}
	public Integer getId() {
		return id;
	}
	
}