package app.controller;

import java.io.IOException;
import java.time.LocalDate;

import app.helpers.Alerts;
import app.model.ProductDAO;
import entities.Product;
import entities.User;
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

public class ProductController {
	
	@FXML
	TextField searchBar;

	@FXML
	TableView<Product> productsTable;
	
	@FXML
	TableColumn<Product, Integer> id;
	
	@FXML
	TableColumn<Product, String> name;
	
	@FXML
	TableColumn<Product, LocalDate> expiration;
	
	@FXML
	TableColumn<Product, String> quantity;
	
	@FXML
	Button buttonNovo;
	
	@FXML
	Stage dialogStage;
	
	private ObservableList<Product> obsList = FXCollections.observableArrayList();
	
	/**
	 * Inicializa dados e habilita busca.
	 */
	@FXML
	public void initialize(){
		loadProductsData();
		enableSearch();
		
	}
	
	/**
	 * Carrega dados inicializados
	 * @return obsList
	 */
	public ObservableList<Product> loadProductsData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		expiration.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("expiration"));
		quantity.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
		
		obsList = FXCollections.observableArrayList(ProductDAO.list());
		
		productsTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	/**
	 * Instancia produto e abre caixa de diálogo para adicionar lista.
	 * @throws IOException
	 */
	public void switchToAddProduct() throws IOException{
		
		Product product = new Product();
		showAddOrEditProduct(product);
		loadProductsData();
		
	}
	
	/**
	 * Abre caixa de diálogo para editar produto selecionado.
	 * @throws IOException
	 */
	public void switchToEditProduct() throws IOException {
		Product product = productsTable.getSelectionModel().getSelectedItem();
		if (product != null) {
			showAddOrEditProduct(product);
			
		} else {
			Alerts.alertEdit();
		}
	}
	
	/**
	 * Remove produto mediante confirmação do usuário.
	 */
    public void removeProduct() {
    	Product product = productsTable.getSelectionModel().getSelectedItem();
		if (product != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> ProductDAO.remove(product.getId()));
			loadProductsData();
			
		}
    }
	
	
	/**
	 * Abre a mesma caixa de diálogo para adicionar ou editar)
	 * @param product
	 * @throws IOException
	 */
	public void showAddOrEditProduct(Product product) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AddOrEditProductController.class.getResource("/app/view/AddProduct.fxml"));
		AnchorPane view = loader.load();
		dialogStage = new Stage();
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		AddOrEditProductController controller = (AddOrEditProductController) loader.getController();
		
		controller.setDialogStage(dialogStage);
		controller.setProduct(product);
		controller.setProductsTable(productsTable);
		dialogStage.showAndWait();
	}
	
	/**
	 * Busca um produto.
	 */
	public void enableSearch() {
		
		FilteredList<Product> filteredData = new FilteredList<>(obsList, content -> true);
		
		
		searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;
				
				String lowCaseFilter = newValue.toLowerCase();
				
				if (product.getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (product.getId().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (product.getExpiration().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				else return false;
			});
		});
		
		SortedList<Product> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(productsTable.comparatorProperty());
		
		productsTable.setItems(sortedData);
		
	}
	
	
	
}
