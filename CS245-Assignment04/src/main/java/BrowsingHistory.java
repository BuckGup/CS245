import java.util.Scanner;

public class BrowsingHistory {

    public static void main(String args[]) {
        String userInput = "";
        String URL = null;
        String valueRead = "";
        Stack historyStack = new Stack<>();
        Scanner userIn = new Scanner(System.in);


        do {
            if (historyStack.isEmpty()) {
                System.out.print("Enter a URL or \"quit\": ");
            } else {
                System.out.print("Enter a URL, \"back\", or \"quit\": ");
            }
            userInput = userIn.nextLine();

            if (!userInput.equalsIgnoreCase("back") && !userInput.equalsIgnoreCase("quit")) {

                if (URL != null) {
                    historyStack.push(URL);
                }
                URL = userInput;
            }


            if (userInput.equalsIgnoreCase("back")) {
                if (historyStack.isEmpty() || URL == historyStack.peek()) {

                    System.out.println("No URL to go back to");
                    if (URL == null) {

                    } else {
                        System.out.println("Current URL: " + URL);
                    }
                } else {
                    URL = (String) historyStack.pop();
                    System.out.println("Current URL: " + URL);
                    System.out.println("");
                }

            } else if (!userInput.equalsIgnoreCase("quit"))
                System.out.println("Current URL: " + URL);
            System.out.println("");


        }
        while (!userInput.equalsIgnoreCase("quit"));

    }

}