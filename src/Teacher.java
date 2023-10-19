import java.io.PrintWriter;

public class Teacher implements CSVPrintable { // The Teacher class implements the CSVPrintable interface and represents teacher data.
    private String firstName;
    private String lastName;
    private int teacherID;
    private long phone; // Store the full 10-digit phone number

    public Teacher(String firstName, String lastName, int teacherID, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherID = teacherID;
        this.phone = Long.parseLong(phone); // This one is needed in order to only get the last 4 digits.
    }

    @Override
    public String getName() {
        return firstName + " " + lastName; // Override return teachers first,last.
    }

    @Override
    public int getID() {
        return teacherID; // Override gets teachers ID and not students.
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(getName() + "," + getID() + "," + (phone % 10000)); //Overrides the print method so that it prints the teachers last 4-digit phone.
    }
}