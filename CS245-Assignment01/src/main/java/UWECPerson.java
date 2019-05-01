public class UWECPerson {

    private int uwecId = 0;
    private String firstName = new String();
    private String lastName = new String();


    public UWECPerson(int uwecId, String firstName, String lastName) {

        this.uwecId = uwecId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UWECPerson() {

    }

    public int getUwecId() {
        return this.uwecId;
    }

    public void setUwecId(int uwecId) {
        this.uwecId = uwecId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String toString() {
        String UWECPersonString = ("UWECPerson " + "= " + "uwecId: " + getUwecId() + "," + " name: " + getFirstName() + " " + getLastName());
        return UWECPersonString;
    }


    public boolean equals(Object other) {

        if (other instanceof UWECPerson) {

            if (((UWECPerson) other).getUwecId() == this.getUwecId()
                    && ((UWECPerson) other).getFirstName().equals(this.getFirstName())
                    && ((UWECPerson) other).getLastName().equals(this.getLastName())) {
                return true;
            }
            return false;
        }


        return false;
    }


}
