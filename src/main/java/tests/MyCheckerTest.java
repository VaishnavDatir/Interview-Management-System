package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utils.MyChecker;

public class MyCheckerTest {

	@Test
	public void demoTestMethod() {
		assertTrue(true);
	}

	@Test
	public void testIsStringValid() {
		String dummyStr1 = "Vaishnav";
		
		boolean expectedOutput = true;
		boolean gotOutput = MyChecker.isStringValid(dummyStr1);
		
		assertEquals("this should pass", expectedOutput, gotOutput);
	}

	@Test
	public void testIsEmailValid() {
		String dummyStr1 = "vaishnavd@birlasoft";
		
		boolean expectedOutput = true;
		boolean gotOutput = MyChecker.isEmailValid(dummyStr1);
		
		assertEquals(expectedOutput, gotOutput);
	}

	@Test
	public void testIsMobileNoValid() {
		String dummyStr1 = "9881339091";
		
		boolean expectedOutput = true;
		boolean gotOutput = MyChecker.isMobileNoValid(dummyStr1);
		
		assertEquals(expectedOutput, gotOutput);
	}

	@Test
	public void testIsPasswordValid1() {
		String dummyStr1 = "123asASd";
		
		boolean expectedOutput = false;
		boolean gotOutput = MyChecker.isPasswordValid(dummyStr1);
		
		assertEquals(expectedOutput, gotOutput);
	}

	@Test
	public void testIsPasswordValid2() {
		String dummyStr1 = "Pass@123";
		
		boolean expectedOutput = true;
		boolean gotOutput = MyChecker.isPasswordValid(dummyStr1);
		
		assertEquals(expectedOutput, gotOutput);
	}

	@Test
	public void testIsValidAge() {
		String dummyStr1 = "2016-05-07";
		
		boolean expectedOutput = false;
		boolean gotOutput = MyChecker.isPasswordValid(dummyStr1);
		
		assertEquals(expectedOutput, gotOutput);
	}

	@Test
	public void testIsActive() {
		String dummyStr1 = "2022-07-26";
		
		boolean expectedOutput = true;
		boolean gotOutput = MyChecker.isPasswordValid(dummyStr1);
		
		assertEquals(expectedOutput, gotOutput);
	}

}
