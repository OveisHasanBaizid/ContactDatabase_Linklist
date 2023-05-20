import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    Scanner input;
    Contact contacts;
    Contact head;
    int size;

    public Main() throws IOException {
        input = new Scanner(System.in);
        head = null;
        size = 0;
        readFromFile();

    }

    private void menu() throws IOException {
        System.out.println(" * * * Main Manu * * * ");
        int item = 0;
        do {
            System.out.println("1.Add list from file");
            System.out.println("2.Delete list");
            System.out.println("3.Add a property to contact");
            System.out.println("4.Add a value to property");
            System.out.println("5.Remove a property of contact");
            System.out.println("6.Remove a value of property");
            System.out.println("7.Check property");
            System.out.println("8.Save list to file");
            System.out.println("9.Print list");
            System.out.println("10.Exit");
            System.out.print("Please choice a option : ");
            item = input.nextInt();
        } while (item > 10 || item < 1);
        switch (item) {
            case 1 -> readFromFile();
            case 2 -> deleteList();
            case 3 -> addProperty();
            case 4 -> System.out.println();
            case 5 -> removeProperty();
            case 6 -> removeValue();
            case 7 -> System.out.println();
            case 8 -> System.out.println();
            case 9 -> System.out.println();
            case 10 -> System.exit(0);
        }
        menu();
    }
    public void addProperty(){
        System.out.println(" * * * Add Property * * * ");
        System.out.print("Enter name contact : ");
        String contactName = input.nextLine();
        System.out.print("Enter name property : ");
        String property = input.nextLine();
        Contact contact = findContact(contactName);
        if(contact==null)
            System.out.println("The desired contact is not available in the list.");
        else{
            addToContact(" -Property: "+property,contact.getProperty());
            System.out.println("Property successfully added.");
        }
    }
    public void removeProperty(){
        System.out.println(" * * * Remove Property * * * ");
        System.out.print("Enter name contact : ");
        String contactName = input.nextLine();
        System.out.print("Enter name property : ");
        String property = input.nextLine();
        Contact contact = findContact(contactName);
        if(contact==null)
            System.out.println("The desired contact is not available in the list.");
        else{
            if (deleteProperty(property,contact.getProperty()))
                System.out.println("Property successfully remove.");
            else
                System.out.println("This feature is not defined for the target contact.");
        }
    }
    public void print(){
        System.out.println(" * * * Print List * * * ");
        Contact contact = head;
        while (contact!=null){
            System.out.println("-Contact: "+contact.getName());
            printProperty(contact.getProperty(),1);
        }
    }
    public void printProperty(PropertyNode propertyNode , int n){
        for (int i = 0; i < n ; i++) {
            System.out.print("\t");
        }
        System.out.println("-Property: "+propertyNode.getTitle());
        DataNode data = propertyNode.getData();
        while (data!=null){
            for (int i = 0; i < n+1; i++) {
                System.out.println("\t");
            }
            System.out.println("-Value: "+data.getValue());
            data = data.getNext();
        }
     //   printProperty(propertyNode.,);
    }
    public void removeValue(){
        System.out.println(" * * * Remove Value * * * ");
        System.out.print("Enter name contact : ");
        String contactName = input.nextLine();
        System.out.print("Enter value : ");
        String value = input.nextLine();
        Contact contact = findContact(contactName);
        if(contact==null)
            System.out.println("The desired contact is not available in the list.");
        else{
            if (deleteValue(value,contact.getProperty()))
                System.out.println("Value successfully remove.");
            else
                System.out.println("This value is not defined for the target contact.");
        }
    }
    public boolean deleteProperty(String title , PropertyNode property){
        if(property==null)
            return false;
        if (property.getTitle().equals(title)){
            property = null;
            return true;
        }
        if (property.getDown()!=null)
            return deleteProperty(title,property.getDown());
        return deleteProperty(title,property.getNext());
    }
    public boolean deleteValue(String value , PropertyNode property){
        if(property==null || property.getData()==null)
            return false;
        DataNode data = property.getData();
        while (data!=null){
            if (data.getValue().equals(value)){
                data = null;
                return true;
            }
            data = data.getNext();
        }
        if (property.getDown()!=null)
            return deleteProperty(value,property.getDown());
        return deleteProperty(value,property.getNext());
    }
    public Contact findContact(String name){
        Contact temp = head;
        while (temp!=null){
            if (temp.getName().equals(name))
                return temp;
            temp = temp.getNext();
        }
        return null;
    }
    public void deleteList(){
        head = null;
        System.out.println("The list was successfully deleted.");
    }
    public void readFromFile() throws IOException {
        FileReader fileReader = new FileReader("");
        BufferedReader br = new BufferedReader(fileReader);
        Contact contact = null;
        String s;
        while ((s = br.readLine()) != null) {
            if (s.charAt(1) == 'C') {
                if (contact == null) {
                    contact = new Contact(s.substring(11));
                    head = contact;
                } else {
                    contact.setNext(new Contact(s.substring(11)));
                    contact = contacts.getNext();
                }
            } else {
                assert contact != null;
                addToContact(s.substring(1), contact.getProperty());
            }
        }
        fileReader.close();
        System.out.println("The file was read successfully.");
    }

    public void addToContact(String line, PropertyNode property) {
        if(line.charAt(2) == 'V') {
            if(property.getNext()!=null){
                setData(line.substring(9),property.getNext().getData());
            } else
                property.setData(new DataNode(line.substring(9)));
        }else if(line.charAt(2) == 'P') {
            if (property == null)
                property = new PropertyNode(line.substring(12));
            else
                addToContact(line,property.getNext());
        }else{
            addToContact(line.substring(1), property.getDown());
        }
    }
    public void setData(String data , DataNode dataNode){
        if (dataNode!=null)
            setData(data,dataNode.getNext());
        else
            dataNode = new DataNode(data);
    }
    public static void main(String[] args) throws IOException {
        new Main();
    }
}