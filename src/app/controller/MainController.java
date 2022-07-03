package app.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.helpers.Facade;
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
	
	
	/**
	 * Inicializa dados através da classe Façade
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Facade.createObjs();
	}
	
	/**
	 * Troca a borderPane recebendo o caminho da view.
	 * @param path
	 */
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
	/**
	 * Troca para tela de vendas.
	 */
	@FXML
	public void switchToSales() {
		switchScreen("/app/view/SaleView.fxml");
	}
	
	/**
	 * Troca para tela de clientes.
	 */
	@FXML
	public void switchToClient() {	
		switchScreen("/app/view/ClientView.fxml");
	}
	
	/**
	 * Troca para tela de usuários
	 */
	@FXML
	public void switchToUser() {	
		switchScreen("/app/view/UserView.fxml");
	}
	
	/**
	 * Troca para tela de produtos
	 */
	@FXML
	public void switchToProducts() {
		switchScreen("/app/view/ProductView.fxml");
	}
	
	/**
	 * Troca para tela de cardápio
	 */
	@FXML
	public void switchToOrders() {
		switchScreen("/app/view/OrderView.fxml");
	}
	
	/**
	 * Troca para tela de fornecedores
	 */
	@FXML
	public void switchToSuppliers() {
		switchScreen("/app/view/SupplierView.fxml");
	}
	/**
	 * Troca para tela de relatórios
	 */
	@FXML
	public void switchToReports() {
		switchScreen("/app/view/Reports.fxml");
	}


	
	
}
