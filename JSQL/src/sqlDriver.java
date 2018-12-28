
import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

public class sqlDriver {
	private String Driver;
	private String URL; 
	private String user;
	private String password; 
	private Connection c;
	private ResultSet r; 
	
	public sqlDriver(String URL, String user, String password) {
		this.URL = URL;
	   Driver = "com.mysql.cj.jdbc.Driver";
		this.user = user;
		this.password = password;
		c = null;
	}
	
public void connectSQL() throws Exception{
	
   		Class.forName(Driver);
	      //OPEN SQL DATABASE CONNECTION 
	      c = DriverManager.getConnection(URL, user, password);
}
public PreparedStatement insertData(String database) throws Exception{
	if(database == null) {
		return null; 
	}
	//CREATE DATE FOR INSERTION
   Calendar calendar = Calendar.getInstance();
   java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

   
   
	      //SQL QUERY
	      String query = "INSERT INTO " + database +" VALUES(?, ?, ?)";
	      
	      //FORMAT QUERY
	      PreparedStatement preparedStmt = c.prepareStatement(query);
	      preparedStmt.setString (1, "4");
	      preparedStmt.setString (2, "Richard K");
	      preparedStmt.setDate   (3, startDate);
	      return preparedStmt; 
}

public PreparedStatement makeQuery(String query) throws SQLException {
   PreparedStatement preparedStmt = c.prepareStatement(query);
   return preparedStmt; 
}

public void sendQuery(PreparedStatement ps) throws Exception{
	if(ps == null) {
		return;
	}

   //EXECUTE QUERY
   ps.execute();
   
   //CLOSE CONNECTION
   c.close();
	
}

public void getData(String query) throws SQLException {
	Statement s; 
	try {
		s = c.createStatement();
		ResultSet r = s.executeQuery(query); 
		while(r.next()) {
			int ID = r.getInt("id");
			String name = r.getString("name");
			String birthday = r.getString("birthday");
			String email = r.getString("email");
			System.out.println(ID + "\t" + name + "\t" + birthday + "\t" + email);
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
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
		//x.sendQuery(x.insertData("friendsList(id, name, birthday)"));
		x.getData("SELECT * FROM friendsList;");
		scan.close(); 
	}
}

