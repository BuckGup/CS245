
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Assignment1Tests {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private UWECPerson p;
	private UWECStudent s;
	private UWECStaff f;
	private int r;
	private int r2;
	private double gpa;

	@BeforeEach
	public void setUpStreams() throws Exception {
		System.setOut(new PrintStream(outContent));
		r = (int)(Math.random() * 100);
		r2 = (int)(Math.random() * 10);
		gpa = Math.random() * 4;
		p = new UWECPerson(r, ""+(r+r2), ""+(r*r2));
		s = new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+1), gpa);
		f = new UWECStaff(r+2, ""+(r+r2+2), ""+(r*r2+2));
		f.setTitle(""+r*r);
		f.setHourlyPay(gpa*3);
		f.setHoursPerWeek(gpa*10);
	}
	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	}

	@Test
	public void testUWECPerson() {
		assertEquals(r, p.getUwecId(), "UWECPerson not assigning or getting id appropriately");
		assertEquals(""+(r+r2), p.getFirstName(), "UWECPerson not assigning or getting first name appropriately");
		assertEquals(""+(r*r2), p.getLastName(), "UWECPerson not assigning or getting last name appropriately");
		assertEquals("UWECPerson = uwecId: " + r + ", name: " + (r+r2) + " " + (r*r2), p.toString(), "UWECPerson toString method not working correctly");
		assertTrue(p.equals(new UWECPerson(r, ""+(r+r2), ""+(r*r2))), "UWECPerson equals method not working correctly");
		assertFalse(p.equals(new UWECPerson(r+1, ""+(r+r2), ""+(r*r2))), "UWECPerson equals method not working correctly (check uwecId usage)");
		assertFalse(p.equals(new UWECPerson(r, ""+(r+r2)+1, ""+(r*r2))), "UWECPerson equals method not working correctly (check firstName usage)");
		assertFalse(p.equals(new UWECPerson(r, ""+(r+r2), ""+(r*r2)+1)), "UWECPerson equals method not working correctly (check lastName usage)");
	}

	@Test
	public void testUWECStudent() {
		assertTrue(s instanceof UWECPerson, "UWECStudent should inherit from UWECPerson");
		assertEquals(r+1, s.getUwecId(), "UWECStudent not assigning or getting id appropriately");
		assertEquals(""+(r+r2+1), s.getFirstName(), "UWECStudent not assigning or getting first name appropriately");
		assertEquals(""+(r*r2+1), s.getLastName(), "UWECStudent not assigning or getting last name appropriately");
		assertEquals(new Double(gpa), new Double(s.getGpa()), "UWECStudent not assigning or getting gpa appropriately");
		assertEquals("UWECStudent = uwecId: " + (r+1) + ", name: " + (r+r2+1) + " " + (r*r2+1) + ", gpa: " + gpa, s.toString(), "UWECStudent toString method not working correctly");
		assertTrue(s.equals( new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+1), gpa)), "UWECStudent equals method not working correctly");
		assertFalse(s.equals( new UWECStudent(r+2, ""+(r+r2+1), ""+(r*r2+1), gpa)), "UWECStudent equals method not working correctly (check uwecId usage)");
		assertFalse(s.equals( new UWECStudent(r+1, ""+(r+r2+2), ""+(r*r2+1), gpa)), "UWECStudent equals method not working correctly (check firstName usage)");
		assertFalse(s.equals( new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+2), gpa)), "UWECStudent equals method not working correctly (check lastName usage)");
		assertFalse(s.equals( new UWECStudent(r+1, ""+(r+r2+1), ""+(r*r2+1), gpa+0.1)), "UWECStudent equals method not working correctly (check gpa usage)");
	}

	@Test
	public void testUWECStaff() {
		assertTrue(f instanceof UWECPerson, "UWECStaff should inherit from UWECPerson");
		assertEquals(r+2, f.getUwecId(), "UWECStaff not assigning or getting id appropriately");
		assertEquals(""+(r+r2+2), f.getFirstName(), "UWECStaff not assigning or getting first name appropriately");
		assertEquals(""+(r*r2+2), f.getLastName(), "UWECStaff not assigning or getting last name appropriately");
		assertEquals(""+(r*r), f.getTitle(), "UWECStaff not assigning or getting title appropriately");
		assertEquals(new Double(gpa*3), new Double(f.getHourlyPay()), "UWECStaff not assigning or getting hourly pay appropriately");
		assertEquals(new Double(gpa*10), new Double(f.getHoursPerWeek()), "UWECStaff not assigning or getting hours per week appropriately");
		assertEquals("UWECStaff = uwecId: " + (r+2) + ", name: " + (r+r2+2) + " " + (r*r2+2) + ", title: " + (r*r) + ", hourly pay: " + (gpa*3) + ", hours/week: " + (gpa*10), f.toString(), "UWECStaff toString method not working correctly");

		UWECStaff tempStaff = new UWECStaff(r+2, ""+(r+r2+2), ""+(r*r2+2));
		tempStaff.setTitle(""+r*r);
		tempStaff.setHourlyPay(gpa*3);
		tempStaff.setHoursPerWeek(gpa*10);
		assertTrue(f.equals(tempStaff), "UWECStaff equals method not working correctly");
		tempStaff.setUwecId(r+3);
		assertFalse(f.equals(tempStaff), "UWECStaff equals method not working correctly (check uwecId usage)");
		tempStaff.setUwecId(r+2);
		tempStaff.setFirstName(""+(r+r2+3));
		assertFalse(f.equals(tempStaff), "UWECStaff equals method not working correctly (check firstName usage)");
		tempStaff.setFirstName(""+(r+r2+2));
		tempStaff.setLastName(""+(r*r2+3));
		assertFalse(f.equals(tempStaff), "UWECStaff equals method not working correctly (check lastName usage)");
		tempStaff.setLastName(""+(r*r2+2));
		tempStaff.setTitle(""+r*r+1);
		assertFalse(f.equals(tempStaff), "UWECStaff equals method not working correctly (check title usage)");
		tempStaff.setTitle(""+r*r);
		tempStaff.setHourlyPay(gpa*3+1);
		assertFalse(f.equals(tempStaff), "UWECStaff equals method not working correctly (check hourlyPay usage)");
		tempStaff.setHourlyPay(gpa*3);
		tempStaff.setHoursPerWeek(gpa*10+1);
		assertFalse(f.equals(tempStaff), "UWECStaff equals method not working correctly (check hoursPerWeek usage)");
	}
	
	@Test
	public void testGetMenuChoice() {
		//test 1-5
		setInput("1\n");
		assertEquals(1, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('1' doesn't work)");
		setInput("2\n");
		assertEquals(2, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('2' doesn't work)");
		setInput("3\n");
		assertEquals(3, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('3' doesn't work)");
		setInput("4\n");
		assertEquals(4, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('4' doesn't work)");
		setInput("5\n");
		assertEquals(5, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('5' doesn't work)");

		//test menu
		String menu = outContent.toString();
		String prompt = "";
		if(menu == null || menu.isEmpty()) {
			fail("UWECPeopleDriver getMenuChoice method doesn't display menu correctly");
		} else {
			//test prompt
			prompt = menu.substring(menu.lastIndexOf("+")+1).trim();
			if(prompt == null || prompt.isEmpty()) {
				fail("UWECPeopleDriver getMenuChoice method doesn't display prompt correctly");
			}
		}
				
		//test 0 and 6
		setInput("0\n5\n");
		assertEquals(5, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('0' should be invalid)");
		setInput("6\n5\n");
		assertEquals(5, UWECPeopleDriver.getMenuChoice(new Scanner(System.in)), "UWECPeopleDriver getMenuChoice method doesn't work correctly ('6' should be invalid)");
		
		//test multiple prompts
		String output = outContent.toString();
		int promptInd = output.indexOf(prompt);
		if(promptInd == -1) {
			fail("UWECPeopleDriver getMenuChoice method doesn't display prompt correctly");
		} else if(output.indexOf(prompt, promptInd) == -1) {
			fail("UWECPeopleDriver getMenuChoice method doesn't repeat prompt correctly");
		}
	}
	
	@Test
	public void testAddStudent() {
		ArrayList<UWECStudent> students = new ArrayList<UWECStudent>();
		
		setInput("3\na\nf\n3.9\n");
		UWECPeopleDriver.addStudent(new Scanner(System.in), students);
		assertEquals(students.size(), 1, "UWECPeopleDriver addStudent doesn't add student to list appropriately");
		UWECStudent student = students.get(0);
		assertEquals(student.getUwecId(), 3, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check uwecId usage)");
		assertEquals(student.getFirstName(), "a", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check firstName usage)");
		assertEquals(student.getLastName(), "f", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check lastName usage)");
		assertEquals(student.getGpa(), 3.9, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check gpa usage)");
		
		setInput("4\no\nk\n3.0\n");
		UWECPeopleDriver.addStudent(new Scanner(System.in), students);
		assertEquals(students.size(), 2, "UWECPeopleDriver addStudent doesn't add student to list appropriately");
		student = students.get(1);
		assertEquals(student.getUwecId(), 4, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check uwecId usage)");
		assertEquals(student.getFirstName(), "o", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check firstName usage)");
		assertEquals(student.getLastName(), "k", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check lastName usage)");
		assertEquals(student.getGpa(), 3.0, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check gpa usage)");
	}
	
	@Test
	public void testAddStaff() {
		ArrayList<UWECStaff> staff = new ArrayList<UWECStaff>();
		
		setInput("3\na\nf\nt\n20.5\n40\n");
		UWECPeopleDriver.addStaff(new Scanner(System.in), staff);
		assertEquals(staff.size(), 1, "UWECPeopleDriver addStaff doesn't add staff to list appropriately");
		UWECStaff aStaff = staff.get(0);
		assertEquals(aStaff.getUwecId(), 3, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check uwecId usage)");
		assertEquals(aStaff.getFirstName(), "a", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check firstName usage)");
		assertEquals(aStaff.getLastName(), "f", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check lastName usage)");
		assertEquals(aStaff.getTitle(), "t", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check title usage)");
		assertEquals(aStaff.getHourlyPay(), 20.5, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hourlyPay usage)");
		assertEquals(aStaff.getHoursPerWeek(), 40, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hoursPerWeek usage)");

		setInput("4\no\nk\nti\n30\n30.5\n");
		UWECPeopleDriver.addStaff(new Scanner(System.in), staff);
		assertEquals(staff.size(), 2, "UWECPeopleDriver addStaff doesn't add student to list appropriately");
		aStaff = staff.get(1);
		assertEquals(aStaff.getUwecId(), 4, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check uwecId usage)");
		assertEquals(aStaff.getFirstName(), "o", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check firstName usage)");
		assertEquals(aStaff.getLastName(), "k", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check lastName usage)");
		assertEquals(aStaff.getTitle(), "ti", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check title usage)");
		assertEquals(aStaff.getHourlyPay(), 30, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hourlyPay usage)");
		assertEquals(aStaff.getHoursPerWeek(), 30.5, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hoursPerWeek usage)");
	}
	
	@Test
	public void testPrintStudents() {
		ArrayList<UWECStudent> students = new ArrayList<UWECStudent>();
		UWECPeopleDriver.printStudents(students);
		assertEquals("No students found.", outContent.toString().trim(), "UWECPeopleDriver printStudents doesn't print correctly (check with no students)");
		
		UWECStudent student = new UWECStudent(5,"first","last",2.75);
		students.add(student);
		outContent.reset();
		UWECPeopleDriver.printStudents(students);
		assertEquals(student.toString(), outContent.toString().trim(), "UWECPeopleDriver printStudents doesn't print correctly (check with 1 student)");
		
		student = new UWECStudent(6,"first2","last2",2.76);
		students.add(student);
		outContent.reset();
		UWECPeopleDriver.printStudents(students);
		assertEquals(students.get(0).toString()+"\n"+student.toString(), outContent.toString().trim(), "UWECPeopleDriver printStudents doesn't print correctly (check with multiple students)");
	}
	
	@Test
	public void testPrintStaff() {
		ArrayList<UWECStaff> staff = new ArrayList<UWECStaff>();
		UWECPeopleDriver.printStaff(staff);
		assertEquals("No staff found.", outContent.toString().trim(), "UWECPeopleDriver printStaff doesn't print correctly (check with no staff)");
		
		UWECStaff aStaff = new UWECStaff(5,"first","last");
		aStaff.setTitle("oh");
		aStaff.setHourlyPay(15.5);
		aStaff.setHoursPerWeek(20);
		staff.add(aStaff);
		outContent.reset();
		UWECPeopleDriver.printStaff(staff);
		assertEquals(aStaff.toString(), outContent.toString().trim(), "UWECPeopleDriver printStaff doesn't print correctly (check with 1 staff)");
		
		aStaff = new UWECStaff(6,"first2","last2");
		aStaff.setTitle("oh ok");
		aStaff.setHourlyPay(32);
		aStaff.setHoursPerWeek(40);
		staff.add(aStaff);
		outContent.reset();
		UWECPeopleDriver.printStaff(staff);
		assertEquals(staff.get(0).toString()+"\n"+aStaff.toString(), outContent.toString().trim(), "UWECPeopleDriver printStaff doesn't print correctly (check with multiple staff)");
	}
	
	@Test
	public void testMain() {
		setInput("5\n");
		UWECPeopleDriver.getMenuChoice(new Scanner(System.in));
		String menu = outContent.toString();
		
		UWECStudent student = new UWECStudent(5,"first","last",2.75);
		setInput("1\n5\nfirst\nlast\n2.75\n3\n5\n");
		UWECPeopleDriver.main(null);
		String printed = student.toString().trim();
		String trimmedOutput = outContent.toString().replace(menu, "").trim();
		assertTrue(trimmedOutput.endsWith(printed), "UWECPeopleDriver main method doesn't work with students correctly (could be caused by other failing methods)");
		
		UWECStaff staff = new UWECStaff(6,"first2","last2");
		staff.setTitle("oh ok");
		staff.setHourlyPay(32);
		staff.setHoursPerWeek(40);
		setInput("2\n6\nfirst2\nlast2\noh ok\n32\n40\n4\n5\n");
		UWECPeopleDriver.main(null);
		printed = staff.toString().trim();
		trimmedOutput = outContent.toString().replace(menu, "").trim();
		assertTrue(trimmedOutput.endsWith(printed), "UWECPeopleDriver main method doesn't work with staff correctly (could be caused by other failing methods)");
	}
	
	private void setInput(String input) {
		ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
		System.setIn(inContent);
	}
}