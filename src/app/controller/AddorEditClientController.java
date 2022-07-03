package app.controller;

import app.helpers.Alerts;
import app.model.ClientDAO;
import entities.Client;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    

	private Stage dialogStage;
	
	private Client client;
	
	private TableView<Client> clientsTable;

	/**
	 * Conecta tabela do controller principal a este secundário.
	 * @param clientsTable
	 */
	public void setClientsTable(TableView<Client> clientsTable) {
		this.clientsTable = clientsTable;
	}

	/**
	 *  Insere os dados do cliente (caso ja tenha) nos campos quando for editar
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
		this.textFieldCPF.setText(client.getCpf());
		this.textFieldEmail.setText(client.getEmail());
		this.textFieldName.setText(client.getName());
		this.textFieldPhone.setText(client.getPhone());
		
	}
	/**
	 * Conecta a caixa de diálogo ao controller principal de Clientes
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	
	/**
	 *  Adiciona ou edita cliente quando clica em confirmar
	 */
    public void onActionConfirmar() {

    	if (validateInput()) {
    		
    		client.setName(textFieldName.getText());
    		client.setCpf(textFieldCPF.getText());
    		client.setPhone(textFieldPhone.getText());
    		client.setEmail(textFieldEmail.getText());
    		
    		ClientDAO.addOrEdit(client);
    		clientsTable.refresh();
    		dialogStage.close();
    		
    	} else {
    		Alerts.alertIncorretInput();
    	}
    }
  

    /**
     * Fecha a caixa de diálogo
     */
    public void onActionCancelar() {
    	dialogStage.close();
    }

    /**
     * Impede que o usuário clique em confirmar antes de preencher todos os campos
     */
	@FXML
	public void initialize() {
		buttonConfirmar.disableProperty().bind(
			    Bindings.isEmpty(textFieldCPF.textProperty())
			    .or(Bindings.isEmpty(textFieldEmail.textProperty())
			    .or(Bindings.isEmpty(textFieldName.textProperty()))
			    .or(Bindings.isEmpty(textFieldPhone.textProperty()))
			    )
			);
	}
	
	/**
	 * Valida entrada de dados do usuário
	 */
	public boolean validateInput() {
		boolean cpfIsNumeric = textFieldCPF.getText().chars().allMatch( Character::isDigit );
		boolean phoneIsNumeric = textFieldPhone.getText().chars().allMatch( Character::isDigit );
		
		if (cpfIsNumeric && phoneIsNumeric) {
			return true;
		}
		
		return false;
		
	}
	
	

	
    
}
