module SalesManagerFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
	
	opens app to javafx.graphics, javafx.fxml;
	opens entities to javafx.base;
	exports app.controller to javafx.fxml;
	opens app.controller to javafx.fxml;
	
}
