package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model modello = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGrafo;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGradoMax(ActionEvent event) {

    	txtResult.clear();
    	txtResult.appendText("Parola: "+modello.findMaxDegree()+",grado: "+modello.displayNeighbours(modello.findMaxDegree()).size()+"\n");
    	for(String s : modello.displayNeighbours(modello.findMaxDegree()))
    		txtResult.appendText(s+"\n");
    	
    }

    @FXML
    void doGrafo(ActionEvent event) {
    	
    	try {
    	modello.createGraph(Integer.parseInt(this.txtNumero.getText()));
    	this.btnVicini.setDisable(false);
    	this.txtParola.setDisable(false);
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    		txtResult.appendText("inserisci un numero");
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtResult.clear();
    	this.txtNumero.clear();
    	this.txtParola.clear();
    	
    }

    @FXML
    void doVicini(ActionEvent event) {

    	txtResult.clear();
    	modello.displayNeighbours(this.txtParola.getText());
    	this.btnGradoMax.setDisable(false);
    	txtResult.appendText("Parole simili : \n");
    	for(String s : modello.displayNeighbours(this.txtParola.getText()))
    		txtResult.appendText(s+"\n");
    }
    
    public void setModel(Model m) {
    	this.modello = m;
    	this.btnGradoMax.setDisable(true);
    	this.btnVicini.setDisable(true);
    	this.txtParola.setDisable(true);
    }

    @FXML
    void initialize() {
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrafo != null : "fx:id=\"btnGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
}