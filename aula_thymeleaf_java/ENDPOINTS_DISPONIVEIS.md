# 📋 Endpoints Disponíveis - Sistema de Orçamentos

## 🌐 **Páginas Web (Thymeleaf)**

### **Usuários**
- **GET** `/usuarios` - Página principal de usuários
- **GET** `/usuarios/pesquisa` - Lista todos os usuários cadastrados

### **Orçamentos**
- **GET** `/orcamentos` - Página principal de orçamentos (com pesquisa de usuário)
- **GET** `/orcamentos/success` - Página de confirmação de orçamento salvo
- **GET** `/orcamentos/database` - Página de acesso ao banco de dados

---

## 🔌 **Endpoints REST API**

### **Usuários** (`/usuarios`)
- **GET** `/usuarios/search?searchTerm={termo}` - Busca usuários por nome (AJAX)
- **POST** `/usuarios` - Cadastra novo usuário

### **Orçamentos** (`/orcamentos`)
- **GET** `/orcamentos/api` - Lista todos os orçamentos (JSON)
- **GET** `/orcamentos/pesquisaid/{id}` - Busca orçamento por ID
- **POST** `/orcamentos` - Cadastra novo orçamento (com redirecionamento)
- **POST** `/orcamentos/put/{id}` - Atualiza orçamento existente
- **DELETE** `/orcamentos/delete/{id}` - Remove orçamento

---

## 🗄️ **Banco de Dados H2**

### **Console H2**
- **GET** `/h2-console` - Interface web do banco H2
  - **URL JDBC:** `jdbc:h2:mem:orcamento_db`
  - **Usuário:** `sa`
  - **Senha:** (vazia)
  - **Driver:** `org.h2.Driver`

### **Página de Acesso**
- **GET** `/orcamentos/database` - Página com instruções e link direto para o console H2

---

## 📱 **Funcionalidades Responsivas Implementadas**

### **Navegação**
- ✅ Menu hambúrguer em dispositivos móveis
- ✅ Texto oculto em telas pequenas (apenas ícones)
- ✅ Container fluido para melhor adaptação

### **Formulários**
- ✅ Campos responsivos com breakpoints Bootstrap
- ✅ Botões adaptáveis (largura total em mobile)
- ✅ Modal scrollável para telas pequenas

### **Cards de Orçamentos**
- ✅ Grid responsivo: 1 coluna (mobile) → 2 colunas (tablet) → 3-4 colunas (desktop)
- ✅ Espaçamento otimizado para diferentes telas
- ✅ Texto e botões redimensionados automaticamente

### **Breakpoints Utilizados**
- **Mobile:** `< 576px` - Layout em coluna única
- **Tablet:** `576px - 768px` - Layout em 2 colunas
- **Desktop:** `> 768px` - Layout em 3-4 colunas

---

## 🚀 **Como Testar**

1. **Iniciar aplicação:** `./mvnw spring-boot:run`
2. **Acessar:** `http://localhost:8080/orcamentos`
3. **Testar responsividade:** Redimensionar janela do navegador
4. **Banco H2:** `http://localhost:8080/h2-console`

---

## 📊 **Estrutura de Dados**

### **Usuário**
```json
{
  "id": 1,
  "nomeUsuario": "João Silva",
  "rg": "123456789",
  "cpf": "12345678901",
  "nomeMae": "Maria Silva"
}
```

### **Orçamento**
```json
{
  "id": 1,
  "valorOrcamento": 1000.00,
  "icmsEstados": "ICMS_MG",
  "valorICMS": 180.00,
  "usuario": {
    "id": 1,
    "nomeUsuario": "João Silva"
  }
}
```

---

## ⚡ **Taxas de ICMS por Estado**
- **Minas Gerais (MG):** 18%
- **São Paulo (SP):** 12%
- **Rio de Janeiro (RJ):** 20%
