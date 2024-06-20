Quarto trabalho prático da disciplina de Algoritmos e Estruturas de Dados III

## RELATÓRIO ##

### 1. Introdução ###

O objetivo desse trabalho é implementar cifração e decifração de entidades. Para isso, foi implementada a classe que contém os métodos `cifrar` e `decifrar` que recebem um array de bytes e retornam um array de bytes cifrado e decifrado, respectivamente. Além disso, foi implementado um método `hashPassword` que retorna o hash da senha e um método `verifyPassword` que verifica se a senha é válida.

### 2. Classes e Métodos ###

    public byte[] toByteArray(): Retorna um array de bytes com os atributos da classe.

    public void fromByteArray(byte[] dados): Preenche os atributos da classe com os dados do array de bytes.

    public byte[] cifrar(byte[] dados): Recebe um array de bytes e retorna um array de bytes cifrado.

    public byte[] decifrar(byte[] dados): Recebe um array de bytes cifrado e retorna um array de bytes decifrado.

    private static String getSalt(): Retorna um salt aleatório.

    private static String hashWithSalt(String password, String salt): Retorna o hash da senha com o salt.

    public static String hashPassword(String senha): Retorna o hash da senha.

    public static boolean verifyPassword(String senha, String senhaArmazenada): Verifica se a senha é válida.

### 3. Checklist ###

- Há uma função de cifragem em todas as classes de entidades, envolvendo pelo menos duas operações diferentes e usando uma chave criptográfica? Sim, a classe `Usuario` possui os métodos `cifrar` e `decifrar` que recebem um array de bytes e retornam um array de bytes cifrado e decifrado, respectivamente.
- Uma das operações de cifragem é baseada na substituição e a outra na transposição? Sim, a operação de cifragem é baseada na substituição e a operação de decifragem
- O trabalho está funcionando corretamente? Sim, o trabalho está funcionando corretamente. 
- O trabalho está completo? Sim, o trabalho está completo.
- O trabalho é original e não a cópia de um trabalho de um colega? Sim, o trabalho é original e desenvolvido por Lívia Câmara, Sophia Carrazza e Yan Sabarense.