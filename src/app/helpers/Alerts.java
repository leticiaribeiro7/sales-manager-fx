package app.helpers;

import javafx.scene.control.Alert;

public class Alerts {
	public static void alertEdit() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Selecione um componente");
		alert.show();
	}
	
	public static void alertIncorrectLogin() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Usuário ou senha inválida");
		alert.show();
	}
	
	public static void alertIncorretInput() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Verifique os dados inseridos e tente novamente.");
		alert.show();
	}
	
	public static void confirmation(int id) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(String.format("Produto de id %d inserido", id));
		alert.show();
	}
	
}
