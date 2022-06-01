package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import app.model.*;
import entities.User;

public class LoginController implements Initializable {

	
    @FXML
    private Button BtnLogin;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldUserName;


    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User user = new User("log1", "123");
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
