
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class sqlDriver {
	private String Driver;
	private String URL; 
	private String user;
	private String password; 
	private Connection c; 
	
	public sqlDriver(String URL, String user, String password) {
		this.URL = URL;
	   Driver = "com.mysql.cj.jdbc.Driver";
		this.user = user;
		this.password = password; 
	}
	
public void connectSQL() throws Exception{
	
   		Class.forName(Driver);
	      //OPEN SQL DATABASE CONNECTION 
	      c = DriverManager.getConnection(URL, user, password);
}
public void insertData(String database) throws Exception{

	//CREATE DATE FOR INSERTION
   Calendar calendar = Calendar.getInstance();
   java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

	      //SQL QUERY
	      String query = "INSERT INTO " + database +" VALUES(?, ?, ?)";

	      //FORMAT QUERY
	      PreparedStatement preparedStmt = c.prepareStatement(query);
	      preparedStmt.setString (1, "1");
	      preparedStmt.setString (2, "testCommit");
	      preparedStmt.setDate   (3, startDate);
	      
	      //EXECUTE QUERY
	      preparedStmt.execute();
	      
	      //CLOSE CONNECTION
	      c.close();
}


	public static void main(String[] args) throws Exception {
	    String user, password, ip, port, database, URL; 
		Scanner scan = new Scanner(System.in); 
		
		System.out.print("IP Address: ");
		ip = scan.next(); 
		System.out.print("Port: ");
		port = scan.next(); 
		System.out.println("Database: ");
		database = scan.next(); 
		
		System.out.print("Username: ");
		user = scan.next();
		System.out.print("Password: ");
		password = scan.next();
		
		URL = "jdbc:mysql://" + ip + ":" + port + "/" + database;
		sqlDriver x = new sqlDriver(URL, user, password); 
		x.connectSQL();
		x.insertData("friendsList(id, name, birthday)");
		scan.close(); 
	}
}

