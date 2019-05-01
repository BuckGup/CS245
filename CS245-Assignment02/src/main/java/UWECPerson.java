public abstract class UWECPerson {

    private String title;
    private int uwecId = 0;
    private String firstName = new String();
    private String lastName = new String();


    public UWECPerson(int uwecId, String firstName, String lastName) {

        this.uwecId = uwecId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public final String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public final int getUwecId() {
        return this.uwecId;
    }

    public void setUwecId(int uwecId) {
        this.uwecId = uwecId;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public abstract String toString();



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
