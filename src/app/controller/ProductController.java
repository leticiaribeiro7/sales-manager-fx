package app.controller;

import java.io.IOException;
import java.time.LocalDate;

import app.model.ProductDAO;
import entities.Product;
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

public class ProductController {

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
	
	@FXML
	public void initialize(){
		loadProductsData();
		
	}
	
	// Carrega dados inicializados
	public ObservableList<Product> loadProductsData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		expiration.setCellValueFactory(new PropertyValueFactory<Product, LocalDate>("expiration"));
		quantity.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
		
		obsList = FXCollections.observableArrayList(ProductDAO.list());
		
		productsTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	// Quando clica no botão adicionar
	public void switchToAddProduct() throws IOException{
		
		Product product = new Product();
		showAddOrEditProduct(product);
		loadProductsData();
		
	}
	
	// Quando clica no botão editar
	public void switchToEditProduct() throws IOException {
		Product product = productsTable.getSelectionModel().getSelectedItem();
		if (product != null) {
			showAddOrEditProduct(product);
			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Selecione um producte");
			alert.show();
		}
	}
	
	//criar classe helper alerta e usar em tds entidades*************
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
	
	
	// Mesma caixa de diálogo para adicionar e editar
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
	
	
	
}