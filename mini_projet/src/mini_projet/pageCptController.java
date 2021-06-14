package mini_projet;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class pageCptController implements Initializable{

    @FXML
    private TableColumn<compte, String> decMaxCol;

    @FXML
    private TableView<compte> table;

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
    private Button addCompte;
    
    @FXML
    private Button refrech;
    
    @FXML
    private Button close;
    
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
	      //add cell of button edit 
        Callback<TableColumn<compte, String>, TableCell<compte, String>> cellFoctory = (TableColumn<compte, String> param) -> {
           // make cell containing buttons
           final TableCell<compte, String> cell = new TableCell<compte, String>() {
           	  @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {

                       Button deleteIcon = new Button("Supprimer");
                       Button editIcon = new Button("Modifier");

                       deleteIcon.setStyle(
                               " -fx-cursor: hand ;"
                               + "-glyph-size:28px;"
                               + "-fx-fill:#ff1744;"
                       );
                       editIcon.setStyle(
                               " -fx-cursor: hand ;"
                               + "-glyph-size:28px;"
                               + "-fx-fill:#00E676;"
                       );
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           try {
                               cpt = table.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM compte WHERE id  = ?";
                               connection = singeltonConnexion.getConn();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.setInt(1, cpt.getNumC());
                               preparedStatement.execute();
                               refrechTable();
                               
                           } catch (SQLException ex) {
                             //  Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                         
                           
                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           cpt = table.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("ajouterCpt.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                             //  Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           AjouterCompteController ajouterCompteController = loader.getController();
                           ajouterCompteController.setUpdate(true);
                           ajouterCompteController.setTextField(cpt.getNumC(), cpt.getSolde(), 
                                   cpt.getDec_max(),cpt.getDebi_max(), cpt.getSituation(),cpt.getCode_cli());
                           Parent parent = loader.getRoot();
                           Stage stage = new Stage();
                           stage.setScene(new Scene(parent));
                           stage.initStyle(StageStyle.UTILITY);
                           stage.show();
                           

                          

                       });
////////////hbox
                       HBox managebtn = new HBox(editIcon, deleteIcon);
                       managebtn.setStyle("-fx-alignment:center");
                       HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                       HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                       setGraphic(managebtn);

                       setText(null);

                   }
               }

           };

           return cell;
       };
       actionCol.setCellFactory(cellFoctory);
        table.setItems(CompteList);
        
        
   
		
	}
    
	  @FXML
	    private void refrechTable() {
	        try {
	        	CompteList.clear();
	            
	            query = "SELECT * FROM compte";
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
	    private void close(MouseEvent event) {
	        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        stage.close();
	    }

	    @FXML
	    private void getAddView(MouseEvent event) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("ajouterCpt.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.show();
	        } catch (IOException ex) {
	            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	    }

}