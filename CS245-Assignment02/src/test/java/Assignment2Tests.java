import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Assignment2Tests {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() throws Exception {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECPerson() {
		try {
			Class uwecPersonClass = Class.forName("UWECPerson");
			assertEquals(1025, uwecPersonClass.getModifiers(), "UWECPerson class does not have the correct modifiers");
			assertEquals(2, uwecPersonClass.getDeclaredField("uwecId").getModifiers(), "UWECPerson uwecId does not have the correct modifiers");
			assertEquals(2, uwecPersonClass.getDeclaredField("firstName").getModifiers(), "UWECPerson firstName does not have the correct modifiers");
			assertEquals(2, uwecPersonClass.getDeclaredField("lastName").getModifiers(), "UWECPerson lastName does not have the correct modifiers");
			assertEquals(2, uwecPersonClass.getDeclaredField("title").getModifiers(), "UWECPerson title does not have the correct modifiers");
			assertEquals(1, uwecPersonClass.getConstructor(int.class, String.class, String.class).getModifiers(), "UWECPerson constructor does not have the correct modifiers and/or signature");
			assertEquals(1, uwecPersonClass.getDeclaredMethod("setTitle", String.class).getModifiers(), "UWECPerson setTitle method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecPersonClass.getDeclaredMethod("getUwecId").getModifiers(), "UWECPerson getUwecId method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecPersonClass.getDeclaredMethod("getFirstName").getModifiers(), "UWECPerson getFirstName method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecPersonClass.getDeclaredMethod("getLastName").getModifiers(), "UWECPerson getLastName method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecPersonClass.getDeclaredMethod("getTitle").getModifiers(), "UWECPerson getTitle method does not have the correct modifiers and/or signature");
			assertEquals(1025, uwecPersonClass.getDeclaredMethod("toString").getModifiers(), "UWECPerson toString method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecPersonClass.getDeclaredMethod("equals", Object.class).getModifiers(), "UWECPerson equals method does not have the correct modifiers and/or signature");
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECPerson class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECPerson field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECPerson method name not found.");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECAcademic() {
		try {
			Class uwecAcademicClass = Class.forName("UWECAcademic");
			assertEquals(1025, uwecAcademicClass.getModifiers(), "UWECAcademic class does not have the correct modifiers");
			assertEquals(2, uwecAcademicClass.getDeclaredField("numTotalCredits").getModifiers(), "UWECAcademic numTotalCredits does not have the correct modifiers");
			assertEquals(1, uwecAcademicClass.getConstructor(int.class, String.class, String.class).getModifiers(), "UWECAcademic constructor does not have the correct modifiers and/or signature");
			assertEquals(17, uwecAcademicClass.getDeclaredMethod("getNumTotalCredits").getModifiers(), "UWECAcademic getNumTotalCredits method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecAcademicClass.getDeclaredMethod("setNumTotalCredits", int.class).getModifiers(), "UWECAcademic setNumTotalCredits method does not have the correct modifiers and/or signature");
			assertEquals(1025, uwecAcademicClass.getDeclaredMethod("toString").getModifiers(), "UWECAcademic toString method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecAcademicClass.getDeclaredMethod("equals", Object.class).getModifiers(), "UWECAcademic equals method does not have the correct modifiers and/or signature");
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECAcademic class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECAcademic field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECAcademic method name not found.");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECEmployee() {
		try {
			Class uwecEmployeeInterface = Class.forName("UWECEmployee");
			assertEquals(1025, uwecEmployeeInterface.getDeclaredMethod("computePaycheck").getModifiers(), "UWECEmployee computePaycheck method does not have the correct modifiers and/or signature");
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECEmployee class");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECEmployee method name not found.");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECStudent() {
		UWECStudent s, ss, sss, ssss, sssss, ssssss;
		s = new UWECStudent(2, "first2", "last2", 3.9);
		ss = new UWECStudent(22, "first2", "last2", 3.9);
		sss = new UWECStudent(2, "first22", "last2", 3.9);
		ssss = new UWECStudent(2, "first2", "last22", 3.9);
		sssss = new UWECStudent(2, "first2", "last2", 3.99);
		ssssss = new UWECStudent(2, "first2", "last2", 3.9);

		assertTrue(s instanceof UWECAcademic, "UWECStudent should inherit from UWECAcademic");
		assertTrue(s instanceof UWECPerson, "UWECStudent should be a descendant of UWECPerson");

		try {
			Class uwecStudentClass = Class.forName("UWECStudent");
			assertEquals(1, uwecStudentClass.getModifiers(), "UWECStudent class does not have the correct modifiers");
			assertEquals(2, uwecStudentClass.getDeclaredField("gpa").getModifiers(), "UWECStudent gpa does not have the correct modifiers");
			assertEquals(1, uwecStudentClass.getConstructor(int.class, String.class, String.class, double.class).getModifiers(), "UWECStudent constructor does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStudentClass.getDeclaredMethod("setNumTotalCredits", int.class).getModifiers(), "UWECStudent setNumTotalCredits method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecStudentClass.getDeclaredMethod("getGpa").getModifiers(), "UWECStudent getGpa method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStudentClass.getDeclaredMethod("toString").getModifiers(), "UWECStudent toString method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStudentClass.getDeclaredMethod("equals", Object.class).getModifiers(), "UWECStudent equals method does not have the correct modifiers and/or signature");
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECStudent class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECStudent field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECStudent method name not found.");
		}

		assertEquals(2, s.getUwecId(), "UWECStudent not assigning or getting id appropriately");
		assertEquals("first2", s.getFirstName(), "UWECStudent not assigning or getting first name appropriately");
		assertEquals("last2", s.getLastName(), "UWECStudent not assigning or getting last name appropriately");
		assertEquals(new Double(3.9), new Double(s.getGpa()), "UWECStudent not assigning or getting gpa appropriately");
		assertEquals(0, s.getNumTotalCredits(), "UWECStudent not assigning or getting number of credits appropriately");
		assertNotEquals(ss, s, "UWECStudent not testing for equality appropriately (check uwecId usage)");
		assertNotEquals(sss, s, "UWECStudent not testing for equality appropriately (check firstName usage)");
		assertNotEquals(ssss, s, "UWECStudent not testing for equality appropriately (check lastName usage)");
		assertNotEquals(sssss, s, "UWECStudent not testing for equality appropriately (check gpa usage)");
		assertEquals(ssssss, s, "UWECStudent not testing for equality appropriately");
		ssssss.setNumTotalCredits(50);
		assertNotEquals(ssssss, s, "UWECStudent not testing for equality appropriately (check numTotalCredits usage)");
		assertEquals("Freshman", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Freshman)");
		s.setNumTotalCredits(23);
		assertEquals(23, s.getNumTotalCredits(), "UWECStudent not assigning or getting number of credits appropriately");
		assertEquals("Freshman", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Freshman)");
		s.setNumTotalCredits(24);
		assertEquals("Sophomore", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Sophomore)");
		s.setNumTotalCredits(57);
		assertEquals("Sophomore", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Sophomore)");
		s.setNumTotalCredits(58);
		assertEquals("Junior", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Junior)");
		s.setNumTotalCredits(85);
		assertEquals("Junior", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Junior)");
		s.setNumTotalCredits(86);
		assertEquals("Senior", s.getTitle(), "UWECStudent not assigning or getting title appropriately (Senior)");
		assertEquals(0, ss.getNumTotalCredits(), "UWECStudent not assigning or getting number of credits appropriately");
		assertEquals("Freshman", ss.getTitle(), "UWECStudent not assigning or getting title appropriately (Freshman)");
		assertEquals("UWECStudent = uwecId: 2, name: first2 last2, title: Senior, credits: 86, gpa: 3.9", s.toString(), "UWECStudent's toString not implemented appropriately");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECStaff() {
		UWECStaff f, ff, fff, ffff, fffff, ffffff, fffffff;
		f = new UWECStaff(3, "first3", "last3", "title1");
		ff = new UWECStaff(33, "first3", "last3", "title1");
		fff = new UWECStaff(3, "first33", "last3", "title1");
		ffff = new UWECStaff(3, "first3", "last33", "title1");
		fffff = new UWECStaff(3, "first3", "last3", "title11");
		ffffff = new UWECStaff(3, "first3", "last3", "staff1");
		fffffff = new UWECStaff(3, "first3", "last3", "staff1");

		assertTrue(f instanceof UWECPerson, "UWECStaff should inherit from UWECPerson");
		assertTrue(f instanceof UWECEmployee, "UWECStaff should implement UWECEmployee");

		try {
			Class uwecStaffClass = Class.forName("UWECStaff");
			assertEquals(1, uwecStaffClass.getModifiers(), "UWECStaff class does not have the correct modifiers");
			assertEquals(2, uwecStaffClass.getDeclaredField("hourlyPay").getModifiers(), "UWECStaff hourlyPay does not have the correct modifiers");
			assertEquals(2, uwecStaffClass.getDeclaredField("hoursPerWeek").getModifiers(), "UWECStaff hoursPerWeek does not have the correct modifiers");
			assertEquals(1, uwecStaffClass.getConstructor(int.class, String.class, String.class, String.class).getModifiers(), "UWECStaff constructor does not have the correct modifiers and/or signature");
			assertEquals(17, uwecStaffClass.getDeclaredMethod("getHourlyPay").getModifiers(), "UWECStaff getHourlyPay method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStaffClass.getDeclaredMethod("setHourlyPay", double.class).getModifiers(), "UWECStaff setHourlyPay method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecStaffClass.getDeclaredMethod("getHoursPerWeek").getModifiers(), "UWECStaff getHoursPerWeek method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStaffClass.getDeclaredMethod("setHoursPerWeek", double.class).getModifiers(), "UWECStaff setHoursPerWeek method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStaffClass.getDeclaredMethod("computePaycheck").getModifiers(), "UWECStaff computePaycheck method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStaffClass.getDeclaredMethod("toString").getModifiers(), "UWECStaff toString method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecStaffClass.getDeclaredMethod("equals", Object.class).getModifiers(), "UWECStaff equals method does not have the correct modifiers and/or signature");
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECStaff class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECStaff field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECStaff method name not found.");
		}

		assertEquals(3, f.getUwecId(), "UWECStaff not assigning or getting id appropriately");
		assertEquals("first3", f.getFirstName(), "UWECStaff not assigning or getting first name appropriately");
		assertEquals("last3", f.getLastName(), "UWECStaff not assigning or getting last name appropriately");
		assertEquals("title1", f.getTitle(), "UWECStaff not assigning or getting title appropriately");

		assertNotEquals(ff, f, "UWECStaff not testing for equality appropriately (check uwecId usage)");
		assertNotEquals(fff, f, "UWECStaff not testing for equality appropriately (check firstName usage)");
		assertNotEquals(ffff, f, "UWECStaff not testing for equality appropriately (check lastName usage)");
		assertNotEquals(fffff, f, "UWECStaff not testing for equality appropriately (check title usage");

		f.setTitle("staff1");
		assertEquals("staff1", f.getTitle(), "UWECStaff not assigning or getting title appropriately");
		f.setHourlyPay(10.5);
		assertEquals(new Double(10.5), new Double(f.getHourlyPay()), "UWECStaff not assigning or getting hourly pay appropriately");
		f.setHoursPerWeek(35);
		assertEquals(new Double(35), new Double(f.getHoursPerWeek()), "UWECStaff not assigning or getting last name appropriately");

		ffffff.setHourlyPay(10);
		assertNotEquals(ffffff, f, "UWECStaff not testing for equality appropriately (check hourlyPay usage)");
		fffffff.setHoursPerWeek(40);
		assertNotEquals(fffffff, f, "UWECStaff not testing for equality appropriately (check hoursPerWeek usage)");
		fffffff.setHoursPerWeek(35);
		fffffff.setHourlyPay(10.5);
		assertEquals(fffffff, f, "UWECStaff not testing for equality appropriately");
		assertEquals(new Double(735.0), new Double(f.computePaycheck()), "UWECStaff not computing paycheck correctly");
		ffffff.setHoursPerWeek(25);
		assertEquals(new Double(500.0), new Double(ffffff.computePaycheck()), "UWECStaff not computing paycheck correctly");
		assertEquals("UWECStaff = uwecId: 3, name: first3 last3, title: staff1, hourly pay: 10.5, hours/week: 35.0", f.toString(), "UWECStaff's toString not implemented appropriately");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUWECFaculty() {
		UWECFaculty t, tt, ttt, tttt, ttttt, tttttt;
		t = new UWECFaculty(5, "first5", "last5", 47);
		tt = new UWECFaculty(55, "first5", "last5", 47);
		ttt = new UWECFaculty(5, "first55", "last5", 47);
		tttt = new UWECFaculty(5, "first5", "last55", 47);
		ttttt = new UWECFaculty(5, "first5", "last5", 48);
		tttttt = new UWECFaculty(5, "first5", "last5", 47);

		assertTrue(t instanceof UWECAcademic, "UWECFaculty should inherit from UWECAcademic");
		assertTrue(t instanceof UWECPerson, "UWECFaculty should be a descendant of UWECPerson");
		assertTrue(t instanceof UWECEmployee, "UWECFaculty should implement UWECEmployee");

		try {
			Class uwecFacultyClass = Class.forName("UWECFaculty");
			assertEquals(1, uwecFacultyClass.getModifiers(), "UWECFaculty class does not have the correct modifiers");
			assertEquals(2, uwecFacultyClass.getDeclaredField("yearlySalary").getModifiers(), "UWECFaculty yearlySalary does not have the correct modifiers");
			assertEquals(1, uwecFacultyClass.getConstructor(int.class, String.class, String.class, int.class).getModifiers(), "UWECFaculty constructor does not have the correct modifiers and/or signature");
			assertEquals(1, uwecFacultyClass.getDeclaredMethod("setNumTotalCredits", int.class).getModifiers(), "UWECFaculty setNumTotalCredits method does not have the correct modifiers and/or signature");
			assertEquals(17, uwecFacultyClass.getDeclaredMethod("getYearlySalary").getModifiers(), "UWECFaculty getYearlySalary method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecFacultyClass.getDeclaredMethod("setYearlySalary", double.class).getModifiers(), "UWECFaculty setYearlySalary method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecFacultyClass.getDeclaredMethod("computePaycheck").getModifiers(), "UWECFaculty computePaycheck method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecFacultyClass.getDeclaredMethod("toString").getModifiers(), "UWECFaculty toString method does not have the correct modifiers and/or signature");
			assertEquals(1, uwecFacultyClass.getDeclaredMethod("equals", Object.class).getModifiers(), "UWECFaculty equals method does not have the correct modifiers and/or signature");
		} catch (ClassNotFoundException e) {
			fail("Cannot find UWECFaculty class");
		} catch (NoSuchFieldException e) {
			fail("At least one UWECFaculty field name not found.");
		} catch (NoSuchMethodException e) {
			fail("At least one UWECFaculty method name not found.");
		}

		assertEquals(5, t.getUwecId(), "UWECFaculty not assigning or getting id appropriately");
		assertEquals("first5",  t.getFirstName(), "UWECFaculty not assigning or getting first name appropriately");
		assertEquals("last5", t.getLastName(), "UWECFaculty not assigning or getting last name appropriately");
		t.setYearlySalary(70000);
		assertEquals(new Double(70000), new Double(t.getYearlySalary()), "UWECFaculty not assigning or getting yearly salary appropriately");
		t.setYearlySalary(0);
		assertEquals(47, t.getNumTotalCredits(), "UWECFaculty not keeping track of or reporting number of credits appropriately");
		assertNotEquals(tt, t, "UWECFaculty not testing for equality appropriately");
		assertNotEquals(ttt, t, "UWECFaculty not testing for equality appropriately");
		assertNotEquals(tttt, t, "UWECFaculty not testing for equality appropriately");
		assertNotEquals(ttttt, t, "UWECFaculty not testing for equality appropriately");
		assertEquals(tttttt, t, "UWECFaculty not testing for equality appropriately");
		t.setYearlySalary(70000);
		assertNotEquals(tttttt, t, "UWECFaculty not testing for equality appropriately");
		assertEquals("Adjunct Professor", t.getTitle(), "UWECFaculty not assigning or getting title appropriately");
		t.setNumTotalCredits(48);
		assertEquals("Assistant Professor", t.getTitle(), "UWECFaculty not assigning or getting title appropriately");
		t.setNumTotalCredits(119);
		assertEquals("Assistant Professor", t.getTitle(), "UWECFaculty not assigning or getting title appropriately");
		t.setNumTotalCredits(120);
		assertEquals("Associate Professor", t.getTitle(), "UWECFaculty not assigning or getting title appropriately");
		t.setNumTotalCredits(239);
		assertEquals("Associate Professor", t.getTitle(), "UWECFaculty not assigning or getting title appropriately");
		t.setNumTotalCredits(240);
		assertEquals("Professor", t.getTitle(), "UWECFaculty not assigning or getting title appropriately");
		assertEquals(new Double(70000.0/26), new Double(t.computePaycheck()), "UWECFaculty not computing paycheck correctly");
		assertEquals(new Double(0), new Double(tttttt.computePaycheck()), "UWECFaculty not computing paycheck correctly");
		assertEquals("UWECFaculty = uwecId: 5, name: first5 last5, title: Professor, credits: 240, yearlySalary: 70000.0", t.toString(), "UWECFaculty not assigning or getting title appropriately");
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
		if(menu != null && !menu.isEmpty()) {
			fail("UWECPeopleDriver getMenuChoice method doesn't display menu correctly");
		}
	}

	@Test
	public void testAddStudent() {
		ArrayList<UWECPerson> students = new ArrayList<UWECPerson>();

		setInput("3\na\nf\n60\n3.9\n");
		UWECPeopleDriver.addStudent(new Scanner(System.in), students);
		assertEquals(students.size(), 1, "UWECPeopleDriver addStudent doesn't add student to list appropriately");
		UWECStudent student = (UWECStudent) students.get(0);
		assertEquals(student.getUwecId(), 3, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check uwecId usage)");
		assertEquals(student.getFirstName(), "a", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check firstName usage)");
		assertEquals(student.getLastName(), "f", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check lastName usage)");
		assertEquals(student.getNumTotalCredits(), 60, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check numTotalCredits usage)");
		assertEquals(student.getGpa(), 3.9, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check gpa usage)");

		setInput("4\no\nk\n50\n3.0\n");
		UWECPeopleDriver.addStudent(new Scanner(System.in), students);
		assertEquals(students.size(), 2, "UWECPeopleDriver addStudent doesn't add student to list appropriately");
		student = (UWECStudent) students.get(1);
		assertEquals(student.getUwecId(), 4, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check uwecId usage)");
		assertEquals(student.getFirstName(), "o", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check firstName usage)");
		assertEquals(student.getLastName(), "k", "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check lastName usage)");
		assertEquals(student.getNumTotalCredits(), 50, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check numTotalCredits usage)");
		assertEquals(student.getGpa(), 3.0, "UWECPeopleDriver addStudent doesn't use provided student data appropriately (check gpa usage)");
	}

	@Test
	public void testAddStaff() {
		ArrayList<UWECPerson> staff = new ArrayList<UWECPerson>();

		setInput("3\na\nf\nt\n20.5\n40\n");
		UWECPeopleDriver.addStaff(new Scanner(System.in), staff);
		assertEquals(staff.size(), 1, "UWECPeopleDriver addStaff doesn't add staff to list appropriately");
		UWECStaff aStaff = (UWECStaff) staff.get(0);
		assertEquals(aStaff.getUwecId(), 3, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check uwecId usage)");
		assertEquals(aStaff.getFirstName(), "a", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check firstName usage)");
		assertEquals(aStaff.getLastName(), "f", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check lastName usage)");
		assertEquals(aStaff.getTitle(), "t", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check title usage)");
		assertEquals(aStaff.getHourlyPay(), 20.5, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hourlyPay usage)");
		assertEquals(aStaff.getHoursPerWeek(), 40, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hoursPerWeek usage)");

		setInput("4\no\nk\nti\n30\n30.5\n");
		UWECPeopleDriver.addStaff(new Scanner(System.in), staff);
		assertEquals(staff.size(), 2, "UWECPeopleDriver addStaff doesn't add student to list appropriately");
		aStaff = (UWECStaff) staff.get(1);
		assertEquals(aStaff.getUwecId(), 4, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check uwecId usage)");
		assertEquals(aStaff.getFirstName(), "o", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check firstName usage)");
		assertEquals(aStaff.getLastName(), "k", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check lastName usage)");
		assertEquals(aStaff.getTitle(), "ti", "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check title usage)");
		assertEquals(aStaff.getHourlyPay(), 30, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hourlyPay usage)");
		assertEquals(aStaff.getHoursPerWeek(), 30.5, "UWECPeopleDriver addStaff doesn't use provided staff data appropriately (check hoursPerWeek usage)");
	}

	@Test
	public void testAddFaculty() {
		ArrayList<UWECPerson> faculty = new ArrayList<UWECPerson>();

		setInput("5\nJoe\nFaculty\n239\n70000\n");
		UWECPeopleDriver.addFaculty(new Scanner(System.in), faculty);
		assertEquals(faculty.size(), 1, "UWECPeopleDriver addFaculty doesn't add faculty to list appropriately");
		UWECFaculty aFaculty = (UWECFaculty) faculty.get(0);
		assertEquals(aFaculty.getUwecId(), 5, "UWECPeopleDriver addFaculty doesn't use provided staff data appropriately (check uwecId usage)");
		assertEquals(aFaculty.getFirstName(), "Joe", "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check firstName usage)");
		assertEquals(aFaculty.getLastName(), "Faculty", "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check lastName usage)");
		assertEquals(aFaculty.getTitle(), "Associate Professor", "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check title usage)");
		assertEquals(aFaculty.getYearlySalary(), 70000, "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check yearlySalary usage)");

		setInput("6\nJane\nFaculty\n240\n80000\n");
		UWECPeopleDriver.addFaculty(new Scanner(System.in), faculty);
		assertEquals(faculty.size(), 2, "UWECPeopleDriver addFaculty doesn't add faculty to list appropriately");
		aFaculty = (UWECFaculty) faculty.get(1);
		assertEquals(aFaculty.getUwecId(), 6, "UWECPeopleDriver addFaculty doesn't use provided staff data appropriately (check uwecId usage)");
		assertEquals(aFaculty.getFirstName(), "Jane", "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check firstName usage)");
		assertEquals(aFaculty.getLastName(), "Faculty", "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check lastName usage)");
		assertEquals(aFaculty.getTitle(), "Professor", "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check title usage)");
		assertEquals(aFaculty.getYearlySalary(), 80000, "UWECPeopleDriver addFaculty doesn't use provided faculty data appropriately (check yearlySalary usage)");
	}

	@Test
	public void testPrintDirectory() {
		ArrayList<UWECPerson> people = new ArrayList<UWECPerson>();
		PrintWriter fileOut;
		Scanner fileIn;
		try {
			fileOut = new PrintWriter("testOut.txt");
			UWECPeopleDriver.printDirectory(fileOut, people);

			UWECStudent student = new UWECStudent(5,"first","last",2.75);
			people.add(student);
			UWECStaff aStaff = new UWECStaff(5,"first","last","employee");
			aStaff.setHourlyPay(15.5);
			aStaff.setHoursPerWeek(20);
			people.add(aStaff);
			UWECPeopleDriver.printDirectory(fileOut, people);
			fileOut.close();
			fileIn = new Scanner(new File("testOut.txt"));
			assertEquals(student.toString(), fileIn.nextLine(), "UWECPeopleDriver printStudents doesn't print correctly (check with 1 student)");
			assertEquals(aStaff.toString(), fileIn.nextLine(), "UWECPeopleDriver printStudents doesn't print correctly (check with 1 student)");
			fileIn.close();
		} catch (FileNotFoundException e) {
			fail("UWECPeopleDriver printDirectory not working correctly");
			e.printStackTrace();
		}
	}

	@Test
	public void testComputeTotalPayroll() {
		ArrayList<UWECPerson> people = new ArrayList<UWECPerson>();
		PrintWriter fileOut;
		Scanner fileIn;
		try {
			fileOut = new PrintWriter("testOut.txt");
			UWECPeopleDriver.computeTotalPayroll(fileOut, people);
			fileOut.close();
			fileIn = new Scanner(new File("testOut.txt"));
			assertTrue(fileIn.nextLine().endsWith("$0.00"), "UWECPeopleDriver computeTotalPayroll doesn't print correctly (check with 0 people)");
			fileIn.close();

			UWECStudent student = new UWECStudent(5,"first","last",2.75);
			people.add(student);
			UWECStaff aStaff = new UWECStaff(5,"first","last","employee");
			aStaff.setHourlyPay(15.5);
			aStaff.setHoursPerWeek(20);
			people.add(aStaff);
			fileOut = new PrintWriter("testOut.txt");
			UWECPeopleDriver.computeTotalPayroll(fileOut, people);
			fileOut.close();
			fileIn = new Scanner(new File("testOut.txt"));
			assertTrue(fileIn.nextLine().endsWith("$620.00"), "UWECPeopleDriver computeTotalPayroll doesn't print correctly (check with multiple people)");
			fileIn.close();
		} catch (FileNotFoundException e) {
			fail("UWECPeopleDriver computeTotalPayroll not working correctly");
			e.printStackTrace();
		}
	}

	@Test
	public void testMain() {
		Scanner fileIn;
		setInput("assignment2Input1.txt\nassignment2Output1.txt\n");
		UWECPeopleDriver.main(null);

		//test fileOut with expected input
		try {
			fileIn = new Scanner(new File("assignment2Output1.txt"));
			assertTrue(fileIn.nextLine().endsWith("$7,419.23"), "UWECPeopleDriver main doesn't work correctly (1st line of assignment2Output1.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECStudent = uwecId: 1, name: Joe Student, title: Sophomore, credits: 24, gpa: 3.9"), "UWECPeopleDriver main doesn't work correctly (2nd line of assignment2Output1.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECStudent = uwecId: 2, name: Jane Student, title: Freshman, credits: 23, gpa: 4.0"), "UWECPeopleDriver main doesn't work correctly (3rd line of assignment2Output1.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECStaff = uwecId: 3, name: Joe Staff, title: Custodian, hourly pay: 13.5, hours/week: 30.0"), "UWECPeopleDriver main doesn't work correctly (4th line of assignment2Output1.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECStaff = uwecId: 4, name: Jane Staff, title: Secretary, hourly pay: 12.0, hours/week: 35.0"), "UWECPeopleDriver main doesn't work correctly (5th line of assignment2Output1.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECFaculty = uwecId: 5, name: Joe Faculty, title: Associate Professor, credits: 239, yearlySalary: 70000.0"), "UWECPeopleDriver main doesn't work correctly (6th line of assignment2Output1.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECFaculty = uwecId: 6, name: Jane Faculty, title: Professor, credits: 240, yearlySalary: 80000.0"), "UWECPeopleDriver main doesn't work correctly (7th line of assignment2Output1.txt doesn't match)");
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outContent.reset();
		setInput("assignment2Input2.txt\nassignment2Output2.txt\n");
		UWECPeopleDriver.main(null);

		//test stdOut with unexpected input
		Scanner stdOut = new Scanner(outContent.toString());
		assertTrue(stdOut.nextLine().endsWith("Unexpected input received in addStudent."), "UWECPeopleDriver main doesn't work correctly (1st non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addStudent."), "UWECPeopleDriver main doesn't work correctly (2nd non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addStaff."), "UWECPeopleDriver main doesn't work correctly (3rd non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addStaff."), "UWECPeopleDriver main doesn't work correctly (4th non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addStaff."), "UWECPeopleDriver main doesn't work correctly (5th non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addFaculty."), "UWECPeopleDriver main doesn't work correctly (6th non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addFaculty."), "UWECPeopleDriver main doesn't work correctly (7th non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Unexpected input received in addFaculty."), "UWECPeopleDriver main doesn't work correctly (8th non-prompt line of console for assignment2Input2.txt doesn't match)");
		assertTrue(stdOut.nextLine().equals("Non-integer entered in getMenuChoice."), "UWECPeopleDriver main doesn't work correctly (9th non-prompt line of console for assignment2Input2.txt doesn't match)");
		stdOut.close();
		//test fileOut with unexpected input
		try {
			fileIn = new Scanner(new File("assignment2Output2.txt"));
			assertTrue(fileIn.nextLine().endsWith("$3,502.31"), "UWECPeopleDriver main doesn't work correctly (1st line of assignment2Output2.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECStudent = uwecId: 1, name: Joe Student, title: Sophomore, credits: 24, gpa: 3.9"), "UWECPeopleDriver main doesn't work correctly (2nd line of assignment2Output2.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECStaff = uwecId: 3, name: Joe Staff, title: Custodian, hourly pay: 13.5, hours/week: 30.0"), "UWECPeopleDriver main doesn't work correctly (3rd line of assignment2Output2.txt doesn't match)");
			assertTrue(fileIn.nextLine().equals("UWECFaculty = uwecId: 5, name: Joe Faculty, title: Associate Professor, credits: 239, yearlySalary: 70000.0"), "UWECPeopleDriver main doesn't work correctly (4th line of assignment2Output2.txt doesn't match)");
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setInput(String input) {
		ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
		System.setIn(inContent);
	}
}
