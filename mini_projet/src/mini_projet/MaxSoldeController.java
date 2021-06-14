package mini_projet;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MaxSoldeController implements Initializable {

    @FXML
    private TableColumn<compte, String> decMaxCol;

    @FXML
    private TableColumn<compte, String> debiMaxCol;

    @FXML
    private TableColumn<compte, String> numCol;

    @FXML
    private TableColumn<compte, String> soldeCol;

    @FXML
    private TableColumn<compte, String> situationCol;

    @FXML
    private TableColumn<compte, String> actionCol;

    @FXML
    private TableColumn<compte, String> codeClientCol;

    @FXML
    private Button close;

    @FXML
    private TableView<compte> table;
    
    String query = null;
    Connection connection = singeltonConnexion.getConn();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    compte cpt = null ;
    
    ObservableList<compte>  CompteList = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 this.loadDate();
		
	}

	private void loadDate() {

		refrechTable();
        
		numCol.setCellValueFactory(new PropertyValueFactory<>("numC"));
        soldeCol.setCellValueFactory(new PropertyValueFactory<>("solde"));
        decMaxCol.setCellValueFactory(new PropertyValueFactory<>("dec_max"));
        debiMaxCol.setCellValueFactory(new PropertyValueFactory<>("debi_max"));
        situationCol.setCellValueFactory(new PropertyValueFactory<>("situation"));
        codeClientCol.setCellValueFactory(new PropertyValueFactory<>("code_cli"));
	}

    @FXML
    private void refrechTable() {
        try {
        	CompteList.clear();
            
            query = "SELECT * FROM compte where solde=(select max(solde) from compte)";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	CompteList.add(new  compte(
            			resultSet.getInt("numC"),
                        resultSet.getDouble("solde"),
                        resultSet.getDouble("dec_max"),
                        resultSet.getDouble("debi_max"),
                        resultSet.getString("situation"),
                        resultSet.getInt("code_cli")
            			));
            	table.setItems(CompteList);
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("controller erreur");
        }
       
    }

    @FXML
    void close(ActionEvent event) {
    	 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        stage.close();
    }

}
