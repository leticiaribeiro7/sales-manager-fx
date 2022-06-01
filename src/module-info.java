module SalesManagerFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens app to javafx.graphics, javafx.fxml;
	opens entities to javafx.base;
	exports app.controller to javafx.fxml;
	opens app.controller to javafx.fxml;
//	exports application.controller to javafx.fxml; opens application.controller to javafx.fxml;
	
}
