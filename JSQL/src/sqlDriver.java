
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class sqlDriver {
	private String Driver;
	private String URL; 
	private String user;
	private String password; 
	
	public sqlDriver(String URL, String Driver,String user, String password) {
		this.URL = URL;
		this.Driver = Driver; 
		this.user = user;
		this.password = password; 
	}
	
public void insertData(String database) throws Exception{
	
   		Class.forName(Driver);
	      //OPEN SQL DATABASE CONNECTION 
	      Connection conn = DriverManager.getConnection(URL, user, password);
	    
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


	public static void main(String[] args) throws Exception {
	    String Driver = "com.mysql.jdbc.Driver";
	    String URL = "jdbc:mysql://localhost:3306/dnd3e";
	    String user, password; 
	    
		Scanner scan = new Scanner(System.in); 
		System.out.print("Username: ");
		user = scan.next();
		System.out.print("Password: ");
		password = scan.next();
		
		sqlDriver x = new sqlDriver(URL, Driver, user, password); 
		x.insertData("friendsList(id, name, birthday)");
		scan.close(); 
	}
}

