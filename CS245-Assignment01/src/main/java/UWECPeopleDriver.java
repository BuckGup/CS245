import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class UWECPeopleDriver {

    public static void main(String[] args) {

        String firstName = new String();
        File file = new File("assignment2Input1.txt");
        try {
            Scanner fileIn = new Scanner(file);
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        ArrayList<UWECStudent> students = new ArrayList<>();
        ArrayList<UWECStaff> staff = new ArrayList<>();
        int menuSelection;
        String lastName = new String();


        do {
            menuSelection = getMenuChoice();
            if (menuSelection == 1) {
                addStudent(stdIn, students);
            }
            if (menuSelection == 2) {
                addStaff(stdIn, staff);
            }

            if (menuSelection == 3) {
                printStudents(students);
            }

            if (menuSelection == 4) {
                printStaff(staff);
            }
            if (menuSelection == 0) {
                System.out.println("Invalid");
            }

            if (menuSelection == 5) {
                break;
            }

        }
        while (menuSelection < 5);
        return;
    }

    public static void addStudent(Scanner stdIn, ArrayList<UWECStudent> students) {
        String firstName;
        String lastName;
        int uwecId;
        double gpa;
        System.out.println("Enter id: \n");
        uwecId = stdIn.nextInt();


        System.out.println("Enter first name: \n");
        firstName = stdIn.next();
        System.out.println("Enter last name: \n");
        lastName = stdIn.next();
        System.out.println("Enter gpa: ");

        gpa = stdIn.nextDouble();

        UWECStudent student1 = new UWECStudent(uwecId, firstName, lastName, gpa);
        students.add(student1);

    }

    public static void addStaff(Scanner stdIn, ArrayList<UWECStaff> staff) {
        String title;
        String firstName;
        String lastName;
        double hourlyPay;
        double hoursPerWeek;
        int uwecId;

        System.out.println("Enter id: \n");
        uwecId = stdIn.nextInt();
        System.out.println("first name: \n");
        firstName = stdIn.next();
        System.out.println("Enter last name: \n");
        lastName = stdIn.next();
        System.out.println("Enter title: \n");
        title = stdIn.nextLine();
        title += stdIn.nextLine();

        System.out.println("Enter hourly pay: \n");
        hourlyPay = stdIn.nextDouble();
        System.out.println("Enter hours per week: ");
        hoursPerWeek = stdIn.nextDouble();


        UWECStaff staff1 = new UWECStaff(uwecId, firstName, lastName);
        staff1.setTitle(title);
        staff1.setHourlyPay(hourlyPay);
        staff1.setHoursPerWeek(hoursPerWeek);
        staff.add(staff1);


    }

    public static int getMenuChoice(Scanner stdIn) {
        System.out.println("+++++++++++++++++++++++++\n " +
                "+ 1. Add Student      +\n " +
                "+ 2. Add Staff        +\n " +
                "+ 3. Print Students   +\n " +
                "+ 4. Print Staff      +\n " +
                "+ 5. Quit             +\n" +
                "+++++++++++++++++++++++++\n" +
                "Enter an option (1-5):");
        int choice;
        choice = stdIn.nextInt();

        if (choice == 0) {
            return 5;
        }
        if (choice > 5) {
            return 5;
        } else {
            return choice;

        }

    }

    public static void printStaff(ArrayList<UWECStaff> staff) {
        if (staff.isEmpty() == true) {
            System.out.println("No staff found.");
        } else {

            for (int i = 0; i < staff.size(); i++) {
                System.out.print(staff.get(i).toString() + "\n");
            }
        }
    }

    public static void printStudents(ArrayList<UWECStudent> students) {
        if (students.isEmpty() == true) {
            System.out.println("No students found.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.print(students.get(i).toString() + "\n");
            }
        }
    }

}