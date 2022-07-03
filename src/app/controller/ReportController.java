package app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import app.helpers.PdfReportGenerator;
import app.model.ProductDAO;
import app.model.SaleDAO;
import app.model.SupplierDAO;
import entities.Order;
import entities.Product;
import entities.Sale;
import entities.Supplier;
import constants.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ReportController {
    @FXML
    private RadioButton aVencer;

    @FXML
    private ComboBox<Product> cbProdutoEstoque;

    @FXML
    private ComboBox<Category> cbTiposPratos;
    
    @FXML
    private ComboBox<Integer> cbFornec;

    @FXML
    private DatePicker dataFim;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private RadioButton fornec;

    @FXML
    private RadioButton fornecProduto;

    @FXML
    private RadioButton geralEstoque;

    @FXML
    private RadioButton geralVenda;

    @FXML
    private RadioButton produtoEstoque;

    @FXML
    private RadioButton tipoPratosVenda;

    @FXML
    private RadioButton vendasPeriodo;
    
    @FXML
    private ComboBox<Product> cbProdutoFornec;
    
    final ToggleGroup group = new ToggleGroup();
    
    private ObservableList<Product> obsProduct = FXCollections.observableArrayList(ProductDAO.list());
    private ObservableList<Category> obsCat = FXCollections.observableArrayList(Category.values());
    private ObservableList<Integer> obsFornec = FXCollections.observableArrayList();
    
   
    
    
    @FXML
    public void initialize() {
    	
    	geralVenda.setToggleGroup(group);
    	geralEstoque.setToggleGroup(group);
    	fornecProduto.setToggleGroup(group);
    	aVencer.setToggleGroup(group);
    	tipoPratosVenda.setToggleGroup(group);
    	vendasPeriodo.setToggleGroup(group);
    	fornec.setToggleGroup(group);
    	produtoEstoque.setToggleGroup(group);
    	
    	cbProdutoEstoque.setVisible(false);
    	cbTiposPratos.setVisible(false);
    	cbProdutoFornec.setVisible(false);
    	dataFim.setVisible(false);
    	dataInicio.setVisible(false);
    	cbFornec.setVisible(false);
    	
    }
    
    @FXML
    public void handleBoxes() {
    	if (vendasPeriodo.isSelected()) {
    		dataInicio.setVisible(true);
    		dataFim.setVisible(true);
    	}
    	
    	if(produtoEstoque.isSelected()) {
    		cbProdutoEstoque.setVisible(true);
        	cbProdutoEstoque.setItems(obsProduct);
    		
    	}
    	
    	if(tipoPratosVenda.isSelected()) {
    		cbTiposPratos.setVisible(true);
    		cbTiposPratos.setItems(obsCat);
    	}
    	
    	if(fornecProduto.isSelected()) {
    		cbProdutoFornec.setVisible(true);
    		cbProdutoFornec.setItems(obsProduct);
    	}
    	
    	if(fornec.isSelected()) {
    		cbFornec.setVisible(true);
    		
    		for (Supplier sup: SupplierDAO.list()) {
    			obsFornec.add(sup.getId());
    		}
    		
    		cbFornec.setItems(obsFornec);
    	}
    	
    }
    
    public void gerar() {
    	if (geralVenda.isSelected()) {
    		PdfReportGenerator.salesReport(SaleDAO.list());
    	}
    	
    	if(vendasPeriodo.isSelected()) {
    		handleDates();
    		PdfReportGenerator.salesReport(handleDates());
    	}
    	
    	if(tipoPratosVenda.isSelected()) {
    		List<Sale> filteredSales = new ArrayList<>();
    		Category selectedCategory = cbTiposPratos.getValue();
    		
    		SaleDAO.list().forEach(sale -> {
    			List <Order> orders = sale.getOrders();  // pega a lista de pedidos de cada venda
    			
    			orders.forEach(order -> {
    				if (order.getCategory() == selectedCategory) {  // verifica a categoria dos pedidos
    					filteredSales.add(sale);
    				};
    			});
    		});	
    		
    		PdfReportGenerator.salesReport(filteredSales);
    	}
    	
    	if(aVencer.isSelected()) {
    		List <Product> orderedByDate = ProductDAO.list().stream()
    		         .sorted(Comparator.comparing(Product::getExpiration))
    				 .collect(Collectors.toList());
    		
    		PdfReportGenerator.productsReport(orderedByDate);
    	}
    	
    	if(produtoEstoque.isSelected()) {
    		Product selectedProduct = cbProdutoEstoque.getValue();
    		
   		 	List <Product> filteredProducts = ProductDAO.list().stream()
			      .filter(prod -> prod.getName() == selectedProduct.getName())
			      .collect(Collectors.toList());
   		 
   		 	PdfReportGenerator.productsReport(filteredProducts);
    	}
    	
    	if(geralEstoque.isSelected()) {
    		PdfReportGenerator.productsReport(ProductDAO.list());
    	}
    	
    	if(fornecProduto.isSelected()) {
    		List<Supplier> filteredSuppliers = new ArrayList<>();
    		for (Supplier sup : SupplierDAO.list()) {
    			
    			List<Integer> productIds = sup.getProductsId();
    			
    			productIds.forEach(id -> {
    				if (id  == cbProdutoFornec.getValue().getId()) {
    					filteredSuppliers.add(sup);
    				}
    			});
    			
    		}
    		
    		PdfReportGenerator.supplierReport(filteredSuppliers);
    	}
    	
    	if(fornec.isSelected()) {
    	    List<Supplier> filteredSupplier = SupplierDAO.list().stream()
    		  	      .filter(sup -> sup.getId() == cbFornec.getValue())
    		  	      .collect(Collectors.toList());
    	    PdfReportGenerator.supplierReport(filteredSupplier);
    	}
    	
    	
    }
    
    
    public List<Sale> handleDates() {
    	LocalDate inicio = dataInicio.getValue();
    	LocalDate fim = dataFim.getValue();
    	List<Sale> filteredSales = SaleDAO.list().stream()
    			.filter( sale -> ( sale.getDate().isAfter(inicio) ) &&
    					( !sale.getDate().isAfter( fim ) ) &&
    					( sale.getDate().equals(LocalDate.now()) ))
    			.toList();
    	
    	return filteredSales;
    }
    
    
    
}
