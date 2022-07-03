
module SalesManagerFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
	requires itextpdf;
	requires java.desktop;
	requires org.junit.jupiter.api;
	requires org.junit.platform.suite.api;
	
	opens app to javafx.graphics, javafx.fxml;
	opens entities to javafx.base;
	exports app.controller to javafx.fxml;
	opens app.controller to javafx.fxml;
	
}

