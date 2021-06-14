package mini_projet;

public class client {

	private int idTit;
	private String nom;
	private String prenom;
	private String adresse;
	
	
	
/*	public client() {
		super();
		// TODO Auto-generated constructor stub
	}*/
	public client(String nom, String prenom, String adresse) {
		super();
		
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getIdTit() {
		return idTit;
	}
	public void setIdTit(int idTit) {
		this.idTit = idTit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	
}
