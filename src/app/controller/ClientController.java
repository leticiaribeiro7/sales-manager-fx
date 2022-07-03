package app.controller;


import java.io.IOException;

import app.helpers.Alerts;
import app.model.ClientDAO;
import entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClientController {
	
	@FXML
	TextField searchBar;

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
	
	/**
	 * Inicializa dados e método de busca
	 */
	@FXML
	public void initialize(){
		loadClientsData();
		search();
		
	}
	
	/**
	 * Carrega dados inicializados
	 * @return obsList
	 */
	public ObservableList<Client> loadClientsData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		
		obsList = FXCollections.observableArrayList(ClientDAO.list());
		
		clientsTable.setItems(obsList);
		
		return obsList;
	}
	
	
	
	
	/**
	 * Instancia cliente e abre caixa de diálogo para adicioná-lo a lista.
	 * @throws IOException
	 */
	public void switchToAddClient() throws IOException{
		
		Client client = new Client();
		showAddOrEditClient(client);
		loadClientsData(); //recarrega os dados para atualizar tabela.
		
	}
	
	/**
	 * Abre caixa de diálogo para editar cliente.
	 * @throws IOException
	 */
	public void switchToEditClient() throws IOException {
		Client client = clientsTable.getSelectionModel().getSelectedItem();
		if (client != null) {
			showAddOrEditClient(client);
			
		} else {
			Alerts.alertEdit();
		}
	}
	
	/**
	 * Remove cliente selecionado mediante confirmação do usuário.
	 */
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
	
	
	/**
	 * Abre a mesma caixa de dialogo para editar ou adicionar
	 * @param client
	 * @throws IOException
	 */
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
	
	/**
	 * Busca cliente atualizando a lista dinamicamente.
	 */
	public void search() {
		
		FilteredList<Client> filteredData = new FilteredList<>(obsList, content -> true);
		
		
		searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
			filteredData.setPredicate(client -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;
				
				String lowCaseFilter = newValue.toLowerCase();
				
				if (client.getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (client.getId().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (client.getEmail().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (client.getPhone().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				else return false;
			});
		});
		
		SortedList<Client> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(clientsTable.comparatorProperty());
		
		clientsTable.setItems(sortedData);
		
	}
	
	
	
}

