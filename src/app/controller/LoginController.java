package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import app.model.*;
import entities.User;

public class LoginController  {

	
    @FXML
    private Button BtnLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldUserName;


    
	@FXML
	public void initialize() {
		User user = new User("user1", "log1", "123");
		UserDAO.addOrEdit(user);
	}
	
	@FXML
	public void login() {
		User u = UserDAO.login(fieldUserName.getText().toString(), fieldPassword.getText().toString());
		if (u == null) {
			System.out.println("Inexistente");
		}
		else {
			System.out.println("Tudo certo");
		}
	}
}
