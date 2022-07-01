package app.controller;

import java.io.IOException;

import app.helpers.Alerts;
import app.model.SupplierDAO;
import entities.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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

public class SupplierController {
	@FXML
	private Button buttonEdit;
	
	@FXML
	private Button buttonNovo;
	
	@FXML
	private Button buttonRemove;
	
	@FXML
	private TableColumn<Supplier, String> cnpj;
	
	@FXML
	private TableColumn<Supplier, Integer> id;
	
	@FXML
	private TableColumn<Supplier, String> name;
	
	@FXML
	TextField searchBar;
	
	@FXML
	private TableView<Supplier> suppliersTable;

	private Stage dialogStage;
	
	
	private ObservableList<Supplier> obsList;
	
	
	// Carrega dados inicializados
	public ObservableList<Supplier> loadSuppliersData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
		cnpj.setCellValueFactory(new PropertyValueFactory<Supplier, String>("cnpj"));
		
		obsList = FXCollections.observableArrayList(SupplierDAO.list());
		
		suppliersTable.setItems(obsList);
		
		return obsList;
	}
	
	@FXML
	public void initialize() {
		loadSuppliersData();
		enableSearch();
	}
	
	
	
	public void removeSupplier(ActionEvent event) {
    	Supplier supplier = suppliersTable.getSelectionModel().getSelectedItem();
		if (supplier != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> SupplierDAO.remove(supplier.getId()));
			loadSuppliersData();
		}
	}
	
	public void switchToAddSupplier(ActionEvent event) throws IOException {
    	Supplier supplier = new Supplier();
		showAddOrEditSupplier(supplier);
		loadSuppliersData();
	}

	public void switchToEditSupplier(ActionEvent event) throws IOException {
		Supplier supplier = suppliersTable.getSelectionModel().getSelectedItem();
		if (supplier != null) {
			showAddOrEditSupplier(supplier);
			
		} else {
			Alerts.alertEdit();
		}
	}
	
	// Mesma caixa de diálogo para adicionar e editar
	public void showAddOrEditSupplier(Supplier supplier) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AddOrEditSupplierController.class.getResource("/app/view/AddSupplier.fxml"));
		AnchorPane view = loader.load();
		dialogStage = new Stage();
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		AddOrEditSupplierController controller = (AddOrEditSupplierController) loader.getController();
		
		controller.setDialogStage(dialogStage);
		controller.setSupplier(supplier);
		controller.setSuppliersTable(suppliersTable);
		dialogStage.showAndWait();
	}
	
	
	public void enableSearch() {
		
		FilteredList<Supplier> filteredData = new FilteredList<>(obsList, content -> true);
		
		
		searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;
				
				String lowCaseFilter = newValue.toLowerCase();
				
				if (user.getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (user.getId().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (user.getCnpj().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				else return false;
			});
		});
		
		SortedList<Supplier> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(suppliersTable.comparatorProperty());
		
		suppliersTable.setItems(sortedData);
		
	}
	

}
