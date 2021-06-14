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
import javafx.scene.control.TextField;

public class AjouterCompteController implements Initializable {

	   @FXML
	    private TextField debitTF;

	    @FXML
	    private TextField situationTF;

	    @FXML
	    private TextField soldeTF;

	    @FXML
	    private TextField codeClientTF;

	    @FXML
	    private TextField decouvertTF;

	  //  @FXML
	   // void 3b90ff(ActionEvent event) {}

	    @FXML
	    
	    String query = null;
	    Connection connection = singeltonConnexion.getConn();
	    PreparedStatement preparedStatement = null ;
	    ResultSet resultSet = null ;
	    compte cpt = null ;
	    int numC;
	    private boolean update;
	
	    @FXML
	    void addCompte(ActionEvent event) {
	    	
	        String situation2 = situationTF.getText();
	        String solde2 =soldeTF.getText();
	        String dec_max2=decouvertTF.getText();
	        String debi_max2=debitTF.getText();
	        String code_cli2=codeClientTF.getText();

	         if (solde2.isEmpty() || situation2.isEmpty() || code_cli2.isEmpty() || dec_max2.isEmpty() || debi_max2.isEmpty()) {
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setContentText("Please Fill All DATA");
	             alert.showAndWait();

	         } else {
	        	
	             getQuery();
	             insert();
	             clean();

	         }
	    }
	    @FXML
	   private void clean() {
		   situationTF.setText(null);
	    	soldeTF.setText(null);
	    	decouvertTF.setText(null);
	    	debitTF.setText(null);
	    	codeClientTF.setText(null);
	        
			
		}



	    private void getQuery() {
	    	   if (update == false) {
	                
	            	query = "INSERT INTO compte(solde,dec_max,debi_max,situation,code_cli) VALUES (?,?,?,?,?)";

	            }else{
	                query = "UPDATE compte SET "
	                        + "solde=?,"
	                        + "dec_max=?,"
	                        + "debi_max=?,"
	                        + "situation=?,"
	                        + "code_cli= ? WHERE numC = '"+numC+"'";
	            }

	        }

		private void insert() {

	        try {
	        

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setDouble(1, Double.parseDouble(soldeTF.getText()));
	            preparedStatement.setDouble(2, Double.parseDouble(decouvertTF.getText()));
	            preparedStatement.setDouble(3, Double.parseDouble(debitTF.getText()));
	            preparedStatement.setString(4, situationTF.getText());
	            preparedStatement.setInt(5, Integer.parseInt(codeClientTF.getText()));
	            preparedStatement.execute();

	        } catch (SQLException ex) {
	          //  Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
	        }
			
		}

		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	void setTextField( int numC,double solde, double d, double e, String situation,int code_cli) {

       
       
    }
	
	void setUpdate(boolean b) {
        this.update = b;

    }
	
	

	
}
