import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner input;
    Contact contacts;
    Contact head;
    int size;
    Contact contact = null;
    ArrayList<PropertyNode> nodeArrayList = new ArrayList<>();
    PropertyNode property;

    public Main() throws IOException {
        input = new Scanner(System.in);
        head = null;
        size = 0;
        readFromFile();
        print();
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

    public void addProperty() {
        System.out.println(" * * * Add Property * * * ");
        System.out.print("Enter name contact : ");
        String contactName = input.nextLine();
        System.out.print("Enter name property : ");
        String property = input.nextLine();
        Contact contact = findContact(contactName);
        if (contact == null)
            System.out.println("The desired contact is not available in the list.");
        else {
            //   addToContact(" -Property: " + property, contact.getProperty());
            System.out.println("Property successfully added.");
        }
    }

    public void removeProperty() {
        System.out.println(" * * * Remove Property * * * ");
        System.out.print("Enter name contact : ");
        String contactName = input.nextLine();
        System.out.print("Enter name property : ");
        String property = input.nextLine();
        Contact contact = findContact(contactName);
        if (contact == null)
            System.out.println("The desired contact is not available in the list.");
        else {
            if (deleteProperty(property, contact.getProperty()))
                System.out.println("Property successfully remove.");
            else
                System.out.println("This feature is not defined for the target contact.");
        }
    }

    public void print() {
        System.out.println(" * * * Print List * * * ");
        contact = head;
        //   while (contact != null) {
        System.out.println("-Contact: " + contact.getName());
        printProperty(contact.getProperty(), 1);
        //   }
    }

    public void printProperty(PropertyNode propertyNode, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("\t");
        }
        System.out.println("-Property: " + propertyNode.getTitle());
        DataNode data = propertyNode.getData();
        while (data != null) {
            for (int i = 0; i < n + 1; i++) {
                System.out.print("\t");
            }
            System.out.println("-Value: " + data.getValue());
            data = data.getNext();
        }
        if (propertyNode.getDown() != null)
            printProperty(propertyNode.getDown(), n);
        if (propertyNode.getNext() != null)
            printProperty(propertyNode.getNext(), n);
    }

    public void removeValue() {
        System.out.println(" * * * Remove Value * * * ");
        System.out.print("Enter name contact : ");
        String contactName = input.nextLine();
        System.out.print("Enter value : ");
        String value = input.nextLine();
        Contact contact = findContact(contactName);
        if (contact == null)
            System.out.println("The desired contact is not available in the list.");
        else {
            if (deleteValue(value, contact.getProperty()))
                System.out.println("Value successfully remove.");
            else
                System.out.println("This value is not defined for the target contact.");
        }
    }

    public boolean deleteProperty(String title, PropertyNode property) {
        if (property == null)
            return false;
        if (property.getTitle().equals(title)) {
            property = null;
            return true;
        }
        if (property.getDown() != null)
            return deleteProperty(title, property.getDown());
        return deleteProperty(title, property.getNext());
    }

    public boolean deleteValue(String value, PropertyNode property) {
        if (property == null || property.getData() == null)
            return false;
        DataNode data = property.getData();
        while (data != null) {
            if (data.getValue().equals(value)) {
                data = null;
                return true;
            }
            data = data.getNext();
        }
        if (property.getDown() != null)
            return deleteProperty(value, property.getDown());
        return deleteProperty(value, property.getNext());
    }

    public Contact findContact(String name) {
        Contact temp = head;
        while (temp != null) {
            if (temp.getName().equals(name))
                return temp;
            temp = temp.getNext();
        }
        return null;
    }

    public void deleteList() {
        head = null;
        System.out.println("The list was successfully deleted.");
    }

    public void readFromFile() throws IOException {
        FileReader fileReader = new FileReader("Data");
        BufferedReader br = new BufferedReader(fileReader);
        Contact contact = null;
        String s;
        while ((s = br.readLine()) != null) {
            if (s.charAt(1) == 'C') {
                if (contact == null) {
                    contact = new Contact(s.substring(11));
                    head = contact;
                    PropertyNode p = new PropertyNode("");
                    contact.setProperty(p);
                    nodeArrayList.add(p);
                } else {
                    contact.setNext(new Contact(s.substring(11)));
                    contact = contacts.getNext();
                }
            } else {
                if (s.contains("-Property: ")) {
                    addPropertyToList(s, 0);
                } else {
                    addValueToList(s, 0);
                }
            }
        }
        fileReader.close();
        System.out.println("The file was read successfully.");
    }

    public void addPropertyToList(String line, int i) {
        if (line.charAt(2) == 'P') {
            PropertyNode p = new PropertyNode(line.substring(12));
            if (i >= nodeArrayList.size()-1 && i != 0) {
                if(nodeArrayList.get(i - 1).getDown()==null){
                    nodeArrayList.get(i - 1).setDown(p);
                    nodeArrayList.add(p);
                }else{
                    PropertyNode pro = nodeArrayList.get(i - 1).getDown();
                    while (pro.getNext()!=null)
                        pro = pro.getNext();
                    p.setNext(p);
                    nodeArrayList.set(i, p);
                }
                System.out.println("PropertyN : "+p.getTitle()+" TO "+nodeArrayList.get(i - 1).getTitle());
            } else {
                nodeArrayList.get(i).setNext(p);
                nodeArrayList.set(i, p);
            }
        } else
            addPropertyToList(line.substring(1), i + 1);
    }

    public void addValueToList(String line, int i) {
        if (line.charAt(2) == 'V') {
            DataNode d = new DataNode(line.substring(9));
            if (nodeArrayList.get(i-1).getData() == null)
                nodeArrayList.get(i-1).setData(d);
            else {
                DataNode data = nodeArrayList.get(i-1).getData();
                while (data.getNext() != null) {
                    data = data.getNext();
                }
                data.setNext(new DataNode(line.substring(9)));
            }
        } else
            addValueToList(line.substring(1), i + 1);
    }

    public void setData(String data, DataNode dataNode) {
        if (dataNode != null)
            setData(data, dataNode.getNext());
        else
            dataNode = new DataNode(data);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}