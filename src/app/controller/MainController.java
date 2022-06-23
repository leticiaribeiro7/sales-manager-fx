package app.controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.model.ClientDAO;
import app.model.OrderDAO;
import app.model.ProductDAO;
import app.model.SaleDAO;
import app.model.SupplierDAO;
import app.model.UserDAO;
import constants.Category;
import constants.UnitOfMeasurement;
import constants.paymentMethod;
import entities.Client;
import entities.Employee;
import entities.Ingredient;
import entities.Manager;
import entities.Order;
import entities.Product;
import entities.Sale;
import entities.Supplier;
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//chamar facade aqui
		ClientDAO.addOrEdit(new Client("Cliente1", "123456789", "client1@email.com", "75888556664"));
		ClientDAO.addOrEdit(new Client("Cliente2", "987654321", "client2@email.com", "75224466546"));
		UserDAO.addOrEdit(new Employee("employee", "login1", "123"));
		UserDAO.addOrEdit(new Manager("manager","login2", "345"));
		ProductDAO.addOrEdit(new Product("Carne", 25.00,  LocalDate.of(2023, 5, 3), 50.0));
		
		List<Order> orderList1 = new ArrayList<>();
		List<Integer> productsId = new ArrayList<>();
		List<Integer> productsId2 = new ArrayList<>();
		List<Ingredient> ingredientsList = new ArrayList<>();
		List<Ingredient> ingredientsList2 = new ArrayList<>();
		
		
		LocalDate exp = LocalDate.of(2023, 5, 3);
		LocalDate exp1 = LocalDate.of(2024, 4, 5);
		
		Product product1 = new Product("Macarrão", 50, exp1, 20.0);
		Product product2 = new Product("Molho de tomate", 2, exp, 15.0);
		Product product3 = new Product("Refri", 5, exp, 100);
		
		ProductDAO.addOrEdit(product1); ProductDAO.addOrEdit(product2);
		ProductDAO.addOrEdit(product3);
		
		Ingredient ing1 = new Ingredient(product1, 0.2, UnitOfMeasurement.KG);
		Ingredient ing2 = new Ingredient(product2, 0.3, UnitOfMeasurement.L);
		Ingredient ing3 = new Ingredient(product3, 1, UnitOfMeasurement.L);
		
		// id de produtos na lista de fornecedores
		productsId.add(product1.getId());
		productsId2.add(product2.getId());
		
		
		Supplier sup1 = new Supplier("00001", "supplier1", "Rua C, 10", productsId);
		Supplier supplier3 = new Supplier("00003", "supplier3", "Rua A, 01", productsId2);
		
		SupplierDAO.addOrEdit(sup1); SupplierDAO.addOrEdit(supplier3);
		
		
		ingredientsList.add(ing1); ingredientsList.add(ing2);
		ingredientsList2.add(ing3);
		
		Order order1 = new Order("Espaguete", 18.00, Category.LUNCH, "Descrição", ingredientsList);
		Order order2 = new Order("Refrigerante", 5.00, Category.DRINKS, "Descrição", ingredientsList2);
		
		OrderDAO.addOrEdit(order1);
		OrderDAO.addOrEdit(order2);
		
		orderList1.add(order1); orderList1.add(order2);
		
		Sale sale1 = new Sale(orderList1, paymentMethod.PIX);
		SaleDAO.addOrEdit(sale1);
	}
	
	
}
