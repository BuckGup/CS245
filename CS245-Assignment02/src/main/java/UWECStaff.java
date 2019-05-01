public class UWECStaff extends UWECPerson implements UWECEmployee {

    private double hourlyPay = 0;
    private double hoursPerWeek = 0;



    public UWECStaff(int uwecId, String firstName, String lastName, String title) {
        super(uwecId, firstName, lastName);
        this.setTitle(title);

    }


    public final double getHourlyPay() {
        return this.hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public final double getHoursPerWeek() {
        return this.hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public double computePaycheck(){
        return ((hoursPerWeek * 2) * hourlyPay);
    }


    public String toString() {
        String UWECStaffString = ("UWECStaff " + "= " + "uwecId: " + getUwecId() + "," + " name: " + getFirstName() + " " + getLastName() +
                ", " + "title: " + getTitle() + ", " + "hourly pay: " + hourlyPay + ", " + "hours/week: " + hoursPerWeek);
        return UWECStaffString;
    }


    public boolean equals(Object other) {

        if (other instanceof UWECStaff) {

            if (((UWECStaff) other).getUwecId() == this.getUwecId()
                    && ((UWECStaff) other).getFirstName().equals(this.getFirstName())
                    && ((UWECStaff) other).getLastName().equals(this.getLastName())
                    && (((UWECStaff) other).getTitle().equals(getTitle()))
                    && ((UWECStaff) other).getHourlyPay() == (this.hourlyPay)
                    && ((UWECStaff) other).getHoursPerWeek() == (this.hoursPerWeek)) {
                return true;
            } else {
                return false;
            }

        }


        return false;
    }

}
