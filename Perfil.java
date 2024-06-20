import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Perfil {
    private int id;
    private String nome;
    private String descricao;

    public Perfil(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.nome);
        dos.writeUTF(this.descricao);
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
        this.descricao = dis.readUTF();
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + nome + "\nDescrição: " + descricao;
    }
}
