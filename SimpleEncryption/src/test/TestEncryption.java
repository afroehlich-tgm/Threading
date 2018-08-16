package test;

import encryption.SimpleEncryption;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestEncryption {

	@Test
	void testEncryption1() {
		String message = "HALLO WELT";
		String encrypted = SimpleEncryption.encryptMessage(message, 2);
		
		assertNotEquals(message, encrypted);
	}
	
	// lowercase buchstaben sollen nicht encrypted werden
	@Test
	void testLowercase() {
		String message = "hallo welt";
		String encrypted = SimpleEncryption.encryptMessage(message, 2);
		
		assertEquals(message, encrypted);
	}
	
	@Test
	void testSpacecase() {
		String message = "       ";
		String encrypted = SimpleEncryption.encryptMessage(message, 2);
		
		assertEquals(message, encrypted);
	}

}
