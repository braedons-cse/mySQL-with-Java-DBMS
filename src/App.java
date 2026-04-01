import java.util.ArrayList;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
public class App {
	private static String DATABASE = "rentalCompany (3).db";
	
	public static Connection conn = null;
	
    /**
     * Connects to the database if it exists, creates it if it does not, and returns the connection object.
     * 
     * @param databaseFileName the database file name
     * @return a connection object to the designated database
     */
    public static Connection initializeDB(String databaseFileName) {
    	/**
    	 * The "Connection String" or "Connection URL".
    	 * 
    	 * "jdbc:sqlite:" is the "subprotocol".
    	 * (If this were a SQL Server database it would be "jdbc:sqlserver:".)
    	 */
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
            	// Provides some positive assurance the connection and/or creation was successful.
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The connection to the database was successful.");
            } else {
            	// Provides some feedback in case the connection failed but did not throw an exception.
            	System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("There was a problem connecting to the database.");
        }
        return conn;
    }
	
    
    
    
	/**
	 * Main menu functionality with all submenus included
	 * @param keyboard serves as input source
	 */
    public void menu(Scanner keyboard){
    	Employee e = new Employee();
    	Customer c = new Customer();
        //prints menu
        System.out.println("Profile");
        System.out.println("    |--> Personal Information (enter 1)");
        System.out.println("Rentals");
        System.out.println("    |--> Rent/Return (enter 2)");
        System.out.println("    |--> Deliver/Pickup (enter 3)");
        System.out.println("Reports (enter 4)");
        
               

        int selection = keyboard.nextInt();     //reads in the selected menu option
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        while(selection<5 && selection>0){

        //Navigates to proper menu selection
        if(selection==1){   //Personal Info
            System.out.println("Enter 0 to register a new customer profile");
            System.out.println("Enter 1 to edit an existing customer profile");
            System.out.println("Enter 2 to delete an existing customer profile");
            System.out.println("Enter 3 to search for an existing customer profile");
            System.out.println();
            System.out.println("Enter 4 to register a new employee profile");
            System.out.println("Enter 5 to edit an existing employee profile");
            System.out.println("Enter 6 to delete an existing employee profile");
            System.out.println("Enter 7 to search for an existing employee profile");

            int custOrEmp=keyboard.nextInt();
            keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
            
            
            /**CUSTOMER SUBMENU*/
            
            if(custOrEmp==0){   //register new customer
                c.buildProfile(keyboard);
            }
            
            else if(custOrEmp==1){  //edit customer
                System.out.println("Enter user ID of customer to edit");
                int id=keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                SQL.ps_findCustByID(id);
                
                //edit customer attributes below
                System.out.println("Which part of the customer profile would you like to change? (enter the option exactly as it's displayed below");
                System.out.println("(UserID, warehouse distance, address, first or last name, start date, email, phone number)");
                String attribute = keyboard.nextLine();
                
                if(attribute.equals("UserID")){
                	System.out.println("Enter user ID in numeric form");
                    int i = keyboard.nextInt();
                    keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                    c.setUserID(i,id);
                }
                else if(attribute.equals("warehouse distance")){
                    System.out.println("Enter warehouse distance in numeric form");
                    int dist = keyboard.nextInt();
                    keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                    c.setWarehouseDist(dist,id);
                }
                else if(attribute.equals("address")){
                    System.out.println("Enter address");
                    String addy = keyboard.nextLine();
                    c.setAddress(addy,id);
                }
                else if(attribute.equals("first name")){
                    System.out.println("Enter first name");
                    String f = keyboard.nextLine();
                    c.setFName(f,id);
                }
                else if(attribute.equals("last name")){
                    System.out.println("Enter last name");
                    String l = keyboard.nextLine();
                    c.setLName(l,id);
                }
                else if(attribute.equals("start date")){
                    System.out.println("Enter start date in form yyyy-mm-dd");
                    String date = keyboard.nextLine();
                    c.setStartDate(date,id);
                }
                else if(attribute.equals("email")){
                    System.out.println("Enter email");
                    String email = keyboard.nextLine();
                    c.setEmail(email,id);
                }
                else if(attribute.equals("phone number")){
                    System.out.println("Enter phone number in numeric form xxxxxxxxxx");
                    long p = keyboard.nextLong();
                    keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                    c.setPhoneNum(p,id);
                }
                
                System.out.println("Done editing.");
            }

            else if(custOrEmp==2){  //delete customer
                System.out.println("Enter user ID of customer to delete");
                int id=keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                
              SQL.ps_removeCustomer(id);
                System.out.println("Customer deleted");
                
            }
            else if(custOrEmp==3){  //search for customer
                System.out.println("Enter user ID of customer to find");
                int id=keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                SQL.ps_findCustByID(id);
                    

            }



          /**EMPLOYEE SUBMENU*/
            
            
            else if(custOrEmp==4){  //register emp
                e.buildProfile(keyboard);
             
            }
            else if(custOrEmp==5){  //edit emp
                System.out.println("Enter employee ID of employee to edit");
                int id=keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                System.out.println("Enter which type of employee profile you're editing (1 for Warehouse Manager, 2 for Service Employee");
                int profileType = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                if(profileType == 1) {	//Warehouse Manager
                	  SQL.ps_findWHMByID(id);
                	  
                	//edit employee attributes below
                      System.out.println("Which part of the employee profile would you like to change?");
                      System.out.println("(EmpID, phone number, email, first or last name, salary)");
                      String attribute = keyboard.nextLine();
                      

                      if(attribute.equals("EmpID")){
                          System.out.println("Enter employee ID in numeric form");
                          int i = keyboard.nextInt();
                          keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                          e.WHMsetEmpID(i,id);
                      }
                      else if(attribute.equals("phone number")){
                          System.out.println("Enter phone number in numeric form");
                          long p = keyboard.nextLong();
                          keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                          e.WHMsetPhoneNum(p,id);
                      }
                      else if(attribute.equals("email")){
                          System.out.println("Enter email");
                          String email = keyboard.nextLine();
                          e.WHMsetEmail(email,id);
                      }
                      else if(attribute.equals("first name")){
                          System.out.println("Enter first name");
                          String f = keyboard.nextLine();
                          e.WHMsetFname(f,id);
                      }
                      else if(attribute.equals("last name")){
                          System.out.println("Enter last name");
                          String l = keyboard.nextLine();
                          e.WHMsetLname(l,id);
                      }
                      else if(attribute.equals("salary")){
                          System.out.println("Enter salary in numeric form");
                          int sal = keyboard.nextInt();
                          keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                          e.setSalary(sal,id);
                      }
                      
                      System.out.println("Done editing.");
                }
                
                else if(profileType == 2) {	//Service Employee
                	 SQL.ps_findSEByID(id);
                	 
                	//edit employee attributes below
                     System.out.println("Which part of the employee profile would you like to change?");
                     System.out.println("(EmpID, phone number, email, first or last name, specialty, availability, wage)");
                     String attribute = keyboard.nextLine();
                     

                     if(attribute.equals("EmpID")){
                         System.out.println("Enter employee ID in numeric form");
                         int i = keyboard.nextInt();
                         keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                         e.SEsetEmpID(i,id);
                     }
                     else if(attribute.equals("phone number")){
                         System.out.println("Enter phone number in numeric form");
                         long p = keyboard.nextLong();
                         keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                         e.SEsetPhoneNum(p,id);
                     }
                     else if(attribute.equals("email")){
                         System.out.println("Enter email");
                         String email = keyboard.nextLine();
                         e.SEsetEmail(email,id);
                     }
                     else if(attribute.equals("first name")){
                         System.out.println("Enter first name");
                         String f = keyboard.nextLine();
                         e.SEsetFname(f,id);
                     }
                     else if(attribute.equals("last name")){
                         System.out.println("Enter last name");
                         String l = keyboard.nextLine();
                         e.SEsetLname(l,id);
                     }
                     else if(attribute.equals("availability")){
                         System.out.println("Enter availability in boolean form");
                         boolean a = keyboard.nextBoolean();
                         keyboard.nextLine();    //consume the '\n' not consumed by nextBoolean()
                         e.setAvailability(a,id);
                     }
                     else if(attribute.equals("wage")){
                         System.out.println("Enter wage in numeric form");
                         int w = keyboard.nextInt();
                         keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                         e.setWage(w,id);
                     }
                     else if(attribute.equals("specialty")){
                         System.out.println("Enter specialty");
                         String sp = keyboard.nextLine();
                         e.setSpecialty(sp,id);
                     }
                     
                     System.out.println("Done editing.");
                }
                
                
                

            }
            else if(custOrEmp==6){  //delete emp
                System.out.println("Enter employee ID of employee to delete");
                int id=keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                boolean removed=false;
                
                System.out.println("Enter which type of employee profile you're deleting (1 for Warehouse Manager, 2 for Service Employee");
                int profileType = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                if(profileType == 1) {	//Warehouse Manager
                	SQL.ps_removeWHM(id);
                    System.out.println("Warehouse Manager deleted");
                }
                
                else if(profileType == 2) {	//Service Employee
                	SQL.ps_removeSE(id);
                    System.out.println("Service Employee deleted");
                }
                
            }
            else if(custOrEmp==7){  //search for emp
                System.out.println("Enter employee ID of employee to find");
                int id=keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                System.out.println("Enter which type of employee profile you're searching for (1 for Warehouse Manager, 2 for Service Employee");
                int profileType = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                if(profileType == 1) {	//Warehouse Manager
                	SQL.ps_findWHMByID(id);
                    
                }
                
                else if(profileType == 2) {	//Service Employee
                	SQL.ps_findSEByID(id);
                }
               
            }
            
            
        }

        



        else if(selection==2){   //Rent/Return
            System.out.println("Would you like to rent equipment (1) or return equipment (2)? Enter your numeric choice.");
            int rentOrReturn = keyboard.nextInt();
            keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
            

            if(rentOrReturn==1){    //rent
                System.out.println("Enter the customer ID of the person renting equipment.");
                int custID = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                System.out.println("Enter how many pieces of equipment you want to rent.");
                int quantity = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                
                int rentalNo = (int)((Math.random()*100)+220220);	//generates a rentalNumber for the new rental
                int cost = (int)((Math.random()*100)+5);	//generates a cost for the new rental
                Date doa = Date.valueOf("2024-04-12");		//sets date of arrival as "current" date
                Date dor = Date.valueOf("2024-05-10");		//sets date of return as a month later, an expected/default return date
                int pickupDrone = 123456;		//default delivery and pickup drone since they haven't selected delivery or pickup yet
                int deliveryDrone = 123456;
                
                //adds new table to RENTAL with the provided info
                SQL.ps_addNewRental(rentalNo, cost, quantity, doa, dor, pickupDrone, deliveryDrone, custID);
                
                for(int i=0;i<quantity;i++){
                    System.out.println("Enter the serial number of the equipment item you'd like to rent.");
                    int sNo = keyboard.nextInt();
                    keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                    
                    //for each equipment item created, add new table in equipment_rentals with rentalNo and sNo
                    SQL.ps_addNewEquipmentRental(rentalNo, sNo);
                                      
                          
                }
                System.out.println("Equipment rented.");
            }
            else{                   //return
                System.out.println("Enter the customer ID of the person returning equipment.");
                int custID = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                System.out.println("Enter the rental number of the rental to be returned.");
                int rentalNo = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                
                //delete table from RENTAL
                SQL.ps_return(rentalNo, custID);
                
                System.out.println("Equipment returned.");
            }
        }
        
        
        
        
        
        else if(selection==3){   //Deliver/Pickup
            System.out.println("Would you like to have equipment delivered (1) or picked up (2)? Enter your numeric choice.");
            int deliverOrPickup = keyboard.nextInt();
            keyboard.nextLine();    //consume the '\n' not consumed by nextInt()

            if(deliverOrPickup==1){ //deliver
                System.out.println("Enter the customer ID of the person receiving equipment.");
                int custID = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                System.out.println("Enter the delivery date you'd like, in the form yyyy-mm-dd");
                String deliveryTime = keyboard.nextLine();
                Date deliveryDate = Date.valueOf(deliveryTime);
                System.out.println("Enter the drone serial number for the delivery.");
                int droneSNo = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                
                //update delivery drone and delivery date
              SQL.ps_updateDelivery(droneSNo, custID, deliveryDate);
                
                System.out.println("Equipment delivered.");
            }

            else{   //pickup
                System.out.println("Enter the customer ID of the person whose rented equipment is being picked up.");
                int custID = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                System.out.println("Enter the pickup date you'd like, in the form yyyy-mm-dd");
                String pickupTime = keyboard.nextLine();
                Date pickupDate = Date.valueOf(pickupTime);
                System.out.println("Enter the drone serial number for the pickup.");
                int droneSNo = keyboard.nextInt();
                keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
                
                
                //update pickup drone and pickup date
              SQL.ps_updatePickup(droneSNo, custID, pickupDate);
                
                System.out.println("Equipment picked up."); 
            }
        }
        
        
        
        
        
        
        

        else if(selection==4){  //Reports
            System.out.println("Useful Reports: ");
            System.out.println("Enter a number 1-6 to select a report:");
            System.out.println("1: Total Number of Equipment Items Rented by Given Customer");
            System.out.println("2: Most Popular Item");
            System.out.println("3: Most Popular Manufacturer by Rented Units");
            System.out.println("4: Most Popular Drone");
            System.out.println("5: Member With Most Items Checked Out");
            System.out.println("6: Equipment By Type Released Before Given Year");
            
            int choice = keyboard.nextInt();
            
            if(choice==1) {
            	System.out.println("Enter User ID of a customer to find the total number of equipment items they've rented:");
                int userId = keyboard.nextInt();
                keyboard.nextLine();  //consumes \n not consumed by nextInt()
                SQL.ps_UsefulReport1(userId);
            }
            else if(choice==2) {
            	SQL.ps_UsefulReport2();	
            }
            else if(choice==3) {
            	SQL.ps_UsefulReport3();
            }
            else if(choice==4) {
            	SQL.ps_UsefulReport4();
            }
            else if(choice==5) {
            	SQL.ps_UsefulReport5();
            }
            else if(choice==6) {
            	System.out.println("Enter which year for equipment report");
    	    	int year = keyboard.nextInt();
    	    	keyboard.nextLine();		//consumes \n not used by nextInt
                SQL.ps_UsefulReport6(year);
            }
 
        }

        System.out.println("Enter another selection, or 5 to quit");
        selection = keyboard.nextInt();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        }
        
    }
    public static void main(String[] args) throws Exception {
        Scanner keyboard = new Scanner(System.in); //opens input stream
        App ui = new App();
        
        conn = initializeDB(DATABASE); //connects to DB
        
        System.out.println("User (Customer/Employee) Menu");
        System.out.println();
        ui.menu(keyboard);
        
        keyboard.close();   //closes input stream
        try {				//closes DB connection
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
    }
}
