# Como Executar o Projeto

## Pré-requisitos
- Java 17 ou superior
- Maven (ou use o wrapper incluído)

## Opções de Execução

### Opção 1: Usando Maven Wrapper (Recomendado)

**No Windows (PowerShell):**
```powershell
cd aula_thymeleaf_java
.\mvnw.cmd spring-boot:run
```

**No Windows (CMD):**
```cmd
cd aula_thymeleaf_java
mvnw.cmd spring-boot:run
```

**No Linux/Mac:**
```bash
cd aula_thymeleaf_java
./mvnw spring-boot:run
```

### Opção 2: Usando Maven instalado

```bash
cd aula_thymeleaf_java
mvn spring-boot:run
```

### Opção 3: Compilar e executar o JAR

```bash
cd aula_thymeleaf_java
mvn clean package
java -jar target/orcamento-0.0.1-SNAPSHOT.jar
```

## Acessar a Aplicação

Após iniciar, a aplicação estará disponível em:

- **Aplicação Web:** http://localhost:8080
- **Console H2 (Banco de Dados):** http://localhost:8080/h2-console

### URLs Disponíveis:

- **Clientes:** http://localhost:8080/clientes
- **Usuários:** http://localhost:8080/usuarios
- **Orçamentos:** http://localhost:8080/orcamentos
- **Banco de Dados:** http://localhost:8080/orcamentos/database

## Configuração do Banco H2

Ao acessar o console H2 em http://localhost:8080/h2-console, use:

- **JDBC URL:** `jdbc:h2:mem:orcamento_db`
- **User Name:** `sa`
- **Password:** (deixe em branco)

## Funcionalidades

1. **Clientes:**
   - Cadastro, listagem, edição e exclusão
   - Busca por nome ou CPF

2. **Orçamentos:**
   - Criar orçamento associado a um cliente
   - Buscar cliente por nome ou CPF
   - Visualizar lista de orçamentos

3. **Usuários:**
   - Gerenciamento de usuários (funcionalidade existente)

## Parar a Aplicação

Pressione `Ctrl + C` no terminal onde a aplicação está rodando.


