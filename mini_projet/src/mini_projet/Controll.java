package mini_projet;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controll implements Initializable {

    @FXML
    private Button crediter;

    @FXML
    private Button decouvert;

    @FXML
    private Button debiter;

    @FXML
    private Button virement;

    @FXML
    private Button client;

    @FXML
    private Button solde;

    @FXML
    private Button close;

    @FXML
    private Button compte;

    @FXML
    void gererClient(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("UnitClientControll.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @FXML
    void close(ActionEvent event) {
    	   Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
           stage.close();
    }

    @FXML
    void gererCompte(ActionEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("UnitControll.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void debiterCompte(ActionEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("DebiterCompte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void virementCompte(ActionEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("VirementCompte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void listeDecouvert(ActionEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("DecouvertCompte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void maxSolde(ActionEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("MaxSoldeCompte.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void crediterCompte(ActionEvent event) {
    	try {
            Parent parent = FXMLLoader.load(getClass().getResource("CrediterCompte.fxml"));
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
		// TODO Auto-generated method stub
		
	}


}
