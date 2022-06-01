package app.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	
	@FXML
	Button BtnClient, BtnHome;
	
	@FXML
	BorderPane mainBorderPane;
	

	@FXML
	public void switchToSales(ActionEvent event) {
        try {
        	Parent saleView = FXMLLoader.load(getClass().getResource("/app/view/SaleView.fxml"));
            mainBorderPane.setCenter(saleView);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

	
	
	@FXML
	public void switchToClient(ActionEvent event) {	
        try {
        	Parent clientView = FXMLLoader.load(getClass().getResource("/app/view/ClientView.fxml"));
            mainBorderPane.setCenter(clientView);
        } catch (IOException e) {
            e.printStackTrace();
        }
       
			
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
