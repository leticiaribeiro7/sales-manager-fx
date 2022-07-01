package app.controller;

import app.model.SupplierDAO;
import entities.Client;
import entities.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    private Stage dialogStage;
    private Supplier supplier;
    SupplierController controller;
    
    @FXML
    void onActionCancelar(ActionEvent event) {
    	dialogStage.close();
    }

    @FXML
    void onActionConfirmar(ActionEvent event) {
		supplier.setName(textFieldName.getText());
		supplier.setCnpj(textFieldCNPJ.getText());
		supplier.setAddress(textFieldAddress.getText());
		
		SupplierDAO.addOrEdit(supplier);
		suppliersTable.refresh();
		dialogStage.close();
    }
	
	
	
	private TableView<Supplier> suppliersTable;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
		this.textFieldCNPJ.setText(supplier.getCnpj());
		this.textFieldName.setText(supplier.getName());
		this.textFieldAddress.setText(supplier.getAddress());
			
	}

	public void setSuppliersTable(TableView<Supplier> suppliersTable) {
		this.suppliersTable = suppliersTable;
	}
}
