
public class UWECStudent extends UWECAcademic {

    private double gpa;
    private int uwecId = 0;
    private String firstName = new String();
    private String lastName = new String();


    public UWECStudent(int uwecId, String firstName, String lastName, double gpa) {
        super(uwecId, firstName, lastName);
        this.gpa = gpa;

    }




    public final double getGpa() {
        return this.gpa;
    }
    public void setNumTotalCredits(int numTotalCredits){
        super.setNumTotalCredits(numTotalCredits);
    }

    public boolean equals(Object other) {

        if (other instanceof UWECStudent) {

            if (((UWECStudent) other).getUwecId() == this.getUwecId()
                    && ((UWECStudent) other).getFirstName().equals(this.getFirstName())
                    && ((UWECStudent) other).getLastName().equals(this.getLastName())
                    && ((UWECStudent) other).getNumTotalCredits() == (super.getNumTotalCredits())
                    && ((UWECStudent) other).getGpa() == this.getGpa()) {
                return true;
            }
            return false;
        }

        return false;
    }

    public String toString() {
        String UWECStudentString = ("UWECStudent " + "= " + "uwecId: " + getUwecId() + "," + " name: " + getFirstName() +  " " + getLastName())
                +", title: " + this.getTitle() + ", credits: " + getNumTotalCredits() + ", gpa: " + this.getGpa();
        return UWECStudentString;

    }


}
