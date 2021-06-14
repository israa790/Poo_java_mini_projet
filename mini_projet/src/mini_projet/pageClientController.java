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

public class pageClientController implements Initializable {

    @FXML
    private TableColumn<client, String> nomCol;

    @FXML
    private TableColumn<client, String> prenomCol;

    @FXML
    private Button addclient;

    @FXML
    private Button refrech;

    @FXML
    private TableColumn<client, String> adresseCol;

    @FXML
    private TableColumn<client, String> actionCol;

    @FXML
    private TableColumn<client, String> codeClientCol;

    @FXML
    private Button close;

    @FXML
    private TableView<client> table;

    String query = null;
    Connection connection = singeltonConnexion.getConn();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    client cpt = null ;
    
    ObservableList<client>  clientList = FXCollections.observableArrayList();
   

    @FXML
    private void refrechTable() {
        try {
        	clientList.clear();
            
            query = "SELECT * FROM client";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	clientList.add(new  client(
            			
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("adresse")));
            	table.setItems(clientList);
                
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
    private void getAddClient(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ajouterClient.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 this.loadDate();
		
	}
	private void loadDate() {

		refrechTable();
        
		codeClientCol.setCellValueFactory(new PropertyValueFactory<>("idTit"));
       nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
       
	    //add cell of button edit 
        Callback<TableColumn<client, String>, TableCell<client, String>> cellFoctory = (TableColumn<client, String> param) -> {
           // make cell containing buttons
           final TableCell<client, String> cell = new TableCell<client, String>() {
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
                               query = "DELETE FROM client WHERE id  = ?";
                               connection = singeltonConnexion.getConn();
                               preparedStatement = connection.prepareStatement(query);
                               preparedStatement.setInt(1, cpt.getIdTit());
                               preparedStatement.execute();
                               refrechTable();
                               
                           } catch (SQLException ex) {
                             //  Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                         
                           
                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           cpt = table.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("ajouterClient.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                             //  Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           AjouterClientController ajouterClientController = loader.getController();
                           ajouterClientController.setUpdate(true);
                           ajouterClientController.setTextField(cpt.getIdTit(), cpt.getNom(), 
                                   cpt.getPrenom(),cpt.getAdresse());
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
        table.setItems(clientList);
        
        
   
		
	}

}
