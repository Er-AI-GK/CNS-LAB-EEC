public class RailFenceCipher {
    public static String encrypt(String text, int rails) {
        char[][] railMatrix = new char[rails][text.length()];
        boolean downward = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == rails - 1) {
                downward = !downward;
            }
            railMatrix[row][col] = text.charAt(i);
            col++;

            if (downward) {
                row++;
            } else {
                row--;
            }
        }

        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (railMatrix[i][j] != 0) {
                    encryptedText.append(railMatrix[i][j]);
                }
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, int rails) {
        char[][] railMatrix = new char[rails][text.length()];
        boolean downward = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == rails - 1) {
                downward = !downward;
            }
            railMatrix[row][col] = '*';
            col++;

            if (downward) {
                row++;
            } else {
                row--;
            }
        }

        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (railMatrix[i][j] == '*' && index < text.length()) {
                    railMatrix[i][j] = text.charAt(index);
                    index++;
                }
            }
        }

        row = 0;
        col = 0;
        downward = false;
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == rails - 1) {
                downward = !downward;
            }
            decryptedText.append(railMatrix[row][col]);
            col++;

            if (downward) {
                row++;
            } else {
                row--;
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Anna University, Chennai";
        int rails = 2;

        String encryptedText = encrypt(plaintext, rails);
        String decryptedText = decrypt(encryptedText, rails);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
