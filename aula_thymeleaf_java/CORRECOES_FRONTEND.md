# Correções do Front-End

## Problemas Corrigidos

### 1. **ClienteController**
- ✅ Agora carrega a lista de clientes na rota GET inicial (`/clientes`)
- ✅ Antes só carregava quando acessava `/clientes/pesquisa`

### 2. **UsuarioController**
- ✅ Agora carrega a lista de usuários na rota GET inicial (`/usuarios`)
- ✅ Método `cadastraUsuario` agora retorna `String` com redirect ao invés de `ResponseEntity`
- ✅ Antes só carregava quando acessava `/usuarios/pesquisa`

### 3. **clientePage.html**
- ✅ Corrigida estrutura da tabela para exibir corretamente quando há ou não clientes
- ✅ Formulário agora funciona corretamente para cadastro e edição
- ✅ Script JavaScript ajustado para detectar edição corretamente
- ✅ Removido conflito entre `th:field` e `th:value`

### 4. **usuarioPage.html**
- ✅ Corrigida estrutura da tabela para exibir mensagem quando não há usuários
- ✅ Removido JavaScript desnecessário que interceptava o submit
- ✅ Formulário agora funciona com submit normal do HTML

### 5. **orcamentoPage.html**
- ✅ Corrigida estrutura do `th:each` para funcionar corretamente
- ✅ Agora exibe mensagem quando não há orçamentos
- ✅ Lista de orçamentos exibe corretamente cliente ou usuário associado

### 6. **HomeController**
- ✅ Criado controller para rota raiz (`/`) que redireciona para `/orcamentos`

## Como Testar

1. **Clientes:**
   - Acesse: http://localhost:8080/clientes
   - Deve mostrar lista (mesmo que vazia)
   - Clique em "Novo Cliente" para cadastrar
   - Clique em "Editar" para editar um cliente
   - Clique em "Excluir" para excluir um cliente

2. **Usuários:**
   - Acesse: http://localhost:8080/usuarios
   - Deve mostrar lista (mesmo que vazia)
   - Preencha o formulário e clique em "Salvar"

3. **Orçamentos:**
   - Acesse: http://localhost:8080/orcamentos
   - Clique em "Novo Orçamento"
   - Busque um cliente por nome ou CPF
   - Preencha valor e estado
   - Salve o orçamento

## Funcionalidades Funcionando

✅ Cadastro de Clientes  
✅ Listagem de Clientes  
✅ Edição de Clientes  
✅ Exclusão de Clientes  
✅ Busca de Clientes por nome ou CPF  
✅ Cadastro de Usuários  
✅ Listagem de Usuários  
✅ Cadastro de Orçamentos com Cliente  
✅ Listagem de Orçamentos  

