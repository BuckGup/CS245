public abstract class UWECAcademic extends UWECPerson {

    private int numTotalCredits;


    public UWECAcademic(int uwecId, String firstName, String lastName) {
        super(uwecId, firstName, lastName);
    }



    public final int getNumTotalCredits() {
        return this.numTotalCredits;
    }


    public void setNumTotalCredits(int numTotalCredits) {
        this.numTotalCredits = numTotalCredits;
    }

    public abstract String toString();

    public boolean equals(Object other) {

        if (other instanceof UWECAcademic) {

            if (((UWECAcademic) other).getUwecId() == this.getUwecId()
                    && ((UWECAcademic) other).getFirstName().equals(this.getFirstName())
                    && ((UWECAcademic) other).getLastName().equals(this.getLastName())) {
                return true;
            }
            return false;
        }


        return false;
    }


}
