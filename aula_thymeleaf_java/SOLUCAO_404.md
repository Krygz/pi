# Solução para o Erro 404 - Clientes não encontrado

## Problema
O Spring Boot não estava encontrando o mapeamento `/clientes` porque os arquivos Java não estavam sendo compilados corretamente junto com os arquivos Kotlin.

## Solução Aplicada
Foi adicionado o plugin `maven-compiler-plugin` no `pom.xml` para garantir que tanto Java quanto Kotlin sejam compilados.

## Como Resolver

### 1. Pare a aplicação (se estiver rodando)
Pressione `Ctrl + C` no terminal

### 2. Limpe e recompile o projeto
```powershell
cd aula_thymeleaf_java
.\mvnw.cmd clean compile
```

### 3. Execute novamente
```powershell
.\mvnw.cmd spring-boot:run
```

### 4. Acesse a aplicação
- **Clientes:** http://localhost:8080/clientes
- **Orçamentos:** http://localhost:8080/orcamentos
- **Usuários:** http://localhost:8080/usuarios

## Se ainda não funcionar

Verifique se o Java está instalado e configurado:
```powershell
java -version
```

Se não estiver instalado, instale o Java 17 ou superior.

