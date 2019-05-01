public class UWECStaff extends UWECPerson {

    private String title;
    private double hourlyPay = 0;
    private double hoursPerWeek = 0;


    public UWECStaff(int uwecId, String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUwecId(uwecId);

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getHourlyPay() {
        return this.hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public double getHoursPerWeek() {
        return this.hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public String toString() {
        String UWECStaffString = ("UWECStaff " + "= " + "uwecId: " + getUwecId() + "," + " name: " + getFirstName() + " " + getLastName() +
                ", " + "title: " + title + ", " + "hourly pay: " + hourlyPay + ", " + "hours/week: " + hoursPerWeek);
        return UWECStaffString;
    }


    public boolean equals(Object other) {

        if (other instanceof UWECStaff) {

            if (((UWECStaff) other).getUwecId() == this.getUwecId()
                    && ((UWECStaff) other).getFirstName().equals(this.getFirstName())
                    && ((UWECStaff) other).getLastName().equals(this.getLastName())
                    && (((UWECStaff) other).getTitle().equals(this.getTitle()))
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
