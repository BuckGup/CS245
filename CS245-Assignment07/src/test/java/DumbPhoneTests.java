import static org.junit.jupiter.api.Assertions.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DumbPhoneTests {
	
	private DumbPhone dumbPhone;
	private Field display;
	private Field topMiddleButton;
	private Field topLeftButton;
	private Field topRightButton;
	private Field numberButtons;
	private Field starButton;
	private Field poundButton;
	private Method actionPerformed;
	private static final String CALL_BUTTON_TEXT = "Call";
	private static final String TEXT_BUTTON_TEXT = "Text";
	private static final String DELETE_BUTTON_TEXT = "Delete";
	private static final String CANCEL_BUTTON_TEXT = "Cancel";
	private static final String SEND_BUTTON_TEXT = "Send";
	private static final String END_BUTTON_TEXT = "End";
	private static final String CALLING_DISPLAY_TEXT = "Calling...";
	private static final String TEXT_DISPLAY_TEXT = "Enter text...";
	private static final String ENTER_NUMBER_TEXT = "Enter a number...";

	@BeforeEach
	public void setUp() throws Exception {
		dumbPhone = new DumbPhone();
		display = DumbPhone.class.getDeclaredField("display");
		display.setAccessible(true);
		topMiddleButton = DumbPhone.class.getDeclaredField("topMiddleButton");
		topMiddleButton.setAccessible(true);
		topLeftButton = DumbPhone.class.getDeclaredField("topLeftButton");
		topLeftButton.setAccessible(true);
		topRightButton = DumbPhone.class.getDeclaredField("topRightButton");
		topRightButton.setAccessible(true);
		numberButtons = DumbPhone.class.getDeclaredField("numberButtons");
		numberButtons.setAccessible(true);
		starButton = DumbPhone.class.getDeclaredField("starButton");
		starButton.setAccessible(true);
		poundButton = DumbPhone.class.getDeclaredField("poundButton");
		poundButton.setAccessible(true);
		
		Class<?> listenerClass = Class.forName("DumbPhone$Listener");
		actionPerformed = listenerClass.getDeclaredMethod("actionPerformed", ActionEvent.class);
		actionPerformed.setAccessible(true);
	}

	@Test
	public void testConstructor() {
		try {
			//check high level stuff
			assertEquals("Dumb Phone", dumbPhone.getTitle(), "Wrong title");
			assertEquals(300, dumbPhone.getSize().getWidth(), "Wrong DumbPhone width");
			assertEquals(500, dumbPhone.getSize().getHeight(), "Wrong DumbPhone height");
			assertEquals(JFrame.EXIT_ON_CLOSE, dumbPhone.getDefaultCloseOperation(), "Wrong close operation");
			assertTrue(dumbPhone.isVisible(), "DumbPhone should be visible");
		} catch (Exception e) {
			fail("DumbPhone constructor is not working correctly");
		}
	}
	
	@Test
	public void testFrameAndPanel() {
		try {
			//check JFrame
			List<Component> jFrameComponents = Arrays.asList(dumbPhone.getContentPane().getComponents());
			assertEquals(2, jFrameComponents.size(), "Wrong number of components in your JFrame (should be 2)");
			assertEquals(BorderLayout.class, dumbPhone.getLayout().getClass(), "Wrong JPanel layout manager");
			assertEquals(display.get(dumbPhone), jFrameComponents.get(0), "Display not present in your JFrame as expected");
			assertEquals(JPanel.class, jFrameComponents.get(1).getClass(), "JPanel not present in your JFrame as expected");
			
			//check display
			JTextArea d = (JTextArea)jFrameComponents.get(0);
			assertEquals(280, d.getPreferredSize().getWidth(), "Display preferred width incorrect");
			assertEquals(80, d.getPreferredSize().getHeight(), "Display preferred height incorrect");
			assertEquals(new Font("Helvetica", Font.PLAIN, 32), d.getFont(), "Display font incorrect");
			assertTrue(d.getLineWrap(), "Display line wrapping incorrect");
			assertFalse(d.isEnabled(), "Display should be disabled");
			assertTrue(d.getText().isEmpty(), "Display contents should initially be empty");
			
			//check JPanel
			JPanel panel = (JPanel)jFrameComponents.get(1);
			List<Component> jPanelComponents = Arrays.asList(panel.getComponents());
			assertEquals(15, jPanelComponents.size(), "Wrong number of components in your JPanel (should be 15)");
			assertEquals(GridLayout.class, panel.getLayout().getClass(), "Wrong JPanel layout manager");
		} catch (Exception e) {
			fail("DumbPhone JFrame, JTextArea, and JPanel not constructed correctly");
		}
	}
	
	@Test
	public void testPanelButtons() {
		try {
			List<Component> jFrameComponents = Arrays.asList(dumbPhone.getContentPane().getComponents());
			JPanel panel = (JPanel)jFrameComponents.get(1);
			List<Component> jPanelComponents = Arrays.asList(panel.getComponents());
			
			//check buttons
			for(int i=0; i<jPanelComponents.size(); ++i) {
				assertTrue(jPanelComponents.get(i) instanceof JButton, "JPanel should only contain buttons");
			}
			JButton b = (JButton)jPanelComponents.get(0);
			assertEquals(topLeftButton.get(dumbPhone), b, "Top left button not right");
			assertEquals(DELETE_BUTTON_TEXT, b.getText(), "Top left button text not right");
			assertFalse(b.isEnabled(), "Top left button should be initially be disabled");
			b = (JButton)jPanelComponents.get(1);
			assertEquals(topMiddleButton.get(dumbPhone), b, "Top middle button not right");
			assertEquals(CALL_BUTTON_TEXT, b.getText(), "Top middle button text not right");
			b = (JButton)jPanelComponents.get(2);
			assertEquals(topRightButton.get(dumbPhone), b, "Top right button not right");
			assertEquals(TEXT_BUTTON_TEXT, b.getText(), "Top right button text not right");
			b = (JButton)jPanelComponents.get(3);
			assertTrue(b.getText().contains("1"), "1 button text not right");
			b = (JButton)jPanelComponents.get(4);
			assertTrue(b.getText().contains("2") && b.getText().contains("ABC"), "2 button text not right");
			b = (JButton)jPanelComponents.get(5);
			assertTrue(b.getText().contains("3") && b.getText().contains("DEF"), "3 button text not right");
			b = (JButton)jPanelComponents.get(6);
			assertTrue(b.getText().contains("4") && b.getText().contains("GHI"), "4 button text not right");
			b = (JButton)jPanelComponents.get(7);
			assertTrue(b.getText().contains("5") && b.getText().contains("JKL"), "5 button text not right");
			b = (JButton)jPanelComponents.get(8);
			assertTrue(b.getText().contains("6") && b.getText().contains("MNO"), "6 button text not right");
			b = (JButton)jPanelComponents.get(9);
			assertTrue(b.getText().contains("7") && b.getText().contains("PQRS"), "7 button text not right");
			b = (JButton)jPanelComponents.get(10);
			assertTrue(b.getText().contains("8") && b.getText().contains("TUV"), "8 button text not right");
			b = (JButton)jPanelComponents.get(11);
			assertTrue(b.getText().contains("9") && b.getText().contains("WXYZ"), "9 button text not right");
			b = (JButton)jPanelComponents.get(12);
			assertEquals(starButton.get(dumbPhone), b, "Star button not right");
			assertEquals("*", b.getText(), "Star button text not right");			
			b = (JButton)jPanelComponents.get(13);
			assertTrue(b.getText().contains("0") && b.getText().contains("space"), "0 button text not right");
			b = (JButton)jPanelComponents.get(14);
			assertEquals(poundButton.get(dumbPhone), b, "Pound button not right");
			assertEquals("#", b.getText(), "Pound button text not right");
		} catch (Exception e) {
			fail("DumbPhone buttons not constructed correctly");
		}
	}
	
	@Test
	public void testCallingPreCall() {
		try {
			JTextArea display = (JTextArea)this.display.get(dumbPhone);
			JButton topLeft = (JButton)topLeftButton.get(dumbPhone);
			JButton topMiddle = (JButton)topMiddleButton.get(dumbPhone);
			JButton[] numbers =(JButton[])numberButtons.get(dumbPhone) ;
			Object listener =  topMiddle.getActionListeners()[0];

			//Call
			ActionEvent e = new ActionEvent(topMiddle, 0, topMiddle.getText());
			actionPerformed.invoke(listener, e);
			assertEquals(ENTER_NUMBER_TEXT, display.getText(), "\"Enter a number...\" should be displayed when \"Call\" is pressed with an empty display");
			//Call
			actionPerformed.invoke(listener, e);
			assertEquals(ENTER_NUMBER_TEXT, display.getText(), "\"Enter a number...\" should be displayed when \"Call\" is pressed with \"Enter a number...\" on display");
			assertFalse(topLeft.isEnabled(), "Delete button should be disabled with \"Enter a number...\" on display");
			//0
			e = new ActionEvent(numbers[0], 0, numbers[0].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("0", display.getText(), "Display contents incorrect after pressing number button with \"Enter a number...\" on display");
			assertTrue(topLeft.isEnabled(), "Delete button should be enabled with numbers on display");
			//01
			e = new ActionEvent(numbers[1], 0, numbers[1].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("01", display.getText(), "Display contents incorrect after pressing number button with number on display");
			//0
			e = new ActionEvent(topLeft, 0, topLeft.getText());
			actionPerformed.invoke(listener, e);
			assertEquals("0", display.getText(), "Display contents incorrect after pressing delete button with multiple numbers on display");
			//
			e = new ActionEvent(topLeft, 0, topLeft.getText());
			actionPerformed.invoke(listener, e);
			assertTrue(display.getText().isEmpty(), "Display contents should be blank after pressing delete button with one number on display");
			assertFalse(topLeft.isEnabled(), "Delete button should be disabled after deleting all numbers on display");
			//Call
			e = new ActionEvent(topMiddle, 0, topMiddle.getText());
			actionPerformed.invoke(listener, e);
			assertEquals(ENTER_NUMBER_TEXT, display.getText(), "\"Enter a number...\" should be displayed when \"Call\" is pressed with empty display");
		} catch (Exception e) {
			fail("Pre-call functionality is not working correctly");
		}
	}
	
	@Test
	public void testCallingMakeCall() {
		try {
			JTextArea display = (JTextArea)this.display.get(dumbPhone);
			JButton topLeft = (JButton)topLeftButton.get(dumbPhone);
			JButton topMiddle = (JButton)topMiddleButton.get(dumbPhone);
			JButton topRight = (JButton)topRightButton.get(dumbPhone);
			JButton[] numbers =(JButton[])numberButtons.get(dumbPhone) ;
			JButton star = (JButton)starButton.get(dumbPhone);
			JButton pound = (JButton)poundButton.get(dumbPhone);
			Object listener =  topMiddle.getActionListeners()[0];

			//*
			ActionEvent e = new ActionEvent(star, 0, star.getText());
			actionPerformed.invoke(listener, e);
			assertEquals("*", display.getText(), "Display contents incorrect after pressing * button with empty display");
			//*#
			e = new ActionEvent(pound, 0, pound.getText());
			actionPerformed.invoke(listener, e);
			assertEquals("*#", display.getText(), "Display contents incorrect after pressing # button with * on display");
			//Call
			e = new ActionEvent(topMiddle, 0, topMiddle.getText());
			actionPerformed.invoke(listener, e);
			assertEquals(CALLING_DISPLAY_TEXT, display.getText(), "\"Calling...\" should be displayed when \"Call\" is pressed with phone number on display");
			assertEquals(END_BUTTON_TEXT, topMiddle.getText(), "Top middle button text should read \"End\" during call");
			assertTrue(topLeft.getText().isEmpty(), "Top left button text should be blank when calling");
			assertFalse(topLeft.isEnabled(), "Top left button should be disabled when calling");
			assertTrue(topRight.getText().isEmpty(), "Top right button text should be blank when calling");
			assertFalse(topRight.isEnabled(), "Top right button should be disabled when calling");
			//2
			e = new ActionEvent(numbers[2], 0, numbers[2].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("2", display.getText(), "Display contents incorrect after pressing number button with \"Calling...\" on display");
			assertEquals(END_BUTTON_TEXT, topMiddle.getText(), "Top middle button text should read \"End\" during call with number on display");
			//2#
			e = new ActionEvent(pound, 0, pound.getText());
			actionPerformed.invoke(listener, e);
			assertEquals("2#", display.getText(), "Display contents incorrect after pressing # button during call with number on display");
			//End
			e = new ActionEvent(topMiddle, 0, topMiddle.getText());
			actionPerformed.invoke(listener, e);
			assertTrue(display.getText().isEmpty(), "Display contents should be empty after call ends");
			assertEquals(DELETE_BUTTON_TEXT, topLeft.getText(), "Top left button text should read \"Delete\" after call ends");
			assertFalse(topLeft.isEnabled(), "Delete button should not be enabled after call ends");
			assertEquals(TEXT_BUTTON_TEXT, topRight.getText(), "Top right button text should read \"Text\" after call ends");
			assertTrue(topRight.isEnabled(), "Text button should be enabled after call ends");
		} catch (Exception e) {
			fail("Calling functionality is not working correctly");
		}
	}
	
	@Test
	public void testTextingPreText() {
		try {
			JTextArea display = (JTextArea)this.display.get(dumbPhone);
			JButton topLeft = (JButton)topLeftButton.get(dumbPhone);
			JButton topMiddle = (JButton)topMiddleButton.get(dumbPhone);
			JButton topRight = (JButton)topRightButton.get(dumbPhone);
			JButton[] numbers =(JButton[])numberButtons.get(dumbPhone) ;
			JButton pound = (JButton)poundButton.get(dumbPhone);
			Object listener =  topMiddle.getActionListeners()[0];
			
			//Text
			ActionEvent e = new ActionEvent(topRight, 0, topRight.getText());
			actionPerformed.invoke(listener, e);
			assertEquals(ENTER_NUMBER_TEXT, display.getText(), "\"Enter a number...\" should be displayed when \"Text\" is pressed with an empty display");
			//Text
			actionPerformed.invoke(listener, e);
			assertEquals(ENTER_NUMBER_TEXT, display.getText(), "\"Enter a number...\" should be displayed when \"Text\" is pressed with \"Enter a number...\" on display");
			assertFalse(topLeft.isEnabled(), "Delete button should be disabled with \"Enter a number...\" on display");
			//3
			e = new ActionEvent(numbers[3], 0, numbers[3].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("3", display.getText(), "Display contents incorrect after pressing number button with \"Enter a number...\" on display");
			assertTrue(topLeft.isEnabled(), "Delete button should be enabled with numbers on display");			
			//3#
			e = new ActionEvent(pound, 0, pound.getText());
			actionPerformed.invoke(listener, e);
			assertEquals("3#", display.getText(), "Display contents incorrect after pressing # button with number on display");
			//3
			e = new ActionEvent(topLeft, 0, topLeft.getText());
			actionPerformed.invoke(listener, e);
			assertEquals("3", display.getText(), "Display contents incorrect after pressing delete button with multiple numbers on display");
			//
			e = new ActionEvent(topLeft, 0, topLeft.getText());
			actionPerformed.invoke(listener, e);
			assertTrue(display.getText().isEmpty(), "Display contents incorrect after pressing delete button with one number on display");
			assertFalse(topLeft.isEnabled(), "Delete button should be disabled after deleting all numbers on display");
		} catch (Exception e) {
			fail("Pre-text functionality is not working correctly");
		}
	}
	
	@Test
	public void testTextingInitial() {
		try {
			JTextArea display = (JTextArea)this.display.get(dumbPhone);
			JButton topLeft = (JButton)topLeftButton.get(dumbPhone);
			JButton topMiddle = (JButton)topMiddleButton.get(dumbPhone);
			JButton topRight = (JButton)topRightButton.get(dumbPhone);
			JButton[] numbers =(JButton[])numberButtons.get(dumbPhone) ;
			JButton star = (JButton)starButton.get(dumbPhone);
			JButton pound = (JButton)poundButton.get(dumbPhone);
			Object listener =  topMiddle.getActionListeners()[0];
			
			//4
			ActionEvent e = new ActionEvent(numbers[4], 0, numbers[4].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("4", display.getText(), "Display contents incorrect after pressing number button with empty display");
			//Text
			e = new ActionEvent(topRight, 0, topRight.getText());
			actionPerformed.invoke(listener, e);
			assertEquals(TEXT_DISPLAY_TEXT, display.getText(), "\"Enter text..\" should be displayed when \"Text\" is pressed with a phone number on display");
			assertEquals(CANCEL_BUTTON_TEXT, topMiddle.getText(), "Top middle button text should read \"Cancel\" when entering text");
			assertEquals(DELETE_BUTTON_TEXT, topLeft.getText(), "Top left button text should read \"Delete\" when entering text");
			assertFalse(topLeft.isEnabled(), "Top left button should be disabled when entering text");
			assertEquals(SEND_BUTTON_TEXT, topRight.getText(), "Top right button text should read \"Send\" when entering text");
			assertFalse(topRight.isEnabled(), "Top right button should be disabled when entering text");
			assertFalse(numbers[1].isEnabled(), "1 button should be disabled when entering text");
			assertFalse(star.isEnabled(), "* button should be disabled when entering text");
			assertFalse(pound.isEnabled(), "# button should be disabled when entering text");
			
			//P
			e = new ActionEvent(numbers[7], 0, numbers[7].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("P", display.getText(), "Display contents incorrect when attempting to text \"P\"");
			
			//Cancel
			e = new ActionEvent(topMiddle, 0, topMiddle.getText());
			actionPerformed.invoke(listener, e);
			assertTrue(display.getText().isEmpty(), "Display contents incorrect after pressing \"Cancel\" with text on display");
			assertEquals(DELETE_BUTTON_TEXT, topLeft.getText(), "Top left button text should read \"Delete\" after pressing \"Cancel\" with text on display");
			assertFalse(topLeft.isEnabled(), "Delete button should not be enabled after pressing \"Cancel\" with text on display");
			assertEquals(TEXT_BUTTON_TEXT, topRight.getText(), "Top right button text should read \"Text\" after pressing \"Cancel\" with text on display");
			assertTrue(topRight.isEnabled(), "Text button should be enabled after pressing \"Cancel\" with text on display");
			//5
			e = new ActionEvent(numbers[5], 0, numbers[5].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("5", display.getText(), "Display contents incorrect after pressing number button with empty display after cancelling text");
		} catch (Exception e) {
			fail("Intial texting functionality is not working correctly");
		}
	}
	
	@Test
	public void testTextingAdvanced() {
		try {
			JTextArea display = (JTextArea)this.display.get(dumbPhone);
			JButton topLeft = (JButton)topLeftButton.get(dumbPhone);
			JButton topMiddle = (JButton)topMiddleButton.get(dumbPhone);
			JButton topRight = (JButton)topRightButton.get(dumbPhone);
			JButton[] numbers =(JButton[])numberButtons.get(dumbPhone) ;
			JButton star = (JButton)starButton.get(dumbPhone);
			JButton pound = (JButton)poundButton.get(dumbPhone);
			Object listener =  topMiddle.getActionListeners()[0];
			
			//9
			ActionEvent e = new ActionEvent(numbers[9], 0, numbers[9].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("9", display.getText(), "Display contents incorrect after pressing number button with empty display");
			//Text
			e = new ActionEvent(topRight, 0, topRight.getText());
			actionPerformed.invoke(listener, e);
			assertEquals(TEXT_DISPLAY_TEXT, display.getText(), "\"Enter text..\" should be displayed when \"Text\" is pressed with a phone number on display");
			assertEquals(CANCEL_BUTTON_TEXT, topMiddle.getText(), "Top middle button text should read \"Cancel\" when entering text");
			assertEquals(DELETE_BUTTON_TEXT, topLeft.getText(), "Top left button text should read \"Delete\" when entering text");
			assertFalse(topLeft.isEnabled(), "Top left button should be disabled when entering text");
			assertEquals(SEND_BUTTON_TEXT, topRight.getText(), "Top right button text should read \"Send\" when entering text");
			assertFalse(topRight.isEnabled(), "Top right button should be disabled when entering text");
			assertFalse(numbers[1].isEnabled(), "1 button should be disabled when entering text");
			assertFalse(star.isEnabled(), "* button should be disabled when entering text");
			assertFalse(pound.isEnabled(), "# button should be disabled when entering text");
			//J
			e = new ActionEvent(numbers[5], 0, numbers[5].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("J", display.getText(), "Display contents incorrect when attempting to text \"J\"");
			//JA
			e = new ActionEvent(numbers[2], 0, numbers[2].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("JA", display.getText(), "Display contents incorrect when attempting to text \"JA\"");
			//JAX
			e = new ActionEvent(numbers[9], 0, numbers[9].getText());
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			assertEquals("JAX", display.getText(), "Display contents incorrect when attempting to text \"JAX\"");
			//JAXY
			Thread.sleep(1100);
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			assertEquals("JAXY", display.getText(), "Display contents incorrect when attempting to text \"JAXY\"");
			//JAXY 
			e = new ActionEvent(numbers[0], 0, numbers[0].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("JAXY ", display.getText(), "Display contents incorrect when attempting to text \"JAXY \"");
			//JAXY U
			e = new ActionEvent(numbers[8], 0, numbers[8].getText());
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			actionPerformed.invoke(listener, e);
			assertEquals("JAXY U", display.getText(), "Display contents incorrect when attempting to text \"JAXY U\"");
			//Send
			e = new ActionEvent(topRight, 0, topRight.getText());
			actionPerformed.invoke(listener, e);
			assertTrue(display.getText().isEmpty(), "Display contents incorrect after pressing \"Send\" with text on display");
			assertEquals(DELETE_BUTTON_TEXT, topLeft.getText(), "Top left button text should read \"Send\" after pressing \"Send\" with text on display");
			assertFalse(topLeft.isEnabled(), "Delete button should not be enabled after pressing \"Send\" with text on display");
			assertEquals(TEXT_BUTTON_TEXT, topRight.getText(), "Top right button text should read \"Text\" after pressing \"Send\" with text on display");
			assertTrue(topRight.isEnabled(), "Text button should be enabled after pressing \"Send\" with text on display");
			//8
			e = new ActionEvent(numbers[8], 0, numbers[8].getText());
			actionPerformed.invoke(listener, e);
			assertEquals("8", display.getText(), "Display contents incorrect after pressing number button with empty display after sending text");
		} catch (Exception e) {
			fail("Advanced texting functionality is not working correctly");
		}
	}
}
