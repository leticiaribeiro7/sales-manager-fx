package app.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.helpers.Facade;
import app.helpers.PdfReportGenerator;
import app.model.SaleDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;


public class MainController implements Initializable {
	
	
	@FXML
	Button BtnClient, BtnHome;
	
	@FXML
	BorderPane mainBorderPane;
	
	@FXML
	public void switchScreen(String path) {
		try {
			Parent view = FXMLLoader.load(getClass().getResource(path));
			view.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			mainBorderPane.setCenter(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToSales(ActionEvent event) {
		switchScreen("/app/view/SaleView.fxml");
}

	
	
	@FXML
	public void switchToClient(ActionEvent event) {	
		switchScreen("/app/view/ClientView.fxml");
	}

	
	
	@FXML
	public void switchToUser(ActionEvent event) {	
		switchScreen("/app/view/UserView.fxml");
	}
	
	@FXML
	public void switchToProducts() {
		switchScreen("/app/view/ProductView.fxml");
	}
	
	@FXML
	public void switchToOrders() {
		switchScreen("/app/view/OrderView.fxml");
	}
	
	
	@FXML
	public void switchToSuppliers() {
		switchScreen("/app/view/SupplierView.fxml");
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Facade.createObjs();
	}
	
	
}
