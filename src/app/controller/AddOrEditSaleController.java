package app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.helpers.PdfReportGenerator;
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
    

    // Table View orders
    private ObservableList<Order> tbOrders = FXCollections.observableArrayList();
    
    private ObservableList<Client> obsClient = FXCollections.observableArrayList(ClientDAO.list());
   
    private ObservableList<paymentMethod> obsPayment= FXCollections.observableArrayList(paymentMethod.values());
    
    // Combobox orders
    private ObservableList<Order> cbOrders = FXCollections.observableArrayList(OrderDAO.list());

    @FXML
    public void initialize() {
    	loadInitialData();
    }
    
    /**
     * Seta coluna da tableview de pratos e insere dados de cliente, pagamento e prato nas comboboxes.
     */
    public void loadInitialData() {
 
    	prato.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
    	
    	comboBoxClient.setItems(obsClient);
    	
    	comboBoxPayment.setItems(obsPayment);
    	
    	comboBoxPrato.setItems(cbOrders);
    	
    }
    /**
     * Adiciona pedido selecionado na combobox dentro da lista observável da tableview
     * @return lista observável
     */
    public ObservableList<Order> loadOrders() {
		
		tbOrders.add(comboBoxPrato.getValue());
    	ordersNameTable.setItems(tbOrders);
    	return tbOrders;
		
    }
  
    public void onActionAddOrder() {
    	loadOrders();
    	
    	sale.setOrders(tbOrders);
    	
    	sale.setPrice();
    	
    	ordersNameTable.refresh();
    	labelPrice.setText(String.format("%.2f", sale.getPrice()));
    }
   
    
    public void onActionConfirmar() {
		sale.setDate(LocalDate.now()); //data atual do sistema
		sale.setPaymentMethod(comboBoxPayment.getValue());
		sale.setClient(comboBoxClient.getValue());
		
		sale.updateInventory();
		
		SaleDAO.addOrEdit(sale);
		
		
		salesTable.refresh();
		dialogStage.close();
		PdfReportGenerator.clientPurchase(sale);
		
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