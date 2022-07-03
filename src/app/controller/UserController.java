package app.controller;

import java.io.IOException;

import app.helpers.Alerts;
import app.model.UserDAO;
import entities.Client;
import entities.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {
	
	@FXML
	TextField searchBar;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonNovo;

    @FXML
    private Button buttonRemove;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> login;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, String> position;
    
	@FXML
	Stage dialogStage;
    
	private ObservableList<User> obsList = FXCollections.observableArrayList();

    /**
     * Inicializa dados e metodo de busca
     */
	@FXML
	public void initialize(){
		loadUsersData();
		search();
		
	}
	
	/**
	 *  Carrega dados inicializados
	 * @return obs list
	 */
	public ObservableList<User> loadUsersData() {
		
		
		id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
		
		// Verifica o tipo de instancia do usuario para Gerente ou Funcionário
		position.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getUserType()));
		
		
		obsList = FXCollections.observableArrayList(UserDAO.list());
		
		usersTable.setItems(obsList);
		
		return obsList;
	}
    
    /**
     * Remove usuario mediante confirmação
     */
    public void removeUser() {
    	User user = usersTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> UserDAO.remove(user.getId()));
			loadUsersData();
		}
    }
    
    /**
     * Manda usuário para view de adicionar
     * @param event
     * @throws IOException
     */
    public void switchToAddUser(ActionEvent event) throws IOException {
    	
    	User user = null;
		showAddOrEditUser(user);
		loadUsersData();
    }

    /**
     * Pega usuario selecionado e manda para view de edição
     * @throws IOException
     */
    public void switchToEditUser() throws IOException {
		User user = usersTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			showAddOrEditUser(user);
			
		} else {
			Alerts.alertEdit();
		}
    }
    
    
    /**
     * Abre caixa de dialogo para editar ou adicionar
     * @param user
     * @throws IOException
     */
    public void showAddOrEditUser(User user) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(AddOrEditUserController.class.getResource("/app/view/AddOrEditUser.fxml"));
		AnchorPane view = loader.load();
		dialogStage = new Stage();
		Scene scene = new Scene(view);
		dialogStage.setScene(scene);
		
		AddOrEditUserController controller = (AddOrEditUserController) loader.getController();
		
		controller.setDialogStage(dialogStage);
		controller.setUser(user);
		controller.setUsersTable(usersTable);
		dialogStage.showAndWait();
    }
    
    
    
    /**
     * Método para busca.
     */
	public void search() {
			
		FilteredList<User> filteredData = new FilteredList<>(obsList, content -> true);
		
		
		searchBar.textProperty().addListener((obs, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;
				
				String lowCaseFilter = newValue.toLowerCase();
				
				if (user.getName().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (user.getId().toString().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				if (user.getLogin().toLowerCase().indexOf(lowCaseFilter) != -1) return true;
				else return false;
			});
		});
		
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(usersTable.comparatorProperty());
		
		usersTable.setItems(sortedData);
		
	}

}

	
