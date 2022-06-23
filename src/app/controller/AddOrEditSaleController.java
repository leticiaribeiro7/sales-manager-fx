package app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.model.ClientDAO;
import app.model.OrderDAO;
import app.model.ProductDAO;
import app.model.SaleDAO;
import constants.Category;
import constants.UnitOfMeasurement;
import constants.paymentMethod;
import entities.Client;
import entities.Ingredient;
import entities.Order;
import entities.Product;
import entities.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddOrEditSaleController {

    @FXML
    private ComboBox<Client> comboBoxClient;

    @FXML
    private ComboBox<paymentMethod> comboBoxPayment;

    @FXML
    private ComboBox<Order> comboBoxPrato;

    @FXML
    private Label labelPrice; //usa settext pra mudar o valor dele

    @FXML
    private TableView<Sale> salesTable;

    @FXML
    private TableColumn<Order, String> prato;
    
    
    @FXML
    private TableView<Order> ordersNameTable;
    
    private Stage dialogStage;
    
    private Sale sale;
    

    // para a table view
    private ObservableList<Order> obsOrders = FXCollections.observableArrayList();
    
//    private ObservableList<Ingredient> obsIng;
    private ObservableList<Client> obsClient = FXCollections.observableArrayList(ClientDAO.list());
   
    private ObservableList<paymentMethod> obsPayment= FXCollections.observableArrayList(paymentMethod.values());
    
    // para combo box
    private ObservableList<Order> orders = FXCollections.observableArrayList(OrderDAO.list());

    @FXML
    public void initialize() {
    	loadBoxes();
    }
  
    public void loadBoxes() {
 
    	
    	comboBoxClient.setItems(obsClient);
    	
    	comboBoxPayment.setItems(obsPayment);
    	
    	comboBoxPrato.setItems(orders);
    	
    }
//    
    public ObservableList<Order> loadOrders() {
		prato.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
		
		obsOrders.add(comboBoxPrato.getValue());
    	ordersNameTable.setItems(obsOrders);
    	return obsOrders;
		
    }
  
    public void onActionAddOrder() {
    	loadOrders();
    	ordersNameTable.refresh();
    	//labelPrice.setText(sale.getPrice().toString()); //VER COMO RESOLVER AQUI. NAO PODE COMEÇAR SENDO NULL
    }
    
    public void onActionConfirmar() {
		sale.setOrders(orders);
		sale.setPrice();
		sale.setDate(LocalDate.now()); //data atual do sistema
		sale.setPaymentMethod(comboBoxPayment.getValue());
		sale.setClient(comboBoxClient.getValue());
		
		SaleDAO.addOrEdit(sale);

		salesTable.refresh();
		dialogStage.close();
    }
    
    public void onActionCancelar() {
    	dialogStage.close();
    }


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	public void setSale(Sale sale) {
		this.sale = sale;
	}


	public void setSalesTable(TableView<Sale> salesTable) {
		this.salesTable = salesTable;
		
	}
    
    
}