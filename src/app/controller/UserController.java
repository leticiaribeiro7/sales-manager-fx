package app.controller;

import java.io.IOException;
import java.util.List;

import app.model.ClientDAO;
import app.model.UserDAO;
import entities.Client;
import entities.Employee;
import entities.User;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {

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

    
	@FXML
	public void initialize(){
		loadUsersData();
		
	}
	
	// Carrega dados inicializados
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
    
    
    public void removeUser(ActionEvent event) {
    	User user = usersTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Tem certeza que quer remover?");
			alert.showAndWait().filter(response -> response == ButtonType.OK)
			.ifPresent(response -> UserDAO.remove(user.getId()));
			loadUsersData();
		}
    }
    
    public void switchToAddUser(ActionEvent event) throws IOException {
    	
    	User user = null;
		showAddOrEditUser(user);
		loadUsersData();
    }


    public void switchToEditUser(ActionEvent event) throws IOException {
		User user = usersTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			showAddOrEditUser(user);
			
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Selecione um usuário");
			alert.show();
		}
    }
    
    

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

}

	
