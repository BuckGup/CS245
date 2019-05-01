
public class UWECStudent extends UWECPerson {

    double gpa = 0;
    private int uwecId = 0;
    private String firstName = new String();
    private String lastName = new String();


    public UWECStudent(int uwecId, String firstName, String lastName, double gpa) {
        this.setUwecId(uwecId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.gpa = gpa;

    }


    public double getGpa() {
        return this.gpa;
    }

    public boolean equals(Object other) {

        if (other instanceof UWECStudent) {

            if (((UWECStudent) other).getUwecId() == this.getUwecId()
                    && ((UWECStudent) other).getFirstName().equals(this.getFirstName())
                    && ((UWECStudent) other).getLastName().equals(this.getLastName())
                    && ((UWECStudent) other).getGpa() == this.getGpa()) {
                return true;
            }
            return false;
        }


        return false;
    }

    public String toString() {
        String UWECStudentString = ("UWECStudent " + "= " + "uwecId: " + getUwecId() + "," + " name: " + getFirstName() + " " + getLastName())
                + ", " + "gpa: " + getGpa();
        return UWECStudentString;

    }


}
