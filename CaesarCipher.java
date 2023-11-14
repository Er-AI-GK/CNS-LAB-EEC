public class CaesarCipher {
    public static String encrypt(String plainText, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char character : plainText.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                char encryptedCharacter = (char) (base + newAlphabetPosition);
                encryptedText.append(encryptedCharacter);
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText, 26 - shift);
    }

    public static void main(String[] args) {
        String originalText = "Anna University";
        int shift = 3;
        
        String encryptedText = encrypt(originalText, shift);
        String decryptedText = decrypt(encryptedText, shift);
        
        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
