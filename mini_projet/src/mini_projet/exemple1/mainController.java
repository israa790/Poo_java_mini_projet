package mini_projet.exemple1;

import java.sql.SQLException;
import java.sql.Statement;

import mini_projet.singeltonConnexion;

public class mainController {

	public static void main (String [] args) throws ClassNotFoundException {
	singeltonConnexion cnx=new singeltonConnexion();
	//cnx.getConn();
	
	try {
		Statement stmt = cnx.getConn().createStatement();
	/*	client clt1=new client("Kim","Tae","Korea");
		compte cpt=new compte(1500,800,1000,"decouvert",1);
		compte cpt2=new compte(900,800,1000,"NonDecouvert",1);
		compte cpt3=new compte(1200,800,1000,"NonDecouvert",1);
		metier m=new metier();*/
		//m.ajouterClient(clt1);
        //m.ModifierClient(1, clt1);
		//m.SupprimerClient(2);
		//m.ListeClient();
		
		//m.ajouterCompte(cpt);
		//m.modifierCompte(7,cpt);
		//m.supprimerCompte(7);
		//m.ListeCompte();
		System.out.println("le compte ayant le solde le plus élevé est ");
		//m.CompteMaxSolde();
		System.out.println("\n la liste des comptes à decouvert ");
		//m.ListeCompteDecouvert();
		System.out.println("\n Crédit compte");
		//m.CreditCompte(400, 2);
		System.out.println("\n retirer compte");
		//m.DebiterCompte(100, 2);
		System.out.println("\n virement de deux comptes");
	//	m.VirementDeuxCompte(200, 4, 2);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	}
}
