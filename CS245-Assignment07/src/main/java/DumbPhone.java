import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;
import java.util.Date;
import java.awt.font.NumericShaper;
import java.awt.image.ImageObserver;

import javax.swing.*;
import javax.swing.border.Border;

public class DumbPhone extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 500;
    private static final String CALL_BUTTON_TEXT = "Call";
    private static final String TEXT_BUTTON_TEXT = "Text";
    private static final String DELETE_BUTTON_TEXT = "Delete";
    private static final String CANCEL_BUTTON_TEXT = "Cancel";
    private static final String SEND_BUTTON_TEXT = "Send";
    private static final String END_BUTTON_TEXT = "End";
    private static final String CALLING_DISPLAY_TEXT = "Calling...";
    private static final String TEXT_DISPLAY_TEXT = "Enter text...";
    private static final String ENTER_NUMBER_TEXT = "Enter a number...";
    private JTextArea display;
    private JButton topMiddleButton;
    private JButton topLeftButton;
    private JButton topRightButton;
    private JButton[] numberButtons;
    private JButton starButton;
    private JButton poundButton;
    private JButton topMiddleButton2;

    public DumbPhone() {
        setTitle("Dumb Phone");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createContents();
        setVisible(true);
    }

    public static void main(String[] args) {
        new DumbPhone();
    }

    private void createContents() {
        //create JPanel, and JTextArea display
        JPanel p = new JPanel(new GridLayout(5, 3));

        display = new JTextArea();
        add(display, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);

        display.setPreferredSize(new Dimension(280, 80));
        display.setLineWrap(true);
        display.setFont(new Font("Helvetica", Font.PLAIN, 32));
        display.setEditable(false);
        display.setEnabled(false);


        //create JButtons
        numberButtons = new JButton[10];
        numberButtons[1] = new JButton("<html><center>1</center></html>");
        numberButtons[2] = new JButton("<html><center>2<br>ABC</center></html>");
        numberButtons[3] = new JButton("<html><center>3<br>DEF</center></html>");
        numberButtons[4] = new JButton("<html><center>4<br>GHI</center></html>");
        numberButtons[5] = new JButton("<html><center>5<br>JKL</center></html>");
        numberButtons[6] = new JButton("<html><center>6<br>MNO</center></html>");
        numberButtons[7] = new JButton("<html><center>7<br>PQRS</center></html>");
        numberButtons[8] = new JButton("<html><center>8<br>TUV</center></html>");
        numberButtons[9] = new JButton("<html><center>9<br>WXYZ</center></html>");
        numberButtons[0] = new JButton("<html><center>0<br>space</center></html>");

        topLeftButton = new JButton(DELETE_BUTTON_TEXT);
        topMiddleButton = new JButton(CALL_BUTTON_TEXT);
        topRightButton = new JButton(TEXT_BUTTON_TEXT);
        starButton = new JButton("*");
        poundButton = new JButton("#");
        topLeftButton.setEnabled(false);
        topMiddleButton2 = new JButton(CANCEL_BUTTON_TEXT);

        //add JButtons to buttons JPanel

        p.add(topLeftButton);
        p.add(topMiddleButton);
        p.add(topRightButton);
        p.add(numberButtons[1]);
        p.add(numberButtons[2]);
        p.add(numberButtons[3]);
        p.add(numberButtons[4]);
        p.add(numberButtons[5]);
        p.add(numberButtons[6]);
        p.add(numberButtons[7]);
        p.add(numberButtons[8]);
        p.add(numberButtons[9]);
        p.add(starButton);
        p.add(numberButtons[0]);
        p.add(poundButton);

        //add Listener instance (inner class) to buttons
        Listener listener = new Listener();

        numberButtons[0].addActionListener(listener);
        numberButtons[1].addActionListener(listener);
        numberButtons[2].addActionListener(listener);
        numberButtons[3].addActionListener(listener);
        numberButtons[4].addActionListener(listener);
        numberButtons[5].addActionListener(listener);
        numberButtons[6].addActionListener(listener);
        numberButtons[7].addActionListener(listener);
        numberButtons[8].addActionListener(listener);
        numberButtons[9].addActionListener(listener);
        topRightButton.addActionListener(listener);
        topMiddleButton.addActionListener(listener);
        topLeftButton.addActionListener(listener);
        starButton.addActionListener(listener);
        poundButton.addActionListener(listener);
        topMiddleButton2.addActionListener(listener);


    }


    private class Listener implements ActionListener {
        int counter = 0;
        long lastPressTime = System.currentTimeMillis();
        private int lastKey = 0;


        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == topRightButton) {
                if ((display.getText().trim().length() == 0) || display.getText().equals(ENTER_NUMBER_TEXT)) {
                    display.setText(ENTER_NUMBER_TEXT);

                } else if (topRightButton.getText().equals(SEND_BUTTON_TEXT)) {
                    display.setText("");
                    topRightButton.setText(TEXT_BUTTON_TEXT);
                    topMiddleButton.setText(CANCEL_BUTTON_TEXT);
                    topLeftButton.setEnabled(false);


                } else {
                    display.setText(TEXT_DISPLAY_TEXT);
                    topRightButton.setEnabled(false);
                    numberButtons[1].setEnabled(false);
                    starButton.setEnabled(false);
                    poundButton.setEnabled(false);
                    topRightButton.setText(SEND_BUTTON_TEXT);
                    topMiddleButton.setText(CANCEL_BUTTON_TEXT);
                    topLeftButton.setEnabled(false);
                }


            } else if (e.getSource() == topMiddleButton) {
                if ((display.getText().trim().length() == 0) || display.getText().equals(ENTER_NUMBER_TEXT)) {
                    display.setText("");
                    display.setText(ENTER_NUMBER_TEXT);

                } else if (topRightButton.getText().equals(SEND_BUTTON_TEXT)) {
                    display.setText("");
                    topRightButton.setText(TEXT_BUTTON_TEXT);
                    topMiddleButton.setText(CALL_BUTTON_TEXT);
                    topLeftButton.setEnabled(false);
                } else if (topLeftButton.getText().equals(TEXT_BUTTON_TEXT)) {
                    display.setText("");


                } else if (topMiddleButton.getText().equals(END_BUTTON_TEXT)) {
                    display.setText("");
                    topMiddleButton.setText(CALL_BUTTON_TEXT);
                    topLeftButton.setText(DELETE_BUTTON_TEXT);
                    topRightButton.setText(TEXT_BUTTON_TEXT);
                    topLeftButton.setVisible(true);
                    topRightButton.setVisible(true);
                    topLeftButton.setEnabled(false);
                    topRightButton.setEnabled(true);


                } else {
                    display.setText("");
                    display.setText(CALLING_DISPLAY_TEXT);
                    topLeftButton.setVisible(false);
                    topRightButton.setVisible(false);
                    topLeftButton.setEnabled(false);
                    topRightButton.setEnabled(false);
                    topLeftButton.setText("");
                    topRightButton.setText("");
                    topMiddleButton.setText(END_BUTTON_TEXT);

                }

            } else if (e.getSource() == topLeftButton) {
                if ((display.getText().length() - 1) == 0) {
                    String s = display.getText();
                    s = s.substring(0, s.length() - 1);
                    display.setText(s);
                    topLeftButton.setEnabled(false);
                } else {
                    String s = display.getText();
                    s = s.substring(0, s.length() - 1);
                    display.setText(s);
                }


            } else if (topRightButton.getText().equals(TEXT_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || topMiddleButton.getText().equals(END_BUTTON_TEXT)) {
                if (e.getSource() == numberButtons[0]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("0");
                    topLeftButton.setEnabled(true);

                } else if (e.getSource() == numberButtons[1]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("1");
                    topLeftButton.setEnabled(true);

                } else if (e.getSource() == numberButtons[2]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("2");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[3]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("3");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[4]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("4");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[5]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("5");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[6]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("6");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[7]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("7");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[8]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("8");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[9]) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("9");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == starButton) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("*");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == poundButton) {
                    if (display.getText().equals(ENTER_NUMBER_TEXT) || display.getText().equals(CALL_BUTTON_TEXT) || display.getText().equals(CALLING_DISPLAY_TEXT) || display.getText().equals(END_BUTTON_TEXT)) {
                        display.setText("");
                    }
                    display.append("#");
                    topLeftButton.setEnabled(true);
                }


            } else if (topRightButton.getText().equals(SEND_BUTTON_TEXT)) {
                if (e.getSource() == numberButtons[0]) {
                    display.append(" ");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[2]) {


                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"A", "B", "C"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 2) {
                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 3]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 3]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 3]));
                        } else {
                            display.setText((ABC[counter % 3]));
                        }

                    }
                    lastKey = 2;
                    lastPressTime = System.currentTimeMillis();

                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[3]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"D", "E", "F"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 3) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 3]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 3]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 3]));
                        } else {
                            display.setText((ABC[counter % 3]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();
                    lastKey = 3;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[4]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"G", "H", "I"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 4) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 3]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 3]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 3]));
                        } else {
                            display.setText((ABC[counter % 3]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();

                    lastKey = 4;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[5]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"J", "K", "L"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 5) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 3]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 3]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 3]));
                        } else {
                            display.setText((ABC[counter % 3]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();
                    lastKey = 5;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[6]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"M", "N", "O"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 6) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 3]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 3]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 3]));
                        } else {
                            display.setText((ABC[counter % 3]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();
                    lastKey = 6;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[7]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"P", "Q", "R", "S"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 7) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 4]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 4]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 4]));
                        } else {
                            display.setText((ABC[counter % 4]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();
                    lastKey = 7;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[8]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"T", "U", "V"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 8) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 3]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 3]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 3]));
                        } else {
                            display.setText((ABC[counter % 3]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();
                    lastKey = 8;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == numberButtons[9]) {
                    if (display.getText().equals(TEXT_DISPLAY_TEXT)) {
                        display.setText("");
                    }


                    String[] ABC = {"W", "X", "Y", "Z"};

                    if ((System.currentTimeMillis() - lastPressTime < 1000) && lastKey == 9) {

                        counter++;

                        if (display.getText().length() != 0) {
                            String s = display.getText();
                            s = s.substring(0, s.length() - 1);
                            s = s + ((ABC[counter % 4]));
                            display.setText(s);
                        } else {
                            display.setText((ABC[counter % 4]));
                        }
                    } else {
                        if (display.getText().length() != 0) {
                            counter = 0;
                            display.append((ABC[counter % 4]));
                        } else {
                            display.setText((ABC[counter % 4]));
                        }

                    }

                    lastPressTime = System.currentTimeMillis();
                    lastKey = 9;
                    topLeftButton.setEnabled(true);
                    topRightButton.setEnabled(true);
                } else if (e.getSource() == starButton) {
                    display.append("*");
                    topLeftButton.setEnabled(true);
                } else if (e.getSource() == poundButton) {
                    display.append("#");
                    topLeftButton.setEnabled(true);
                }
            }
        }
    }
}
