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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddOrEditOrderController {

	//
    @FXML
    private ComboBox<Category> comboBoxCategory;

    @FXML
    private ComboBox<UnitOfMeasurement> comboBoxMedida;

    @FXML
    private ComboBox<Integer> comboBoxProduct;

    //
    @FXML
    private TableView<Ingredient> ingredientsTable;
    
    @FXML
    private TableColumn<Ingredient, UnitOfMeasurement> measurement;

    @FXML
    private TableColumn<Ingredient, String> product;

    @FXML
    private TableColumn<Ingredient, Double> quantity;
    //

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
    
    private ObservableList<Integer> obsProduct = FXCollections.observableArrayList();
    private ObservableList<Ingredient> obsIng = FXCollections.observableArrayList();
    
    private List<Ingredient> ingredients = new ArrayList<>();

    @FXML
    public void initialize() {
    	comboBoxCategory.getItems().setAll(Category.values());
    	comboBoxMedida.getItems().setAll(UnitOfMeasurement.values());
    	
    	for (Product p : ProductDAO.list()) {
    		obsProduct.add(p.getId());
    	}
    	
    	
    	comboBoxProduct.setItems(obsProduct);
    	obsIng.addAll(ingredients);
    	ingredientsTable.setItems(obsIng);

    }
    
    
    
    public void loadIngredients() {
    	product.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("product"));
		measurement.setCellValueFactory(new PropertyValueFactory<Ingredient, UnitOfMeasurement>("measurement"));
		quantity.setCellValueFactory(new PropertyValueFactory<Ingredient, Double>("quantity"));
    	
		// Achando o produto de acordo ao id colocado pelo usuario na combobox
		Product prod = null;
		for (Product p : ProductDAO.list()) {
			if (p.getId() == comboBoxProduct.getValue()) {
				prod = p;
			}
		}
		
		ingredients.add(new Ingredient(
				prod,
				Double.parseDouble(textFieldQuantity.getText()),
				comboBoxMedida.getValue()));
		
    }
    
    public void onActionAddIngredient() {
    	loadIngredients();
    	ingredientsTable.refresh();
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