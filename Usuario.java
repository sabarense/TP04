import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha; // Armazena o hash da senha.
    private static final String CHAVE = "7RggKNLfxE";
    private static final Map<Byte, Byte> MAPA_SUBSTITUICAO = new HashMap<>();
    private static final Random RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 16;

    static {
        byte[] chaveBytes = CHAVE.getBytes();
        for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
            MAPA_SUBSTITUICAO.put(b, chaveBytes[(b + 128) % chaveBytes.length]);
        }
    }

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.writeUTF(this.email);
        // usa a senha já hashada
        dos.writeUTF(this.senha);
        byte[] dados = baos.toByteArray();
        byte[] dadosCifrados = cifrar(dados);
        return dadosCifrados;
    }

    public void fromByteArray(byte[] dados) throws Exception {
        byte[] dadosDecifrados = decifrar(dados);
        ByteArrayInputStream bais = new ByteArrayInputStream(dadosDecifrados);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.email = dis.readUTF();
        //carrega a senha hashada
        this.senha = dis.readUTF();
    }

    public byte[] cifrar(byte[] dados) {
        byte[] dadosCifrados = new byte[dados.length];
        byte[] chaveBytes = CHAVE.getBytes();

        // substituição
        for (int i = 0; i < dados.length; i++) {
            dadosCifrados[i] = (byte) (dados[i] ^ chaveBytes[i % chaveBytes.length]);
        }

        // transposição
        int meio = dados.length / 2;
        for (int i = 0; i < meio; i++) {
            byte temp = dadosCifrados[i];
            dadosCifrados[i] = dadosCifrados[i + meio];
            dadosCifrados[i + meio] = temp;
        }
        return dadosCifrados;
    }

    public byte[] decifrar(byte[] dados) {
        byte[] dadosDecifrados = new byte[dados.length];
        byte[] chaveBytes = CHAVE.getBytes();

        // transposição (inverso da cifração)
        int meio = dados.length / 2;
        for (int i = 0; i < meio; i++) {
            byte temp = dados[i];
            dadosDecifrados[i] = dados[i + meio];
            dadosDecifrados[i + meio] = temp;
        }

        // substituição (inverso da cifração)
        for (int i = 0; i < dados.length; i++) {
            dadosDecifrados[i] = (byte) (dadosDecifrados[i] ^ chaveBytes[i % chaveBytes.length]);
        }
        return dadosDecifrados;
    }

    //gera um sal aleatório
    private static String getSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    //hasheia a senha com o sal dado usando SHA-256
    private static String hashWithSalt(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] hashedBytes = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }


    public static String hashPassword(String plainTextPassword) throws NoSuchAlgorithmException {
        String salt = getSalt();
        String hashedPassword = hashWithSalt(plainTextPassword, salt);
        return salt + ":" + hashedPassword;
    }

    //verifica uma senha contra uma senha hashada
    public static boolean verifyPassword(String plainTextPassword, String storedPassword) throws NoSuchAlgorithmException {
        String[] parts = storedPassword.split(":");
        String salt = parts[0];
        String storedHash = parts[1];
        String calculatedHash = hashWithSalt(plainTextPassword, salt);
        return storedHash.equals(calculatedHash);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nEmail: " + email + "\nSenha: " + senha;
    }
}