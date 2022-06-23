package app.controller;

import java.io.IOException;
import java.time.LocalDate;

import app.model.OrderDAO;
import constants.Category;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderController {
	@FXML
    private Button buttonEdit;

    @FXML
    private Button buttonNovo;

    @FXML
    private Button buttonRemove;

    @FXML
    private TableColumn<Order, Category> category;

    @FXML
    private TableColumn<Order, Integer> id;

    @FXML
    private TableColumn<Order, String> name;

    @FXML
    private TableColumn<Order, Double> price;
    
    @FXML
    private TableView<Order> ordersTable;
    
	@FXML
	Stage dialogStage;
	
	private ObservableList<Order> obsList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		loadOrdersData();
		
	}
	
	// Carrega dados inicializados
	public ObservableList<Order> loadOrdersData() {
		
		id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		category.setCellValueFactory(new PropertyValueFactory<Order, Category>("category"));
		
		obsList = FXCollections.observableArrayList(OrderDAO.list());
		
		ordersTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	// Quando clica no botão adicionar
	public void switchToAddOrder() throws IOException{
		
		Order order = new Order();
		showAddOrEditOrder(order);
		loadOrdersData();
		
	}
	
	// Quando clica no botão editar
	public void switchToEditOrder() throws IOException {
		Order order = ordersTable.getSelectionModel().getSelectedItem();
		if (order != null) {
			showAddOrEditOrder(order);
			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Selecione um prato");
			alert.show();
		}
	}
	
	//criar classe helper alerta e usar em tds entidades*************
    public void removeOrder() {
    	Order order = ordersTable.getSelectionModel().getSelectedItem();
		if (order != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> OrderDAO.remove(order.getId()));
			loadOrdersData();
			
		}
    }
	
	
	// Mesma caixa de diálogo para adicionar e editar
	public void showAddOrEditOrder(Order order) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AddOrEditOrderController.class.getResource("/app/view/AddOrder.fxml"));
		AnchorPane view = loader.load();
		dialogStage = new Stage();
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		AddOrEditOrderController controller = (AddOrEditOrderController) loader.getController();
		
		controller.setDialogStage(dialogStage);
		controller.setOrder(order);
		controller.setOrdersTable(ordersTable);
		dialogStage.showAndWait();
	}
}
