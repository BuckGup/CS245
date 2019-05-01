import javax.swing.*;
import java.awt.*;
public class DumbPhone extends JFrame {

    private static final String CALL_BUTTON_TEXT = "Call";
    private static final String TEXT_BUTTON_TEXT = "Text";
    private static final String DELETE_BUTTON_TEXT = "Delete";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";
    private static final String SEND_BUTTON_TEXT = "Send";
    private static final String END_BUTTON_TEXT = "End";
    private static final String CALLING_DISPLAY_TEXT = "Calling...";
    private static final String TEXT_DISPLAY_TEXT = "Enter text...";
    private static final String ENTER_NUMBER_TEXT = "Enter a number...";

    private static final int WIDTH = 500;
    private static final int HEIGHT = 100;

    private JTextArea display;
    private JButton topMiddleButton;
    private JButton topLeftButton;
    private JButton topRightButton;
    private JButton numberButtons;
    private JButton starButton;
    private JButton poundButton;


    public DumbPhone(){

    setSize(WIDTH, HEIGHT);
    display = new JTextArea(80,280);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    add(new JButton("Delete"), BorderLayout.WEST);
    add(display, BorderLayout.NORTH);

    JPanel p = new JPanel(new GridLayout(3,5));
    add(p,BorderLayout.CENTER);

    JButton b1 = new JButton("<html><center>1</center></html>");
    JButton b2 = new JButton("<html><center>2<br>ABC</center></html>");
    JButton b3 = new JButton("<html><center>3<br>DEF</center></html>");
    JButton b4 = new JButton("<html><center>4<br>GHI</center></html>");
    JButton b5 = new JButton("<html><center>5<br>JKL</center></html>");
    JButton b6 = new JButton("<html><center>6<br>MNO</center></html>");
    JButton b7 = new JButton("<html><center>7<br>PQRS</center></html>");
    JButton b8 = new JButton("<html><center>8<br>TUV</center></html>");
    JButton b9 = new JButton("<html><center>9<br>WXYZ</center></html>");







    }





}
