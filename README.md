# 📚 Library API

API desenvolvida em **Spring Boot** para gerenciamento de uma biblioteca.  
Permite o **cadastro e gerenciamento de livros, autores e usuários**, além de conter regras de negócio relacionadas a empréstimos e controle de acesso.

---

## 🚀 Funcionalidades

- 📖 **Livros**
  - Cadastro, listagem, atualização e exclusão de livros.
  - Associação com autores e gêneros.
  - Controle de disponibilidade (ex: se o livro está emprestado).

- ✍️ **Autores**
  - Cadastro e consulta de autores.
  - Relação entre autores e seus respectivos livros.

- 👤 **Usuários**
  - Registro de novos usuários com senha criptografada (hash).
  - Login e autenticação (JWT ou Session, conforme a implementação).
  - Perfis com permissões (ex: administrador e usuário comum).

- 🔐 **Autenticação e Autorização**
  - Rotas protegidas por token JWT (caso implementado).
  - Controle de permissões baseado em roles.

- 🧩 **Regras de Negócio**
  - Verificação de disponibilidade antes de um empréstimo.
  - Restrições de cadastro duplicado (ISBN, e-mail, etc.).
  - Registro automático de data de criação e atualização.

---

## 🏗️ Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)** e boas práticas de desenvolvimento com o **Spring Boot**.

