# TEDUND2ADSNOTT1GR4_USERService

## Perfil do usuário

O cadastro e a atualização de usuários recebem `perfilId`, que deve ser o ID
de um perfil existente na tabela `perfil`. As consultas de usuários também
retornam `perfilId`. O login retorna `token`, `nome`, `tempoValidade` em minutos,
`perfilId` e `perfilNome`.

Usuários cadastrados antes dessa alteração podem continuar com `perfilId` nulo
e fazer login; nesse caso, o nome do perfil também será nulo. Para associar um
perfil no H2, crie-o caso ainda não exista e consulte o ID gerado:

```sql
INSERT INTO perfil (nome, descricao) VALUES ('ADMIN', 'Administrador');
SELECT id_perfil, nome FROM perfil;
```

Use o ID consultado para atualizar o usuário desejado:

```sql
UPDATE usuario SET id_perfil = <ID_DO_PERFIL> WHERE email = 'admin@exemplo.com';
```

Substitua `<ID_DO_PERFIL>` por um número, sem os sinais `<` e `>`. Reinicie a
aplicação após atualizar o código para que o Hibernate crie a coluna, se
necessário. Não é criado automaticamente um perfil nem alterado o usuário
existente. O perfil é retornado no login, mas ainda não restringe permissões
nas rotas.
