package mini_projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class singeltonConnexion {

	private static Connection conn;
	static
	{
		//String url = "jdbc:mysql://localhost:3306/tpjava_avance";
		String url = "jdbc:mysql://localhost:3306/porg_avance_projet";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn= DriverManager.getConnection(url, "root", "");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
	public static Connection getConn() {
		return conn;
	}
}
