package app.controller;

import java.io.IOException;
import java.time.LocalDate;

import app.helpers.Alerts;
import app.model.SaleDAO;
import constants.paymentMethod;
import entities.Client;
import entities.Sale;
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

public class SaleController {
	
	@FXML
	TextField searchBar;
	
	@FXML
	private TableView<Sale> salesTable;

    @FXML
    private TableColumn<Sale, Client> client;

    @FXML
    private TableColumn<Sale, LocalDate> date;

    @FXML
    private TableColumn<Sale, Integer> id;

    @FXML
    private TableColumn<Sale, paymentMethod> payment;

    @FXML
    private TableColumn<Sale, Double> price;
    
    
	@FXML
	Button buttonNovo;
	
	@FXML
	Stage dialogStage;
	
	private ObservableList<Sale> obsList = FXCollections.observableArrayList();
	
	/**
	 * Inicializa dados e habilita busca.
	 */
	@FXML
	public void initialize(){
		loadSalesData();
		enableSearch();
		
	}
	
	/**
	 * Carrega dados inicializados
	 * @return obsList
	 */
	public ObservableList<Sale> loadSalesData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("id"));
		date.setCellValueFactory(new PropertyValueFactory<Sale, LocalDate>("date"));
		price.setCellValueFactory(new PropertyValueFactory<Sale, Double>("price"));
		payment.setCellValueFactory(new PropertyValueFactory<Sale, paymentMethod>("paymentMethod"));
		client.setCellValueFactory(new PropertyValueFactory<Sale, Client>("client"));
		
		obsList = FXCollections.observableArrayList(SaleDAO.list());
		
		salesTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	/**
	 * Instancia venda e envia pra caixa de dialogo para adicionar uma nova.
	 * @throws IOException
	 */
	public void switchToAddSale() throws IOException{
		
		Sale sale = new Sale();
		showAddOrEditSale(sale);
		loadSalesData();
		
	}
	
	/**
	 * Abre caixa de dialogo para editar venda selecionada pelo usuario
	 * @throws IOException
	 */
	public void switchToEditSale() throws IOException {
		Sale sale = salesTable.getSelectionModel().getSelectedItem();
		if (client != null) {
			showAddOrEditSale(sale);
			
		} else {
			Alerts.alertEdit();
		}
	}
	

    public void removeSale() {
    	Sale client = salesTable.getSelectionModel().getSelectedItem();
		if (client != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> SaleDAO.remove(client.getId()));
			loadSalesData();
			
		}
    }
	
	
	/**
	 * Abre caixa de dialogo que serve para editar e adicionar.
	 * @param sale
	 * @throws IOException
	 */
	public void showAddOrEditSale(Sale sale) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AddOrEditSaleController.class.getResource("/app/view/AddSale.fxml"));
		AnchorPane view = loader.load();
		dialogStage = new Stage();
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		AddOrEditSaleController controller = (AddOrEditSaleController) loader.getController();
		
		controller.setDialogStage(dialogStage);
		controller.setSale(sale);
		controller.setSalesTable(salesTable);
		dialogStage.showAndWait();
	}
	
	
	/**
	 * Busca uma venda.
	 */
	public void enableSearch() {
		
		FilteredList<Sale> filteredData = new FilteredList<>(obsList, content -> true);
		
		
		searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
			filteredData.setPredicate(sale -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;
				
				String lowCaseFilter = newValue.toLowerCase();
				
				if (sale.getDate().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (sale.getId().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (sale.getClient().getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (sale.getPaymentMethod().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				else return false;
			});
		});
		
		SortedList<Sale> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(salesTable.comparatorProperty());
		
		salesTable.setItems(sortedData);
		
	}
	

}
