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

public class CreditController implements Initializable {

    @FXML
    private TextField numeroTF;

    @FXML
    private TextField soldeTF;

    @FXML
    private Button refrech;

    @FXML
    private Button crediterCpt;
    String query = null;
    Connection connection = singeltonConnexion.getConn();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    client clt = null;
    private boolean update;
    int numC;


    @FXML
    void crediterCompte(MouseEvent event) {

    	Connection connection = singeltonConnexion.getConn();
        int numero = Integer.parseInt(numeroTF.getText());
        double solde =Double.parseDouble(soldeTF.getText());
       
            getQuery();
            insert();
            clean(event);
    }


    @FXML
    void clean(MouseEvent event) {
    	numeroTF.setText(null);
    	soldeTF.setText(null);
    }
    
    private void getQuery() {
     
            query = "update compte set solde= solde+ ? where numC= ?";
    }

    private void insert() {

        try {
        	  int numero = Integer.parseInt(numeroTF.getText());
              double solde =Double.parseDouble(soldeTF.getText());
              
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, solde);
            preparedStatement.setInt(2, numero);
            preparedStatement.execute();

        } catch (SQLException ex) {
           // Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
