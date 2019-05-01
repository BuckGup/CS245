import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UWECPeopleDriver {

    public static void main(String[] args) {


        String firstName = new String();
        int menuSelection;
        String lastName = new String();
        ArrayList<UWECPerson> universityPeople = new ArrayList<>();

        Scanner stdIn = new Scanner(System.in);
        System.out.println("What is the name of the input file?");
        String inputFileName = stdIn.nextLine();

        System.out.println("What is the name of the output file?");
        String outputFileName = stdIn.nextLine();


        File assig2In = new File(inputFileName);
        File assig2Out = new File(outputFileName);


        try {
            Scanner fileIn = new Scanner(assig2In);
            PrintWriter fileOut = new PrintWriter(assig2Out);

            while (fileIn.hasNext()) {
                int menuChoice = getMenuChoice(fileIn);

                switch (menuChoice) {

                    case 1:
                        addStudent(fileIn, universityPeople);
                        break;
                    case 2:
                        addStaff(fileIn, universityPeople);
                        break;
                    case 3:
                        addFaculty(fileIn, universityPeople);
                        break;
                    case 4:
                        computeTotalPayroll(fileOut, universityPeople);
                        break;
                    case 5:
                        printDirectory(fileOut, universityPeople);
                        break;
                    case 6:
                        break;
                    default:
                        fileOut.println("DEfault wrong.");
                        break;
                }

            }
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void addStudent(Scanner fileIn, ArrayList<UWECPerson> universityPeople) {

        String title;
        String firstName = "";
        String lastName = "";
        int uwecId = 0;
        double gpa = 0;
        int numTotalCredits = 0;



        if (fileIn.hasNextInt()) {
            uwecId = fileIn.nextInt();
        } else {
            System.out.print("Unexpected input received in addStudent.\n");
//            fileIn.nextLine();
        }


        if (fileIn.hasNext()) {
            firstName = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addStudent.\n");
//            fileIn.nextLine();
        }

        if (fileIn.hasNext()) {
            lastName = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addStudent.\n");
//            fileIn.nextLine();
        }


//        if (fileIn.hasNext() && !fileIn.hasNextInt() && !fileIn.hasNextDouble()) {
//            title = fileIn.next();
//        } else {
//            System.out.print("Unexpected input received in addStudent.\n");
////            fileIn.nextLine();
//        }

        if (fileIn.hasNextInt()) {
            numTotalCredits = fileIn.nextInt();
        } else {
            System.out.print("Unexpected input received in addStudent.\n");
//            fileIn.nextLine();
        }

        if (fileIn.hasNextDouble()) {
            gpa = fileIn.nextDouble();
        } else {
            System.out.print("Unexpected input received in addStudent.\n");
        }


            if(numTotalCredits < 24){
                title = "Freshman";
            }
            else if (numTotalCredits == 0){
                title = "Freshman";
            }
            else if(numTotalCredits < 57){
                title = "Sophomore";
            }
            else if(numTotalCredits < 85){
                title = "Junior";
            }
            else {
                title = "Senior";
            }


        UWECStudent student1 = new UWECStudent(uwecId, firstName, lastName, gpa);
        universityPeople.add(student1);
        student1.setNumTotalCredits(numTotalCredits);
        student1.setTitle(title);

    }

    public static void addStaff(Scanner fileIn, ArrayList<UWECPerson> universityPeople) {

        String title = "";
        String firstName = "";
        String lastName = "";
        int numTotalCredits = 0;
        int uwecId = 0;
        double hourlyPay = 0.0;
        double hoursPerWeek = 0.0;

        if (fileIn.hasNextInt()) {
            uwecId = fileIn.nextInt();
        } else {
            System.out.print("Unexpected input received in addStaff.\n");
//            fileIn.nextLine();
        }


        if (fileIn.hasNext()) {
            firstName = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addStaff.\n");
//            fileIn.nextLine();
        }


        if (fileIn.hasNext()) {
            lastName = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addStaff.\n");
//            fileIn.nextLine();
        }


        if (fileIn.hasNext()) {
            title = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addStaff.\n");
//            fileIn.nextLine();
        }

        if (fileIn.hasNextDouble() || fileIn.hasNextInt()) {
            hourlyPay = fileIn.nextDouble();
        } else {
            System.out.print("Unexpected input received in addStaff.\n");
//            fileIn.nextLine();
        }


        if (fileIn.hasNextDouble() || fileIn.hasNextInt()) {
            hoursPerWeek = fileIn.nextDouble();
        } else {
            System.out.print("Unexpected input received in addStaff.\n");
//            fileIn.nextLine();
        }


        UWECStaff staff1 = new UWECStaff(uwecId, firstName, lastName, title);
        staff1.setTitle(title);
        staff1.setHourlyPay(hourlyPay);
        staff1.setHoursPerWeek(hoursPerWeek);
        universityPeople.add(staff1);

    }


    public static void addFaculty(Scanner fileIn, ArrayList<UWECPerson> universityPeople) {

        String title;
        String firstName = "";
        String lastName = "";
        int uwecId = 0;
        double yearlySalary = 0.0;
        int numTotalCredits = 0;

        if (fileIn.hasNextInt()) {
            uwecId = fileIn.nextInt();
        } else {
            System.out.print("Unexpected input received in addFaculty.\n");
//            fileIn.nextLine();
        }

        if (fileIn.hasNext()) {
            firstName = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addFaculty.\n");
//            fileIn.nextLine();
        }

        if (fileIn.hasNext()) {
            lastName = fileIn.next();
        } else {
            System.out.print("Unexpected input received in addFaculty.\n");
//            fileIn.nextLine();
        }
//        if (fileIn.hasNext() && !fileIn.hasNextInt()) {
//            title = fileIn.next();
//        } else {
//            System.out.print("Unexpected input received in addFaculty.\n");
////            fileIn.nextLine();
//        }

        if (fileIn.hasNextInt()) {
            numTotalCredits = fileIn.nextInt();
        } else {
            System.out.print("Unexpected input received in addFaculty.\n");
//            fileIn.nextLine();
        }


        if (fileIn.hasNextDouble() || fileIn.hasNextInt()) {
            yearlySalary = fileIn.nextInt();
        } else {
            System.out.print("Unexpected input received in addFaculty.\n");
//            fileIn.nextLine();
        }

        if (numTotalCredits <= 48){
            title = "Adjunct Professor";
        }
        else if (numTotalCredits <= 119){
            title = "Assistant Professor";
        }
        else if (numTotalCredits <= 239){
            title = "Associate Professor";
        }
        else if (numTotalCredits > 239){
            title = "Professor";
        }
        else {
            title = "Adjunct Professor";
        }
        System.err.print(title);
        System.err.print(numTotalCredits);



        UWECFaculty faculty1 = new UWECFaculty(uwecId, firstName, lastName, numTotalCredits);
        faculty1.setTitle(title);
        faculty1.setYearlySalary(yearlySalary);
        faculty1.setNumTotalCredits(numTotalCredits);
        universityPeople.add(faculty1);


    }

    public static int getMenuChoice(Scanner fileIn) {

        String choice;

        if (fileIn.hasNext()) {
            choice = fileIn.next();

            if (choice.equals("1")) {
                return 1;
            }
            if (choice.equals("2")) {
                return 2;
            }
            if (choice.equals("3")) {
                return 3;
            }
            if (choice.equals("4")) {
                return 4;
            }
            if (choice.equals("5")) {
                return 5;
            }
            if (choice.equals("6")) {
                return 6;
            }
            if (choice.equalsIgnoreCase("one")) {
                return 1;
            }
            if (choice.equalsIgnoreCase("Two")) {
                return 2;
            }
            if (choice.equalsIgnoreCase("three")) {
                return 3;
            }
            if (choice.equalsIgnoreCase("four")) {
                return 4;
            }
            if (choice.equalsIgnoreCase("five")) {
                return 5;
            }
            if (choice.equalsIgnoreCase("six")) {
                return 6;
            } else {
                return 6;
            }

        } else {
            System.out.print("Non-integer entered in getMenuChoice");
            return -1;
        }



    }

    public static void computeTotalPayroll(PrintWriter fileOut, ArrayList<UWECPerson> universityPeople) {

        Double payCheck = 0.00;
        Double payCheck2 = 0.00;
        Double payCheckFinal = 0.00;

        for (UWECPerson person : universityPeople) {
            if (person instanceof UWECFaculty) {
                payCheck += ((UWECFaculty) person).computePaycheck();
            }
            else if(person instanceof UWECStaff){

                payCheck2 += ((UWECStaff) person).computePaycheck();

            }
        }

       payCheckFinal = payCheck + payCheck2;


        fileOut.printf("Total payroll: $%,.2f\n", payCheckFinal);

    }


    public static void printDirectory(PrintWriter fileOut, ArrayList<UWECPerson> universityPeople) {

        for (UWECPerson person : universityPeople) {
            fileOut.println(person.toString());

        }

    }

}

