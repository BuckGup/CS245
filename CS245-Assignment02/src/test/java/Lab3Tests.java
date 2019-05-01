import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Lab3Tests {

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
}
