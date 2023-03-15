import java.util.Scanner;

public class Main {
	public static String extraCreditEncrypt(String data, int rows, int cols) {
		// pad data
		int n = rows * cols;
		int pad = (data.length() + n - 1) / n * n - data.length();
		for (int i = 0; i < pad; i++)
			data += "A";
		// transform data
		String[][] resultBuf = new String[rows][cols];
		int idx = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				resultBuf[i][j] = "";
				for (int k = idx; k < data.length(); k += n)
					resultBuf[i][j] += data.substring(k, k + 1);
				idx++;
			}
		}
		// encrypt data
		String result = "";
		for (int i = 0; i < cols; i++)
			for (int j = 0; j < rows; j++)
				result += resultBuf[j][i];
		return result;
	}

	public static String extraCreditDecrypt(String data, int rows, int cols) {
		int n = rows * cols;
		int cnt = data.length() / (rows * cols);
		// transform data
		String[][] resultBuf = new String[rows][cols];
		int idx = 0;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				resultBuf[j][i] = data.substring(idx, idx + cnt);
				idx += cnt;
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				System.out.print(resultBuf[i][j] + "|");
			System.out.println();
		}
		// decrypt data
		String result = "";
		for (int k = 0; k < cnt; k++)
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++)
					result += resultBuf[i][j].substring(k, k + 1);
		// unpad result
		int padIdx = result.length();
		while (padIdx > 0 && result.substring(padIdx - 1, padIdx).equals("A"))
			padIdx--;
		return result.substring(0, padIdx);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("The Encryptor");
			System.out.println("[E]ncrypt");
			System.out.println("[D]ecrypt");
			System.out.println("[E]ncrypt (E[X]tra Credit)");
			System.out.println("[D]ecrypt (E[X]tra Credit)");
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
			else if (choice.equals("EX")) {
				System.out.print("To Encrypt: ");
				String data = scanner.nextLine();
				System.out.print("Key (Rows Cols): ");
				String[] key = scanner.nextLine().split(" ");
				int rows = Integer.parseInt(key[0]);
				int cols = Integer.parseInt(key[1]);
				System.out.println("Encrypted: " + extraCreditEncrypt(data, rows, cols));
			}
			else if (choice.equals("DX")) {
				System.out.print("To Decrypt: ");
				String data = scanner.nextLine();
				System.out.print("Key (Rows Cols): ");
				String[] key = scanner.nextLine().split(" ");
				int rows = Integer.parseInt(key[0]);
				int cols = Integer.parseInt(key[1]);
				System.out.println("Decrypted: " + extraCreditDecrypt(data, rows, cols));
			}

			System.out.println();
		}
	}
}
