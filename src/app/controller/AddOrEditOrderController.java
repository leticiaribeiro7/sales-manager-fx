package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.model.ProductDAO;
import constants.Category;
import constants.UnitOfMeasurement;
import entities.Ingredient;
import entities.Order;
import entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddOrEditOrderController {

    @FXML
    private ComboBox<Category> comboBoxCategory;

    @FXML
    private ComboBox<UnitOfMeasurement> comboBoxMedida;

    @FXML
    private ComboBox<String> comboBoxProduct;

    @FXML
    private TableView<Ingredient> ingredientsTable;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private TextField textFieldQuantity;
    
    private Stage dialogStage;
    
    private Order order;
    
    private TableView<Order> ordersTable;
    
    private ObservableList<Category> obsCategory = FXCollections.observableArrayList();
    private ObservableList<UnitOfMeasurement> obsMedida = FXCollections.observableArrayList();
    private ObservableList<String> obsProduct = FXCollections.observableArrayList();


    
    public void initialize(URL url, ResourceBundle b) {
    	obsCategory.add(Category.DRINKS);
    	obsProduct.addAll(ProductDAO.list().toString());
    	obsMedida.add(UnitOfMeasurement.KG);
    	comboBoxCategory.setItems(obsCategory);
    	comboBoxMedida.setItems(obsMedida);
    	comboBoxProduct.setItems(obsProduct);
    }
    
    
    public void onActionAddIngredient() {
    	
    }
    
    public void onActionConfirmar() {
    	
    }
    
    public void onActionCancelar() {
    	
    }


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public void setOrdersTable(TableView<Order> ordersTable) {
		this.ordersTable = ordersTable;
		
	}
    
    
    
    
}