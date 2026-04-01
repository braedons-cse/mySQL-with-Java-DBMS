import java.util.Scanner;
public class Equipment {
    int serialNo;
    int modelNo;
    int year;
    int warrantyLen;
    int ht;
    int wt;
    int len;
    String description;
    String status;
    String type;
    String manufacturer;
    String location;

    Scanner keyboard = new Scanner(System.in);
    //setters
    public void setSerial(int s){
        serialNo=s;
    }
    public void setModelNo(int m){
        modelNo=m;
    }
    public void setYear(int y){
        year=y;
    }
    public void setWarrantyLen(int w){
        warrantyLen=w;
    }
    public void setHt(int h){
        ht=h;
    }
    public void setWt(int weight){
        wt=weight;
    }
    public void setLen(int l){
        len=l;
    }
    public void setDescription(String d){
        description=d;
    }
    public void setStatus(String stat){
        status=stat;
    }
    public void setType(String ty){
        type=ty;
    }
    public void setManufacturer(String man){
        manufacturer=man;
    }
    public void setLocation(String loc){
        location=loc;
    }

    //getters
    public int getSerial(){
        return serialNo;
    }
    public int getModel(){
        return modelNo;
    }
    public int getYear(){
        return year;
    }
    public int getWarrantyLen(){
        return warrantyLen;
    }
    public int getHt(){
        return ht;
    }
    public int getWt(){
        return wt;
    }
    public int getLen(){
        return len;
    }
    public String getDescription(){
        return description;
    }
    public String getType(){
        return type;
    }
    public String getStatus(){
        return status;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public String getLocation(){
        return location;
    }

    public void buildItem(int sNo){
        setSerial(sNo);
        System.out.println("Enter model number");
        setModelNo(keyboard.nextInt());
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        System.out.println("Enter year");
        setYear(keyboard.nextInt());
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        System.out.println("Enter warranty length");
        setWarrantyLen(keyboard.nextInt());
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        System.out.println("Enter ht");
        setHt(keyboard.nextInt());
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        System.out.println("Enter wt");
        setWt(keyboard.nextInt());
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        System.out.println("Enter length");
        setLen(keyboard.nextInt());
        keyboard.nextLine();    //consume the '\n' not consumed by nextInt()
        System.out.println("Enter description");
        setDescription(keyboard.nextLine());
        System.out.println("Enter status");
        setStatus(keyboard.nextLine());
        System.out.println("Enter type");
        setType(keyboard.nextLine());
        System.out.println("Enter location");
        setLocation(keyboard.nextLine());
        System.out.println("Enter manufacturer");
        setManufacturer(keyboard.nextLine());
    }
}
