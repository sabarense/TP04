Quarto trabalho prático da disciplina de Algoritmos e Estruturas de Dados III

## RELATÓRIO ##

### 1. Introdução ###

O objetivo deste trabalho é implementar funcionalidades de criptografia e segurança para operações de cifração, decifração e hash de senhas em entidades de um sistema. Para isso, foi desenvolvida a classe Cifragem, que contém métodos para cifrar e decifrar dados, além de implementar técnicas de hash para segurança de senhas. Os métodos cifrar e decifrar permitem a manipulação segura de dados sensíveis, utilizando uma chave padrão ou uma chave fornecida pelo usuário. Adicionalmente, o método hashPassword gera um hash seguro para senhas utilizando um algoritmo SHA-256 combinado com um sal aleatório, enquanto o método verifyPassword verifica a validade de uma senha em relação ao hash armazenado, garantindo a integridade e confidencialidade das informações no sistema.

### 2. Classes ###
    
    - Usuario: Representa uma entidade usuário. Possui os atributos `id`, `nome`, `email`, `senha` e `salt`.
    - Perfil: Representa uma entidade perfil. Possui os atributos `id`,`nome` e `descrição`.
    - Cifragem: Representa a classe carregada de cifrar e decifrar entidades. Possui os métodos `cifrar`, `decifrar`, `getSalt`, `hashWithSalt`, `hashPassword` e `verifyPassword`.

### 3. Métodos ###

    public byte[] toByteArray(): Retorna um array de bytes com os atributos da classe.

    public void fromByteArray(byte[] dados): Preenche os atributos da classe com os dados do array de bytes.

    public byte[] cifrar(byte[] dados): cifrar(byte[] dados, String chave): Criptografa os dados fornecidos utilizando uma técnica de substituição XOR seguida por uma transposição dos bytes. A chave utilizada para a cifração é fornecida como parâmetro.

    public byte[] decifrar(byte[] dados): Decifra os dados fornecidos utilizando o inverso da operação realizada na cifração. Primeiro, realiza a transposição inversa e depois a substituição inversa usando a chave fornecida.

    private static String getSalt(): Gera um valor aleatório de sal (salt) como uma sequência codificada em Base64. O sal é usado para aumentar a segurança ao hashear senhas.

    private static String hashWithSalt(String password, String salt): Recebe uma senha e um sal como entrada, e utiliza o algoritmo de hash SHA-256 para produzir um hash da senha concatenada com o sal. Retorna o hash codificado em Base64.

    public static String hashPassword(String senha): Gera um sal aleatório usando getSalt() e, em seguida, utiliza o método hashWithSalt para gerar um hash seguro da senha fornecida concatenada com o sal. Retorna uma string no formato salt:hashedPassword.

    public static boolean verifyPassword(String senha, String senhaArmazenada): Verifica se a senha fornecida (senha) corresponde ao hash armazenado (senhaArmazenada). Extrai o sal e o hash armazenado de senhaArmazenada, calcula o hash da senha fornecida com o mesmo sal e compara com o hash armazenado para determinar a validade da senha.

### 4. Checklist ###

- Há uma função de cifragem em todas as classes de entidades, envolvendo pelo menos duas operações diferentes e usando uma chave criptográfica? Sim, a classe Cifragem possui os métodos `cifrar` e `decifrar` que envolvem duas operações diferentes e usam uma chave criptográfica.
- Uma das operações de cifragem é baseada na substituição e a outra na transposição? Sim, a operação de cifragem é baseada na substituição e a operação de decifragem
- O trabalho está funcionando corretamente? Sim, o trabalho está funcionando corretamente. 
- O trabalho está completo? Sim, o trabalho está completo.
- O trabalho é original e não a cópia de um trabalho de um colega? Sim, o trabalho é original e desenvolvido por Lívia Câmara, Sophia Carrazza e Yan Sabarense.
