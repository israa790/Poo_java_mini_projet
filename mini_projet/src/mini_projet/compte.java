package mini_projet;

public class compte {

	private int numC;
	private double solde;
	private double dec_max;
	private double debi_max;
	private String situation;
	private int code_cli;
	
	
	public compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public compte(int numC,double solde, double dec_max, double debi_max, String situation, int code_cli) {
		super();
		this.numC=numC;
		this.solde = solde;
		this.dec_max = dec_max;
		this.debi_max = debi_max;
		this.situation = situation;
		this.code_cli = code_cli;
	}
	public int getNumC() {
		return numC;
	}
	public void setNumC(int numC) {
		this.numC = numC;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public double getDec_max() {
		return dec_max;
	}
	public void setDec_max(double dec_max) {
		this.dec_max = dec_max;
	}
	public double getDebi_max() {
		return debi_max;
	}
	public void setDebi_max(double debi_max) {
		this.debi_max = debi_max;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public int getCode_cli() {
		return code_cli;
	}
	public void setCode_cli(int code_cli) {
		this.code_cli = code_cli;
	}
	
}
