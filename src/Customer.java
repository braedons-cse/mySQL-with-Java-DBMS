import java.util.Scanner;
public class Customer {
    int userID;
    int warehouseDist;
    String address;
    String startDate;  
    String fname;
    String lname;
    String email;
    long phoneNum;   //to be stored as digits with no dashes between

    //setters, SQL calls made here
    
    public void setUserID(int id, int c_ID){
    	SQL.ps_setUserID(id, c_ID);
    }

    public void setWarehouseDist(int dist, int id){
    	SQL.ps_setWarehouseDist(dist, id);
    }

    public void setAddress(String addy, int id){
    	SQL.ps_setAddress(addy, id);
    }

    public void setStartDate(String date, int id){
    	SQL.ps_setStartDate(date, id);
    }

    public void setFName(String f, int id){
    	SQL.ps_setFName(f, id);
    }

    public void setLName(String l, int id){
    	SQL.ps_setLName(l, id);
    }

    public void setEmail(String e, int id){
    	SQL.ps_setEmail(e, id);
    }

    public void setPhoneNum(long num, int id){
    	SQL.ps_setPhoneNum(num, id);
    }


    //create new customer profile
    public void buildProfile(Scanner keyboard){
        
        
        System.out.println("Enter user ID in numeric form");
        int id = keyboard.nextInt();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        
        System.out.println("Enter warehouse distance in numeric form");
        int dist = keyboard.nextInt();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        
        System.out.println("Enter address");
        String addy = keyboard.nextLine();
        
        System.out.println("Enter start date in form yyyy-mm-dd");
        String date = keyboard.nextLine();
       
        System.out.println("Enter first name");
        String f = keyboard.nextLine();
      
        System.out.println("Enter last name");
        String l = keyboard.nextLine();
      
        System.out.println("Enter email");
        String e = keyboard.nextLine();
    
        System.out.println("Enter phone number in numeric form xxxxxxxxxx");
        long p = keyboard.nextLong();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
    
        SQL.ps_buildCustProfile(id, dist, addy, date, f, l, e, p);
        
    }
    

}
