package entities;

public class Client {
	private Integer id;
	private static Integer latestId = 1;  //Guarda último id instanciado. Atributo estático da classe.
	private String name;
	private String cpf;
	private String email;
	private String phone;
	
	
	/**
	 * Construtor
	 * @param name
	 * @param cpf
	 * @param email
	 * @param phone
	 */
	public Client(String name, String cpf, String email, String phone) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phone = phone;
		this.id = latestId;
		Client.latestId++;
	}
	
	/**
	 * Sobrecarga de construtor com id (para edição)
	 * @param name
	 * @param cpf
	 * @param email
	 * @param phone
	 * @param id
	 */
	public Client(String name, String cpf, String email, String phone, int id) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phone = phone;
		this.id = id;
	}
	
	/**
	 * Sobrecarga de construtor vazio (para interface grafica)
	 */
	public Client() {
		this.id = latestId;
		Client.latestId++;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
}
