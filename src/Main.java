// All of the following necessary imports.
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main { // The Main class is the entry point of the program and handles user input and data storage.
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileWriter("out.csv"))) { // Create file under the following name.
            Scanner scanner = new Scanner(System.in);
            List<CSVPrintable> entries = new ArrayList<>(); // I implemented an ArrayList to store printable objects. Easier to access and store.

            int n = 0; // This is assuming the number of entries is 0.
            boolean validInput = false; // Valid input is set to false until said otherwise.

            // Input for the number of entries "n" is handled here, with an input mismatch handler.
            while (!validInput) { // While valid input...
                try {
                    System.out.print("Enter the number of entries (n): ");
                    n = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    validInput = true; // Set it to true
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for 'n'. Please enter a valid integer.");
                    scanner.next(); // Consume the invalid input
                }
            }

            // Handling user input for data entry. Try catches for input mismatches and format mismatches.
            while (n > 0) {
                try {
                    System.out.print("Enter position (Student/Teacher/TA): ");
                    String position = scanner.next().trim(); // This will trim the rest of the input from the question so that it can read just position.
                    if (!position.matches("(Student|Teacher|TA)")) {
                        System.out.println("Invalid position. Please enter Student, Teacher, or TA.");
                        continue; // Implementation of continue helps the flow of the validation while effectively checking each case.
                    }

                    System.out.print("Enter Name (First,Last): ");
                    String nameInput = scanner.next().trim(); // This will trim the rest of the input from the question so that it can read just first,last.
                    String[] nameParts = nameInput.split(","); // Use regular expression to split by the comma and validate.
                    if (nameParts.length != 2) {
                        System.out.println("Invalid name format. Please use First,Last.");
                        continue; // Explained in first instance.
                    }
                    String firstName = nameParts[0]; // Store it into an array separating firstName in order to store easily to file.
                    String lastName = nameParts[1]; // Store it into an array separating lastName in order to store easily to file.

                    System.out.print("Enter Student ID (Enter \"0\" if teacher): ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Teacher ID (Enter \"0\" if student): ");
                    int teacherID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Phone (10-digit): ");
                    String phone = scanner.next().trim();
                    if (phone.length() != 10 || !phone.matches("\\d+")) { // If phone is not 10 digits long...
                        System.out.println("Phone number must be a 10-digit number.");
                        continue; // Explained in first instance.
                    }

                    CSVPrintable entry; // Create entry list object and creates all the following params to write to file.
                    if (position.equals("Student")) {
                        entry = new Student(firstName, lastName, studentID, phone);
                    } else if (position.equals("Teacher")) {
                        entry = new Teacher(firstName, lastName, teacherID, phone);
                    } else {
                        entry = new TA(firstName, lastName, studentID, teacherID, phone);
                    }

                    entries.add(entry); // Add entry to list.
                    entry.csvPrintln(out); // Print to file.
                    System.out.println("Data has been written to out.csv");

                    n--; // Remove 1 n as first entry is complete.

                    if (n > 0) { // If there are more than 1 n it will continue asking after its stored.
                        System.out.println("Remaining entries: " + n);
                    }
                } catch (NoSuchElementException | IllegalStateException e) { // These check for the correct formatting.
                    // Handle input errors
                    System.out.println("Invalid input. Please enter data in the correct format.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        } catch (IOException e) { // Final catch in case any other mismatch is detected.
            e.printStackTrace();
        }
    }
}