public class PlayfairCipher {
    private String key;
    private char[][] matrix = new char[5][5];

    public PlayfairCipher(String key) {
        this.key = preprocessKey(key);
        generateMatrix();
    }

    private String preprocessKey(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder processedKey = new StringBuilder(key);
        for (int i = 0; i < processedKey.length(); i++) {
            char current = processedKey.charAt(i);
            for (int j = i + 1; j < processedKey.length(); j++) {
                if (current == processedKey.charAt(j)) {
                    processedKey.deleteCharAt(j);
                }
            }
        }
        return processedKey.toString();
    }

    private void generateMatrix() {
        String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        int keyLength = key.length();
        int i, j, k;

        for (i = 0; i < keyLength; i++) {
            matrix[i / 5][i % 5] = key.charAt(i);
            alphabet = alphabet.replaceAll("" + key.charAt(i), "");
        }

        for (k = 0; k < alphabet.length(); k++) {
            matrix[(i + k) / 5][(i + k) % 5] = alphabet.charAt(k);
        }
    }

    private String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder processedText = new StringBuilder(text);

        for (int i = 1; i < processedText.length(); i += 2) {
            if (processedText.charAt(i) == processedText.charAt(i - 1)) {
                processedText.insert(i, 'X');
            }
        }

        if (processedText.length() % 2 != 0) {
            processedText.append('X');
        }

        return processedText.toString();
    }

    public String encrypt(String text) {
        text = prepareText(text);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = text.charAt(i + 1);
            int[] firstPos = findPosition(first);
            int[] secondPos = findPosition(second);

            int row1 = firstPos[0];
            int col1 = firstPos[1];
            int row2 = secondPos[0];
            int col2 = secondPos[1];

            if (row1 == row2) {
                col1 = (col1 + 1) % 5;
                col2 = (col2 + 1) % 5;
            } else if (col1 == col2) {
                row1 = (row1 + 1) % 5;
                row2 = (row2 + 1) % 5;
            } else {
                int temp = col1;
                col1 = col2;
                col2 = temp;
            }

            encryptedText.append(matrix[row1][col1]);
            encryptedText.append(matrix[row2][col2]);
        }

        return encryptedText.toString();
    }

    public String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = text.charAt(i + 1);
            int[] firstPos = findPosition(first);
            int[] secondPos = findPosition(second);

            int row1 = firstPos[0];
            int col1 = firstPos[1];
            int row2 = secondPos[0];
            int col2 = secondPos[1];

            if (row1 == row2) {
                col1 = (col1 + 4) % 5;
                col2 = (col2 + 4) % 5;
            } else if (col1 == col2) {
                row1 = (row1 + 4) % 5;
                row2 = (row2 + 4) % 5;
            } else {
                int temp = col1;
                col1 = col2;
                col2 = temp;
            }

            decryptedText.append(matrix[row1][col1]);
            decryptedText.append(matrix[row2][col2]);
        }

        return decryptedText.toString();
    }

    private int[] findPosition(char c) {
        int[] position = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }

        return position;
    }

    public static void main(String[] args) {
        String key = "CSE";
        PlayfairCipher cipher = new PlayfairCipher(key);

        String plaintext = "Security Lab";
        String ciphertext = cipher.encrypt(plaintext);
        String decryptedText = cipher.decrypt(ciphertext);

        System.out.println("Key: " + key);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
