package app.model;

import java.util.ArrayList;
import java.util.List;

import entities.Client;

public class ClientDAO {
	private static List<Client> clients = new ArrayList<>();
	
	/**
	 * Método que verifica se id do objeto passado por parâmetro já existe na lista ou não.
	 * @param client
	 */
	public static void addOrEdit(Client client) {
		for(Client c : clients) {
			if(c.getId() == client.getId()) {
				edit(client);
				return;
			}
		} create(client);
	}
	
	
	/** Método que insere objeto cliente na lista de clientes.
	 * @param client
	 */
	private static void create(Client client) {
		clients.add(client);
	}
	
	/**
	 * Método que edita atributos de um objeto Client.
	 * @param client
	 */
	private static void edit(Client client) {
		
		for ( int i = 0; i < clients.size(); i++) {
	        if (clients.get(i).getId() == client.getId()) {
	        	
	            clients.get(i).setName(client.getName());
	            clients.get(i).setEmail(client.getEmail());
	            clients.get(i).setPhone(client.getPhone());
	        }
		}

	}

	/**
	 * Método para remover um cliente da lista
	 * @param id
	 * @return id do cliente removido
	 */
	public static Integer remove(int id) {
		boolean removed = clients.removeIf(c -> c.getId() == id);
		if (removed) return id;
		return null;  // retorna nulo caso não encontre o id do objeto na lista
	}

	/**
	 * Método para listar clientes
	 * @return lista de clientes
	 */
	public static List<Client> list() {
		return clients;
	}

}
