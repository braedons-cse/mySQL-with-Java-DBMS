import java.util.Scanner;
public class Employee {
    
    int empID;
    long phoneNum;
    String email;
    String fname;
    String lname;
    
    int wage;		//service emp specific attributes
    boolean availability;
    String specialty;
    
    int salary;		//wh manager specific attributes
    
    
    //setters
    public void WHMsetEmpID(int id, int e_id){
        SQL.ps_WHMsetEmpID(id, e_id);
    }
    public void WHMsetPhoneNum(long p, int e_id){
    	SQL.ps_WHMsetPhoneNum(p, e_id);
    }
    public void WHMsetEmail(String e, int e_id){
    	SQL.ps_WHMsetEmail(e, e_id);
    }
    public void WHMsetFname(String f, int e_id){
    	SQL.ps_WHMsetFname(f, e_id);
    }
    public void WHMsetLname(String l, int e_id){
    	SQL.ps_WHMsetLname(l, e_id);
    }
    
    
    
    public void SEsetEmpID(int id, int e_id){
    	SQL.ps_SEsetEmpID(id, e_id);
    }
    public void SEsetPhoneNum(long p, int e_id){
    	SQL.ps_SEsetPhoneNum(p, e_id);
    }
    public void SEsetEmail(String e, int e_id){
    	SQL.ps_SEsetEmail(e, e_id);
    }
    public void SEsetFname(String f, int e_id){
    	SQL.ps_SEsetFname(f, e_id);
    }
    public void SEsetLname(String l, int e_id){
    	SQL.ps_SEsetLname(l, e_id);
    }
    
    
    public void setWage(int w, int e_id){
    	SQL.ps_SEsetWage(w, e_id);
    }
    
    public void setSpecialty(String sp, int e_id){
    	SQL.ps_SEsetSpecialty(sp, e_id);
    }
    
    public void setAvailability(boolean a, int e_id) {
    	SQL.ps_SEsetAvailability(a, e_id);
    }
    
    
    public void setSalary(int s, int e_id){
    	SQL.ps_WHMsetSalary(s, e_id);
    }

   



    public void buildProfile(Scanner keyboard){
        System.out.println("Enter empID");
        int id = keyboard.nextInt();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
      

        System.out.println("Enter phone number");
        long p = keyboard.nextLong();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
      

        System.out.println("Enter email");
        String e = keyboard.nextLine();
     

        System.out.println("Enter fname");
        String f = keyboard.nextLine();
      

        System.out.println("Enter lname");
        String l = keyboard.nextLine();
 

        
        //check which type of employee profile they want to build- warehouse manager or service employee
        System.out.println("Enter which type of employee profile you're building (1 for Warehouse Manager, 2 for Service Employee");
        int profileType = keyboard.nextInt();
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        
        if(profileType == 1) {	//Warehouse Manager
        	  System.out.println("Enter salary");
              int sal = keyboard.nextInt();
              keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
              
              SQL.ps_buildWHMProfile(id,f,l,p,e,sal);
        }
        
        else if(profileType == 2) {	//Service Employee
        	 System.out.println("Enter wage");
             int w = keyboard.nextInt();
             keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
             
             
             System.out.println("Enter availability");
             boolean a = keyboard.nextBoolean();
             keyboard.nextLine();	//consumes newline not consumed by nextBoolean()
             
           
             System.out.println("Enter specialty");
             String sp = keyboard.nextLine();
         
             
             SQL.ps_buildSEProfile(id,f,l,p,e,w,a,sp);
        }
       
    }
}
