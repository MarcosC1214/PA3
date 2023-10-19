import java.io.PrintWriter;

public class Student implements CSVPrintable { // The Student class implements the CSVPrintable interface and represents student data.
    private String firstName;
    private String lastName;
    private int studentID;
    private long phone;

    public Student(String firstName, String lastName, int studentID, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.phone = Long.parseLong(phone); // This is technically unused since the entire phone number is required.
    }

    @Override
    public String getName() {
        return firstName + " " + lastName; // Override return students first,last.
    }

    @Override
    public int getID() {
        return studentID; // Override gets students ID and not teachers.
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(getName() + "," + getID() + "," + phone); // Overrides the print method so that it prints the students 10-digit phone.
    }
}