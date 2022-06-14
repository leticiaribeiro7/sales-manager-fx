package app.controller;


import java.io.IOException;
import app.model.ClientDAO;
import entities.Client;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientController {

	@FXML
	TableView<Client> clientsTable;
	
	@FXML
	TableColumn<Client, Integer> id;
	
	@FXML
	TableColumn<Client, String> name;
	
	@FXML
	TableColumn<Client, String> email;
	
	@FXML
	TableColumn<Client, String> phone;
	
	@FXML
	Button buttonNovo;
	
	@FXML
	Stage dialogStage;
	
	private ObservableList<Client> obsList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize(){
		loadClientsData();
		
	}
	
	// Carrega dados inicializados
	public ObservableList<Client> loadClientsData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		
		obsList = FXCollections.observableArrayList(ClientDAO.list());
		
		clientsTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	// Quando clica no botão adicionar
	public void switchToAddClient() throws IOException{
		
		Client client = new Client();
		showAddOrEditClient(client);
		loadClientsData();
		
	}
	
	// Quando clica no botão editar
	public void switchToEditClient() throws IOException {
		Client client = clientsTable.getSelectionModel().getSelectedItem();
		if (client != null) {
			showAddOrEditClient(client);
			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Selecione um cliente");
			alert.show();
		}
	}
	
	//criar classe helper alerta e usar em tds entidades*************
    public void removeClient() {
    	Client client = clientsTable.getSelectionModel().getSelectedItem();
		if (client != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> ClientDAO.remove(client.getId()));
			loadClientsData();
			
		}
    }
	
	
	// Mesma caixa de diálogo para adicionar e editar
	public void showAddOrEditClient(Client client) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AddorEditClientController.class.getResource("/app/view/AddClient.fxml"));
		AnchorPane view = loader.load();
		dialogStage = new Stage();
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		AddorEditClientController controller = (AddorEditClientController) loader.getController();
		
		controller.setDialogStage(dialogStage);
		controller.setClient(client);
		controller.setClientsTable(clientsTable);
		dialogStage.showAndWait();
	}
	
	
	
}

