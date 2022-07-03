package app.controller;

import java.io.IOException;

import app.helpers.Alerts;
import app.model.OrderDAO;
import constants.Category;
import entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderController {
	
	@FXML
	private TextField searchBar;
	
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
	
	/**
	 * Inicializa dados e método de busca.
	 */
	@FXML
	public void initialize(){
		loadOrdersData();
		enableSearch();
		
	}
	
	/**
	 * Carrega dados inicializados na tabela.
	 * @return
	 */
	public ObservableList<Order> loadOrdersData() {
		
		id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
		price.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));
		category.setCellValueFactory(new PropertyValueFactory<Order, Category>("category"));
		
		obsList = FXCollections.observableArrayList(OrderDAO.list());
		
		ordersTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	/**
	 * Instancia pedido e abre caixa de diálogo para adicioná-lo na lista.
	 * @throws IOException
	 */
	public void switchToAddOrder() throws IOException{
		
		Order order = new Order();
		showAddOrEditOrder(order);
		loadOrdersData(); // recarrega tabela depois de adicionar
		
	}
	
	/**
	 * Abre caixa de diálogo para editar pedido selecionado
	 * @throws IOException
	 */
	public void switchToEditOrder() throws IOException {
		Order order = ordersTable.getSelectionModel().getSelectedItem();
		if (order != null) {
			showAddOrEditOrder(order);
			
		} else {
			Alerts.alertEdit();
		}
	}
	
	/**
	 * Remove pedido mediante confirmação do usuário
	 */
    public void removeOrder() {
    	Order order = ordersTable.getSelectionModel().getSelectedItem();
    	
		if (order != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait()
			.filter(response -> response == ButtonType.OK)
			.ifPresent(response -> OrderDAO.remove(order.getId()));
			
			loadOrdersData();
			
		}
    }
	
	
	/**
	 * Abre mesma caixa de diálogo para editar ou adicionar.
	 * @param order
	 * @throws IOException
	 */
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
	
	
	/**
	 * Método de busca que atualiza dinamicamente.
	 */
	public void enableSearch() {
		
		FilteredList<Order> filteredData = new FilteredList<>(obsList, content -> true);
		
		
		searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
			filteredData.setPredicate(order -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;
				
				String lowCaseFilter = newValue.toLowerCase();
				
				if (order.getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (order.getId().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (order.getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (order.getCategory().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				else return false;
			});
		});
		
		SortedList<Order> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(ordersTable.comparatorProperty());
		
		ordersTable.setItems(sortedData);
		
	}
	
}
