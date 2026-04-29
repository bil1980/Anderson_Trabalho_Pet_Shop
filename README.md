# 🐾 Sistema de Clínica Veterinária

## 📌 Descrição

Este projeto consiste em um sistema de gerenciamento de clínica veterinária desenvolvido em Java com integração a banco de dados PostgreSQL.

O sistema permite o cadastro, consulta, atualização e exclusão de informações relacionadas a clientes, pets, veterinários e consultas, além de realizar consultas complexas utilizando JOIN.

---

## 🚀 Tecnologias Utilizadas

* Java (JDBC)
* PostgreSQL
* NetBeans
* GitHub

---

## 🗄️ Estrutura do Projeto

```
/diagrama   -> Diagrama Entidade-Relacionamento (DER)
/ddl        -> Scripts de criação das tabelas
/dml        -> Scripts de inserção, atualização e exclusão
/dql        -> Consultas SQL (SELECT, JOIN, filtros)
/src        -> Código-fonte da aplicação Java
README.md   -> Documentação do projeto
```

---

## 🔐 Funcionalidade de Login

O sistema inicia com uma autenticação simples:

* Usuário: `admin`
* Senha: `123`

---

## ⚙️ Funcionalidades do Sistema (CRUD)

### ➕ CREATE (Inserir)

* Cadastro de Clientes
* Cadastro de Pets
* Cadastro de Veterinários
* Cadastro de Consultas

### 📖 READ (Consultar)

* Listagem de Clientes
* Listagem de Pets
* Listagem de Veterinários
* Listagem de Consultas

### 🔄 UPDATE (Atualizar)

* Atualização de dados de Clientes
* Atualização de Pets
* Atualização de Veterinários
* Atualização de Consultas

### ❌ DELETE (Excluir)

* Remoção de Clientes
* Remoção de Pets
* Remoção de Veterinários
* Remoção de Consultas

---

## 🔗 Consultas com JOIN

O sistema realiza consultas relacionando tabelas:

### 🔹 INNER JOIN

* Clientes + Pets
* Pets + Consultas

### 🔹 LEFT JOIN

* Pets + Consultas
  (Mostra pets que foram e que não foram à consulta)

---

## 🖼️ Prints da Aplicação





* Tela de login
<img width="455" height="103" alt="Captura de tela 2026-04-28 232511" src="https://github.com/user-attachments/assets/e012cf03-6fd0-4ac2-a970-419b6aaa49a6" />
* Resultado de JOIN
  
<img width="402" height="240" alt="Captura de tela 2026-04-28 232544" src="https://github.com/user-attachments/assets/243bc4e2-1162-409b-88b0-94ce33adb6d2" />
<img width="611" height="223" alt="Captura de tela 2026-04-28 232529" src="https://github.com/user-attachments/assets/8d7c987a-984f-4990-9e45-b7d6bd55fbec" />
<img width="395" height="192" alt="Captura de tela 2026-04-28 232537" src="https://github.com/user-attachments/assets/125af7f0-fe8d-425e-ba64-8ca19bd5b1c7" />


* Menu principal
<img width="267" height="204" alt="Captura de tela 2026-04-28 232517" src="https://github.com/user-attachments/assets/829bf499-d497-435f-9e99-5b10758739f8" />


---

## ▶️ Instruções de Execução

### 🔧 1. Pré-requisitos

* Java JDK 8 ou superior
* PostgreSQL instalado
* IDE (NetBeans recomendado)

---

### 🗄️ 2. Criar o Banco de Dados

```sql
CREATE DATABASE clinica_veterinaria;
```

---

### 📜 3. Executar Scripts
 
 Criar as tabelas:

 

 <img width="470" height="473" alt="Criando tabelas clientes, consultas e veterinario " src="https://github.com/user-attachments/assets/8971d275-f99a-42b6-892f-d4ce1117d82b" />


 <img width="826" height="255" alt="Criando tabelas veterinario" src="https://github.com/user-attachments/assets/cf554668-1373-4f01-b817-fcb046d03837" />


 Inserir os valores:


 <img width="554" height="140" alt="Captura de tela 2026-04-22 154846" src="https://github.com/user-attachments/assets/7ba2ca6a-0d1e-477c-845d-5da09f8548ef" />

 
 <img width="820" height="145" alt="Captura de tela 2026-04-22 160155" src="https://github.com/user-attachments/assets/d6a1bc14-5a57-4e1e-bda9-e89cbf10240c" />


<img width="707" height="99" alt="Captura de tela 2026-04-22 160644" src="https://github.com/user-attachments/assets/e350fc74-8dcb-454c-a5f4-8da5920e0085" />



<img width="842" height="78" alt="Captura de tela 2026-04-22 160958" src="https://github.com/user-attachments/assets/9c42aba8-4504-417d-a6cc-b8bc35782fd1" />





---

### ⚙️ 4. Configurar Conexão

No arquivo Java:

```java
String url = "jdbc:postgresql://localhost:5432/clinica_veterinaria";
String user = "SEU_USUARIO";
String password = "SUA_SENHA";
```

---

### ▶️ 5. Executar o Sistema

* Abra o projeto na IDE
* Execute a classe principal (`Sistema.java`)
* Faça login e utilize o sistema via console

---

## 🎥 Vídeo Demonstrativo

Link do vídeo:
https://drive.google.com/file/d/1mzcOxF0m08IBIRYBkv4SpAl4Hcb_q3uT/view?usp=drive_link

---


---

## 💡 Observações

* O sistema utiliza banco de dados relacional com chaves primárias e estrangeiras
* As consultas com JOIN demonstram o relacionamento entre as tabelas
* Projeto desenvolvido para fins acadêmicos na disciplina de Banco de Dados

---

## 👨‍🏫 Autor

* Bilsã Ferreira
* Curso: EG. de Software
* Disciplina: Banco de Dados
* Professor: Anderson Costa
