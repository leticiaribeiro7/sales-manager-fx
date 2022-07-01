package app.helpers;

import javafx.scene.control.Alert;

public class Alerts {
	public static void alertEdit() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText("Selecione um componente");
		alert.show();
	}
}
