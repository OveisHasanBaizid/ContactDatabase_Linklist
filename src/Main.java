import java.util.Scanner;

public class Main {
    Scanner input;
    Contact contacts;
    Contact head;
    int size;
    public Main() {
        input = new Scanner(System.in);
        head = null;
        size=0;
        menu();
    }

    private void menu() {
        System.out.println(" * * * Main Manu * * * ");
        int item =0;
        do{
            System.out.println("1.Add list of file");
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
        }while(item>10 || item<1);
        switch (item){
            case 1 -> System.out.println();
            case 2 -> System.out.println();
            case 3 -> System.out.println();
            case 4 -> System.out.println();
            case 5 -> System.out.println();
            case 6 -> System.out.println();
            case 7 -> System.out.println();
            case 8 -> System.out.println();
            case 9 -> System.out.println();
            case 10 -> System.exit(0);
        }
        menu();
    }
    public void readFromFile(){

    }
    public static void main(String[] args) {
        new Main();
    }
}