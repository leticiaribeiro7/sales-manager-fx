package app.controller;

import java.time.LocalDate;

import app.helpers.Alerts;
import app.helpers.PdfReportGenerator;
import app.model.ClientDAO;
import app.model.OrderDAO;
import app.model.SaleDAO;
import constants.paymentMethod;
import entities.Client;
import entities.Order;
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
    private Label labelPrice;

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
  
    /**
     * Atualiza tabela de pedidos ao adicioná-los na venda.
     */
    public void onActionAddOrder() {
    	loadOrders();
    	
    	sale.setOrders(tbOrders);
    	
    	sale.setPrice();
    	
    	ordersNameTable.refresh();
    	labelPrice.setText(String.format("%.2f", sale.getPrice()));
    }
   
    /**
     * Confirma a criação da venda.
     */
    public void onActionConfirmar() {
    	if (validateInput()) {
    		sale.setDate(LocalDate.now()); //data atual do sistema
    		sale.setPaymentMethod(comboBoxPayment.getValue());
    		sale.setClient(comboBoxClient.getValue());
    		
    		sale.updateInventory();
    		
    		SaleDAO.addOrEdit(sale);
    		
    		
    		salesTable.refresh();
    		dialogStage.close();
    		PdfReportGenerator.clientPurchase(sale);
    		
    	} else Alerts.alertIncorretInput();
		
    }
    /**
     * Fecha a caixa de dialogo.
     */
    public void onActionCancelar() {
    	dialogStage.close();
    }

    /**
     * Conexão da caixa de dialogo entre esse controller e o principal
     * @param dialogStage
     */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Conexão da venda entre esse controller e o principal
	 * @param sale
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	/**
	 * Conexão da tabela de vendas entre esse controller e o principal
	 * @param salesTable
	 */
	public void setSalesTable(TableView<Sale> salesTable) {
		this.salesTable = salesTable;
		
	}
	
	/**
	 * Valida entradas do usuario
	 * @return boolean
	 */
	public boolean validateInput() {
		boolean hasOrders = tbOrders.size() != 0;
		boolean selectedPM = !comboBoxPayment.getSelectionModel().isEmpty();
		boolean selectedClient = !comboBoxClient.getSelectionModel().isEmpty();
		
		if (hasOrders && selectedPM && selectedClient) return true;
		
		return false;
	}
    
    
}