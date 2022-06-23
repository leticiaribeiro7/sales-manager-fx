package app.controller;

import java.io.IOException;
import java.time.LocalDate;

import app.model.SaleDAO;
import constants.paymentMethod;
import entities.Client;
import entities.Sale;
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

public class SaleController {
	
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
	
	@FXML
	public void initialize(){
		loadSalesData();
		
	}
	
	// Carrega dados inicializados
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
	
	
	
	
	// Quando clica no botão adicionar
	public void switchToAddSale() throws IOException{
		
		Sale sale = new Sale();
		showAddOrEditSale(sale);
		loadSalesData();
		
	}
	
	// Quando clica no botão editar
	public void switchToEditSale() throws IOException {
		Sale sale = salesTable.getSelectionModel().getSelectedItem();
		if (client != null) {
			showAddOrEditSale(sale);
			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Selecione um cliente");
			alert.show();
		}
	}
	
	//criar classe helper alerta e usar em tds entidades*************
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
	
	
	// Mesma caixa de diálogo para adicionar e editar
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

}
