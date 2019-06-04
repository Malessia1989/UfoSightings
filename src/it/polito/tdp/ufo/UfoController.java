/**
 * Sample Skeleton for 'Ufo.fxml' Controller Class
 */

package it.polito.tdp.ufo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.model.AnnoCount;
import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class UfoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<AnnoCount> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxStato"
    private ComboBox<String> boxStato; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void handleAnalizza(ActionEvent event) {
    	
    	String statoSelezionato= boxStato.getValue();
    	if(statoSelezionato != null) {
    		List<String> elenco1=model.getSuccessori(statoSelezionato);
    		List<String> elenco2= model.getPredecessori(statoSelezionato);
    		List<String> raggiungibili =this.model.getRaggiungibili(statoSelezionato);
    		
    		
    		txtResult.appendText("Predecessori: ");
    		for(String s: elenco1) {
    			txtResult.appendText(s + "\n");
    		}

    		txtResult.appendText("Successori: ");
    		for(String s: elenco2) {
    			txtResult.appendText(s + "\n");
    		}
    		txtResult.appendText("Raggiungibili: ");
    		for(String s: raggiungibili) {
    			txtResult.appendText(s + "\n");
    		}
    	}else {
    		ShowAlert("Devi selezionare uno stato!");
    	}
    }

    @FXML
    void handleAvvistamenti(ActionEvent event) {
    	AnnoCount annoInput = boxAnno.getValue();
    	if(annoInput != null) {
    		model.creaGrafo(annoInput.getAnno());
    	}else {
    		ShowAlert("Devi selezionare un anno!");
    	}
    	boxStato.getItems().addAll(model.getStati()); 
    }

    private void ShowAlert(String message) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
		
	}

	@FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert boxStato != null : "fx:id=\"boxStato\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Ufo.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getAnnoCount());		
	}
}
