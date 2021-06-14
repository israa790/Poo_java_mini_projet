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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AjouterClientController implements Initializable{

    @FXML
    private Button add;

    @FXML
    private Button refrech;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField adresseTF;

    @FXML
    private TextField prenomTF;

    String query = null;
    Connection connection = singeltonConnexion.getConn();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    client clt = null;
    private boolean update;
    int IdTit;

    
    @FXML
    private void addClient(MouseEvent event) {

    	Connection connection = singeltonConnexion.getConn();
        String nom = nomTF.getText();
        String prenom = prenomTF.getText();
        String adress = adresseTF.getText();
      

            getQuery();
            insert();
            clean(event);

        
    }
        @FXML
        private void clean(MouseEvent event) {
        	nomTF.setText(null);
        	prenomTF.setText(null);
        	adresseTF.setText(null);
            
            
        }
        private void getQuery() {

            if (update == false) {
                
                query = "INSERT INTO client( nom, prenom, adresse) VALUES (?,?,?)";

            }else{
                query = "UPDATE client SET "
                        + "nom=?, "
                        + "prenom=?, "
                        + " adresse=?"
                        + " WHERE id = '"+IdTit+"'";
            }

        }

        private void insert() {

            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, nomTF.getText());
                preparedStatement.setString(2, prenomTF.getText());
                preparedStatement.setString(3, adresseTF.getText());
                preparedStatement.execute();

            } catch (SQLException ex) {
               // Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

  

   // @FXML
   // void clean(ActionEvent event) {}



	public void setTextField(int idTit, String nom, String prenom, String adresse) {
		 

		       IdTit = idTit;
		        nomTF.setText(nom);
		        prenomTF.setText(prenom);
		        adresseTF.setText(adresse);
		       

		    }
	 void setUpdate(boolean b) {
	        this.update = b;

	    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
