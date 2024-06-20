import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class Cifragem {
    private static final String CHAVE_PADRAO = "7RggKNLfxE";
    private static final int SALT_LENGTH = 16;
    private static final Random RANDOM = new SecureRandom();

    // Método para cifrar os dados
    public static byte[] cifrar(byte[] dados, String chave) {
        byte[] dadosCifrados = new byte[dados.length];
        byte[] chaveBytes = chave.getBytes(StandardCharsets.UTF_8);

        // Substituição
        for (int i = 0; i < dados.length; i++) {
            dadosCifrados[i] = (byte) (dados[i] ^ chaveBytes[i % chaveBytes.length]);
        }

        // Transposição
        int meio = dados.length / 2;
        for (int i = 0; i < meio; i++) {
            byte temp = dadosCifrados[i];
            dadosCifrados[i] = dadosCifrados[i + meio];
            dadosCifrados[i + meio] = temp;
        }

        return dadosCifrados;
    }

    // Sobrecarga do método para usar a chave padrão
    public static byte[] cifrar(byte[] dados) {
        return cifrar(dados, CHAVE_PADRAO);
    }

    // Método para decifrar os dados
    public static byte[] decifrar(byte[] dados, String chave) {
        byte[] dadosDecifrados = new byte[dados.length];
        byte[] chaveBytes = chave.getBytes(StandardCharsets.UTF_8);

        // Transposição (deve ser o inverso da cifração)
        int meio = dados.length / 2;
        for (int i = 0; i < meio; i++) {
            byte temp = dados[i];
            dadosDecifrados[i] = dados[i + meio];
            dadosDecifrados[i + meio] = temp;
        }

        // Substituição (deve ser o inverso da cifração)
        for (int i = 0; i < dados.length; i++) {
            dadosDecifrados[i] = (byte) (dadosDecifrados[i] ^ chaveBytes[i % chaveBytes.length]);
        }

        return dadosDecifrados;
    }

    // Sobrecarga do método para usar a chave padrão
    public static byte[] decifrar(byte[] dados) {
        return decifrar(dados, CHAVE_PADRAO);
    }

    // Método para gerar um sal aleatório
    public static String getSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Método para hashear uma senha com o sal dado usando SHA-256
    public static String hashWithSalt(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedBytes);
    }

    // Método para hashear uma senha
    public static String hashPassword(String plainTextPassword) throws NoSuchAlgorithmException {
        String salt = getSalt();
        String hashedPassword = hashWithSalt(plainTextPassword, salt);
        return salt + ":" + hashedPassword;
    }

    // Método para verificar uma senha contra uma senha hashada
    public static boolean verifyPassword(String plainTextPassword, String storedPassword) throws NoSuchAlgorithmException {
        String[] parts = storedPassword.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato da senha armazenada inválido.");
        }
        String salt = parts[0];
        String storedHash = parts[1];
        String calculatedHash = hashWithSalt(plainTextPassword, salt);
        return storedHash.equals(calculatedHash);
    }
}
