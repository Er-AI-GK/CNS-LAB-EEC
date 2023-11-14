public class RowColumnTransform {
    public static String encrypt(String plainText, int numRows, int numCols) {
        // Remove spaces and fill with placeholder characters
        plainText = plainText.replaceAll("\\s", "");
        int length = plainText.length();
        int numPlaceholders = numRows * numCols - length;
        
        if (numPlaceholders > 0) {
            for (int i = 0; i < numPlaceholders; i++) {
                plainText += "X";
            }
        }
        
        char[][] matrix = new char[numRows][numCols];
        int textIndex = 0;
        
        // Fill the matrix row by row
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                matrix[row][col] = plainText.charAt(textIndex);
                textIndex++;
            }
        }
        
        StringBuilder encryptedText = new StringBuilder();
        
        // Read the matrix column by column
        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                encryptedText.append(matrix[row][col]);
            }
        }
        
        return encryptedText.toString();
    }

    public static String decrypt(String cipherText, int numRows, int numCols) {
        char[][] matrix = new char[numRows][numCols];
        int textIndex = 0;
        
        // Fill the matrix column by column
        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                matrix[row][col] = cipherText.charAt(textIndex);
                textIndex++;
            }
        }
        
        StringBuilder decryptedText = new StringBuilder();
        
        // Read the matrix row by row
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                decryptedText.append(matrix[row][col]);
            }
        }
        
        return decryptedText.toString().replaceAll("X", " "); // Restore spaces
    }

    public static void main(String[] args) {
        String plaintext = "Security Lab";
        int numRows = 4;
        int numCols = 4;
        
        String encryptedText = encrypt(plaintext, numRows, numCols);
        String decryptedText = decrypt(encryptedText, numRows, numCols);
        
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
