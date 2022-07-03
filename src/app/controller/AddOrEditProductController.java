package app.controller;

import java.time.LocalDate;

import app.helpers.Alerts;
import app.model.ProductDAO;
import entities.Product;
import javafx.beans.binding.Bindings;
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
    

	private Stage dialogStage;
	
	private Product product;
	
	private TableView<Product> productsTable;

	@FXML
	public void initialize() {
		buttonConfirmar.disableProperty().bind(
			    Bindings.isEmpty(textFieldQuantity.textProperty())
			    .or(Bindings.isEmpty(textFieldName.textProperty())
			    )
			);
	}
	
	
	/**
	 * Insere os dados do produto (caso ja tenha) nos campos quando for editar.
	 * @param product
	 */
	public void setProduct(Product product) {
		this.product = product;
		this.textFieldName.setText(product.getName());
		this.textFieldQuantity.setText(product.getQuantity().toString());
	}
	/**
	 * Conecta caixa de dialogo com o controller principal
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	/**
	 * Conecta tabela de produtos com este controller secundário.
	 * @param productsTable
	 */
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
		Adiciona ou edita produto após validações quando clica em confirmar
	 */
    public void onActionConfirmar() {

    	if (validateInput()) {
    		
    		product.setName(textFieldName.getText());
    		product.setQuantity(Double.parseDouble(textFieldQuantity.getText()));
    		product.setExpiration(getDate());
    		
    		ProductDAO.addOrEdit(product);
    		productsTable.refresh();
    		dialogStage.close();
    		
    	}
    	else Alerts.alertIncorretInput();
    }
  
    /**
     * Fecha a caixa de diálogo
     */
    public void onActionCancelar() {
    	dialogStage.close();
    }

	
    /**
     * Valida inputs de data e quantidade
     * @return
     */
	public boolean validateInput() {
		boolean validQtd = textFieldQuantity.getText().chars().allMatch( Character::isDigit ) ||
				textFieldQuantity.getText() != "0.0";
		
		boolean validDate = getDate() instanceof LocalDate;
		
		if (validQtd && validDate) {
			return true;
		}
		
		return false;
	}
	
	
	
}
