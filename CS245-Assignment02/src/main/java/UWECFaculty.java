import java.util.GregorianCalendar;

public class UWECFaculty extends UWECAcademic implements UWECEmployee {


    private double yearlySalary = 0;


    public UWECFaculty(int uwecId, String firstName, String lastName, int numTotalCredits) {
        super(uwecId, firstName, lastName);
        this.setNumTotalCredits(numTotalCredits);
        this.setYearlySalary(getYearlySalary());
    }


    public void setNumTotalCredits(int numTotalCredits) {

        super.setNumTotalCredits(numTotalCredits);
    }


    public final double getYearlySalary() {
        return this.yearlySalary;
    }


    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public double computePaycheck() {
       double finalResult;
      finalResult = (yearlySalary/26);
        return finalResult;
    }


    public String toString() {
        String UWECFacultyString = ("UWECFaculty " + "= " + "uwecId: " + getUwecId() + "," + " name: " + getFirstName() + " " + getLastName() + ", "
                 + "title: " + this.getTitle() + ", credits: " + getNumTotalCredits()  + ", yearlySalary: " + getYearlySalary());
        return UWECFacultyString;
    }


    public boolean equals(Object other) {

        if (other instanceof UWECFaculty) {

            if (((UWECFaculty) other).getUwecId() == this.getUwecId()
                    && ((UWECFaculty) other).getFirstName().equals(this.getFirstName())
                    && ((UWECFaculty) other).getLastName().equals(this.getLastName())
                    && ((UWECFaculty) other).getYearlySalary() == this.getYearlySalary()
//                    && ((UWECFaculty) other).getTitle().equals(super.getTitle())
                    && ((UWECFaculty) other).getNumTotalCredits() == (this.getNumTotalCredits())) {
                return true;
            }
            return false;
        }


        return false;
    }
}
