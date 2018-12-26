
import java.sql.*;
import java.util.Calendar;
public class sqlDriver {
	
	private String Driver;
	private String URL; 
	
	public sqlDriver(String URL, String Driver) {
		this.URL = URL;
		this.Driver = Driver; 
	}
public void insertData(String database) {
	    try{
	      Class.forName(Driver);
	      //OPEN SQL DATABASE CONNECTION 
	      Connection conn = DriverManager.getConnection(URL, "root", "");
	    
	      //CREATE DATE FOR INSERTION
	      Calendar calendar = Calendar.getInstance();
	      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

	      //SQL QUERY
	      String query = "INSERT INTO " + database +" VALUES(?, ?, ?)";

	      //FORMAT QUERY
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, "1");
	      preparedStmt.setString (2, "testCommit");
	      preparedStmt.setDate   (3, startDate);
	      
	      //EXECUTE QUERY
	      preparedStmt.execute();
	      
	      //CLOSE CONNECTION
	      conn.close();
	    }
	    catch (Exception e){
	      System.err.println("ERROR PRINTING DATA. "
	      		+ "CHECK DATABASE CONNECTION.");
	    }
	  }


	public static void main(String[] args) {
	    String Driver = "org.gjt.mm.mysql.Driver";
	    String URL = "jdbc:mysql://localhost/";
	    
		
		sqlDriver x = new sqlDriver(Driver, URL); 
		x.insertData("friendsList(id, name, birthday)");
	}
}

