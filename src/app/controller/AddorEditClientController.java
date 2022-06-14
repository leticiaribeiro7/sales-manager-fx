package app.controller;

import app.model.ClientDAO;
import entities.Client;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddorEditClientController {
	@FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPhone;
    
    
    
	ClientController controller;

	private Stage dialogStage;
	
	private Client client;
	
	private TableView<Client> clientsTable;


	public void setClientsTable(TableView<Client> clientsTable) {
		this.clientsTable = clientsTable;
	}

	// Adiciona ou edita cliente quando clica em confirmar
    public void onActionConfirmar() {

    //	if (validateData() == true) {
    		
    		client.setName(textFieldName.getText());
    		client.setCpf(textFieldCPF.getText());
    		client.setPhone(textFieldPhone.getText());
    		client.setEmail(textFieldEmail.getText());
    		
    		ClientDAO.addOrEdit(client);
    		clientsTable.refresh();
    		dialogStage.close();
    		
    //	}
    }
  
    // Fecha a caixa de dialogo
    public void onActionCancelar() {
    	dialogStage.close();
    }

	// Insere os dados do cliente (caso ja tenha) nos campos quando for editar
	public void setClient(Client client) {
		this.client = client;
		this.textFieldCPF.setText(client.getCpf());
		this.textFieldEmail.setText(client.getEmail());
		this.textFieldName.setText(client.getName());
		this.textFieldPhone.setText(client.getPhone());
		
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
    
//	public boolean validateData() {
//
//	}
	
	

	
    
}
