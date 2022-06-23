package app.controller;

import java.time.LocalDate;

import app.model.ProductDAO;
import entities.Client;
import entities.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddOrEditProductController {
	@FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private DatePicker expiration;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldQuantity;
    
    
    
	ClientController controller;

	private Stage dialogStage;
	
	private Product product;
	
	private TableView<Product> productsTable;


	public void setProductsTable(TableView<Product> productsTable) {
		this.productsTable = productsTable;
	}
	
	
	/**
	 * Pega a data do datepicker
	 */
	public LocalDate getDate() {
		LocalDate date = expiration.getValue();
		return date;
	}
	
	
	
	/**
		Adiciona ou edita produto quando clica em confirmar 
	 */
    public void onActionConfirmar() {

    //	if (validateData() == true) {
    		
    		product.setName(textFieldName.getText());
    		product.setQuantity(Double.parseDouble(textFieldQuantity.getText()));
    		product.setExpiration(getDate());
    		
    		ProductDAO.addOrEdit(product);
    		productsTable.refresh();
    		dialogStage.close();
    		
    //	}
    }
  
    /**
     * Fecha a caixa de diálogo
     */
    public void onActionCancelar() {
    	dialogStage.close();
    }

	/**
	 * Insere os dados do produto (caso ja tenha) nos campos quando for editar
	 */
	
	public void setProduct(Product product) {
		this.product = product;
		this.textFieldName.setText(product.getName());
		this.textFieldQuantity.setText(product.getQuantity().toString());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
    
//	public boolean validateData() {
//
//	}
	
	
	
}
