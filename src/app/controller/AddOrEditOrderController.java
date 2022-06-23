package app.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.model.OrderDAO;
import app.model.ProductDAO;
import constants.Category;
import constants.UnitOfMeasurement;
import entities.Ingredient;
import entities.Order;
import entities.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddOrEditOrderController {

	// Ingredientes
	
    @FXML
    private ComboBox<Category> comboBoxCategory;

    @FXML
    private ComboBox<UnitOfMeasurement> comboBoxMedida;

    @FXML
    private ComboBox<Product> comboBoxProduct;

    @FXML
    private TableView<Ingredient> ingredientsTable;

    @FXML
    private TableColumn<Ingredient, String> product;

    @FXML
    private TableColumn<Ingredient, Double> quantity;
    
    
    // Prato

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
    
    private ObservableList<Product> obsProduct;
    private ObservableList<Ingredient> obsIng;
    
    private ObservableList<UnitOfMeasurement> obsMedida = FXCollections.observableArrayList(UnitOfMeasurement.values());
    private ObservableList<Category> obsCat = FXCollections.observableArrayList(Category.values());
    
    private List<Ingredient> ingredients = new ArrayList<>();

    @FXML
    public void initialize() {
    	loadBoxes();
    }
    
    
    public void loadBoxes() {
    	comboBoxCategory.setItems(obsCat);
    	comboBoxMedida.setItems(obsMedida);
    	obsProduct = FXCollections.observableArrayList(ProductDAO.list());
    	
    	
    	comboBoxProduct.setItems(obsProduct);
    }
    
    public ObservableList<Ingredient> loadIngredients() {
    	product.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("product"));
		quantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("quantity"));
		
		
		// Cria ingrediente e add na lista
		ingredients.add(new Ingredient(
				comboBoxProduct.getValue(),
				Double.parseDouble(textFieldQuantity.getText()),
				comboBoxMedida.getValue()));
		
		
    	obsIng = FXCollections.observableArrayList(ingredients);
    	ingredientsTable.setItems(obsIng);
    	return obsIng;
		
    }
    
    
    
    public void onActionAddIngredient() {
    	loadIngredients();
    	ingredientsTable.refresh();
    }
    
    public void onActionConfirmar() {
		order.setName(textFieldName.getText());
		order.setPrice(Double.parseDouble(textFieldPrice.getText()));
		order.setDescription(textFieldDescription.getText());
		order.setIngredients(ingredients);
		order.setCategory(comboBoxCategory.getValue());
		
		OrderDAO.addOrEdit(order);

		ordersTable.refresh();
		dialogStage.close();
    }
    
    public void onActionCancelar() {
    	dialogStage.close();
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