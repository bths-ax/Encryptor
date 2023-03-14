import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("The Encryptor");
			System.out.println("[E]ncrypt");
			System.out.println("[D]ecrypt");
			System.out.println("[Q]uit");

			System.out.print("> ");
			String choice = scanner.nextLine().toUpperCase();
			System.out.println();

			if (choice.equals("Q")) break;
			else if (choice.equals("E")) {
				System.out.print("To Encrypt: ");
				String data = scanner.nextLine();
				System.out.print("Key (Rows Cols): ");
				String[] key = scanner.nextLine().split(" "); // assume proper input
				int rows = Integer.parseInt(key[0]);
				int cols = Integer.parseInt(key[1]);
				Encryptor encryptor = new Encryptor(rows, cols);
				System.out.println("Encrypted: " + encryptor.encryptMessage(data));
			}
			else if (choice.equals("D")) {
				System.out.print("To Decrypt: ");
				String data = scanner.nextLine();
				System.out.print("Key (Rows Cols): ");
				String[] key = scanner.nextLine().split(" ");
				int rows = Integer.parseInt(key[0]);
				int cols = Integer.parseInt(key[1]);
				Encryptor encryptor = new Encryptor(rows, cols);
				System.out.println("Decrypted: " + encryptor.decryptMessage(data));
			}

			System.out.println();
		}
	}
}
