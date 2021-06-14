package mini_projet.exemple1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mini_projet.client;
import mini_projet.compte;
import mini_projet.singeltonConnexion;


public class metier {

	public void ajouterClient(client c) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "insert into client(nom,prenom,adresse) values( ?, ?, ?)";
		try {
			
			PreparedStatement st = cnx.prepareStatement(query);
			st.setString(1, c.getNom());
			st.setString(2, c.getPrenom());
			st.setString(3, c.getAdresse());
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}
	public void ModifierClient(int id,client c) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "update client set nom=? ,prenom=? ,adresse=? where idTit= ?";
		try {
			
			PreparedStatement st = cnx.prepareStatement(query);
			st.setString(1, c.getNom());
			st.setString(2, c.getPrenom());
			st.setString(3, c.getAdresse());
			st.setInt(4, id);
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}

	public void SupprimerClient(int id) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "delete from client  where idTit= ?";
		try {
			
			PreparedStatement st = cnx.prepareStatement(query);
			
			st.setInt(1, id);
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}
	
	public void ListeClient() throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "select * from client";
		try {
			
			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getInt("idTit") + " – " + rs.getString("nom")
				+ " – " + rs.getString("prenom")+ " – " + rs.getString("adresse"));}}

				
		 catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}
	}
	
	
	
	///////////////////////Compte/////////////
	
	
	
	
	public void ajouterCompte(compte cpt) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "insert into compte(solde,dec_max,debi_max,situation,code_cli) values( ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = cnx.prepareStatement(query);
			st.setDouble(1, cpt.getSolde());
			st.setDouble(2, cpt.getDec_max());
			st.setDouble(3, cpt.getDebi_max());
			st.setString(4, cpt.getSituation());
			st.setInt(5, cpt.getCode_cli());
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}
	public void modifierCompte(int n,compte cpt) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "update compte set solde=?, dec_max=?, debi_max=?, situation=?, code_cli=? where numC=?";
		try {
			
			PreparedStatement st = cnx.prepareStatement(query);
			st.setDouble(1, cpt.getSolde());
			st.setDouble(2, cpt.getDec_max());
			st.setDouble(3, cpt.getDebi_max());
			st.setString(4, cpt.getSituation());
			st.setInt(5, cpt.getCode_cli());
			st.setInt(6, n);
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}
	public void supprimerCompte(int n) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "delete from compte where numC=?";
		try {
			
			PreparedStatement st = cnx.prepareStatement(query);
			st.setInt(1, n);
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}

	public void ListeCompte() throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "select * from compte";
		try {
			
			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getInt("numC") + " – " + rs.getDouble("solde")
				+ " – " + rs.getDouble("dec_max")+ " – " + rs.getDouble("debi_max")
				+ " – " + rs.getString("situation")+ " – " + rs.getInt("code_cli"));}}

				
		 catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}
	}

	
	////////////compte max solde

	public void CompteMaxSolde() throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "select * from compte c"
				+ " where  "//c.code_cli=clt.idTit
				+ "  solde=(select max(solde) from compte)";
		try {
			
			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getInt("numC") + " – " + rs.getDouble("solde")
				+ " – " + rs.getDouble("dec_max")+ " – " + rs.getDouble("debi_max")
				+ " – " + rs.getString("situation")+ " – " + rs.getInt("code_cli")
				);}}

				
		 catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}
	}
	///////liste compte à decouvert
	public void ListeCompteDecouvert() throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "select * from compte c where"
				+ "  lower(situation)='decouvert'";
		try {
			
			Statement st = cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {			
	int n=rs.getInt("numC");
	int cdl=rs.getInt("code_cli");
	double s=rs.getDouble("solde");
	double cm= rs.getDouble("dec_max");
	double bm=rs.getDouble("debi_max");
	String sit=rs.getString("situation");
	System.out.println("----------------");
	System.out.println("numéro de compte : "+n+"\n solde : "+s+"\n decouvert max : "+cm+"\n debit max : "+bm+
			"\n situation : "+sit);
	}
	
		}
		 catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}
	}
	
	//////// créditer un compte
	
	public void CreditCompte(double s,int n) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "update compte set solde=solde + ? where numC= ? ";
		try {
		
			PreparedStatement st = cnx.prepareStatement(query);
			st.setDouble(1, s);
			st.setDouble(2, n);
			st.executeUpdate();
System.out.println("l'ajout est effectuee ");
		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
		}

	}
	
	
	///////////debiter un compte
	
	public boolean DebiterCompte(double s,int n) throws ClassNotFoundException {
		Connection cnx =singeltonConnexion.getConn();
		String query = "update compte set solde=solde - ? where numC= ? and "
				+ "solde - ? >= dec_max";
		try {
			
			PreparedStatement st = cnx.prepareStatement(query);
			st.setDouble(1, s);
			st.setDouble(2, n);
			st.setDouble(3, s);
			if(st.executeUpdate()==1)
			{
                System.out.println("le retirement de compte est effectuee ");
                 return true;
}
		} catch (SQLException e) {
			System.err.println("Error executing query: " + e.getMessage());
			
		}
		return false;

	}

	public void VirementDeuxCompte(double s,int c1,int c2) throws ClassNotFoundException {
		
    if(this.DebiterCompte(s, c1))
    {
    	this.CreditCompte(s, c2);
    }

	}
	
}
