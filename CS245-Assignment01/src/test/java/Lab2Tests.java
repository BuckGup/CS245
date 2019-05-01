import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Lab2Tests {
	
	private UWECPerson p;
	private int r;
	private int r2;

	@BeforeEach
	public void setUp() throws Exception {
		r = (int)(Math.random() * 100);
		r2 = (int)(Math.random() * 10);
		p = new UWECPerson(r, ""+(r+r2), ""+(r*r2));
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

}
