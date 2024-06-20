public class Main {
    public static void main(String[] args) {
        try {
            //cria um usuário original com a senha "senha"
            String senhaOriginal = "senhaTeste123";
            String senhaHashada = Usuario.hashPassword(senhaOriginal);
            Usuario usuarioOriginal = new Usuario(1, "Nome", "email@example.com", senhaHashada);

            //converte o usuário para um array de bytes
            byte[] dadosCifrados = usuarioOriginal.toByteArray();

            //cria um novo usuário e lê os dados do array de bytes
            Usuario usuarioDecifrado = new Usuario(0, "", "", "");
            usuarioDecifrado.fromByteArray(dadosCifrados);

            //verifica se a senha decifrada é válida
            boolean senhaValida = Usuario.verifyPassword(senhaOriginal, usuarioDecifrado.getSenha());

            //compara os dados do usuário original e do usuário decifrado
            boolean idIgual = usuarioOriginal.getId() == usuarioDecifrado.getId();
            boolean nomeIgual = usuarioOriginal.getNome().equals(usuarioDecifrado.getNome());
            boolean emailIgual = usuarioOriginal.getEmail().equals(usuarioDecifrado.getEmail());

            //testes
            System.out.println("Senha original: " + senhaOriginal);
            System.out.println("Senha hashada: " + usuarioOriginal.getSenha());
            System.out.println("Senha decifrada e verificada: " + senhaValida);

            if (idIgual && nomeIgual && emailIgual && senhaValida) {
                System.out.println("Teste passou!");
            } else {
                System.out.println("Teste falhou!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
