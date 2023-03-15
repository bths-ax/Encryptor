public class Encryptor {
	/** A two-dimensional array of single-character strings, instantiated in the constructor */
	private String[][] letterBlock;

	/** The number of rows of letterBlock, set by the constructor */
	private int numRows;

	/** The number of columns of letterBlock, set by the constructor */
	private int numCols;

	/** Constructor*/
	public Encryptor(int r, int c) {
		letterBlock = new String[r][c];
		numRows = r;
		numCols = c;
	}

	public String[][] getLetterBlock() {
		return letterBlock;
	}

	/** Places a string into letterBlock in row-major order.
	 *
	 *   @param str  the string to be processed
	 *
	 *   Postcondition:
	 *     if str.length() < numRows * numCols, "A" in each unfilled cell
	 *     if str.length() > numRows * numCols, trailing characters are ignored
	 */
	public void fillBlock(String str) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				int idx = i * numCols + j;
				if (idx < str.length()) {
					letterBlock[i][j] = str.substring(idx, idx + 1);
				} else {
					letterBlock[i][j] = "A";
				}
			}
		}
	}

	/** Extracts encrypted string from letterBlock in column-major order.
	 *
	 *   Precondition: letterBlock has been filled
	 *
	 *   @return the encrypted string from letterBlock
	 */
	public String encryptBlock() {
		String result = "";
		for (int i = 0; i < numCols; i++)
			for (int j = 0; j < numRows; j++)
				result += letterBlock[j][i];
		return result;
	}

	/** Encrypts a message.
	 *
	 *  @param message the string to be encrypted
	 *
	 *  @return the encrypted message; if message is the empty string, returns the empty string
	 */
	public String encryptMessage(String message) {
		String result = "";
		while (message.length() > 0) {
			fillBlock(message);
			result += encryptBlock();
			message = message.substring(Math.min(message.length(), numRows * numCols));
		}
		return result;
	}

	/**  Decrypts an encrypted message. All filler 'A's that may have been
	 *   added during encryption will be removed, so this assumes that the
	 *   original message (BEFORE it was encrypted) did NOT end in a capital A!
	 *
	 *   NOTE! When you are decrypting an encrypted message,
	 *         be sure that you have initialized your Encryptor object
	 *         with the same row/column used to encrypted the message! (i.e.
	 *         the “encryption key” that is necessary for successful decryption)
	 *         This is outlined in the precondition below.
	 *
	 *   Precondition: the Encryptor object being used for decryption has been
	 *                 initialized with the same number of rows and columns
	 *                 as was used for the Encryptor object used for encryption.
	 *
	 *   @param encryptedMessage  the encrypted message to decrypt
	 *
	 *   @return  the decrypted, original message (which had been encrypted)
	 *
	 *   TIP: You are encouraged to create other helper methods as you see fit
	 *        (e.g. a method to decrypt each section of the decrypted message,
	 *         similar to how encryptBlock was used)
	 */
	public String decryptMessage(String encryptedMessage) {
		String result = "";
		while (encryptedMessage.length() > 0) {
			// fill block
			for (int i = 0; i < numCols; i++) {
				for (int j = 0; j < numRows; j++) {
					int idx = i * numRows + j;
					if (idx < encryptedMessage.length()) {
						letterBlock[j][i] = encryptedMessage.substring(idx, idx + 1);
					} else {
						letterBlock[j][i] = "?";
					}
				}
			}
			// decrypt block
			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numCols; j++)
					result += letterBlock[i][j];
			encryptedMessage = encryptedMessage.substring(Math.min(encryptedMessage.length(), numRows * numCols));
		}
		// remove trailing a
		int trailIdx = result.length();
		while (trailIdx > 0 && result.substring(trailIdx - 1, trailIdx).equals("A"))
			trailIdx--;
		return result.substring(0, trailIdx);
	}
}
