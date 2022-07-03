package app.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import app.helpers.Alerts;
import app.helpers.Facade;
import app.model.*;
import entities.User;

public class LoginController  {

	
    @FXML
    private Button BtnLogin;

    @FXML
    private PasswordField fieldPassword;

    @FXML
    private TextField fieldUsername;


    /**
     * Cria logins e impede que o usuário faça login antes de preencher todos os campos.
     */
	@FXML
	public void initialize() {
		Facade.createLogin();
		
		BtnLogin.disableProperty().bind(
			    Bindings.isEmpty(fieldUsername.textProperty())
			    .or(Bindings.isEmpty(fieldPassword.textProperty()))
			);
	}
	/**
	 * Trata mecanismo de login.
	 */
	@FXML
	public void login() {
		User user = UserDAO.login(
					fieldUsername.getText().toString(),
					fieldPassword.getText().toString()
				);
		
		if (user == null) {
			Alerts.alertIncorrectLogin();
		}
		else {
			try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("/app/view/MainView.fxml"));
		        Scene scene = new Scene(fxmlLoader.load());
		        Stage stage = new Stage();
		        stage.setResizable(false);
		        stage.setScene(scene);
		        stage.show();
		        
		        // Fecha tela de login depois de login bem sucedido.
		        Stage loginStage = (Stage) BtnLogin.getScene().getWindow();
		        loginStage.close();
		        
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
