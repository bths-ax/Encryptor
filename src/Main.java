import java.util.Scanner;

public class Main {
	public static String encrypt(String data, String[] key) {
		int rows = Integer.parseInt(key[0]);
		int cols = Integer.parseInt(key[1]);
		String result = "";
		int idx = 0;
		// pad input with A's
		int pad = (data.length() + rows * cols - 1) / (rows * cols) * (rows * cols) - data.length();
		for (int i = 0; i < pad; i++)
			data += "A";
		// encryption
		while (idx < data.length()) {
			String[][] buf = new String[rows][cols];
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++, idx++)
					buf[i][j] = data.substring(idx, idx + 1);
			for (int i = 0; i < cols; i++)
				for (int j = 0; j < rows; j++)
					result += buf[j][i];
		}
		return result;
	}

	public static String decrypt(String data, String[] key) {
		int rows = Integer.parseInt(key[0]);
		int cols = Integer.parseInt(key[1]);
		String result = "";
		int idx = 0;
		// decryption
		while (idx < data.length()) {
			String[][] buf = new String[rows][cols];
			for (int i = 0; i < cols; i++)
				for (int j = 0; j < rows; j++, idx++)
					buf[j][i] = data.substring(idx, idx + 1);
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++)
					result += buf[i][j];
		}
		return result;
	}

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
				String[] key = scanner.nextLine().split(" ");
				System.out.println("Encrypted: " + encrypt(data, key));
			}
			else if (choice.equals("D")) {
				System.out.print("To Decrypt: ");
				String data = scanner.nextLine();
				System.out.print("Key (Rows Cols): ");
				String[] key = scanner.nextLine().split(" ");
				System.out.println("Decrypted: " + decrypt(data, key));
			}

			System.out.println();
		}
	}
}
