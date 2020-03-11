package PP02CourseWork;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainBookingSystem extends Application {

    static final int SEATING_CAPACITY = 42;
    private ArrayList<String> bookedSeats = new ArrayList<>(SEATING_CAPACITY);
    private ArrayList<String> bookedNames = new ArrayList<>(SEATING_CAPACITY);
    private ArrayList<String> orderedNames = new ArrayList<>(SEATING_CAPACITY);

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {


        welcome();
    }

    //Basic Console Menu
    private void welcome() {
        displayOptions();

    }
    private void displayOptions() {
        System.out.println("Hello Welcome to Train Booking System " +
                "\n==============================================" +
                "\n**********************************************");
        System.out.println("Press Q : Quit the Booking" +
                "\nPress E :Display Empty seats " +
                "\nPress D : Delete the clicked Seats " +
                "\nPress F: Find seats " +
                "\nPress S : store the data " +
                "\nPress L :Load from file " +
                "\nPress O : view Seats Alphabetllcally");
        System.out.println("==============================================" +
                "\n**********************************************");
        customerSelectOption();
    }
    private void customerSelectOption() {
        Scanner usrInput = new Scanner(System.in);
        System.out.print("Enter Your Option :");
        String customerOption = usrInput.next();
        switch (customerOption) {
            case ("Q"):
            case ("q"):
                System.out.println("Train booking System is Quitting..");
                System.out.println("Thank you for Using Our Train Booking System");
                System.out.println("Hello Welcome to Train Booking System " +
                        "\n==============================================" +
                        "\n**********************************************");
                System.out.println("All the Booking saved to the File... ");
                System.exit(0);

            case ("V"):
            case ("v"):
                System.out.println("View All Seats Command is Executing...");
                viewAllSeats(bookedSeats);
                break;

            case ("A"):
            case ("a"):
                System.out.println("Add Customers to Seat Command is Executing...");
                addCustomerToSeats(bookedSeats, bookedNames);
                break;

            case ("E"):
            case ("e"):
                System.out.println("View Empty Seats Command is Executing...");
                viewEmptySeats(bookedSeats);
                break;

            case ("F"):
            case ("f"):
                System.out.println("Find Seats Command is Executing...  ");
                findSeatsOfCustomer(bookedSeats, bookedNames);
                break;

            case ("D"):
            case ("d"):
                System.out.println("Delete Customer Command is Executing... ");
                deleteCustomerSeats(bookedSeats, bookedNames);
                break;

            case ("S"):
            case ("s"):
                System.out.println("Your Details Being Saved To a File... ");
                saveToFile();
                break;

            case ("L"):
            case ("l"):
                System.out.println("Loading From File... ");
                loadFromFile();
                break;

            case ("O"):
            case ("o"):
                System.out.println("View All booking Command is Executing... ");
                sortBooking(bookedNames, bookedSeats, orderedNames);
                break;
            //Error Handles for Option Other than Given Ones.
            default:
                System.out.println("Invalid Option Please Choose the Right Option...");
                break;
        }
    }

    //Methods for Creating buttons and Alert Box
    private void createButtons(GridPane grid, int i, Button btn) {
        //Creating Button as Row By Row.
        //Total seats number define AS static final int SEATING_CAPACITY
        int row1 = 11;
        int row2 = 11;
        int row3 = 10;
        int col1 = 4;
        int col2 = 6;
        int col3 = 12;
        int col4 = 14;
        if (i <= row1) {
            grid.add(btn, col1, i);
        } else if (i <= (row1 + row2)) {
            grid.add(btn, col2, (i - row1));
        } else if (i <= (row1 + row2 + row3)) {
            grid.add(btn, col3, (i - (row1 + row2)));
        } else {
            grid.add(btn, col4, (i - (row1 + row2 + row3)));
        }
    }
    public void displayAlertBox(String title,String message){

        Stage alertBox = new Stage();
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);

        Label label =new Label();
        label.setText(message);

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(event -> {
            alertBox.close();
            addCustomerToSeats(bookedSeats, bookedNames);

        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,btnCancel);

        Scene alertScene = new Scene(layout,400,300);
        alertBox.setScene(alertScene);
        alertBox.show();

    }

    //GUI
    private void viewAllSeats(ArrayList<String> bookedSeats) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //Creating Buttons
        for (int i = 1; i <= SEATING_CAPACITY; i++) {
            Button btn = new Button("A" + i);
            btn.setId(String.valueOf(i));
            btn.setPadding(new Insets(10, 10, 10, 10));
            if (bookedSeats.contains(btn.getId())) {
                btn.setStyle("-fx-background-color: red");
                btn.setDisable(true);
            }
            createButtons(grid, i, btn);
        }

        Button btnQuit = new Button();
        btnQuit.setId("quit");
        btnQuit.setText("Quit");
        grid.add(btnQuit, 14, 20);
        btnQuit.setOnAction(event -> {
            Stage stage1 = (Stage) btnQuit.getScene().getWindow();
            stage1.close();
            welcome();
            System.out.println("Are you Sure You want to Quit? \n Press q to Quit..");
        });

        Button btnContinue = new Button();
        btnContinue.setId("continue");
        btnContinue.setText("Continue --->");
        grid.add(btnContinue, 16, 20);
        btnContinue.setOnAction(event -> {
            Stage stage1 = (Stage) btnContinue.getScene().getWindow();
            stage1.close();
            welcome();
        });

        Stage stage1 = new Stage();
        stage1.setTitle("Train Booking System");
        Scene viewScene = new Scene(grid, 600, 800);
        stage1.setScene(viewScene);
        stage1.setOnCloseRequest(event->{
            stage1.close();
            welcome();
        });
        stage1.show();
    }
    private void addCustomerToSeats(ArrayList<String> bookedSeats, ArrayList<String> namesList) {
        //Temporary Array to save Selected Seats by Customer
        ArrayList<String> selectSeats = new ArrayList<>();
        //Creating 2nd Stage
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //Creating Buttons 42 Times
        for (int i = 1; i <= SEATING_CAPACITY; i++) {
            Button btn = new Button("A" + i);
            btn.setId(String.valueOf(i));
            btn.setPadding(new Insets(10, 10, 10, 10));
            for(int j =0 ; j<42;j++) {
//                if (bookedSeats.get(j).contains(btn.getId()))) {
                if (bookedSeats.get(j).equals(btn.getId())) {
                    btn.setStyle("-fx-background-color: red;");
                    btn.setDisable(true);
                }
            }
            //method of adding Buttons In grid.
            createButtons(grid, i, btn);
            //check whether seat Number in Temporary Array. It add and remove element form list So it give more User Accessibility.
            btn.setOnAction(event -> {
                if (!selectSeats.contains(btn.getId())) {
                    selectSeats.add(btn.getId());
                    System.out.println(selectSeats);
                   // printSeats(selectSeats);
                    btn.setStyle("-fx-background-color: red;");
                } else {
                    selectSeats.remove(btn.getId());
                    btn.setStyle("");
                }
                System.out.println(selectSeats);
            });
        }

        TextField tf = new TextField();
        tf.setId("Name");
        grid.add(tf, 10, 20);


        Button btnGoBack = new Button();
        btnGoBack.setId("GoBack");
        btnGoBack.setText("Go Back <---");
        grid.add(btnGoBack, 12, 20);
        btnGoBack.setOnAction(event -> {
            Stage stage2 = (Stage) btnGoBack.getScene().getWindow();
            stage2.close();
            welcome();
        });

        Button btnBook = new Button();
        btnBook.setId("book");
        btnBook.setText("Book Now");
        grid.add(btnBook, 14, 20);
        btnBook.setOnAction(event -> {
            bookedSeats.addAll(selectSeats);
            if (tf.getText() == null || tf.getText().trim().isEmpty()) {
            //  Alert alertBox =new Alert.AlertType();

//                Stage stage2 = (Stage) btnBook.getScene().getWindow();
//                stage2.close();
//                System.out.println("No Name Found For Booked Seats.\nEnter The Name if you want tob Book Seats for This Train\nPress 'Q' to Exit the Booking\nThank You..");
//                Scanner sc= new Scanner(System.in);
//                System.out.print("Enter Your Name :");
//                String input =sc.nextLine();
//               if (input.equals("Q") || input.equals("q")){
//                       System.out.println("Thank you for Using Train Booking Service.");
//                       System.exit(0);
//               }else{
//                   namesList.add(input);
//                   welcome();
//
//               }
            }else{
                for (String item: selectSeats){
                    namesList.add(tf.getText());
                    System.out.println(namesList);
                }


                Stage stage2 = (Stage) btnBook.getScene().getWindow();
                stage2.close();
                welcome();
            }

        });

        Stage stage2 = new Stage();
        stage2.setTitle("Train Booking System");
        Scene addScene = new Scene(grid, 600, 800);
        stage2.setScene(addScene);
        stage2.setOnCloseRequest(event->{
            stage2.close();
            welcome();
        });
        stage2.show();
    }
    private void viewEmptySeats(ArrayList<String> bookedSeats) {

        GridPane grid;
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //Creating Buttons
        for (int i = 1; i <= SEATING_CAPACITY; i++) {
            Button btn = new Button("A" + i);
            btn.setId(String.valueOf(i));
            btn.setPadding(new Insets(10, 10, 10, 10));
            //  grid.getChildren().add(btn);
            if (bookedSeats.contains(btn.getId())) {
                btn.setStyle("-fx-background-color: red");
                btn.setDisable(true);
            }
            createButtons(grid, i, btn);
        }

        Button btnContinue = new Button();
        btnContinue.setId("continue");
        btnContinue.setText("Continue");
        grid.add(btnContinue, 15, 16);
        btnContinue.setOnAction(event -> {
            Stage stage3 = (Stage) btnContinue.getScene().getWindow();
            stage3.close();
            welcome();
        });

        Stage stage3 = new Stage();
        stage3.setTitle("Train Booking System");
        Scene emptyScene = new Scene(grid, 600, 800);
        stage3.setScene(emptyScene);
        stage3.setOnCloseRequest(event->{
            stage3.close();
            welcome();
        });
        stage3.show();
    }

    private static void addToList(String btnId, ArrayList<String> list) {
        // getButtonId(btn);
        if (!list.contains(btnId)) {
            list.add(btnId);
        }
        //  printSeats();
    }
    private static void printSeats(ArrayList<String> list) {
        // for (int i = 1; i<Seats.size(); i++){
        for (String it : list) {
            System.out.print(" " + it);
        }
    }
    //Console
    private void findSeatsOfCustomer(ArrayList<String> bookedNames, ArrayList<String> bookedSeats) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer Name You Want to search : ");
        String customerName = sc.nextLine();
        if (bookedNames.contains(customerName)) {
            int indexInList = bookedNames.indexOf(customerName);
            String seatNumbers = bookedSeats.get(indexInList);
            System.out.println("Customer Name : " + customerName + "\nSeat Numbers : " + seatNumbers);
            welcome();
        } else {
            System.out.println("Entered Name Not in the Booked Persons List..\nPlease Check The Name...\nThank you..");
            welcome();
        }
    }

    private void deleteCustomerSeats(ArrayList<String> bookedNames, ArrayList<String> bookedSeats) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer Name you want to Delete : ");
        String customerName = sc.nextLine();
        if (bookedNames.contains(customerName)) {
            int indexInList = bookedNames.indexOf(customerName);
            bookedSeats.remove(indexInList);
            System.out.println("Customer " + customerName + " Booking was Deleted..\nThank You...");
        }else{
            System.out.println("Customer Name Not Exists");
            welcome();
        }
    }

    private void saveToFile( ) {
        try {
            FileWriter fr = new FileWriter("src/PP02CourseWork/BookedNames.txt");
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter out = new PrintWriter(br);
            for (int i = 0; i < bookedNames.size(); i++) {
                if (bookedNames.get(i) != null)
                    out.write(bookedSeats.get(i)+ " = "+bookedNames.get(i));
                out.write("\n");
            }
            out.close();
            welcome();
//
//            FileWriter fr2 = new FileWriter("src/PP02CourseWork/BookedSeats.txt");
//            BufferedWriter br2 = new BufferedWriter(fr2);
//            PrintWriter out2 = new PrintWriter(br2);
//            for (int i = 0; i < bookedSeats.size(); i++) {
//                if (bookedSeats.get(i) != null)
//                    out.write(bookedSeats.get(i));
//                out.write("\n");
//            }
//            out.close();
//            welcome();
        }catch (IOException e){
                System.err.println(e);
            }
        }


    private void loadFromFile() {
        try {
//            File file = new File("src/PP02CourseWork/BookedSeats.txt");
//            BufferedReader br = new BufferedReader(new FileReader(file));
            Scanner sc = new Scanner(new File("src/PP02CourseWork/BookedNames.txt"));
//            String st;
//            while ((st = br.readLine()) != null)
                while (sc.hasNextLine()){
                    String line = sc.nextLine();
                    String[] NewArray = line.split(" = ");
                    bookedSeats.add(NewArray[0]);
                    bookedNames.add(NewArray[1]);
                    System.out.println(bookedSeats);
                    System.out.println(bookedNames);
                }
                System.out.println();
        } catch (IOException err) {
            System.err.println(err);
        }
        welcome();
    }

    private void sortBooking(ArrayList<String> bookedNames, ArrayList<String> bookedSeats, ArrayList<String> orderedNames) {
        for (int j = 0; j < bookedNames.size(); j++) {
            for (int i = j + 1; i < bookedNames.size(); i++) {
                // comparing adjacent strings
                if (bookedNames.get(i).compareTo(bookedNames.get(j)) < 0) {
                    System.out.println("i   " + bookedNames.get(i));
                    orderedNames.add(bookedNames.get(i));
                } else {
                    System.out.println("j   " + bookedNames.get(j));
                    orderedNames.add(bookedNames.get(j));
                }
                printSeats(orderedNames);
            }
            welcome();
        }
    }
}
