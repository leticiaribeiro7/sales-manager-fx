package app.controller;


import java.util.ArrayList;
import java.util.List;

import app.helpers.Alerts;
import app.model.OrderDAO;
import app.model.ProductDAO;
import constants.Category;
import constants.UnitOfMeasurement;
import entities.Ingredient;
import entities.Order;
import entities.Product;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    
    @FXML
    private TextField textFieldQuantity;
    
    @FXML
    private Button BtnIngredient;
    
    
    // Prato

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPrice;

    
    @FXML
    private Button btnConfirm;
    
    
    // Conexão com controller principal
    
    private Stage dialogStage;
    
    private Order order;
    
    private TableView<Order> ordersTable;
    
    // Observable lists para combobox de produto, ingredientes, unidade de medida e categoria.
    private ObservableList<Product> obsProduct;
    private ObservableList<Ingredient> obsIng = FXCollections.observableArrayList();
    private ObservableList<UnitOfMeasurement> obsMedida = FXCollections.observableArrayList(UnitOfMeasurement.values());
    private ObservableList<Category> obsCat = FXCollections.observableArrayList(Category.values());
    
    private List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Inicializa dados e impede a inserção de dados com alguns campos vazios.
     */
    @FXML
    public void initialize() {
    	loadBoxes();
    	
		btnConfirm.disableProperty().bind(
			    Bindings.isEmpty(textFieldName.textProperty())
			    .or(Bindings.isEmpty(textFieldPrice.textProperty())
			    .or(Bindings.isEmpty(textFieldQuantity.textProperty())
			    ))
			);
    }
    /**
     * Conecta caixa de dialogo ao controller principal
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
    	this.dialogStage = dialogStage;
    }
    
    /**
     * Conecta prato ao controller principal
     * @param order
     */
    public void setOrder(Order order) {
    	this.order = order;
    }
    
    /**
     * Conecta tabela do cardápio ao controller principal
     * @param ordersTable
     */
    public void setOrdersTable(TableView<Order> ordersTable) {
    	this.ordersTable = ordersTable;
    }
    
    /**
     * Insere dados dos enums nas combobox.
     */
    public void loadBoxes() {
    	comboBoxCategory.setItems(obsCat);
    	comboBoxMedida.setItems(obsMedida);
    	obsProduct = FXCollections.observableArrayList(ProductDAO.list());
    	
    	
    	comboBoxProduct.setItems(obsProduct);
    }
    
    /**
     * Carrega ingredientes adicionados na lista.
     * @return observableListIngredients
     */
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
    
    
    /**
     * Adiciona ingrediente na lista.
     */
    public void onActionAddIngredient() {
    	loadIngredients();
    	ingredientsTable.refresh();
    }
    
    /**
     * Confirma adição de prato mediante validação dos campos.
     */
    public void onActionConfirmar() {
    	
    	if (validateInput()) {
    		order.setName(textFieldName.getText());
    		order.setPrice(Double.parseDouble(textFieldPrice.getText()));
    		order.setDescription(textFieldDescription.getText());
    		order.setIngredients(ingredients);
    		order.setCategory(comboBoxCategory.getValue());
    		
    		OrderDAO.addOrEdit(order);
    		
    		ordersTable.refresh();
    		dialogStage.close();
    	}
    	
    	else {
    		Alerts.alertIncorretInput();
    	}
    }
    /**
     * Fecha caixa de diálogo
     */
    public void onActionCancelar() {
    	dialogStage.close();
    }

    /**
     * Valida se lista de ingredientes foi populada e se o preço é numérico.
     * @return
     */
    public boolean validateInput() {
    	boolean ingredientsSelected = ingredients.size() != 0;
    	boolean validPrice = textFieldPrice.getText().chars().allMatch( Character::isDigit );
    	
    	if (ingredientsSelected && validPrice) {
    		return true;
    	}
    	
    	return false;
 
    }
     
    
    
}