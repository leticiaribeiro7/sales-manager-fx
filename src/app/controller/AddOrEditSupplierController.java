package app.controller;

import java.util.ArrayList;
import java.util.List;

import app.helpers.Alerts;
import app.model.ProductDAO;
import app.model.SupplierDAO;

import entities.Product;
import entities.Supplier;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddOrEditSupplierController {
	
	
    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldCNPJ;

    @FXML
    private TextField textFieldName;
    
    
    @FXML
    private ComboBox<Product> comboBoxProd;


    private ObservableList<Product> obsProd = FXCollections.observableArrayList(ProductDAO.list());
    
    private List<Integer> productsId = new ArrayList<>();

    

    private TableView<Supplier> suppliersTable;
    private Stage dialogStage;
    private Supplier supplier;
    
    /**
     * Desabilita botao de confirmar antes de preenchimento dos campos.
     */
    @FXML
    public void initialize() {
		buttonConfirmar.disableProperty().bind(
			    Bindings.isEmpty(textFieldName.textProperty())
			    .or(Bindings.isEmpty(textFieldCNPJ.textProperty())
			    .or(Bindings.isEmpty(textFieldAddress.textProperty())
			    ))
			);

		comboBoxProd.setItems(obsProd);
    }
    

    /**
     * Adiciona id do produto na lista e confirma a inserção
     */
    public void addProductId() {
    	productsId.add(comboBoxProd.getValue().getId());
    	Alerts.confirmation(comboBoxProd.getValue().getId());
    }
    
    /**
     * Fecha caixa de dialogo
     * @param event
     */

    public void onActionCancelar(ActionEvent event) {
    	dialogStage.close();
    }

    /**
     * Confirma inserção de fornecedor na lista apos validação de campos
     * @param event
     */
   
    public void onActionConfirmar(ActionEvent event) {
    	if (validateInput()) {
    		supplier.setName(textFieldName.getText());
    		supplier.setCnpj(textFieldCNPJ.getText());
    		supplier.setAddress(textFieldAddress.getText());
    		supplier.setProductsId(productsId);
    		
    		SupplierDAO.addOrEdit(supplier);
    		suppliersTable.refresh();
    		dialogStage.close();
    	} else {
    		Alerts.alertIncorretInput();
    	}
    }
	
	
	
    /**
     * Conecta controller principal com secundário
     * @param dialogStage
     */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Conecta fornecedor do controller principal a este secundário
	 * @param supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
		this.textFieldCNPJ.setText(supplier.getCnpj());
		this.textFieldName.setText(supplier.getName());
		this.textFieldAddress.setText(supplier.getAddress());
			
	}

	/**
	 * Conecta tabela do controller principal a este secundário
	 * @param suppliersTable
	 */
	public void setSuppliersTable(TableView<Supplier> suppliersTable) {
		this.suppliersTable = suppliersTable;
	}
	
	/**
	 * Valida cnpj numerico
	 * @return boolean
	 */
	public boolean validateInput() {
		return textFieldCNPJ.getText().chars().allMatch( Character::isDigit ) 
				&& productsId.size() > 0;
	}
}
