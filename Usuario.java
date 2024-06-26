import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha; // Armazena o hash da senha.

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
        dos.writeUTF(this.senha); // Usa a senha já hashada
        byte[] dados = baos.toByteArray();
        byte[] dadosCifrados = Cifragem.cifrar(dados); // Usando Cifragem
        return dadosCifrados;
    }

    public void fromByteArray(byte[] dados) throws Exception {
        byte[] dadosDecifrados = Cifragem.decifrar(dados); // Usando Cifragem
        ByteArrayInputStream bais = new ByteArrayInputStream(dadosDecifrados);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.nome = dis.readUTF();
        this.email = dis.readUTF();
        this.senha = dis.readUTF(); // Carrega a senha hashada
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nEmail: " + email + "\nSenha: " + senha;
    }
}
