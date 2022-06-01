package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.model.UserDAO;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientController implements Initializable {

	@FXML
	TableView<User> clientsTable;
	
	@FXML
	TableColumn<User, Integer> idColumn;
	
	@FXML
	TableColumn<User, String> nameColumn;
	
	private ObservableList<User> obsList;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User user = new User("log1", "123");
		UserDAO.addOrEdit(user);
	
		
		idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
		
		obsList = FXCollections.observableArrayList(UserDAO.list());
		clientsTable.setItems(obsList);
		
	}

	
}
