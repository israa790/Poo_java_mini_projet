package mini_projet;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import mini_projet.exemple1.metier;

public class virementController implements Initializable{

    @FXML
    private TextField soldeTF;

    @FXML
    private TextField numDebTF;

    @FXML
    private Button refrech;

    @FXML
    private TextField numCrediterTF;

    @FXML
    private Button virementCpt;

    String query = null;
    Connection connection = singeltonConnexion.getConn();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    client clt = null;
    private boolean update;
    int numC;
    
    @FXML
    void virementCompte(ActionEvent  event) {

    	Connection connection = singeltonConnexion.getConn();
     
            insert();
            clean(event);
            
    }


    private void insert()  {
    	 try {
    		 int numCR = Integer.parseInt(numCrediterTF.getText());
           int numDEB = Integer.parseInt(numDebTF.getText());
           double solde =Double.parseDouble(soldeTF.getText());
metier m=new metier();
        
			m.VirementDeuxCompte(solde, numDEB, numCR);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@FXML
    void clean(ActionEvent event) {
    	numCrediterTF.setText(null);
    	numDebTF.setText(null);
    	soldeTF.setText(null);
    	
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
