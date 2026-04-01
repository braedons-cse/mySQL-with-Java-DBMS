import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;


/**
 * Class to handle SQL querying 
 */

public class SQL {
	private static PreparedStatement ps;
	
    /**
     * Queries the database and prints the results.
     * 
     * @param conn a connection object
     * @param sql a SQL statement that returns rows:
     * 		this query is written with the Statement class, typically 
     * 		used for static SQL SELECT statements.
     */
    public static void sqlQuery(Connection conn, String sql){
        try {
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	for (int i = 1; i <= columnCount; i++) {
        		String value = rsmd.getColumnName(i);
        		System.out.print(value);
        		if (i < columnCount) System.out.print(",  ");
        	}
			System.out.print("\n");
        	while (rs.next()) {
        		for (int i = 1; i <= columnCount; i++) {
        			String columnValue = rs.getString(i);
            		System.out.print(columnValue);
            		if (i < columnCount) System.out.print(",  ");
        		}
    			System.out.print("\n");
        	}
        	rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Queries the database and prints the results.
     * 
     * @param conn a connection object
     * @param sql a SQL statement that returns rows:
     * 		this query is written with the PrepareStatement class, typically 
     * 		used for dynamic SQL SELECT statements.
     */
    public static void sqlQuery(Connection conn, PreparedStatement sql){
        try {
        	ResultSet rs = sql.executeQuery();
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	for (int i = 1; i <= columnCount; i++) {
        		String value = rsmd.getColumnName(i);
        		System.out.print(value);
        		if (i < columnCount) System.out.print(",  ");
        	}
			System.out.print("\n");
        	while (rs.next()) {
        		for (int i = 1; i <= columnCount; i++) {
        			String columnValue = rs.getString(i);
            		System.out.print(columnValue);
            		if (i < columnCount) System.out.print(",  ");
        		}
    			System.out.print("\n");
        	}
        	rs.close();
        	ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    /**
     * Menu/submenu queries below
     */
    
    
    
   public static void ps_buildCustProfile(int id, int dist, String addy, String date, String f, String l, String email, long p) {	//inserts Customer profile
     try{
   	  String sql = "INSERT INTO CUSTOMER(User_id, Fname, Lname, Phone_num, Email, Address, Warehouse_dist, Start_date) VALUES(?,?,?,?,?,?,?,?)";
   	  ps = App.conn.prepareStatement(sql);
   	  ps.setInt(1,id);
   	  ps.setString(2,f);
   	  ps.setString(3,l);
   	  ps.setLong(4,p);
   	  ps.setString(5,email);
   	  ps.setString(6,addy);
   	  ps.setInt(7,dist);
   	  ps.setString(8,date);
     }
     catch (SQLException e) {
   	  System.out.println(e.getMessage());
     }
     
     sqlQuery(App.conn, ps); 
   }
   
   public static void ps_findCustByID(int id) {
	      try{
	    	  String sql = "SELECT * FROM CUSTOMER WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
   }
   
   public static void ps_removeCustomer(int id) {
	      try{
	    	  String sql = "DELETE FROM CUSTOMER WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_setUserID(int id, int c_ID) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET User_id = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	    	  ps.setInt(2, c_ID);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
   }
   
   public static void ps_setWarehouseDist(int dist, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Warehouse_dist = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, dist);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_setAddress(String addy, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Address = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, addy);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_setStartDate(String date, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Start_date = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  //translate date to sql.date
	    	  Date sqlDate = Date.valueOf(date);
	    	  ps.setDate(1, sqlDate);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}

   public static void ps_setFName(String f, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Fname = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, f);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_setLName(String l, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Fname = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, l);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_setEmail(String e, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Email = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, e);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException exc) {
	    	  System.out.println(exc.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_setPhoneNum(long num, int id) {
	      try{
	    	  String sql = "UPDATE CUSTOMER SET Phone_num = ? WHERE User_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setLong(1, num);
	    	  ps.setInt(2, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   
   public static void ps_buildWHMProfile(int id, String f, String l, long p, String e, int sal) {	//inserts Warehouse Manager profile
	     try{
	      String sql = "INSERT INTO WAREHOUSE_MANAGER(Emp_id, Fname, Lname, Phone_num, Email, Salary) VALUES(?,?,?,?,?,?)";
	   	  ps = App.conn.prepareStatement(sql);
	   	  ps.setInt(1, id);
	   	  ps.setString(2, f);
	   	  ps.setString(3, l);
	   	  ps.setLong(4, p);
	   	  ps.setString(5, e);
	   	  ps.setInt(6, sal);
	    
	     }
	     catch (SQLException exc) {
	   	  System.out.println(exc.getMessage());
	     }
	     
	     sqlQuery(App.conn, ps); 
	   }
   
   
   public static void ps_buildSEProfile(int id, String f, String l, long p, String e, int w, boolean a, String sp) {	//inserts Service Employee profile
	     try{
	    	 String sql = "INSERT INTO SERVICE_EMPLOYEE(Emp_id, Fname, Lname, Phone_num, Email, Specialty, Wage, Availability) VALUES(?,?,?,?,?,?,?,?)";
		   	  ps = App.conn.prepareStatement(sql);
		   	  ps.setInt(1, id);
		   	  ps.setString(2, f);
		   	  ps.setString(3, l);
		   	  ps.setLong(4, p);
		   	  ps.setString(5, e);
		   	  ps.setString(6, sp);
		   	  ps.setInt(7, w);
		   	  ps.setBoolean(8, a);
	    
	     }
	     catch (SQLException exc) {
	   	  System.out.println(exc.getMessage());
	     }
	     
	     sqlQuery(App.conn, ps); 
	   }
   
   public static void ps_findWHMByID(int id) {
	      try{
	    	  String sql = "SELECT * FROM WAREHOUSE_MANAGER WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_findSEByID(int id) {
	      try{
	    	  String sql = "SELECT * FROM SERVICE_EMPLOYEE WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
    
    
   public static void ps_removeWHM(int id) {
	      try{
	    	  String sql = "DELETE FROM WAREHOUSE_MANAGER WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_removeSE(int id) {
	      try{
	    	  String sql = "DELETE FROM SERVICE_EMPLOYEE WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   
 
   
   
   public static void ps_WHMsetEmpID(int id, int e_id) {
	      try{
	    	  String sql = "UPDATE WAREHOUSE_MANAGER SET Emp_id = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_WHMsetFname(String f, int e_id) {
	      try{
	    	  String sql = "UPDATE WAREHOUSE_MANAGER SET Fname = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, f);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_WHMsetLname(String l, int e_id) {
	      try{
	    	  String sql = "UPDATE WAREHOUSE_MANAGER SET Lname = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, l);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_WHMsetPhoneNum(long p, int e_id) {
	      try{
	    	  String sql = "UPDATE WAREHOUSE_MANAGER SET Phone_num = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setLong(1, p);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_WHMsetEmail(String email, int e_id) {
	      try{
	    	  String sql = "UPDATE WAREHOUSE_MANAGER SET Email = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, email);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_WHMsetSalary(int sal, int e_id) {
	      try{
	    	  String sql = "UPDATE WAREHOUSE_MANAGER SET Salary = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, sal);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_SEsetEmpID(int id, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Emp_id = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_SEsetFname(String f, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Fname = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, f);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_SEsetLname(String l, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Lname = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, l);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_SEsetEmail(String email, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Email = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, email);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_SEsetPhoneNum(long p, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Phone_num = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setLong(1, p);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_SEsetWage(int w, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Wage = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, w);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_SEsetSpecialty(String sp, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Specialty = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setString(1, sp);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_SEsetAvailability(boolean a, int e_id) {
	      try{
	    	  String sql = "UPDATE SERVICE_EMPLOYEE SET Availability = ? WHERE Emp_id = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setBoolean(1, a);
	    	  ps.setInt(2, e_id);
	     
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_UsefulReport1(int id) {
	      try{
	    	  System.out.println("Total Number of Equipment Items Rented by Given Customer:");
	    	  String sql = "SELECT Type, Date_of_arrival FROM CUSTOMER AS C, RENTAL AS R, EQUIPMENT_RENTALS AS ER, EQUIPMENT AS E WHERE C.User_id = R.Rented_from_customer AND R.Rental_Number = ER.Rental_no AND E.Serial_Number = ER.Equip_serial_no AND C.User_id = ?;";
	    	  		
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, id);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_UsefulReport2() {
	      try{
	    	  System.out.println("Most Popular Item:");
	    	  String sql = "SELECT Serial_number AS Item_Serial_Number, COUNT(*) AS Items_Rented, SUM(CAST (JulianDay(Date_of_return) - JulianDay(Date_of_arrival) AS Integer)) AS Days_Rented FROM Equipment, Equipment_Rentals, Rental WHERE Serial_number = Equip_serial_no AND Rental_number = Rental_no GROUP BY Serial_Number ORDER BY Days_Rented Desc;";
	    	  ps = App.conn.prepareStatement(sql);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_UsefulReport3() {
	      try{
	    	  System.out.println("Most Popular Manufacturer by Rented Units:");
	    	  
	    	  String sql = "SELECT Manufacturer, MAX(Items_Rented) FROM( SELECT Manufacturer, COUNT(*) AS Items_Rented FROM Equipment, Equipment_Rentals WHERE Serial_number = Equip_serial_no GROUP BY Manufacturer)";
	    	  ps = App.conn.prepareStatement(sql);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_UsefulReport4() {
	      try{
	    	  System.out.println("Most Popular Drone:");
	    	  
	    	  String sql = "SELECT Serial_Number as DRONE, Total_Distance FROM( SELECT Serial_Number, SUM(Dist) AS Total_Distance, SUM(items) AS Items_Delivered FROM (SELECT Serial_number, SUM(warehouse_dist) AS Dist, SUM(No_of_items) AS items FROM Customer, Rental, Drone WHERE user_id = rented_from_customer AND pickup_drone = serial_number GROUP BY Serial_number UNION ALL SELECT Serial_number, SUM(warehouse_dist) AS Dist, SUM(No_of_items) AS items FROM Customer, Rental, Drone WHERE user_id = rented_from_customer AND delivery_drone = serial_number GROUP BY Serial_number) Group BY Serial_number ORDER BY Items_Delivered desc) LIMIT 1;";
	    	  ps = App.conn.prepareStatement(sql);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_UsefulReport5() {
	      try{
	    	  System.out.println("Member With Most Items Checked Out:");
	    	  
	    	  String sql = "SELECT Fname, Lname, User_id, MAX(No_of_items) FROM( SELECT Fname, Lname, User_id, COUNT(Rented_from_customer), No_of_items FROM CUSTOMER, RENTAL, EQUIPMENT_RENTALS WHERE User_id = Rented_from_customer AND Rental_Number = Rental_no GROUP BY User_id)";
	    	  ps = App.conn.prepareStatement(sql);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_UsefulReport6(int year) {
	      try{
	    	  
	    	  System.out.println("Equipment By Type Released Before " + year + ":");
	    	  
	    	  String sql = "SELECT Description, Type FROM EQUIPMENT WHERE Year <= ? ORDER BY Type DESC";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, year);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   
   
   public static void ps_addNewRental(int rentalNo, int cost, int quantity, Date doa, Date dor, int pickupDrone, int deliveryDrone, int custID) {
	      try {    	      	  
	    	  String sql = "INSERT INTO RENTAL VALUES(?,?,?,?,?,?,?,?);";
	    	  ps = App.conn.prepareStatement(sql);
	    	 ps.setInt(1, rentalNo);
	    	 ps.setInt(2, cost);
	    	 ps.setInt(3, quantity);
	    	 ps.setDate(4, doa);
	    	 ps.setDate(5, dor);
	    	 ps.setInt(6, deliveryDrone);
	    	 ps.setInt(7, pickupDrone);
	    	 ps.setInt(8, custID);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_addNewEquipmentRental(int rentalNo, int sNo) {
	      try {    	      	  
	    	  String sql = "INSERT INTO EQUIPMENT_RENTALS VALUES(?,?);";
	    	  ps = App.conn.prepareStatement(sql);
	    	  ps.setInt(1, rentalNo);
	    	  ps.setInt(2, sNo);    
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_return(int rentalNo, int custID) {
	      try {    	      	  
	    	  String sql = "DELETE FROM RENTAL WHERE Rental_Number = ? AND Rented_from_customer = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	 ps.setInt(1, rentalNo);
	    	 ps.setInt(2, custID);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   public static void ps_updateDelivery(int droneSNo, int custID, Date deliveryDate) {
	      try {    	      	  
	    	  String sql = "UPDATE RENTAL SET Delivery_drone = ?, Date_of_arrival = ? WHERE Rented_from_customer = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	 ps.setInt(1, droneSNo);
	    	 ps.setDate(2, deliveryDate);
	    	 ps.setInt(3, custID);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   public static void ps_updatePickup(int droneSNo, int custID, Date pickupDate) {
	      try {    	      	  
	    	  String sql = "UPDATE RENTAL SET Pickup_drone = ?, Date_of_return = ? WHERE Rented_from_customer = ?";
	    	  ps = App.conn.prepareStatement(sql);
	    	 ps.setInt(1, droneSNo);
	    	 ps.setDate(2, pickupDate);
	    	 ps.setInt(3, custID);
	     
	      }
	      catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	      }
	      
	      sqlQuery(App.conn, ps); 
}
   
   
    
    
}
