public class TA extends Student { // The TA class extends the Student class and represents TA (Teaching Assistant) data.
    private int teacherID;

    public TA(String firstName, String lastName, int studentID, int teacherID, String phone) {
        super(firstName, lastName, studentID, phone);
        this.teacherID = teacherID;
    }

    @Override
    public int getID() {
        return Math.max(super.getID(), teacherID); // Override gets the max value of the two ID's and only store the largest.
    }
}