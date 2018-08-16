package encryption;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

public class SimpleEncryption implements Runnable {

	int start;
	int ende;
	int offset;
	int key = 3;
	static String message;
	static char[] encrypted;
	static Object lock = new Object();
	char[] abc = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };

	// start: Ab wann es Encrypted werden soll
	public SimpleEncryption(int start, int ende, int offset) {
		this.start = start;
		this.ende = ende;
		this.offset = offset;
	}

	public static void main(String[] args) throws InterruptedException {
		// Anzahl der Threads
		// Die nachricht die verschlüsselt wird
		System.out.println("Welche Message");
		Scanner sc = new Scanner(System.in);
		String message = sc.nextLine();
		System.out.println("Bitte geben Sie ein, wie viele Threads sie erstellen wollen");
		int threadanzahl = sc.nextInt();
		
		String encrypted = encryptMessage(message, threadanzahl);
		System.out.println("Encrypted: " + encrypted);
	}

	public static String encryptMessage(String message, int threadCount) {
		SimpleEncryption.setMessage(message);
		// Thread String
		Thread[] threads = new Thread[threadCount];

		if (threads.length <= message.length()) {

			// 1: 0 2 4 6 8 10
			// 2: 1 3 5 7 9
			for (int i = 0; i < threadCount; i++) {
				threads[i] = new Thread(new SimpleEncryption(i, message.length(), threadCount));
				threads[i].start();
			}

		} else {

		}

		// warten bis alle fertig sind weil SimpleEncryption.getEncrypted sonst noch
		// nicht verarbeitet von thread wurde
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return SimpleEncryption.getEncrypted();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// String in dem die Nachricht gespeichert wird
		String message = SimpleEncryption.getMessage();
		char[] messageArray = message.toCharArray();

		// Verschlüsselung
		for (int i = start; i < ende; i += offset) {
			// buchstabe in abc array suchen
			for (int j = 0; j < abc.length; j++) {
				if (abc[j] == messageArray[i]) {
					// index im alphabet berechnen: jetzigen index um {key} stellen verschieben ('A'
					// -> 'C')
					// modulo damit es innerhalb der range bleibt ('Z' -> 'B' statt outofbounds)
					int newIndex = (i + key) % abc.length;

					// locken auf statisches objekt
					synchronized (lock) {
						// verschlüsselte message holen und neuen buchstaben zurückschreiben
						// 'A' = 'C'
						SimpleEncryption.setChar(abc[newIndex], i);
					}
					break;
				}
			}
		}
	}

	public static void setMessage(String message) {
		// "HALLO WELT"
		SimpleEncryption.message = message;
		// { 'H', 'A', ... }
		SimpleEncryption.encrypted = message.toCharArray();
	}

	public static String getMessage() {
		return SimpleEncryption.message;
	}

	public static String getEncrypted() {
		return new String(SimpleEncryption.encrypted);
	}

	public static void setChar(char ch, int index) {
		SimpleEncryption.encrypted[index] = ch;
	}
}