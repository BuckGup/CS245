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

public class Lab8Tests {
	
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
}
