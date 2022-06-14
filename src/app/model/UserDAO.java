package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.User;

public class UserDAO {
	
	private static List<User> users = new ArrayList<>();
	
	/**
	 * Método que verifica se id do objeto passado por parâmetro já existe na lista ou não.
	 * @param user
	 */
	public static void addOrEdit(User user) {
		for(User u : users) {
			if(u.getId() == user.getId()) {
				edit(user);
				return;
			}
		} create(user);
	}
	
	
	/** Método que insere objeto usuário na lista de usuários.
	 * @param user
	 */
	private static void create(User user) {
		users.add(user);
	}
	
	/**
	 * Método que edita atributos de um objeto User.
	 * @param user
	 */
	private static void edit(User user) {
		
		for ( int i = 0; i < users.size(); i++) {
	        if (users.get(i).getId() == user.getId()) {
	            users.get(i).setPassword(user.getPassword());
	            users.get(i).setLogin(user.getLogin());
	        }
		}

	}

	/**
	 * Método para remover um usuário da lista
	 * @param id
	 * @return id do usuário removido
	 */
	public static Integer remove(int id) {
		boolean removed = users.removeIf(u -> u.getId() == id);
		if (removed) return id;
		return null;  // retorna nulo caso não encontre o id do objeto na lista
	}

	/**
	 * Método para listar usuários
	 * @return lista de usuários
	 */
	public static List<User> list() {
		return users;
	}
	
	public static User login(String login, String password) {
		for (User u : users) {
			if (u.getLogin().equals(login) && 
				u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	
}
