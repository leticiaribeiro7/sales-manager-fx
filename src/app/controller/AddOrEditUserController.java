package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.model.UserDAO;
import entities.Employee;
import entities.Manager;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddOrEditUserController implements Initializable {
 	@FXML
    private ComboBox<String> comboBoxUserType;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextField textFieldName;
    
    private Stage dialogStage;
    
    private User user;
    
	private TableView<User> usersTable;
    

    
    private ObservableList<String> comboList = FXCollections.observableArrayList();
    
    public void initialize(URL url, ResourceBundle b) {
    	comboList.add("Employee");
    	comboList.add("Manager");
    	
    	comboBoxUserType.setItems(comboList);
    }
    
	// Adiciona ou edita cliente quando clica em confirmar
    public void onActionConfirmar() {

    	
		// pegar oq ta selecionado na combo box
		// fazer casting do tipo e mandar pro DAO
		String type = comboBoxUserType.getValue();
		
		if (type == "Employee") user = new Employee();
		else if (type == "Manager") user = new Manager();
		
		user.setName(textFieldName.getText());
		user.setLogin(textFieldLogin.getText());
		user.setPassword(passwordField.getText());
		
		UserDAO.addOrEdit(user);
		usersTable.refresh();
		dialogStage.close();
    		
    }
  
    // Fecha a caixa de dialogo
    public void onActionCancelar() {
    	dialogStage.close();
    }
    
    
	public void setUser(User user) {
		if (user != null) {
			this.user = user;
			this.textFieldLogin.setText(user.getLogin());
			this.textFieldName.setText(user.getName());
			
		}
	}
	
	
	public void setUsersTable(TableView<User> usersTable) {
		this.usersTable = usersTable;
	}
	

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
    
}
