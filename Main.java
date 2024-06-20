public class Main {
    public static void main(String[] args) {
        try {
            // Criando e testando a entidade Usuario
            String senhaOriginal = "senha";
            String senhaHashada = Cifragem.hashPassword(senhaOriginal);
            Usuario usuarioOriginal = new Usuario(1, "Nome", "email@example.com", senhaHashada);

            // Converte o usuário para um array de bytes
            byte[] dadosCifradosUsuario = usuarioOriginal.toByteArray();

            // Cria um novo usuário e lê os dados do array de bytes
            Usuario usuarioDecifrado = new Usuario(0, "", "", "");
            usuarioDecifrado.fromByteArray(dadosCifradosUsuario);

            // Verifica se a senha decifrada é válida
            boolean senhaValida = Cifragem.verifyPassword(senhaOriginal, usuarioDecifrado.getSenha());

            // Compara os dados do usuário original e do usuário decifrado
            boolean idUsuarioIgual = usuarioOriginal.getId() == usuarioDecifrado.getId();
            boolean nomeUsuarioIgual = usuarioOriginal.getNome().equals(usuarioDecifrado.getNome());
            boolean emailUsuarioIgual = usuarioOriginal.getEmail().equals(usuarioDecifrado.getEmail());

            System.out.println("=== Teste Usuario ===");
            System.out.println("Senha original: " + senhaOriginal);
            System.out.println("Senha hashada: " + usuarioOriginal.getSenha());
            System.out.println("Senha decifrada e verificada: " + senhaValida);

            if (idUsuarioIgual && nomeUsuarioIgual && emailUsuarioIgual && senhaValida) {
                System.out.println("Teste Usuario passou!");
            } else {
                System.out.println("Teste Usuario falhou!");
            }

            // Criando e testando a entidade Perfil
            Perfil perfilOriginal = new Perfil(101, "Admin", "Perfil com permissões de administrador");

            // Converte o perfil para um array de bytes
            byte[] dadosCifradosPerfil = perfilOriginal.toByteArray();

            // Cria um novo perfil e lê os dados do array de bytes
            Perfil perfilDecifrado = new Perfil(0, "", "");
            perfilDecifrado.fromByteArray(dadosCifradosPerfil);

            // Compara os dados do perfil original e do perfil decifrado
            boolean idPerfilIgual = perfilOriginal.getId() == perfilDecifrado.getId();
            boolean nomePerfilIgual = perfilOriginal.getNome().equals(perfilDecifrado.getNome());
            boolean descricaoPerfilIgual = perfilOriginal.getDescricao().equals(perfilDecifrado.getDescricao());

            System.out.println("\n=== Teste Perfil ===");
            System.out.println("Perfil original: " + perfilOriginal.toString());
            System.out.println("Perfil decifrado: " + perfilDecifrado.toString());

            if (idPerfilIgual && nomePerfilIgual && descricaoPerfilIgual) {
                System.out.println("Teste Perfil passou!");
            } else {
                System.out.println("Teste Perfil falhou!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
